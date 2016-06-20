package com.example.zzj.demoview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/6/1.
 */
public class CoordinateView extends View {

    private static final String TAG = "Coordinate";
    public CoordinateView(Context context) {
        super(context);
    }

    public CoordinateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordinateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        Log.i(TAG, "CoordinateView layout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "CoordinateView onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "CoordinateView onDraw");
    }
}
