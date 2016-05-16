package com.community.yuequ;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * modou
 */
public class YQApplication extends Application{
    private static YQApplication instance;

    /* Up to 2 threads maximum, inactive threads are killed after 2 seconds */
    private ThreadPoolExecutor mThreadPool = new ThreadPoolExecutor(0, 2, 2, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        OkHttpUtils.getInstance().setConnectTimeout();


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    /**
     * @return the main context of the Application
     */
    public static Context getAppContext()
    {
        return instance;
    }

    /**
     * @return the main resources from the Application
     */
    public static Resources getAppResources()
    {
        return instance.getResources();
    }

    public static void runBackground(Runnable runnable) {
        instance.mThreadPool.execute(runnable);
    }

    public static boolean removeTask(Runnable runnable) {
        return instance.mThreadPool.remove(runnable);
    }


}
