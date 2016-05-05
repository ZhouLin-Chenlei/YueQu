package com.community.yuequ;

import android.app.Application;
import android.content.Context;

/**
 * modou
 */
public class YQApplication extends Application{
    private static YQApplication instance;
    public static int screenWidth;
    public static int screenHeight;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
}
