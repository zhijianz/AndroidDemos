package com.example.zzj.demorecyclerview.gank.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */

public class AndroidResult extends TypedResult {
    public AndroidResult(String failMsg) {
        super(failMsg);
    }

    public AndroidResult(GankResult<List<GankBean>> data) {
        super(data);
    }
}
