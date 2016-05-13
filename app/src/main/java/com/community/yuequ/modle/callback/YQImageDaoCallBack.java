package com.community.yuequ.modle.callback;

import com.community.yuequ.modle.YQImageDao;
import com.community.yuequ.util.AESUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/6.
 */
public abstract class YQImageDaoCallBack extends Callback<YQImageDao>{
    @Override
    public YQImageDao parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        String result = AESUtil.decrypt(string);
        YQImageDao imageDao = new Gson().fromJson(result, YQImageDao.class);
        return imageDao;

    }


}