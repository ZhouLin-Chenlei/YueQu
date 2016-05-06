package com.community.yuequ.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.community.yuequ.R;

import java.lang.ref.WeakReference;

/**
 *
 */
public class WelcomeActivity extends AppCompatActivity {
    private MyHandler mMyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        mMyHandler = new MyHandler(this);

    }

    private static class MyHandler extends Handler {
        private WeakReference<WelcomeActivity> activityWeakReference;

        public MyHandler(WelcomeActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            WelcomeActivity activity = activityWeakReference.get();
            if (activity != null) {
                activity.startMainActivity();
            }
        }
    }

    protected void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        if(!isFinishing()){
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMyHandler.hasMessages(1)) {
            mMyHandler.removeMessages(1);
        }
        mMyHandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public void onBackPressed() {
        mMyHandler.removeMessages(1);
        finish();
    }
}
