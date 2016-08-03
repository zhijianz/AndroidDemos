package com.example.zzj.demoview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.zzj.demoview.utils.DimensionUtils;

/**
 * Created by Administrator on 2016/6/22.
 */
public class PersonGrowthProgressBar extends View {

    // DP
    private final int DEFAULT_HEIGHT = 10;
    private final int DEFAULT_WIDTH = 300;
    private final int DEFAULT_PADDING = 2;
    private int mCurrGrowth = 50;
    private int mTotalGrowth = 50;

    public PersonGrowthProgressBar(Context context) {
        super(context);
    }

    public PersonGrowthProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PersonGrowthProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setGrowth(int currGrowth, int totalGrowth){
        mCurrGrowth = currGrowth;
        mTotalGrowth = totalGrowth;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(DimensionUtils.Dp2Px(getContext(), DEFAULT_WIDTH), MeasureSpec.EXACTLY);
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(DimensionUtils.Dp2Px(getContext(), DEFAULT_HEIGHT), MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(getHeight());
        mPaint.setColor(Color.RED);
        int padding = DimensionUtils.Dp2Px(getContext(), DEFAULT_PADDING);
        int startX = padding * 4;
        int endX = getMeasuredWidth() - padding * 4;
        canvas.drawLine(50, getHeight() / 2, 150, getHeight() / 2, mPaint);
//
//        mPaint.setColor(Color.WHITE);
//        mPaint.setColor(Color.WHITE);
//        mPaint.setStrokeWidth(getHeight() - padding * 2);
//
//        endX = (endX - startX) * mCurrGrowth/mTotalGrowth + startX;
//        endX = Math.max(endX, startX);
//        canvas.drawLine(startX, getHeight() / 2, endX, getHeight() / 2, mPaint);
    }
}
