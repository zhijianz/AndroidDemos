package com.example.zzj.demorecyclerview.common.http;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/1/13.
 */

public interface HttpResultCallback {
    void onFail(Request request, Exception e);
    void onSuccess(Request request, String result);
}
