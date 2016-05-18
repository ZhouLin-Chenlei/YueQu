package com.community.yuequ.modle.callback;

import com.community.yuequ.modle.YQVideoOrPicGroupDao;
import com.community.yuequ.util.AESUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/6.
 */
public abstract class YQVideoDaoCallBack extends Callback<YQVideoOrPicGroupDao>{
    @Override
    public YQVideoOrPicGroupDao parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        String result = AESUtil.decrypt(string);
        YQVideoOrPicGroupDao videoDao = new Gson().fromJson(result, YQVideoOrPicGroupDao.class);
        return videoDao;

    }


}
