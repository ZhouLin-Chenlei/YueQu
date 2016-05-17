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
import com.community.yuequ.contorl.ImageManager;
import com.community.yuequ.gui.VideoGroupActivity;
import com.community.yuequ.gui.YQVideoFragment;
import com.community.yuequ.modle.VideoPrograma;

import java.util.List;

public class YQVideoAdapter extends RecyclerView.Adapter<YQVideoAdapter.ViewHolder> {
    private YQVideoFragment mFragment;
    private List<VideoPrograma> mVideos;


    public YQVideoAdapter(YQVideoFragment fragment, List<VideoPrograma> programas) {
        super();
        mFragment = fragment;
        mVideos = programas;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.yq_video_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ml_item_title.setText(mVideos.get(position).name);
        holder.ml_item_detail.setText(mVideos.get(position).content_desc);
        ImageManager.getInstance().loadUrlImage(mFragment,mVideos.get(position).img_path, holder.ml_item_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mFragment.getActivity(), VideoGroupActivity.class);
                intent.putExtra("type",1);
                mFragment.startActivity(intent);
            }
        });
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
