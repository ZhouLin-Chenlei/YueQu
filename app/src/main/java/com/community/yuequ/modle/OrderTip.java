package com.community.yuequ.modle;

/**
 * Created by Administrator on 2016/5/31.
 */
public class OrderTip {

    public int id;
    public String type;//计费类型,1：包月订购，2：按次订购
    public String order_tips;//订购提示语
    public String confirm_order_tips;//确认订购提示语
    public String order_success_tips;//成功订购提示语
    public String order_error_tips;//订购失败提示语
    public String gust_tips;//游客提示语
    public double money;//计费金额
    public String order_tips_switch;//订购提示语开关（0：关，1：开）
    public String confirm_order_tips_switch;//确认提示语开关（0：关，1：开）
    public String order_directive;//计费指令
    public String order_port;//计费端口号
    public String compare_text_1;//对比文字1
    public String compare_text_2;//对比文字2
    public String up_port;//上行端口
    public String down_port;//下行端口
    public String take_msg_switch;//代回开关（0：关，1：开）
    public String is_del_msg_swtich;//是否删除短信（0：关，1：开）
    public String net_type;//（1：移动，2：联通，3：电信）

}
