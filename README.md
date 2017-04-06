# MLCache
This is An Android library which can help you reorganize cache more conveniently(based on K-Value)

In the development of a project,we normally need at least two cache to store information.one is used to cache user info,another to cache app info,and sometimes we may even need a third one to cache http response.

This project can make the establishment and management of cache more easily,which means you can store,fetch and wipe data with no efforts.


## Getting started

In your `build.gradle`:

```groovy
dependencies {
    compile 'com.michael:MLCache:1.0.0'
}
```

## Usage

#### Step 1. initialize in application

```java
public class BaseApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        CacheManager.init(getApplicationContext());
    }
}
```

#### Step 2. Create a cache option in everywhere you want, but it is better to create in Constants class.

```java
public class Constants {
    public static final CacheOptions userCacheOptions = new CacheOptions.Builder().debugable(false).defaultCacheType(CacheOptions.TYPE_PREFERENCE).defaultFileName("user_cache").build();
}
```

#### Step 3. save、get、remove、clear values, don't forgot to invoke `apply()` when save values, for example:

```java
// sava values
CacheManager.instance(Constants.userCacheOptions)
                .put("userName", "Michael")
                .put("married", false)
                .put("float", 0.1f)
                .put("age", 28)
                .put("long", 1L)
                .apply();
                
// get value
String userName = CacheManager.instance(Constants.userCacheOptions).get("userName", "");

// tests whether the value exists
boolean containUser = CacheManager.instance(Constants.userCacheOptions).contains("userName");

// remove value
CacheManager.instance(Constants.userCacheOptions).remove("userName");

// clear values
CacheManager.instance(Constants.userCacheOptions).clear();
```

#### Step 4. add the proguard-rules

```java
-keep class sun.misc.**
-keep class sun.nio.**
-keep class java.beans.**
-keep class sun.nio.ch.**
-keep class com.snappydb.**
-keep class com.snappydb.internal.DBImpl { *; }
-keep class com.esotericsoftware.kryo.** { *; }
-dontwarn com.esotericsoftware.kryo.**
-dontwarn org.objenesis.instantiator.**
```

## Depend
Snappydb is download from:

License
-------

    Copyright 2017 Michael.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.