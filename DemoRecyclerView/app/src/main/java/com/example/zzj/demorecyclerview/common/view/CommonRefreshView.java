package com.example.zzj.demorecyclerview.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by Administrator on 2017/1/13.
 */

public class CommonRefreshView extends TextView implements SwipeRefreshTrigger, SwipeTrigger{
    public CommonRefreshView(Context context) {
        super(context);
    }

    public CommonRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onRefresh() {
        setText("REFRESHING");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        if (!b){
            if (i >= getHeight()){
                setText("RELEASE TO REFRESH");
            }else {
                setText("SWIPE TO REFRESH");
            }
        }else {
            setText("REFRESH RUNNING");
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        setText("REFRESH COMPLETE");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
