package com.community.yuequ.pay;

import android.support.v7.app.AppCompatActivity;

import com.community.yuequ.modle.OrderTip;
import com.community.yuequ.widget.ConfirmOrderTipsDialog;
import com.community.yuequ.widget.OrderTipsDialog;

/**
 * Created by Administrator on 2016/6/1.
 */
public class SmsPayUtils {
    public static final String TIPS_SWITCH_CLOSE = "0";
    public static final String TIPS_SWITCH_OPEN = "1";
    private AppCompatActivity mActivity;

    public SmsPayUtils(AppCompatActivity activity){
        this.mActivity = activity;

    }
    public void topay(OrderTip orderTip) {

        if(TIPS_SWITCH_OPEN.equals(orderTip.order_tips_switch)){
            OrderTipsDialog tipsDialog = OrderTipsDialog.newInstance(orderTip.order_tips);
            tipsDialog.show(mActivity.getSupportFragmentManager(),"dialog");

        }else if(TIPS_SWITCH_OPEN.equals(orderTip.confirm_order_tips_switch)){
            ConfirmOrderTipsDialog orderTipsDialog = ConfirmOrderTipsDialog.newInstance(orderTip.confirm_order_tips);
            orderTipsDialog.show(mActivity.getSupportFragmentManager(),"dialog");
        }
    }
}
