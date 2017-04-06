package com.cache.sample;

import android.app.Application;

import com.michael.cache.core.CacheManager;

/**
 * <p>***********************************************************************
 * <p> Author: Michael
 * <p> CreateData: 2017-04-06 10:29
 * <p> Version: 1.0.0
 * <p> Description: the base application
 * <p>
 * <p>***********************************************************************
 */

public class BaseApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initCache();
    }

    /**
     * initialize the cache
     */
    private void initCache(){
        CacheManager.init(getApplicationContext());
    }
}
