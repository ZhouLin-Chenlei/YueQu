package com.community.yuequ.gui;

import android.app.FragmentBreadCrumbs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.yuequ.R;
import com.community.yuequ.view.TitleBarLayout;

public class VideoDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private TitleBarLayout mTitleBarLayout;
    private ImageView iv_img;
    private TextView tv_dec;
    private Button btn_play;
    private TextView tv_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        mTitleBarLayout = new TitleBarLayout(this)
                .setText(getString(R.string.recreation))
                .setLeftButtonVisibility(true)
                .setLeftButtonClickListener(this);

        iv_img = (ImageView) findViewById(R.id.iv_img);
        tv_dec = (TextView) findViewById(R.id.tv_dec);
        btn_play = (Button) findViewById(R.id.btn_play);
        tv_detail = (TextView) findViewById(R.id.tv_detail);

        btn_play.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_play:

                break;
            default:
                break;
        }
    }
}
