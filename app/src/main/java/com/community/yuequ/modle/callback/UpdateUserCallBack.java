package com.community.yuequ.modle.callback;

import com.community.yuequ.modle.MessageBean;
import com.community.yuequ.util.AESUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/6.
 */
public abstract class UpdateUserCallBack extends Callback<MessageBean>{
    @Override
    public MessageBean parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        String result = AESUtil.decrypt(string);
        MessageBean channelDao = new Gson().fromJson(result,  MessageBean.class);
        return channelDao;

    }


}
