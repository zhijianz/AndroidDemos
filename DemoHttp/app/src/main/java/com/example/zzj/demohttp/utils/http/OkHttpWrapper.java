package com.example.zzj.demohttp.utils.http;

/**
 * Created by Administrator on 2016/12/27.
 */

public class OkHttpWrapper {
    private static OkHttpWrapper instance;

    public static OkHttpWrapper newInstance(){
        if (instance == null){
            instance = new OkHttpWrapper();
        }
        return instance;
    }

    private OkHttpWrapper(){}
}
