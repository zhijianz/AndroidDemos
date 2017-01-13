package com.example.zzj.demohttp.beans.books;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/12/28.
 */

public class BookResult<T> {
    @SerializedName("resultcode")
    public int resultCode;
    @SerializedName("error_code")
    public int errorCode;
    @SerializedName("result")
    public T data;

    public String reason;
}
