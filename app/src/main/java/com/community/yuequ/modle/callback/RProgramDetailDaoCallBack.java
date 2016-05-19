package com.community.yuequ.modle.callback;

import com.community.yuequ.modle.InitDao;
import com.community.yuequ.modle.RProgramDetailDao;
import com.community.yuequ.util.AESUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by xmyb on 2016/5/19.
 */
public abstract class RProgramDetailDaoCallBack extends Callback<RProgramDetailDao> {
    @Override
    public RProgramDetailDao parseNetworkResponse(Response response) throws Exception {

        String string = response.body().string();
        String result = AESUtil.decrypt(string);
        RProgramDetailDao detailDao = new Gson().fromJson(result, RProgramDetailDao.class);
        return detailDao;
    }
}
