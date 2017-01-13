package com.example.zzj.demorecyclerview.common;

/**
 * Created by Administrator on 2017/1/13.
 */

public abstract class IPresenter<V> {
    protected V mView;
    public void register(V v){
        mView = v;
    }

    public abstract void unregister();
}
