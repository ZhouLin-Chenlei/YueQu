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
public class VideoGroupAdapter extends RecyclerView.Adapter<VideoGroupAdapter.ViewHolder>{
    @Override
    public VideoGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.video_group_card , parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VideoGroupAdapter.ViewHolder holder, int position) {
        holder.tv_group_name.setText("position:"+position);
        holder.iv_grouppic.setImageResource(R.mipmap.test3);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv_grouppic;
        public TextView tv_group_name;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_grouppic = (ImageView) itemView.findViewById(R.id.iv_grouppic);
            tv_group_name = (TextView) itemView.findViewById(R.id.tv_group_name);
        }
    }
}
