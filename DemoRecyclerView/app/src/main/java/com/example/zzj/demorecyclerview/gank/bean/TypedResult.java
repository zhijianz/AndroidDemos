package com.example.zzj.demorecyclerview.gank.bean;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */

public class TypedResult {
    protected GankResult<List<GankBean>> result;
    protected boolean isFail;
    protected String failMsg;

    public TypedResult(String failMsg){
        isFail = true;
        this.failMsg = failMsg;
    }

    public TypedResult(GankResult<List<GankBean>> data){
        result = data;
        isFail = false;
        failMsg = "";
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
