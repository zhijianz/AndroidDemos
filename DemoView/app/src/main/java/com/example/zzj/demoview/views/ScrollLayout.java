package com.example.zzj.demoview.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/6/15.
 */
public class ScrollLayout extends ViewGroup {

    private static final String TAG = "Translation";

    float lastX, lastY;

    public ScrollLayout(Context context) {
        super(context);
        init();
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        ColorView child = obtainChildView(Color.BLACK);
        addView(child);

        child = obtainChildView(Color.DKGRAY);
        addView(child);

        child = obtainChildView(Color.GRAY);
        addView(child);

        child = obtainChildView(Color.LTGRAY);
        addView(child);

        child = obtainChildView(Color.RED);
        addView(child);

        child = obtainChildView(Color.GREEN);
        addView(child);

        child = obtainChildView(Color.BLUE);
        addView(child);

        child = obtainChildView(Color.YELLOW);
        addView(child);

        child = obtainChildView(Color.CYAN);
        addView(child);
    }

    private ColorView obtainChildView(int color){
        ColorView child = new ColorView(getContext());
        child.setColor(color);
        return child;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "ScrollLayout_onLayout");
        int height = b - t;
        int width = r - l;

        getChildAt(0).layout(-width, - height, 0, 0);
        getChildAt(1).layout(0, -height, width, 0);
        getChildAt(2).layout(width, -height, 2*width, 0);
        getChildAt(3).layout(-width, 0, 0, height);
        getChildAt(4).layout(0, 0, width, height);
        getChildAt(5).layout(width, 0, 2*width, height);
        getChildAt(6).layout(-width, height, 0, 2*height);
        getChildAt(7).layout(0, height, width, 2*height);
        getChildAt(8).layout(width, height, 2*width, 2*height);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                lastY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (lastX - event.getX());
                int dy = (int) (lastY - event.getY());
                lastX = event.getX();
                lastY = event.getY();
                scrollBy(dx, dy);
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "ScrollLayout_onMeasure");
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }
}
