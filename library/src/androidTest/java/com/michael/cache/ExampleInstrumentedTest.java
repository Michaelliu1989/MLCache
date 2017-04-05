package com.michael.cache;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.michael.cache.core.CacheManager;
import com.michael.cache.core.CacheOptions;
import com.michael.cache.core.ICache;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.michael.cache.test", appContext.getPackageName());
    }

    @Test
    public void testCacheManager() throws Exception {
        String _1 = "just for test";
        boolean _2 = true;
        float _3 = 1.0f;
        int _4 = 1;
        long _5 = 1L;

        CacheManager.init(getContext());

        CacheOptions options1 = new CacheOptions.Builder().debugable(true).defaultCacheType(CacheOptions.TYPE_PREFERENCE).defaultFileName("test_gengmei_shared").build();
        CacheOptions options2 = new CacheOptions.Builder().debugable(true).defaultCacheType(CacheOptions.TYPE_DB).defaultFileName("test_gengmei_db").build();

        assertNotNull(options1);
        assertNotNull(options2);

        ICache cache1 = CacheManager.instance(options1);
        assertNotNull(cache1);
        cache1.put("string", _1)
                .put("boolean", _2)
                .put("float", _3)
                .put("int", _4)
                .put("long", _5)
                .apply();

        ICache cache2 = CacheManager.instance(options2);
        assertNotNull(cache2);
        cache2.put("string", _1)
                .put("boolean", _2)
                .put("float", _3)
                .put("int", _4)
                .put("long", _5)
                .apply();

        assertEquals(CacheManager.instance(options1).get("string", ""), _1);
        assertEquals(CacheManager.instance(options1).get("boolean", false), _2);
        assertEquals(CacheManager.instance(options1).get("float", 0.0f), _3, 0);
        assertEquals(CacheManager.instance(options1).get("int", 0), _4);
        assertEquals(CacheManager.instance(options1).get("long", 0L), _5);

        assertEquals(CacheManager.instance(options2).get("string", ""), _1);
        assertEquals(CacheManager.instance(options2).get("boolean", false), _2);
        assertEquals(CacheManager.instance(options2).get("float", 0.0f), _3, 0);
        assertEquals(CacheManager.instance(options2).get("int", 0), _4);
        assertEquals(CacheManager.instance(options2).get("long", 0L), _5);

        CacheManager.destroy();
    }

    @Test
    public void testCacheOptions() throws Exception {
        //		CacheOptions options1 = new CacheOptions.Builder().build();
        CacheOptions options2 = new CacheOptions.Builder().debugable(true).defaultCacheType(CacheOptions.TYPE_PREFERENCE).defaultFileName("test_gengmei").build();

        CacheOptions options3 = new CacheOptions.Builder().debugable(false).defaultCacheType(CacheOptions.TYPE_PREFERENCE).defaultFileName("test_gengmei").build();
        CacheOptions options4 = new CacheOptions.Builder().debugable(true).defaultCacheType(CacheOptions.TYPE_DB).defaultFileName("test_gengmei").defaultFilePath("data").build();
        //		CacheOptions options5 = new CacheOptions.Builder().debugable(true).defaultCacheType(CacheOptions.TYPE_PREFERENCE).defaultFileName("wanmeizhensuo").defaultFilePath("data").build();
        CacheOptions options6 = new CacheOptions.Builder().debugable(true).defaultCacheType(CacheOptions.TYPE_PREFERENCE).defaultFileName("test_gengmei").build();
    }
}
