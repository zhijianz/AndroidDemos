package com.example.zzj.demorecyclerview.gank.presenter;

import com.example.zzj.demorecyclerview.common.IPresenter;
import com.example.zzj.demorecyclerview.gank.bean.WelfareResult;
import com.example.zzj.demorecyclerview.gank.model.WelfareModel;
import com.example.zzj.demorecyclerview.gank.view.IWelfareView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/1/13.
 */

public class WelfarePresenter extends IPresenter<IWelfareView> {
    private WelfareModel mModel;

    @Override
    public void register(IWelfareView iWelfareView) {
        super.register(iWelfareView);
        mModel = WelfareModel.instance();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onQueryWelfareResult(WelfareResult result){
        if (result.isSuccessful()){
            if (mModel.isFirstPage()){
                mView.refreshWelfare(result);
            }else {
                mView.appendWelfare(result);
            }
        }else {
            mView.showQueryFail(mModel.isFirstPage(), result.getFailMessage());
        }
    }

    public void refreshWelfare(){
        mModel.refresh();
    }

    public void loadMoreWelfare(){
        mModel.loadMore();
    }

    @Override
    public void unregister() {
        EventBus.getDefault().unregister(this);
    }
}
