package com.community.yuequ.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.community.yuequ.Contants;
import com.community.yuequ.R;
import com.community.yuequ.Session;
import com.community.yuequ.YQApplication;
import com.community.yuequ.contorl.ImageManager;
import com.community.yuequ.modle.PicListDao;
import com.community.yuequ.modle.RProgram;
import com.community.yuequ.modle.RProgramDetail;
import com.community.yuequ.modle.RProgramDetailDao;
import com.community.yuequ.modle.callback.PicListDaoCallBack;
import com.community.yuequ.modle.callback.RProgramDetailDaoCallBack;
import com.community.yuequ.player.VideoViewActivity;
import com.community.yuequ.util.AESUtil;
import com.community.yuequ.view.PageStatuLayout;
import com.community.yuequ.view.TitleBarLayout;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

public class VideoDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = VideoDetailActivity.class.getSimpleName();

    private TitleBarLayout mTitleBarLayout;
    private PageStatuLayout mStatuLayout;
    private ImageView iv_img;
    private TextView tv_dec;
    private Button btn_play;
    private TextView tv_detail;
    private RProgram mRProgram;
    private Session session;
    private RProgramDetail programDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        session = Session.get(this);
        Intent intent = getIntent();
        mRProgram = (RProgram) intent.getSerializableExtra("program");
        mTitleBarLayout = new TitleBarLayout(this)
                .setLeftButtonVisibility(true)
                .setLeftButtonClickListener(this);
        mStatuLayout = new PageStatuLayout(this)
                .hide();
        iv_img = (ImageView) findViewById(R.id.iv_img);
        tv_dec = (TextView) findViewById(R.id.tv_dec);
        btn_play = (Button) findViewById(R.id.btn_play);
        tv_detail = (TextView) findViewById(R.id.tv_detail);

        btn_play.setOnClickListener(this);

        if (mRProgram != null) {
            mTitleBarLayout.setText(mRProgram.name);
            tv_dec.setText(mRProgram.name);
            tv_detail.setText(mRProgram.remark);
            ImageManager.getInstance().loadUrlImage(this, mRProgram.img_path, iv_img);
        }
        getData();
    }


    private void display() {
        if (programDetail != null) {
            mTitleBarLayout.setText(programDetail.name);
            tv_dec.setText(programDetail.name);
            tv_detail.setText(programDetail.remark);
            ImageManager.getInstance().loadUrlImage(this, programDetail.img_path, iv_img);
        }
    }


    private void getData() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("program_id", mRProgram.id);
        hashMap.put("imei", session.getIMEI());
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
                .content(content)
                .url(Contants.URL_PROGRAMDETAIL)
                .tag(TAG)
                .build()
                .execute(new RProgramDetailDaoCallBack() {
                    @Override
                    public void onError(Call call, Exception e) {


                        if (mStatuLayout != null) {
                            mStatuLayout.show()
                                    .setProgressBarVisibility(false)
                                    .setText(getString(R.string.load_data_fail));
                        }
                    }

                    @Override
                    public void onResponse(RProgramDetailDao response) {
                        programDetail = response.result;
                        display();
                        if (mStatuLayout != null) {
                            if(response.errorCode!=Contants.HTTP_OK){
                                mStatuLayout.show()
                                        .setProgressBarVisibility(false)
                                        .setText(response.errorMessage);
                            }else{
                                mStatuLayout.hide()
                                        .setProgressBarVisibility(false)
                                        .setText(null);
                            }

                        }

                    }

                    @Override
                    public void onBefore(Request request) {
                        mStatuLayout.show()
                                .setProgressBarVisibility(true)
                                .setText(null);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                    }
                });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_play:
                Intent intent = new Intent(this,VideoViewActivity.class);
                intent.putExtra("programDetail",programDetail);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
