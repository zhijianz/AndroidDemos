package com.example.zzj.demohttp.utils.http;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.zzj.demohttp.utils.ValidateUtil;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/28.
 */

public class HttpHelper {
    private static HttpHelper mHelper;
    HandlerThread mIoThread;
    Handler mHandler;

    private HttpHelper(){
        mIoThread = new HandlerThread("ioThread");
        mIoThread.start();
        mHandler = new Handler(mIoThread.getLooper());
    }

    private static HttpHelper instance(){
        if (mHelper == null){
            mHelper = new HttpHelper();
        }
        return mHelper;
    }

    public static void postIoThread(Runnable runnable, int delay){
        if (runnable != null){
            instance().mHandler.postDelayed(runnable, Math.max(delay, 0));
        }
    }

    public static void get(final String url, final OnResponseCallback callback){
        if (!ValidateUtil.empty(url)){
            postIoThread(new Runnable() {
                @Override
                public void run() {
                    Request request = new Request.Builder().url(url).build();
                    Response response;
                    try{
                        response = new OkHttpClient().newCall(request).execute();
                    } catch (IOException e) {
                        response = null;
                    }
                    if (callback != null){
                        callback.onResponse(response);
                    }
                }
            }, 0);
        }
    }
}
