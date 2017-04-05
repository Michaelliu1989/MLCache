package com.michael.cache.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.michael.cache.core.ICache;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;

import java.lang.ref.WeakReference;

/**
 * Created by michaelliu on 25/12/15.
 * SnappyDB cache
 *
 * https://github.com/nhachicha/SnappyDB
 */
public class DBCache implements ICache {

	private final String TAG = "DBCache";

	private DB                     snappyDB     = null;
	private WeakReference<Context> contextRef   = null;
	private String                 fileName     = null;
	private String                 absolutePath = null;
	private boolean                debug        = false;

	public DBCache(WeakReference<Context> contextRef, String fileName, String absolutePath, boolean debug) {
		this.contextRef = contextRef;
		this.fileName = fileName;
		this.absolutePath = absolutePath;
		this.debug = debug;
		open();
	}

	public void open() {
		if (null == contextRef) {
			return;
		}
		try {
			if (TextUtils.isEmpty(absolutePath)) {
				snappyDB = DBFactory.open(contextRef.get(), fileName);
			} else {
				snappyDB = new SnappyDB.Builder(contextRef.get())
						.directory(absolutePath)
						.name(fileName)
						.build();
			}
			debug("open SnappyDB success");
		} catch (SnappydbException e) {
			e.printStackTrace();
			debug("open SnappyDB failure");
		}
	}

	@Override
	public ICache put(String key, boolean value) {
		try {
			snappyDB.putBoolean(key, value);
			debug("put boolean value: " + value + " success");
		} catch (SnappydbException e) {
			debug("put boolean value: " + value + " failure");
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public ICache put(String key, float value) {
		try {
			snappyDB.putFloat(key, value);
			debug("put float value: " + value + " success");
		} catch (SnappydbException e) {
			debug("put float value: " + value + " failure");
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public ICache put(String key, int value) {
		try {
			snappyDB.putInt(key, value);
			debug("put int value: " + value + " success");
		} catch (SnappydbException e) {
			debug("put int value: " + value + " failure");
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public ICache put(String key, long value) {
		try {
			snappyDB.putLong(key, value);
			debug("put long value: " + value + " success");
		} catch (SnappydbException e) {
			debug("put long value: " + value + " failure");
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public ICache put(String key, String value) {
		try {
			snappyDB.put(key, value);
			debug("put String value: " + value + " success");
		} catch (SnappydbException e) {
			debug("put String value: " + value + " failure");
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public boolean get(String key, boolean defaultValue) {
		try {
			return snappyDB.getBoolean(key);
		} catch (SnappydbException e) {
			debug("get boolean key: " + key + " failure");
			e.printStackTrace();
		}
		return defaultValue;
	}

	@Override
	public float get(String key, float defaultValue) {
		try {
			return snappyDB.getFloat(key);
		} catch (SnappydbException e) {
			debug("get float key: " + key + " failure");
			e.printStackTrace();
		}
		return defaultValue;
	}

	@Override
	public int get(String key, int defaultValue) {
		try {
			return snappyDB.getInt(key);
		} catch (SnappydbException e) {
			debug("get int key: " + key + " failure");
			e.printStackTrace();
		}
		return defaultValue;
	}

	@Override
	public long get(String key, long defaultValue) {
		try {
			return snappyDB.getLong(key);
		} catch (SnappydbException e) {
			debug("get long key: " + key + " failure");
			e.printStackTrace();
		}
		return defaultValue;
	}

	@Override
	public String get(String key, String defaultValue) {
		try {
			return snappyDB.get(key);
		} catch (SnappydbException e) {
			debug("get String key: " + key + " failure");
			e.printStackTrace();
		}
		return defaultValue;
	}

	@Override
	public boolean contains(String key) {
		try {
			snappyDB.exists(key);
		} catch (SnappydbException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ICache remove(String key) {
		try {
			snappyDB.del(key);
			debug("remove key: " + key + " success");
		} catch (SnappydbException e) {
			debug("remove key: " + key + " failure");
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public void apply() {
		try {
			snappyDB.close();
		} catch (SnappydbException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ICache clear() {
		try {
			snappyDB.destroy();
		} catch (SnappydbException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void debug(String value) {
		if (debug) {
			Log.d(TAG, value);
		}
	}
}
