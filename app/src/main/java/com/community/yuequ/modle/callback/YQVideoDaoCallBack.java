package com.community.yuequ.modle.callback;

import com.community.yuequ.modle.YQVideoDao;
import com.community.yuequ.util.AESUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/6.
 */
public abstract class YQVideoDaoCallBack extends Callback<YQVideoDao>{
    @Override
    public YQVideoDao parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        String result = AESUtil.decrypt(string);
        YQVideoDao videoDao = new Gson().fromJson(result, YQVideoDao.class);
        return videoDao;

    }


}
