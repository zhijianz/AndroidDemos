package com.example.zzj.demorecyclerview.gank.bean;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 */

public class WelfareResult {
    private GankResult<List<GankBean>> result;
    private boolean isFail;
    private String failMsg;

    public static WelfareResult newFailInstance(String msg){
        WelfareResult result = new WelfareResult();
        result.isFail = true;
        result.failMsg = msg;
        return result;
    }

    public static WelfareResult newSuccessInstance(GankResult<List<GankBean>> data){
        WelfareResult result = new WelfareResult();
        result.result = data;
        result.isFail = false;
        result.failMsg = "";
        return result;
    }

    public boolean isSuccessful(){
        return !isFail && !result.isError;
    }

    public String getFailMessage(){
        return failMsg;
    }

    public List<GankBean> getData(){
        if (result == null || result.data == null){
            return Collections.emptyList();
        }else {
            return result.data;
        }
    }
}
