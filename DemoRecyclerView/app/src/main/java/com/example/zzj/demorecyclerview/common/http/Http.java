package com.example.zzj.demorecyclerview.common.http;

import android.util.Log;

import com.example.zzj.demorecyclerview.common.Utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/1/13.
 */

public class Http {
    private static final String TAG = "Http";
    private OkHttpClient mClient;
    private static Http mInstance;

    private Http(){
        mClient = new OkHttpClient.Builder().build();
    }

    public static Http instance(){
        if (mInstance == null){
            mInstance = new Http();
        }
        return mInstance;
    }

    public void getAsync(final String url, final HttpResultCallback callback){
        if (Utils.isEmpty(url)){
            Log.e(TAG, "getAsync empty url");
            return;
        }
        final Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure with url: " + url);
                if (callback != null){
                    Request request1 = null;
                    if (call != null){
                        request1 = call.request();
                    }
                    callback.onFail(request1, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callback != null){
                    Request req = null;
                    if (call != null){
                        req = call.request();
                    }
                    String result = "";
                    if (response != null){
                        result = response.body().string();
                    }
                    callback.onSuccess(req, result);
                }
            }
        });
    }

}
