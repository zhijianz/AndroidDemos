package com.example.zzj.demoview.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.zzj.demoview.R;

/**
 * Created by Administrator on 2016/11/14.
 */

public class TimerProgressView extends View {
    final int DURATION = 6 * 1000;
    ValueAnimator animator;
    float endAngle = 0;

    public TimerProgressView(Context context) {
        super(context);
    }

    public TimerProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startTimer(){
        if (animator != null && animator.isRunning()){
            return;
        }

        animator = ValueAnimator.ofInt(6000, 0);
        animator.setDuration(DURATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                endAngle = (float) value / DURATION * 360;
                invalidate();
            }
        });
        animator.start();
    }

    public void stopTimer(){
        if (animator != null && animator.isRunning()){
            animator.end();
        }
    }

    public boolean isTiming(){
        return animator != null && animator.isRunning();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = getResources().getDimensionPixelSize(R.dimen.protect_timer_size);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.AT_MOST){
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        }
        mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode == MeasureSpec.AT_MOST){
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.WHITE);
        RectF rect = new RectF(0 + 5, 0 + 5, getMeasuredWidth() - 5, getMeasuredHeight() - 5);
        canvas.drawArc(rect, - 90, endAngle, false, mPaint);
    }
}
