package com.community.yuequ.modle.callback;

import com.community.yuequ.modle.VideoProgramaDao;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/6.
 */
public abstract class VideoProgramaCallBack extends Callback<VideoProgramaDao>{
    @Override
    public VideoProgramaDao parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        VideoProgramaDao programa = new Gson().fromJson(string, VideoProgramaDao.class);
        return programa;

    }


}
