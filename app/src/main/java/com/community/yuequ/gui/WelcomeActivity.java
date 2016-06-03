package com.community.yuequ.gui;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Toast;

import com.community.yuequ.Contants;
import com.community.yuequ.R;
import com.community.yuequ.Session;
import com.community.yuequ.YQApplication;
import com.community.yuequ.modle.InitDao;
import com.community.yuequ.modle.UpgradeInfo;
import com.community.yuequ.modle.callback.InitDaoCallBack;
import com.community.yuequ.util.AESUtil;
import com.community.yuequ.util.AndroidDevices;
import com.community.yuequ.widget.UpgradeDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import okhttp3.Call;

/**
 *
 */
public class WelcomeActivity extends AppCompatActivity implements UpgradeDialog.UpgradeListener {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    private Session mSession;
    private MyHandler mMyHandler;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        mMyHandler = new MyHandler(this);
        mSession = Session.get(this);
        mSession.setScreenSize(this);
        mFragmentManager = getSupportFragmentManager();
        YQApplication.runBackground(new Runnable() {
            @Override
            public void run() {
                mSession.getApplicationInfo();
                mMyHandler.sendEmptyMessage(1);

            }
        });
//    AESUtil.main(new String[]{});
    }


    private static class MyHandler extends Handler {
        private WeakReference<WelcomeActivity> activityWeakReference;

        public MyHandler(WelcomeActivity activity) {
            activityWeakReference = new WeakReference<WelcomeActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            WelcomeActivity activity = activityWeakReference.get();
            if (activity != null) {
                activity.intitNet();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



    private void intitNet() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("mac", mSession.getMac());
        hashMap.put("imsi", mSession.getImsi());
        hashMap.put("imei", mSession.getIMEI());
        hashMap.put("telnum", mSession.getPhoneNumber());
        hashMap.put("dpi", mSession.getScreenSize());
        hashMap.put("platform", mSession.getBuildVersion());
        hashMap.put("device", mSession.getModel());
        hashMap.put("systemNo", mSession.getOsVersion() + "");
        hashMap.put("netType", AndroidDevices.getNetworkType());
        hashMap.put("netOperator", AndroidDevices.getMobileType());
        hashMap.put("productName", Contants.PRODUCTNAME);
        hashMap.put("channelID", mSession.getCid());
        hashMap.put("version", mSession.getVersionName());

        String content = "";
        try {
            content = AESUtil.encode(new Gson().toJson(hashMap));
        } catch (Exception e) {
            throw new RuntimeException("加密错误！");
        }
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(YQApplication.getAppContext(), R.string.unknow_erro, Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .postString()
                .url(Contants.URL_INIT)
                .content(content)
                .tag(TAG)
                .build()
                .execute(new MyInitDaoCallBack(this));
    }

    public static class MyInitDaoCallBack extends InitDaoCallBack {
        private WeakReference<WelcomeActivity> mWeakReference;

        public MyInitDaoCallBack(WelcomeActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onError(Call call, Exception e) {
            WelcomeActivity welcomeActivity = mWeakReference.get();
            if (welcomeActivity != null) {
                welcomeActivity.onError();
            }
        }

        @Override
        public void onResponse(InitDao response) {
            WelcomeActivity welcomeActivity = mWeakReference.get();
            if (welcomeActivity != null) {
                welcomeActivity.onResponse(response);
            }
        }
    }

    protected void onResponse(InitDao response) {
        mSession.setInitMsg(response.result);

        boolean showUpgradeDialog = mSession.isShowUpgradeDialog();
        if (showUpgradeDialog) {
            UpgradeDialog upgradeDialog = UpgradeDialog.newInstance();
            upgradeDialog.show(getSupportFragmentManager(), "upgrade");

        } else {
            startMainActivity();
        }

    }

    protected void onError() {
        startMainActivity();
    }


    protected void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void upgrade(UpgradeInfo upgrade) {
        //如果是非强升级
        intoDownloadManager(upgrade);
        if ("1".equals(upgrade.is_force_up)) {
            finish();
        } else {
            startMainActivity();
        }
    }


    private void intoDownloadManager(UpgradeInfo upgrade) {

        DownloadManager dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(upgrade.app_path);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        StringBuilder builder = new StringBuilder();
        builder.append("yuequ");
        builder.append(System.currentTimeMillis());
        builder.append(".apk");
        // 设置下载路径和文件名
        request.setDestinationInExternalPublicDir("download", builder.toString());
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setMimeType("application/vnd.android.package-archive");
        request.setTitle(YQApplication.getAppResources().getString(R.string.app_name));
        // 设置为可被媒体扫描器找到
        request.allowScanningByMediaScanner();

        // 设置为可见和可管理

        request.setVisibleInDownloadsUi(true);

        long refernece = dManager.enqueue(request);

        // 把当前下载的ID保存起来
        SharedPreferences sPreferences = getSharedPreferences("downloadapk", 0);
        sPreferences.edit().putLong("apkid", refernece).commit();

    }


    @Override
    public void cancel(boolean is_force) {
        if (is_force) {
            finish();
            System.exit(0);
        } else {
            startMainActivity();
        }
    }

//    @Override
//    public void onBackPressed() {
//        finish();
//    }
    @Override
    protected void onDestroy() {
        while (mFragmentManager.getBackStackEntryCount() > 0){
//            mFragmentManager.popBackStack();
            mFragmentManager.popBackStackImmediate();
        }

        OkHttpUtils.getInstance().cancelTag(TAG);
        super.onDestroy();

    }
}
