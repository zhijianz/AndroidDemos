package com.example.zzj.demorecyclerview.gank.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 */

public class WelfareResult extends TypedResult {

    public WelfareResult(String failMsg) {
        super(failMsg);
    }

    public WelfareResult(GankResult<List<GankBean>> data) {
        super(data);
    }
}
