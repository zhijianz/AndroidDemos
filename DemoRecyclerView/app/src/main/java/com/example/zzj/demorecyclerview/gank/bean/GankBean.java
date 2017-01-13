package com.example.zzj.demorecyclerview.gank.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/1/13.
 */

public class GankBean {
//    "_id":"5876e811421aa9315bfbe85f",
//            "createdAt":"2017-01-12T10:21:05.74Z",
//            "desc":"1-12",
//            "publishedAt":"2017-01-12T11:30:59.369Z",
//            "source":"chrome",
//            "type":"福利",
//            "url":"http://ww2.sinaimg.cn/large/0060lm7Tgw1fbnmsjogt9j30u00u0jvv.jpg",
//            "used":true,
//            "who":"daimajia"
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
}
