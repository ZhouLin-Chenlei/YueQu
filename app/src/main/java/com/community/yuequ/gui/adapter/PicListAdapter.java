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
import com.community.yuequ.gui.PicDetailActivity;
import com.community.yuequ.gui.VideoDetailActivity;

/**
 * modou
 */
public class PicListAdapter extends RecyclerView.Adapter<PicListAdapter.ViewHolder>{
    private Context mContext;
    public PicListAdapter(Context  context){
        this.mContext = context;
    }
    @Override
    public PicListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_pic_list , parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PicListAdapter.ViewHolder holder, int position) {
        holder.tv_second_title.setText("position:"+position);
        holder.tv_dsc.setText("hahhahahah");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,PicDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_second_title;
        public TextView tv_dsc;
        public ImageView iv_img1;
        public ImageView iv_img2;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_second_title = (TextView) itemView.findViewById(R.id.tv_second_title);
            tv_dsc = (TextView) itemView.findViewById(R.id.tv_dsc);
            iv_img1 = (ImageView) itemView.findViewById(R.id.iv_img1);
            iv_img2 = (ImageView) itemView.findViewById(R.id.iv_img2);
        }
    }
}
