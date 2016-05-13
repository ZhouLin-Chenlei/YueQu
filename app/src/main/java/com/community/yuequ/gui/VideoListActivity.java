package com.community.yuequ.gui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.community.yuequ.R;
import com.community.yuequ.gui.adapter.VideoListAdapter;
import com.community.yuequ.view.DividerItemDecoration;
import com.community.yuequ.view.PageStatuLayout;
import com.community.yuequ.view.SwipeRefreshLayout;
import com.community.yuequ.view.TitleBarLayout;

import java.util.List;

public class VideoListActivity extends AppCompatActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    private TitleBarLayout mTitleBarLayout;
    private PageStatuLayout mStatuLayout;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private VideoListAdapter mListAdapter;
    private int lastVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_group);

        mStatuLayout = new PageStatuLayout(this)
                .hide();

        mTitleBarLayout = new TitleBarLayout(this)
                .setText("新鲜娱闻")
                .setLeftButtonVisibility(true)
                .setLeftButtonClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(android.R.id.list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.pink900);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.addOnScrollListener(mScrollListener);
        mListAdapter = new VideoListAdapter(this);
        mRecyclerView.setAdapter(mListAdapter);

    }

    RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:
                    int size = recyclerView.getAdapter().getItemCount();
                    if (lastVisibleItem + 1 == size && mListAdapter.isLoadMoreShown() &&
                            !mListAdapter.getLoadMoreViewText().equals(getString(R.string.load_data_adequate))) {
                        onScrollLast();
                    }
                    break;
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    break;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            int topRowVerticalPosition =
                    (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
            mSwipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
        }
    };


    //上拉加载数据
    protected void onScrollLast(){
        Toast.makeText(this, "加载更多...", Toast.LENGTH_SHORT).show();
    }


    //数据为空
    public void getDataEmpty() {
        mSwipeRefreshLayout.setRefreshing(false);

        mListAdapter.setLoadMoreViewVisibility(View.GONE);
        mListAdapter.notifyDataSetChanged();
    }

    //数据足够PAGE_SIZE
    public void getDataAdequate() {
        mSwipeRefreshLayout.setRefreshing(false);
        mListAdapter.setLoadMoreViewVisibility(View.VISIBLE);
        mListAdapter.setLoadMoreViewText(getString(R.string.loading_data));


        mListAdapter.notifyDataSetChanged();
    }

    //数据不足PAGE_SIZE
    public void getDataInadequate() {
        mSwipeRefreshLayout.setRefreshing(false);
        mListAdapter.setLoadMoreViewVisibility(View.GONE);


        mListAdapter.notifyDataSetChanged();
    }

    //加载失败
    public void getDataFail() {
        mSwipeRefreshLayout.setRefreshing(false);
        mListAdapter.setLoadMoreViewVisibility(View.GONE);


        mListAdapter.notifyDataSetChanged();
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

    @Override
    public void onRefresh() {
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mSwipeRefreshLayout.setRefreshing(false);
                getDataAdequate();
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }


        };
        asyncTask.execute();
    }
}
