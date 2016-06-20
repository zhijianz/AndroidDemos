package com.example.zzj.demoview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class CoordinateViewGroup extends ViewGroup {

    private static final String TAG = "Translation_ViewGroup";
    private List<View> mMatchParentViews = new ArrayList<>();

    public CoordinateViewGroup(Context context) {
        super(context);
    }

    public CoordinateViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordinateViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure");

        int count = getChildCount();
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child == null){
                continue;
            }
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            if (lp.width == LayoutParams.MATCH_PARENT || lp.height == LayoutParams.MATCH_PARENT){
                mMatchParentViews.add(child);
                continue;
            }
            int usedWidth = getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin;
            int usedHeight = getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin;
            measureChildWithMargins(child, widthMeasureSpec, usedWidth, heightMeasureSpec, usedHeight);
            maxWidth = Math.max(maxWidth, child.getMeasuredWidth() + usedWidth);
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight() + usedHeight);
        }

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 1), resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
        for (View mMatchParentView : mMatchParentViews) {
            if (mMatchParentView == null){
                continue;
            }
            MarginLayoutParams params = (MarginLayoutParams) mMatchParentView.getLayoutParams();
            int childWidthMeasureSpec;
            if (params.width == LayoutParams.MATCH_PARENT){
                int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - params.leftMargin - params.rightMargin;
                width = Math.max(0, width);
                childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            }else {
                childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + params.leftMargin + params.rightMargin, params.width);
            }

            int childHeightMeasureSpec;
            if (params.height == LayoutParams.MATCH_PARENT){
                int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - params.topMargin - params.bottomMargin;
                height = Math.max(0, height);
                childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            }else {
                childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + params.topMargin + params.bottomMargin, params.height);
            }

            mMatchParentView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "CoordinateViewGroup onLayout");

        int top = getPaddingTop();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child == null){
                continue;
            }
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int left = getPaddingLeft() + lp.leftMargin;
            top += lp.topMargin;
            int right = r - getPaddingRight() - lp.rightMargin;
            right = Math.min(left + child.getMeasuredWidth(), right);
            right = Math.max(0, right);
            int bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom);
            top = bottom + lp.bottomMargin;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "CoordinateViewGroup onDraw");

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2);

        int startX = 0;
        int startY = 0;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(startX + width, startY);
        path.lineTo(startX + width, startY + height);
        path.lineTo(startX, startY + height);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CoordinateViewGroup.LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new CoordinateViewGroup.LayoutParams(p);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new CoordinateViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public static class LayoutParams extends MarginLayoutParams{

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
