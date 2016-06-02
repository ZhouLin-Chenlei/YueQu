package com.community.yuequ.modle.callback;

import com.community.yuequ.modle.BuyProgramDao;
import com.community.yuequ.modle.UpdateUserDao;
import com.community.yuequ.util.AESUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/6.
 */
public abstract class BuyCallBack extends Callback<BuyProgramDao>{
    @Override
    public BuyProgramDao parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        String result = AESUtil.decrypt(string);
        BuyProgramDao channelDao = new Gson().fromJson(result,  BuyProgramDao.class);
        return channelDao;

    }


}
