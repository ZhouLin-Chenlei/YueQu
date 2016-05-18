package com.community.yuequ.modle.callback;

import com.community.yuequ.modle.PicListDao;
import com.community.yuequ.util.AESUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/18.
 */
public abstract class PicListDaoCallBack extends Callback<PicListDao> {
    @Override
    public PicListDao parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        String result = AESUtil.decrypt(string);
        PicListDao picListDao = new Gson().fromJson(result, PicListDao.class);
        return picListDao;
    }

}
