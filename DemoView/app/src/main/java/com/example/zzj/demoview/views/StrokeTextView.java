package com.example.zzj.demoview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/6/23.
 */
public class StrokeTextView extends TextView {
    TextPaint m_TextPaint;

    public StrokeTextView(Context context) {
        super(context);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        m_TextPaint = getPaint();
    }

    private void setTextColorUseReflection(int color) {
        Field textColorField;
        try {
            textColorField = TextView.class.getDeclaredField("mCurTextColor");
            textColorField.setAccessible(true);
            textColorField.set(this, color);
            textColorField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        m_TextPaint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setTextColorUseReflection(Color.RED);
        m_TextPaint.setStyle(Paint.Style.STROKE);
        m_TextPaint.setStrokeWidth(5);
        m_TextPaint.setFakeBoldText(true);
        super.onDraw(canvas);

        canvas.translate(5, - 5);
        setTextColorUseReflection(Color.WHITE);
        m_TextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        m_TextPaint.setStrokeWidth(0);
        m_TextPaint.setFakeBoldText(false);
        super.onDraw(canvas);
    }
}
