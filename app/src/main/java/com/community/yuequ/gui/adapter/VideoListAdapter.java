package com.community.yuequ.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.yuequ.R;
import com.community.yuequ.gui.VideoDetailActivity;

/**
 * modou
 */
public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private View footView;
    private static final int TYPE_LIST = 0;
    private static final int TYPE_FOOT_VIEW = 1;


    public VideoListAdapter(Context  context){
        this.mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case TYPE_LIST:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_list, parent, false);
                viewHolder = new ViewHolder(view);
                break;
            case TYPE_FOOT_VIEW:
                footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_footview_layout, parent, false);
                footView.setVisibility(View.GONE);
                viewHolder = new FootViewHolder(footView);
                break;
            default:
                viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_list, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tv_name.setText("position:"+position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext,VideoDetailActivity.class));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 20;
    }
    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOT_VIEW;
        } else {
            return TYPE_LIST;
        }
    }

    public void setLoadMoreViewText(String text) {
        if (footView == null) return;
        ((TextView) footView.findViewById( R.id.tv_loading_more)).setText(text);
        notifyItemChanged(getItemCount());
    }

    public void setLoadMoreViewVisibility(int visibility) {
        if (footView == null) return;
        footView.setVisibility(visibility);
        notifyItemChanged(getItemCount());
    }

    public boolean isLoadMoreShown() {
        if (footView == null) return false;
        return footView.isShown();
    }

    public String getLoadMoreViewText() {
        if (footView == null) return "";
        return ((TextView) footView.findViewById( R.id.tv_loading_more)).getText().toString();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv_cover;
        public ImageView iv_play;
        public TextView tv_name;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            iv_play = (ImageView) itemView.findViewById(R.id.iv_play);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);

        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        TextView tvLoadingMore;

        public FootViewHolder(View itemView) {
            super(itemView);
            tvLoadingMore = (TextView) itemView.findViewById(R.id.tv_loading_more);
        }
    }
}
