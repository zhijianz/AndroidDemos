package com.example.zzj.demorecyclerview.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by Administrator on 2017/1/13.
 */

public class CommonLoadMoreView extends TextView implements SwipeLoadMoreTrigger, SwipeTrigger{
    public CommonLoadMoreView(Context context) {
        super(context);
    }

    public CommonLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLoadMore() {
        setText("LOADING MORE");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        if (!b){
            if (i <= -getHeight()){
                setText("RELEASE TO LOAD MORE");
            }else {
                setText("SWIPE TO LOAD MORE");
            }
        }else {
            setText("LOAD MORE RUNNING");
        }
    }

    @Override
    public void onRelease() {
        setText("LOADING MORE");
    }

    @Override
    public void onComplete() {
        setText("COMPLETE");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
