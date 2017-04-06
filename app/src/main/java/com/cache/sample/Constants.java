package com.cache.sample;

import com.michael.cache.core.CacheOptions;

/**
 * <p>***********************************************************************
 * <p> Author: Michael
 * <p> CreateData: 2017-04-06 10:24
 * <p> Version: 1.0.0
 * <p> Description: constants class
 * <p>
 * <p>***********************************************************************
 */

public class Constants {
    // simple of CacheOptions(SharedPreferences)
    // the cache not clear when logout
    public static final CacheOptions appCacheOptions = new CacheOptions.Builder().debugable(false).defaultCacheType(CacheOptions.TYPE_PREFERENCE).defaultFileName("app_cache").build();
    // the cache will clear when logout
    public static final CacheOptions userCacheOptions = new CacheOptions.Builder().debugable(false).defaultCacheType(CacheOptions.TYPE_PREFERENCE).defaultFileName("user_cache").build();

    // simple of CacheOptions(SnappyDB)
    public static final CacheOptions dbCacheOptions = new CacheOptions.Builder().debugable(false).defaultCacheType(CacheOptions.TYPE_DB).defaultFileName("db_cache").build();
}
