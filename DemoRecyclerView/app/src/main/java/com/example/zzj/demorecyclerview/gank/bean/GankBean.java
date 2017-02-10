package com.example.zzj.demorecyclerview.gank.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/1/13.
 */

public class GankBean {
    @SerializedName("_id")
    public String id;
    @SerializedName("createAt")
    public String createDate;
    @SerializedName("publishedAt")
    public String publishDate;
    public String source;
    public String type;
    public String url;
    public boolean used;
    @SerializedName("who")
    public String provider;
    public String[] images;
}
