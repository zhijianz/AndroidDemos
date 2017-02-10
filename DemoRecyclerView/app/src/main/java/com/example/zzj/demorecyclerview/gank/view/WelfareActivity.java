package com.example.zzj.demorecyclerview.gank.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.example.zzj.demorecyclerview.R;
import com.example.zzj.demorecyclerview.gank.bean.WelfareResult;
import com.example.zzj.demorecyclerview.gank.presenter.WelfarePresenter;
import com.example.zzj.demorecyclerview.itempool.ItemPool;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareActivity extends AppCompatActivity implements IWelfareView, OnLoadMoreListener, OnRefreshListener{

    @BindView(R.id.swipe_target) RecyclerView mRecycler;

    WelfarePresenter mWelfarePresenter;
    ItemPool mPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);
        ButterKnife.bind(this);
        initRecyclerView();
        mWelfarePresenter = new WelfarePresenter();
        mWelfarePresenter.register(this);
        mWelfarePresenter.refreshWelfare();
    }

    void initRecyclerView(){
//        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        mPool = new ItemPool();
        mPool.addType(WelfareItem.class);
        mPool.attachTo(mRecycler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWelfarePresenter.unregister();
    }

    @Override
    public void refreshWelfare(WelfareResult result) {
        mPool.clear();
        mPool.addAll(result.getData());
        mPool.notifyDataSetChanged();
    }

    @Override
    public void appendWelfare(WelfareResult result) {
        mPool.addAll(result.getData());
        mPool.notifyDataSetChanged();
    }

    @Override
    public void showQueryFail(boolean isFirstPage, String msg) {

    }

    @Override
    public void onLoadMore() {
        mWelfarePresenter.loadMoreWelfare();
    }

    @Override
    public void onRefresh() {
        mWelfarePresenter.refreshWelfare();
    }
}
