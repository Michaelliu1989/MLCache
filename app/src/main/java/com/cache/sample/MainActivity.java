package com.cache.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.michael.cache.core.CacheManager;
import com.michael.cache.core.ICache;

import static com.cache.sample.Constants.appCacheOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save();
        getCache();
        logout();
    }

    private void save() {
        CacheManager.instance(appCacheOptions)
                .put("string", "1")
                .put("boolean", true)
                .put("float", 0.1f)
                .put("int", 1)
                .put("long", 1L)
                .apply();

        CacheManager.instance(Constants.userCacheOptions)
                .put("string", "2")
                .put("boolean", true)
                .put("float", 0.2f)
                .put("int", 2)
                .put("long", 2L)
                .apply();

        CacheManager.instance(Constants.dbCacheOptions)
                .put("string", "3")
                .put("boolean", true)
                .put("float", 0.3f)
                .put("int", 3)
                .put("long", 3L)
                .apply();
    }

    private void getCache() {
        ICache appCache = CacheManager.instance(Constants.appCacheOptions);
        ICache userCache = CacheManager.instance(Constants.userCacheOptions);
        ICache dbCache = CacheManager.instance(Constants.dbCacheOptions);
        ((TextView) findViewById(R.id.activity_tv_text)).setText(
                        "the appCacheOptions string:" + appCache.get("string", "") + "\n" +
                        "the appCacheOptions boolean:" + appCache.get("boolean", false) + "\n" +
                        "the appCacheOptions float:" + appCache.get("float", 0.0f) + "\n" +
                        "the appCacheOptions int:" + appCache.get("int", 0) + "\n" +
                        "the appCacheOptions long:" + appCache.get("long", 0L) + "\n" +

                        "the userCacheOptions string:" + userCache.get("string", "") + "\n" +
                        "the userCacheOptions boolean:" + userCache.get("boolean", false) + "\n" +
                        "the userCacheOptions float:" + userCache.get("float", 0.0f) + "\n" +
                        "the userCacheOptions int:" + userCache.get("int", 0) + "\n" +
                        "the userCacheOptions long:" + userCache.get("long", 0L) + "\n" +

                        "the dbCacheOptions string:" + dbCache.get("string", "") + "\n" +
                        "the dbCacheOptions boolean:" + dbCache.get("boolean", false) + "\n" +
                        "the dbCacheOptions float:" + dbCache.get("float", 0.0f) + "\n" +
                        "the dbCacheOptions int:" + dbCache.get("int", 0) + "\n" +
                        "the dbCacheOptions long:" + dbCache.get("long", 0L)

        );
    }

    /**
     * clear user cache when logout
     */
    private void logout() {
        CacheManager.instance(Constants.userCacheOptions).clear();
    }
}
