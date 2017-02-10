package com.example.zzj.demorecyclerview.common;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Administrator on 2017/2/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
