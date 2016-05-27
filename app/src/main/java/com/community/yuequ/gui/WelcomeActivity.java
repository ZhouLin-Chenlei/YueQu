package com.community.yuequ.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.community.yuequ.Contants;
import com.community.yuequ.R;
import com.community.yuequ.Session;
import com.community.yuequ.YQApplication;
import com.community.yuequ.modle.InitDao;
import com.community.yuequ.modle.callback.InitDaoCallBack;
import com.community.yuequ.util.AESUtil;
import com.community.yuequ.util.AndroidDevices;
import com.community.yuequ.util.Log;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

/**
 *
 */
public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    private Session mSession;
    private MyHandler mMyHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        mMyHandler = new MyHandler(this);
        mSession = Session.get(this);
        mSession.setScreenSize(this);
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

    @Override
    public void onBackPressed() {

    }

    private void intitNet() {

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("mac",mSession.getMac());
        hashMap.put("imsi",mSession.getSim());
        hashMap.put("imei",mSession.getIMEI());
        hashMap.put("telnum",mSession.getPhoneNumber());
        hashMap.put("dpi",mSession.getScreenSize());
        hashMap.put("platform",mSession.getBuildVersion());
        hashMap.put("device",mSession.getModel());
        hashMap.put("systemNo",mSession.getOsVersion()+"");
        hashMap.put("netType", AndroidDevices.getNetworkType());
        hashMap.put("netOperator",AndroidDevices.getMobileType());
        hashMap.put("productName",Contants.PRODUCTNAME);
        hashMap.put("channelID",mSession.getCid());
        hashMap.put("version",mSession.getVersionName());

        String content = "";
        try {
            content = AESUtil.encode(new Gson().toJson(hashMap));
        } catch (Exception e) {
           throw new RuntimeException("加密错误！");
        }
        if (TextUtils.isEmpty(content)){
            Toast.makeText(YQApplication.getAppContext(), R.string.unknow_erro, Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .postString()
                .url(Contants.URL_INIT)
                .content(content)
                .tag(TAG)
                .build()
                .execute(new InitDaoCallBack() {
                    @Override
                    public void onError(Call call, Exception e) {

                        getDataFail();
                    }

                    @Override
                    public void onResponse(InitDao response) {
                        Log.i(TAG,response.toString());
                    }

                    @Override
                    public void onBefore(Request request) {
                        getDataBefore();
                    }

                    @Override
                    public void onAfter() {

                        startMainActivity();
                    }
                });
    }

    protected void getDataBefore() {
    }

    protected void getDataFail() {

    }
    protected void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
//        finish();
    }

   public void onClickButton(View v){
       Intent intent = new Intent(this, MainActivity.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       startActivity(intent);
   }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(TAG);

    }
}
