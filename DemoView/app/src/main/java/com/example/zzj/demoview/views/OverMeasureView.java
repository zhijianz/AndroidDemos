package com.example.zzj.demoview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/1.
 */
public class OverMeasureView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public OverMeasureView(Context context) {
        super(context);
    }

    public OverMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int centerX = getWidth()/2;
        int centerY = getHeight()/2;
        float startX = centerX - width/2;
        float startY = centerY - height/2;
        mPaint.setColor(Color.GREEN);
        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(startX + width, startY);
        path.lineTo(startX + width, startY + height);
        path.lineTo(startX, startY + height);
        path.lineTo(startX, startY);
        canvas.drawPath(path, mPaint);
        mPaint.setColor(Color.RED);

        int radius = Math.max(getMeasuredWidth(), getMeasuredHeight())/2;
        canvas.drawCircle(getWidth()/2, getHeight()/2, radius, mPaint);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        View parent = (View) getParent();
        l = t = 0;
        r = parent.getWidth();
        b = parent.getHeight();
        super.layout(l, t, r, b);
    }
}
