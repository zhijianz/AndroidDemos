package com.example.zzj.demorecyclerview.gank.view;

import com.example.zzj.demorecyclerview.gank.bean.WelfareResult;

/**
 * Created by Administrator on 2017/1/13.
 */

public interface IWelfareView {
    void refreshWelfare(WelfareResult result);
    void appendWelfare(WelfareResult result);
    void showQueryFail(boolean isFirstPage, String msg);
}
