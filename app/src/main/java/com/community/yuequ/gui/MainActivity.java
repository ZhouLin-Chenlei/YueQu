package com.community.yuequ.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.community.yuequ.R;
import com.community.yuequ.YQApplication;
import com.community.yuequ.util.AESUtil;
import com.community.yuequ.util.AESUtil3;
import com.community.yuequ.util.DisplayUtil;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private SimpleFragmentPagerAdapter pagerAdapter;

    private RelativeLayout rl_recommend;
    private RelativeLayout rl_video;
    private RelativeLayout rl_channel;
    private RelativeLayout rl_tuwen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            AESUtil.main(new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        mViewPager = ((ViewPager) findViewById(R.id.viewpager));
        rl_recommend = ((RelativeLayout) findViewById(R.id.rl_recommend));
        rl_video = ((RelativeLayout) findViewById(R.id.rl_video));
        rl_channel = ((RelativeLayout) findViewById(R.id.rl_channel));
        rl_tuwen = ((RelativeLayout) findViewById(R.id.rl_tuwen));

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(pagerAdapter);

        mViewPager.addOnPageChangeListener(this);
        rl_recommend.setOnClickListener(this);
        rl_video.setOnClickListener(this);
        rl_channel.setOnClickListener(this);
        rl_tuwen.setOnClickListener(this);

        mViewPager.setCurrentItem(0);
        rl_recommend.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_recommend:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rl_video:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rl_channel:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rl_tuwen:
                mViewPager.setCurrentItem(3);

                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        rl_recommend.setSelected(position==0);
        rl_video.setSelected(position==1);
        rl_channel.setSelected(position==2);
        rl_tuwen.setSelected(position==3);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 4;

        private Context context;

        public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new RecommendFragment();
            }else if(position==1){
                return new YQVideoFragment();
            }else if(position==2){
                return new ChannelFragment();
            }else {
                return new YQImageFragment();
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }
}
