package com.example.zzj.demoview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/12/1.
 */

public class WeightTextView extends TextView {
    public WeightTextView(Context context) {
        super(context);
    }

    public WeightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            Field mPaint = TextView.class.getDeclaredField("mTextPaint");
            mPaint.setAccessible(true);
            TextPaint paint = (TextPaint) mPaint.get(this);
            paint.setStrokeWidth(0);
            mPaint.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TextView", "e: " + e);
        }
        super.onDraw(canvas);
    }
}
