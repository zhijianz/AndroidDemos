package com.example.zzj.demoview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;


/**
 * Created by Administrator on 2016/6/1.
 */
public class CoordinateLayout extends FrameLayout {

    private static final String TAG = "Coordinate";

    public CoordinateLayout(Context context) {
        super(context);
    }

    public CoordinateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordinateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        int startX = getLeft();
        int startY = getTop();
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(startX + width, startY);
        path.lineTo(startX + width, startY + height);
        path.lineTo(startX, startY + height);
        path.lineTo(startX, startY);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "CoordinateLayout onLayout");
    }
}
