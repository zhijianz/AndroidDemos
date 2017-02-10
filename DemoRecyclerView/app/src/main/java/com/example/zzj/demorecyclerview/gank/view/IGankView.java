package com.example.zzj.demorecyclerview.gank.view;

import com.example.zzj.demorecyclerview.gank.bean.GankBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/10.
 */

public interface IGankView {
    void refreshData(List<GankBean> data);
    void appendData(List<GankBean> data);
    void showLoadMoreError();
    void showRefreshError();
}
