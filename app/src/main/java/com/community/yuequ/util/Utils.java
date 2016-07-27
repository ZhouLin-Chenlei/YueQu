package com.community.yuequ.util;

import android.text.TextUtils;

import com.community.yuequ.Contants;

/**
 * Created by apple on 16/5/26.
 */
public class Utils {

    public static boolean isPass(String is_cost,int erroCode){

        return "0".equals(is_cost)||erroCode == Contants.HTTP_VIP ||erroCode == Contants.HTTP_ONECE;

    }
    public static boolean isPass(int erroCode){

        return erroCode == Contants.HTTP_VIP ||erroCode == Contants.HTTP_ONECE;

    }

    /**
     * 把字符串转化为整形的工具方法
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static int StringToInt(String str, int defaultValue) {
        if (TextUtils.isEmpty(str)) {
            return defaultValue;
        }

        int result = defaultValue;
        try {
            result = Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static float StringToFloat(String str, float defaultValue) {
        if (TextUtils.isEmpty(str)) {
            return defaultValue;
        }
        float result = defaultValue;
        try {
            result = Float.parseFloat(str);
        } catch (Exception e) {
            e.printStackTrace();
            result = defaultValue;
        }
        return result;
    }
}
