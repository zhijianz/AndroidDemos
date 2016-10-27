package com.example.zhijianz.democustomattr;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/27.
 */

public class CustomView extends LinearLayout {
    private String content;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        TypedArray a = context.obtainStyledAttributes(R.styleable.CustomView);
//        TypedArray a = context.obtainStyledAttributes(null, R.styleable.CustomView);
        TypedArray a = context.obtainStyledAttributes(null, R.styleable.CustomView, 0, 0);
        content = a.getString(R.styleable.CustomView_attr_1);
        a.recycle();
        init();
    }

    private void init(){
        Context context = getContext();
        // add child view
        removeAllViews();
        TextView view = new TextView(context);
        view.setGravity(Gravity.CENTER);
        view.setTextColor(Color.BLACK);
        view.setTextSize(50);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        view.setText(content);
        addView(view);
    }
}
