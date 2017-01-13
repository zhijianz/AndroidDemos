package com.example.zzj.demorecyclerview.gank.callback;

import com.example.zzj.demorecyclerview.gank.bean.WelfareResult;

/**
 * Created by Administrator on 2017/1/13.
 */

public interface WelfakeCallback {
    interface QueryCallback{
        void onQueryWelfakeFail(String msg);
        void onQueryWelfakeSuccess(WelfareResult result);
    }
}
