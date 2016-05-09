package com.community.yuequ.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.renderscript.Type;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.yuequ.R;
import com.community.yuequ.gui.PicListActivity;
import com.community.yuequ.gui.VideoListActivity;

/**
 * modou
 */
public class VideoGroupAdapter extends RecyclerView.Adapter<VideoGroupAdapter.ViewHolder>{
    private Context mContext;
    private int type = 1;
    public VideoGroupAdapter(Context context){
        this.mContext = context;
    }

    public void setType(int type){
        this.type = type;
    }
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==1){
                    mContext.startActivity(new Intent(mContext,VideoListActivity.class));
                }else{
                    mContext.startActivity(new Intent(mContext,PicListActivity.class));

                }

            }
        });
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
