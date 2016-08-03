package com.example.zzj.demoview.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.zzj.demoview.R;
import com.example.zzj.demoview.utils.DimensionUtils;

/**
 * Created by Administrator on 2016/6/22.
 */
public class PersonLevelView extends View {
    // default dp size
    private final int DEFAULT_WIDTH = 280;
    private final int DEFAULT_HEIGHT = 150;

    private int mLevel;
    private int mCurrGrowth;
    private int mTotalGrowth;
    private int mLevelTextSize = 140;

    public PersonLevelView(Context context) {
        super(context);
    }

    public PersonLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PersonLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setmLevel(int mLevel){
        this.mLevel = mLevel;
        invalidate();
    }

    public void setGrowth(int currGrowth, int totalGrowth){
        this.mCurrGrowth = Math.max(currGrowth, 0);
        this.mTotalGrowth = Math.max(currGrowth, totalGrowth);
        invalidate();
    }

    private Bitmap getLevelBG(){
        int resId;
        if (mLevel < 20){
            resId = R.drawable.bg_level_star;
        }else if (mLevel < 40){
            resId = R.drawable.bg_level_moon;
        }else {
            resId = R.drawable.bg_level_sun;
        }

        Bitmap temp = BitmapFactory.decodeResource(getContext().getResources(), resId);
        Matrix matrix = new Matrix();
        matrix.setScale((float) getMeasuredWidth()/temp.getWidth(), (float) getMeasuredHeight()/temp.getHeight());
        return Bitmap.createBitmap(temp, 0, 0, temp.getWidth(), temp.getHeight(), matrix, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST){
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(DimensionUtils.Dp2Px(getContext(), DEFAULT_HEIGHT), MeasureSpec.EXACTLY);
        }
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST){
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(DimensionUtils.Dp2Px(getContext(), DEFAULT_WIDTH), MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String mLevelStr = String.valueOf(mLevel);
        Rect strRect = new Rect();

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(getLevelBG(), 0, 0, mPaint);
        canvas.save();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(mLevelTextSize);
        mPaint.setStrokeWidth(4);
        mPaint.setFakeBoldText(true);
        mPaint.setColor(Color.RED);
        mPaint.getTextBounds(mLevelStr, 0, mLevelStr.length(), strRect);

        canvas.translate((getMeasuredWidth() - strRect.width())/2, (getMeasuredHeight() + strRect.height())/2);

        canvas.drawText(mLevelStr, 0, 0, mPaint);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setFakeBoldText(false);
        mPaint.setColor(Color.WHITE);
        canvas.drawText(mLevelStr, 0, 0, mPaint);
        canvas.restore();

        // delta dp
        float deltaDP = 35;
        canvas.translate(getMeasuredWidth()/2, getMeasuredHeight()/2 + DimensionUtils.Dp2Px(getContext(), deltaDP));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        canvas.drawPoint(0, 0, mPaint);

        String mGrowthStr = mCurrGrowth + "/" + mTotalGrowth;
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(22);
        mPaint.getTextBounds(mGrowthStr, 0, mGrowthStr.length(), strRect);
        canvas.drawText(mGrowthStr, - strRect.width()/2, 0, mPaint);
    }

}
