package com.michael.cache.core;

import android.content.Context;

import com.michael.cache.impl.DBCache;
import com.michael.cache.impl.SharePrefCache;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaelliu on 29/12/15.
 * 针对disk缓存(key-value)的管理类，目前包含SnappyDB和SharedPreferences
 *
 * @see DBCache
 * @see SharePrefCache
 */
public class CacheManager {

    /**
     * Context的弱引用
     */
    private static WeakReference<Context> mContextRef = null;
    /**
     * 缓存实例集合
     */
    private static Map<Integer, ICache> caches = new HashMap<>();

    private CacheManager() {

    }

    /**
     * 获取cache instance
     *
     * @param options the options for cache
     * @return {@link ICache}
     */
    public synchronized static ICache instance(CacheOptions options) {
        if (options.getCacheType() == CacheOptions.TYPE_PREFERENCE) {
            return createSharedPreferenceCache(options);
        }
        if (options.getCacheType() == CacheOptions.TYPE_DB) {
            return createDBCache(options);
        }
        throw new RuntimeException("can't find the type: " + options.getCacheType() + ",please input the constant of #CacheOptions.TYPE_PREFERENCE or #CacheOptions.TYPE_DB");
    }

    /**
     * 创建或获取DB实例
     *
     * @param options the options for cache
     * @return {@link ICache}
     */
    private static ICache createDBCache(CacheOptions options) {
        if (caches.containsKey(options.hashCode())) {
            DBCache dbCache = (DBCache) caches.get(options.hashCode());
            dbCache.open();
            return dbCache;
        } else {
            DBCache dbCache = new DBCache(mContextRef, options.getFileName(), options.getAbsolutePath(), options.isDebug());
            caches.put(options.hashCode(), dbCache);
            return dbCache;
        }
    }

    /**
     * 创建或获取SharedPreference实例
     *
     * @param options the options for cache
     * @return {@link ICache}
     */
    private static ICache createSharedPreferenceCache(CacheOptions options) {
        if (caches.containsKey(options.hashCode())) {
            return caches.get(options.hashCode());
        } else {
            SharePrefCache cache = new SharePrefCache(mContextRef, options.getFileName(), options.isDebug());
            caches.put(options.hashCode(), cache);
            return cache;
        }
    }

    /**
     * 初始化，目前包含Context的初始化
     *
     * @param context the context
     */
    public static void init(Context context) {
        mContextRef = new WeakReference<>(context.getApplicationContext());
    }

    /**
     * 销毁，释放Context的引用和清空cacheMap
     */
    public static void destroy() {
        mContextRef = null;
        caches.clear();
        caches = null;
    }
}
