package com.example.zzj.demorecyclerview.gank.model;

import android.util.Log;

import com.example.zzj.demorecyclerview.common.http.Http;
import com.example.zzj.demorecyclerview.common.http.HttpResultCallback;
import com.example.zzj.demorecyclerview.common.Utils;
import com.example.zzj.demorecyclerview.gank.bean.GankBean;
import com.example.zzj.demorecyclerview.gank.bean.GankResult;
import com.example.zzj.demorecyclerview.gank.bean.WelfareResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/1/13.
 */

public class WelfareModel {
    private static final String TAG = "WelfareModel";
    private static WelfareModel mInstance;
    public static final int PAGE_SIZE = 100;
    private int mPage;

    private WelfareModel(){

    }

    public static WelfareModel instance(){
        if (mInstance == null){
            mInstance = new WelfareModel();
        }
        return mInstance;
    }

    public void queryWelfare(int PAGE_SIZE, final int page){
        Http.instance().getAsync(getWelfareURL(PAGE_SIZE, page), new HttpResultCallback() {
            @Override
            public void onFail(Request request, Exception e) {
                Log.e(TAG, "queryWelfare.onFail exception: " + e);
                EventBus.getDefault().post(WelfareResult.newFailInstance(e.toString()));
            }

            @Override
            public void onSuccess(Request request, String result) {
                if (Utils.isEmpty(result)){
                    EventBus.getDefault().post(WelfareResult.newFailInstance("empty result"));
                }else {
                    Log.i(TAG, "queryWelfare.onSuccess, url: " + (request == null ? "" : request.url()));
                    Gson gson = new Gson();
                    GankResult<List<GankBean>> data = gson.fromJson(result, new TypeToken<GankResult<List<GankBean>>>(){}.getType());
                    EventBus.getDefault().post(WelfareResult.newSuccessInstance(data));
                    mPage = page;
                }
            }
        });
    }

    public void refresh(){
        mPage = 0;
        queryWelfare(PAGE_SIZE, mPage);
    }

    public void loadMore(){
        queryWelfare(PAGE_SIZE, Math.max(mPage + 1, 0));
    }

    public String getWelfareURL(int PAGE_SIZE, int page){
        return String.format("http://gank.io/api/data/福利/%d/%d", PAGE_SIZE, page);
    }

    public boolean isFirstPage(){
        return mPage == 0;
    }
}
