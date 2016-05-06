package com.community.yuequ.gui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.community.yuequ.R;
import com.community.yuequ.YQApplication;
import com.community.yuequ.modle.MediaWrapper;
import com.community.yuequ.view.CustomRatioImageView;
import com.community.yuequ.view.NetworkImageHolderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private RecommendFragment mFragment;

    private ArrayList<MediaWrapper> mVideos = new ArrayList<>();

    private static final int TYPE_HEAD_VIEW = 0;
//    private static final int TYPE_FOOT_VIEW = 1;
    private static final int TYPE_LIST_TITLE = 1;
    private static final int TYPE_LIST_M2 = 2;
    private static final int TYPE_LIST_M3 = 3;
    private static final int TYPE_LIST_P2 = 4;
    private static final int TYPE_LIST_P3 = 5;

    private View headView;
//    private View footView;

    public RecommendAdapter(RecommendFragment fragment) {
        mFragment = fragment;
        MediaWrapper wrapper = new MediaWrapper();
        wrapper.type = 1;
        mVideos.add(wrapper);
        wrapper = new MediaWrapper();
        wrapper.type = 2;
        mVideos.add(wrapper);
        wrapper = new MediaWrapper();
        wrapper.type = 2;
        mVideos.add(wrapper);

        wrapper = new MediaWrapper();
        wrapper.type = 1;
        mVideos.add(wrapper);
        wrapper = new MediaWrapper();
        wrapper.type = 3;
        mVideos.add(wrapper);
        wrapper = new MediaWrapper();
        wrapper.type = 1;
        mVideos.add(wrapper);
        wrapper = new MediaWrapper();
        wrapper.type = 3;
        mVideos.add(wrapper);

        wrapper = new MediaWrapper();
        wrapper.type = 1;
        mVideos.add(wrapper);
        wrapper = new MediaWrapper();
        wrapper.type = 4;
        mVideos.add(wrapper);
        wrapper = new MediaWrapper();
        wrapper.type = 4;
        mVideos.add(wrapper);

        wrapper = new MediaWrapper();
        wrapper.type = 1;
        mVideos.add(wrapper);
        wrapper = new MediaWrapper();
        wrapper.type = 5;
        mVideos.add(wrapper);
        wrapper = new MediaWrapper();
        wrapper.type = 5;
        mVideos.add(wrapper);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case TYPE_HEAD_VIEW:
                viewHolder = new HeadViewHolder(headView);
                break;
            case TYPE_LIST_TITLE:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rl_list_title, parent, false);
                viewHolder = new TitleViewHolder(view);
                break;
            case TYPE_LIST_M2:
                View movie2Row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rl_list_movie_2row, parent, false);
                viewHolder = new Movie2RowViewHolder(movie2Row);

                break;
            case TYPE_LIST_M3:
                View movie3Row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rl_list_movie_3row, parent, false);
                viewHolder = new Movie2RowViewHolder(movie3Row);

                break;
            case TYPE_LIST_P2:
                View pic2Row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rl_list_pic_2row, parent, false);
                viewHolder = new Movie2RowViewHolder(pic2Row);

                break;
            default:
                View pic3Row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rl_list_pic_3row, parent, false);
                viewHolder = new Movie2RowViewHolder(pic3Row);
                break;
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeadViewHolder){


        }else  if (holder instanceof TitleViewHolder) {
            TitleViewHolder listViewHolder = ((TitleViewHolder) holder);
            listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFragment.startActivity(new Intent(mFragment.getActivity(),ImageActivity.class));
                }
            });

        }else  if (holder instanceof Movie2RowViewHolder) {
            Movie2RowViewHolder movie2RowViewHolder = ((Movie2RowViewHolder) holder);
            movie2RowViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFragment.startActivity(new Intent(mFragment.getActivity(),VideoListActivity.class));
                }
            });

        }else  if (holder instanceof Movie3RowViewHolder) {
            Movie3RowViewHolder movie3RowViewHolder = ((Movie3RowViewHolder) holder);


        }else  if (holder instanceof Pic2RowViewHolder) {
            Pic2RowViewHolder pic2RowViewHolder = ((Pic2RowViewHolder) holder);


        }else  if (holder instanceof Pic3RowViewHolder) {
            Pic3RowViewHolder pic3RowViewHolder = ((Pic3RowViewHolder) holder);


        }
    }

    @Override
    public long getItemId(int position) {
        return 0l;
    }

    @Override
    public int getItemCount() {
        return mVideos.size() + 1;
    }

    public MediaWrapper getItem(int position){
        if(position==0){
            return null;
        }
        return mVideos.get(position-1);
    }
    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_HEAD_VIEW;
        }else if (getItem(position).type==1) {
            return TYPE_LIST_TITLE;
        } else if(getItem(position).type==2){
            return TYPE_LIST_M2;
        }else if(getItem(position).type==3){
            return TYPE_LIST_M3;
        }else if(getItem(position).type==4){
            return TYPE_LIST_P2;
        }else{
            return TYPE_LIST_P3;
        }
    }

//    public void setLoadMoreViewText(String text) {
//        if (footView == null) return;
//        ((TextView) footView.findViewById(R.id.tv_loading_more)).setText(text);
//        notifyItemChanged(getItemCount());
//    }
//
//    public void setLoadMoreViewVisibility(int visibility) {
//        if (footView == null) return;
//        footView.setVisibility(visibility);
//        notifyItemChanged(getItemCount());
//    }
//
//    public boolean isLoadMoreShown() {
//        if (footView == null) return false;
//        return footView.isShown();
//    }
//
//    public String getLoadMoreViewText() {
//        if (footView == null) return "";
//        return ((TextView) footView.findViewById(R.id.tv_loading_more)).getText().toString();
//    }





    public void addHeadView(View headView) {
        this.headView = headView;
        notifyItemChanged(0);
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {


        public TitleViewHolder(View itemView) {
            super(itemView);

        }

    }

    public class Movie2RowViewHolder extends RecyclerView.ViewHolder {
        public CustomRatioImageView iv_movie_cover_left;
        public TextView tv_movie_name_left;
        public TextView tv_movie_dsc_left;
        public CustomRatioImageView iv_movie_cover_right;
        public TextView tv_movie_name_right;
        public TextView tv_movie_dsc_right;

        public Movie2RowViewHolder(View itemView) {
            super(itemView);
            iv_movie_cover_left = (CustomRatioImageView) itemView.findViewById(R.id.iv_movie_cover_left);
            tv_movie_name_left = (TextView) itemView.findViewById(R.id.tv_movie_name_left);
            tv_movie_dsc_left = (TextView) itemView.findViewById(R.id.tv_movie_dsc_left);
            iv_movie_cover_right = (CustomRatioImageView) itemView.findViewById(R.id.iv_movie_cover_right);
            tv_movie_name_right = (TextView) itemView.findViewById(R.id.tv_movie_name_right);
            tv_movie_dsc_right = (TextView) itemView.findViewById(R.id.tv_movie_dsc_right);
        }

    }
    public class Movie3RowViewHolder extends RecyclerView.ViewHolder {


        public Movie3RowViewHolder(View itemView) {
            super(itemView);

        }

    }
    public class Pic2RowViewHolder extends RecyclerView.ViewHolder {


        public Pic2RowViewHolder(View itemView) {
            super(itemView);

        }

    }
    public class Pic3RowViewHolder extends RecyclerView.ViewHolder {


        public Pic3RowViewHolder(View itemView) {
            super(itemView);

        }

    }
    static class HeadViewHolder extends RecyclerView.ViewHolder {

        View headView;

        public HeadViewHolder(View view) {
            super(view);
            headView = view;
        }
    }
}
