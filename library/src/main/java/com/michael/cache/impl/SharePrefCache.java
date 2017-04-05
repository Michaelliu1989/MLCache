package com.michael.cache.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.michael.cache.core.ICache;

import java.lang.ref.WeakReference;

/**
 * Created by michaelliu on 25/12/15.
 * preference cache
 *
 * @see SharedPreferences
 * @see SharedPreferences.Editor
 */
public class SharePrefCache implements ICache {

    private final String TAG = "SharedPreferenceCache";

    private SharedPreferences.Editor edit = null;
    private SharedPreferences sp = null;
    private boolean debug = false;

    public SharePrefCache(WeakReference<Context> contextRef, String fileName, boolean debug) {
        this.debug = debug;
        init(contextRef, fileName);
    }

    private void init(WeakReference<Context> contextRef, String fileName) {
        if (null == contextRef) {
            return;
        }
        sp = contextRef.get().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    @Override
    public SharePrefCache put(String key, boolean value) {
        edit.putBoolean(key, value);
        debug("put boolean value: " + value + " success");
        return this;
    }

    @Override
    public SharePrefCache put(String key, float value) {
        edit.putFloat(key, value);
        debug("put float value: " + value + " success");
        return this;
    }

    @Override
    public SharePrefCache put(String key, int value) {
        edit.putInt(key, value);
        debug("put int value: " + value + " success");
        return this;
    }

    @Override
    public SharePrefCache put(String key, long value) {
        edit.putLong(key, value);
        debug("put long value: " + value + " success");
        return this;
    }

    @Override
    public SharePrefCache put(String key, String value) {
        edit.putString(key, value);
        debug("put String value: " + value + " success");
        return this;
    }
    /*above API level 11*/
    //		public SharePrefCache put(String key, Set<String> values) {
    //			edit.putStringSet(key, values);
    //			return mInstance;
    //		}

    @Override
    public boolean get(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    @Override
    public float get(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    @Override
    public int get(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    @Override
    public long get(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    @Override
    public String get(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

	/*above API level 11*/
    //	public Set<String> get(String key, Set<String> defaultValues) {
    //		return sp.getStringSet(key, defaultValues);
    //	}

    @Override
    public boolean contains(String key) {
        return sp.contains(key);
    }

    @Override
    public SharePrefCache remove(String key) {
        edit.remove(key);
        edit.apply();
        return this;
    }

    @Override
    public void apply() {
        edit.apply();
    }

    /**
     * 彩蛋：
     * 推荐使用apply的方式，异步存储；如果需要实时获取结果可以使用commit
     *
     * @return Returns true if the new values were successfully written
     * to persistent storage.
     */
    public boolean commit() {
        return edit.commit();
    }

    @Override
    public SharePrefCache clear() {
        edit.clear();
        edit.commit();
        return this;
    }

    private void debug(String value) {
        if (debug) {
            Log.d(TAG, value);
        }
    }
}
