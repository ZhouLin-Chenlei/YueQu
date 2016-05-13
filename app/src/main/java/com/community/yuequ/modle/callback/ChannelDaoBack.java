package com.community.yuequ.modle.callback;

import com.community.yuequ.modle.ChannelDao;
import com.community.yuequ.modle.InitDao;
import com.community.yuequ.util.AESUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/6.
 */
public abstract class ChannelDaoBack extends Callback< ChannelDao>{
    @Override
    public ChannelDao parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        String result = AESUtil.decrypt(string);
        ChannelDao channelDao = new Gson().fromJson(result,  ChannelDao.class);
        return channelDao;

    }


}
