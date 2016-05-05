package com.community.yuequ.gui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.yuequ.R;

/**
 * modou
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder>{
    @Override
    public VideoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_video_list , parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VideoListAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText("position:"+position);

    }

    @Override
    public int getItemCount() {
        return 10;
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
}
