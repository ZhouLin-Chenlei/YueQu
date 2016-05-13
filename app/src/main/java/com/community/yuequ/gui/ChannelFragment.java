package com.community.yuequ.gui;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.community.yuequ.Contants;
import com.community.yuequ.R;
import com.community.yuequ.gui.adapter.ChannelListAdapter;
import com.community.yuequ.modle.ChannelDao;
import com.community.yuequ.modle.callback.ChannelDaoBack;
import com.community.yuequ.view.DividerItemDecoration;
import com.community.yuequ.view.PageStatuLayout;
import com.community.yuequ.view.SwipeRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 专题
 */
public class ChannelFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private final static String TAG = ChannelFragment.class.getSimpleName();
    protected RecyclerView mRecyclerView;
    protected PageStatuLayout mStatuLayout;
    private ChannelListAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private ChannelDao mChannelDao;

    public ChannelFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ChannelListAdapter(this);

    }

    RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int topRowVerticalPosition =
                    (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
            mSwipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        mStatuLayout = new PageStatuLayout(convertView).hide();
        mRecyclerView = findView(android.R.id.list);
        mSwipeRefreshLayout = findView(R.id.swipeLayout);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.pink900);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.addOnScrollListener(mScrollListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        OkHttpUtils
                .post()
                .url(Contants.URL_SPECIALSUBJECTLIST)
                .tag(TAG)
                .build()
                .execute(new ChannelDaoBack() {
                    @Override
                    public void onError(Call call, Exception e) {
                        getDataFail();
                    }

                    @Override
                    public void onResponse(ChannelDao response) {
                        mChannelDao = response;

                    }

                    @Override
                    public void onBefore(Request request) {
                        getDataBefore();
                    }
                    @Override
                    public void onAfter() {
                        getDataAfter();
                    }
                });
    }

    protected void getDataBefore() {
        super.getDataBefore();
    }

    protected void getDataFail() {
        super.getDataFail();
    }
    protected void getDataAfter() {
        super.getDataAfter();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        OkHttpUtils.getInstance().cancelTag(TAG);
    }

    @Override
    public void onRefresh() {
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mSwipeRefreshLayout.setRefreshing(false);
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
