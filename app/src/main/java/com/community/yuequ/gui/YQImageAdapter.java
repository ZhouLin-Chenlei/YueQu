package com.community.yuequ.gui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.yuequ.R;
import com.community.yuequ.modle.MediaWrapper;

import java.util.ArrayList;

public class YQImageAdapter extends RecyclerView.Adapter<YQImageAdapter.ViewHolder> {
    private YQImageFragment mFragment;
    private volatile ArrayList<MediaWrapper> mVideos = new ArrayList<>();


    public YQImageAdapter(YQImageFragment fragment) {
        super();
        mFragment = fragment;

        for(int i = 0;i<100;i++){
            mVideos.add(new MediaWrapper());
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.yq_video_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ml_item_title.setText("Position:"+ position);
        holder.ml_item_detail.setText("哈哈哈哈");
    }

    @Override
    public long getItemId(int position) {
        return 0l;
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ml_item_image;
        public TextView ml_item_title;
        public TextView ml_item_detail;
        public ViewHolder(View itemView) {
            super(itemView);
            ml_item_image = (ImageView) itemView.findViewById(R.id.ml_item_image);
            ml_item_title = (TextView) itemView.findViewById(R.id.ml_item_title);
            ml_item_detail = (TextView) itemView.findViewById(R.id.ml_item_detail);
        }

    }
}
