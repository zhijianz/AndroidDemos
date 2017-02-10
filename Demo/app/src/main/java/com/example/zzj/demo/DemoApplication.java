package com.example.zzj.demo;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Administrator on 2017/2/10.
 */

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
