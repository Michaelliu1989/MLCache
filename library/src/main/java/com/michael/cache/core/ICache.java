package com.michael.cache.core;

/**
 * Created by michaelliu on 25/12/15.
 * cache interface
 */
public interface ICache {

	ICache put(String key, boolean value);

	ICache put(String key, float value);

	ICache put(String key, int value);

	ICache put(String key, long value);

	ICache put(String key, String value);

	boolean get(String key, boolean defaultValue);

	float get(String key, float defaultValue);

	int get(String key, int defaultValue);

	long get(String key, long defaultValue);

	String get(String key, String defaultValue);

	boolean contains(String key);

	ICache remove(String key);

	void apply();

	ICache clear();
}
