package com.community.yuequ.gui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.community.yuequ.R;

/**
 * Created by Administrator on 2016/5/27.
 */
public class PayListAdapter extends RecyclerView.Adapter<PayListAdapter.ViewHolder>{
    @Override
    public PayListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = View.inflate(parent.getContext(), R.layout.paylist_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PayListAdapter.ViewHolder holder, int position) {
        holder.pay_name.setText("10块钱"+position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView pay_name;
        public ViewHolder(View itemView) {
            super(itemView);
            pay_name = (TextView) itemView.findViewById(R.id.pay_name);
        }
    }
}
