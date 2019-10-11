package com.example.mystudydemo;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

public class MyApplication extends Application {

    /**系统上下文*/
    private static Context mAppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("Xbanner"))
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                .build();
//        OkHttpUtils.initClient(okHttpClient);


        mAppContext = getApplicationContext();
    }

    /**获取系统上下文：用于ToastUtil类*/
    public static Context getAppContext()
    {
        return mAppContext;
    }
}
