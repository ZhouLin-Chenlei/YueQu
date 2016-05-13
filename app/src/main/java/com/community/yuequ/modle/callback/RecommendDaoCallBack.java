package com.community.yuequ.modle.callback;

import com.community.yuequ.modle.InitDao;
import com.community.yuequ.modle.RecommendDao;
import com.community.yuequ.util.AESUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/6.
 */
public abstract class RecommendDaoCallBack extends Callback<RecommendDao>{
    @Override
    public RecommendDao parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        String result = AESUtil.decrypt(string);
        RecommendDao recommendDao = new Gson().fromJson(result, RecommendDao.class);
        return recommendDao;

    }


}
