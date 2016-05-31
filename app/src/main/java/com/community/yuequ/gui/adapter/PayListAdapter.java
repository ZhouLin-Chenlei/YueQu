package com.community.yuequ.gui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.community.yuequ.R;
import com.community.yuequ.modle.OrderTip;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/27.
 */
public class PayListAdapter extends RecyclerView.Adapter<PayListAdapter.ViewHolder>{
    ArrayList<OrderTip> mOrderTips;

    public PayListAdapter(ArrayList<OrderTip> orderTips) {
        mOrderTips = orderTips;
    }

    @Override
    public PayListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = View.inflate(parent.getContext(), R.layout.paylist_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PayListAdapter.ViewHolder holder, int position) {
        OrderTip orderTip = mOrderTips.get(position);
        if(OrderTip.TYPE_ONCE.equals(orderTip.type)){
            holder.pay_name.setText("按次订购"+orderTip.money+"元");

        }else if(OrderTip.TYPE_MONTHLY.equals(orderTip.type)){
            holder.pay_name.setText("包月订购"+orderTip.money+"元");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mOrderTips==null)
            return 0;
        return mOrderTips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView pay_name;
        public ViewHolder(View itemView) {
            super(itemView);
            pay_name = (TextView) itemView.findViewById(R.id.pay_name);
        }
    }
}
