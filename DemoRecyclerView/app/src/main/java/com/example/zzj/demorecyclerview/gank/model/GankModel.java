package com.example.zzj.demorecyclerview.gank.model;

import android.util.Log;

import com.example.zzj.demorecyclerview.common.http.Http;
import com.example.zzj.demorecyclerview.common.http.HttpResultCallback;
import com.example.zzj.demorecyclerview.common.Utils;
import com.example.zzj.demorecyclerview.gank.bean.GankBean;
import com.example.zzj.demorecyclerview.gank.bean.GankResult;
import com.example.zzj.demorecyclerview.gank.bean.TypedResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Request;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/1/13.
 */

public class GankModel <T extends TypedResult> {
    private static final String TAG = "GankModel";

    public static final String TYPE_WELFARE = "福利";
    public static final String TYPE_ANDROID = "Android";
    public static final String TYPE_IOS = "IOS";
    public static final String TYPE_REST_VEDIO = "休息视频";
    public static final String TYPE_EXPAND_RES = "拓展资源";
    public static final String TYPE_WEB = "前端";
    public static final String TYPE_ALL = "all";

    private static ConcurrentHashMap<Class, GankModel> mInstances = new ConcurrentHashMap<>();
    public static final int PAGE_SIZE = 100;
    private int mPage;
    private Class<T> clz;
    private String mType;

    public synchronized static <K extends TypedResult> GankModel instance(Class<K> clz, String type) {
        GankModel instance = mInstances.get(clz);
        if (instance == null){
            instance = new GankModel<K>();
            instance.clz = clz;
            instance.mType = type;
            mInstances.put(clz, instance);
        }
        return instance;
    }

    public void queryGank(int PAGE_SIZE, final int page) {
        Http.instance().getAsync(getUrl(PAGE_SIZE, page), new HttpResultCallback() {
            @Override
            public void onFail(Request request, Exception e) {
                Log.e(TAG, "queryGank.onFail exception: " + e);
                Timber.e(e, "queryGank.onFail");
                try {
                    Constructor<T> constructor = clz.getConstructor(String.class);
                    T obj = constructor.newInstance(e.toString());
                    EventBus.getDefault().post(obj);
                } catch (Exception e1) {
                    Timber.e(e1);
                }
            }

            @Override
            public void onSuccess(Request request, String result) {
                if (Utils.isEmpty(result)) {
                    try {
                        Constructor<T> constructor = clz.getConstructor(String.class);
                        T obj = constructor.newInstance("empty result");
                        EventBus.getDefault().post(obj);
                    } catch (Exception e) {
                        Timber.e(e);
                    }
                } else {
                    Timber.d("queryGank.onSuccess, url: %s", result == null ? "" : request.url());
                    Gson gson = new Gson();
                    GankResult<List<GankBean>> data = gson.fromJson(result, new TypeToken<GankResult<List<GankBean>>>() {
                    }.getType());
                    try {
                        Constructor<T> constructor = clz.getConstructor(GankResult.class);
                        T obj = constructor.newInstance(data);
                        EventBus.getDefault().post(obj);
                        mPage = page;
                    } catch (Exception e) {
                        Timber.e(e);
                    }
                }
            }
        });
    }

    public void refresh() {
        mPage = 0;
        queryGank(PAGE_SIZE, mPage);
    }

    public void loadMore() {
        queryGank(PAGE_SIZE, Math.max(mPage + 1, 0));
    }

    public String getUrl(int PAGE_SIZE, int page) {
        return String.format("http://gank.io/api/data/%s/%d/%d", mType, PAGE_SIZE, page);
    }

    public boolean isFirstPage() {
        return mPage == 0;
    }
}
