package com.example.zzj.demorecyclerview.gank.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/1/13.
 */

public class GankResult <T>{
    @SerializedName("error")
    public boolean isError;
    @SerializedName("results")
    public T data;
}
