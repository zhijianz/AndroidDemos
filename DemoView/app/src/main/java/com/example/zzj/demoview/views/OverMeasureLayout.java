package com.example.zzj.demoview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/6/1.
 */
public class OverMeasureLayout extends FrameLayout {
    private static final String TAG = "OverMeasureLayout";
    public OverMeasureLayout(Context context) {
        super(context);
    }

    public OverMeasureLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverMeasureLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View parentView = (View) getParent();
        if (r != parentView.getMeasuredWidth()){
            Log.i(TAG, "onLayout: Relayout");
            setLeft(0);
            setTop(0);
            setRight(parentView.getMeasuredWidth());
            setBottom(parentView.getMeasuredHeight());
            requestLayout();
        }
    }
}
