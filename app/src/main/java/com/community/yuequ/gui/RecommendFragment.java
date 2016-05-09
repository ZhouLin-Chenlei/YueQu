package com.community.yuequ.gui;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.community.yuequ.R;
import com.community.yuequ.gui.adapter.RecommendAdapter;
import com.community.yuequ.util.Log;
import com.community.yuequ.view.NetworkImageHolderView;
import com.community.yuequ.view.PageStatuLayout;
import com.community.yuequ.view.SwipeRefreshLayout;

import java.util.Arrays;
import java.util.List;

/**
 * 推荐页
 */
public class RecommendFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener ,OnItemClickListener{
    public static final String TAG = RecommendFragment.class.getSimpleName();
    protected PageStatuLayout mStatuLayout;
    private RecommendAdapter mRecommendAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private View headView;
    private ConvenientBanner mConvenientBanner;
    private Object mObjects = new Object();


    public RecommendFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecommendAdapter = new RecommendAdapter(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        mStatuLayout = new PageStatuLayout(convertView).hide();
        mRecyclerView = findView(android.R.id.list);
        mSwipeRefreshLayout = findView(R.id.swipeLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.pink900);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        headView = mLayoutInflater.inflate(R.layout.recommed_banner_layout,null);
        mConvenientBanner = (ConvenientBanner) headView.findViewById(R.id.convenientBanner);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.addOnScrollListener(mScrollListener);
        mRecyclerView.setAdapter(mRecommendAdapter);
        mRecyclerView.setHasFixedSize(true);


        List<String> stringList = Arrays.asList(images);
        //本地图片例子
        mConvenientBanner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, stringList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.circular_indicator_white, R.drawable.circular_indicator_red})
                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this);

        mRecommendAdapter.addHeadView(headView);
//        View headView = View.inflate(getContext(), R.layout.recommed_banner_layout, null);
//        mConvenientBanner = ((ConvenientBanner) headView.findViewById(R.id.convenientBanner));

    }

    @Override
    protected void initData() {

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

    String[] images={"http://b.hiphotos.baidu.com/image/pic/item/962bd40735fae6cdba41808d0bb30f2443a70f60.jpg",
                     "http://f.hiphotos.baidu.com/image/h%3D200/sign=713ba20d2b2eb938f36d7df2e56085fe/a686c9177f3e6709ece1e4133fc79f3df9dc557c.jpg",
                    "http://a.hiphotos.baidu.com/image/h%3D200/sign=e62356eaa8af2eddcbf14ee9bd110102/b03533fa828ba61e38f9e0c34534970a314e59c2.jpg"};
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }



    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        mConvenientBanner.startTurning(4000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        mConvenientBanner.stopTurning();
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

    @Override
    public void onItemClick(int position) {
//        Toast.makeText(YQApplication.getAppContext(), "Position:"+position, Toast.LENGTH_SHORT).show();
       startActivity(new Intent(getActivity(),VideoGroupActivity.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView-----------");
    }
}
