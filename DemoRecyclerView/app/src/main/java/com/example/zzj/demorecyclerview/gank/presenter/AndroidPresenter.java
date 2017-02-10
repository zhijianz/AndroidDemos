package com.example.zzj.demorecyclerview.gank.presenter;

import com.example.zzj.demorecyclerview.common.IPresenter;
import com.example.zzj.demorecyclerview.gank.bean.AndroidResult;
import com.example.zzj.demorecyclerview.gank.model.GankModel;
import com.example.zzj.demorecyclerview.gank.view.IGankView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/2/10.
 */

public class AndroidPresenter extends IPresenter<IGankView> {
    private GankModel<AndroidResult> mAndroidModel;

    @Override
    public void register(IGankView iAndroidView) {
        super.register(iAndroidView);
        mAndroidModel = GankModel.instance(AndroidResult.class, GankModel.TYPE_ANDROID);
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregister() {
        mView = null;
        EventBus.getDefault().unregister(this);
    }

    public void queryAndoridInfo(){
        mAndroidModel.loadMore();
    }

    public void refresh(){
        mAndroidModel.refresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void onQueryAndroidInfoResult(AndroidResult result){
        if (mAndroidModel.isFirstPage()){
            if (result.isSuccessful()){
                mView.refreshData(result.getData());
            }else {
                mView.showRefreshError();
            }
        }else {
            if (result.isSuccessful()){
                mView.appendData(result.getData());
            }else {
                mView.showLoadMoreError();
            }
        }
    }
}
