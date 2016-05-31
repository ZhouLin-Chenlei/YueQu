package com.community.yuequ.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.community.yuequ.R;
import com.community.yuequ.gui.adapter.PayListAdapter;
import com.community.yuequ.modle.OrderTip;
import com.community.yuequ.view.TitleBarLayout;

import java.util.ArrayList;

public class PayListActivity extends AppCompatActivity implements View.OnClickListener{
    private TitleBarLayout mTitleBarLayout;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private PayListAdapter mListAdapter;
    private ArrayList<OrderTip> mOrderTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_list);

        mOrderTips = getIntent().getParcelableArrayListExtra("ordertips");
        mTitleBarLayout = new TitleBarLayout(this)
                .setText("购买")
                .setLeftButtonVisibility(true)
                .setLeftButtonClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mListAdapter = new PayListAdapter(mOrderTips);
        mRecyclerView.setAdapter(mListAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }
}
