package com.example.zzj.demoview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.zzj.demoview.utils.DimensionUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasDrawActivity extends AppCompatActivity {

    Canvas mCanvas;
    Paint mPaint;
    Bitmap mBitmap;
    int mBitmapWidth, mBitmapHeight;
    @Bind(R.id.iv_draw)
    ImageView mImageView;

    public static void navigateFrom(Context context){
        Intent intent = new Intent(context, CanvasDrawActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_draw);
        ButterKnife.bind(this);

        mImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mBitmapWidth = mImageView.getMeasuredWidth();
                mBitmapHeight = mImageView.getMeasuredHeight();
                mBitmap = Bitmap.createBitmap(mImageView.getMeasuredWidth(), mImageView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mBitmap);
                mImageView.setImageBitmap(mBitmap);
                mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint.setColor(Color.RED);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeCap(Paint.Cap.ROUND);
                mPaint.setStrokeWidth(10);
                mPaint.setTextSize(150);
            }
        });
    }

    @OnClick(R.id.btn_clean)
    void onCleanBtnClick(){
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawARGB(255, 255, 255, 255);
        mImageView.invalidate();
    }

    @OnClick(R.id.btn_draw_arc)
    void onDrawArcClick(){
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(0, 0, mBitmapWidth, mBitmapHeight);
        mCanvas.drawArc(rectF, 0, 90, false, mPaint);
        mImageView.invalidate();
    }

    @OnClick(R.id.btn_draw_argb)
    void onDrawArgbClick(){
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawARGB(203, 132, 144, 24);
        mImageView.invalidate();
    }

    @OnClick(R.id.btn_draw_circle)
    void onDrawCircleBtnClick(){
        mPaint.setStyle(Paint.Style.STROKE);
        int radius = Math.min(mBitmapWidth/2, mBitmapHeight/2);
        mCanvas.drawCircle(mBitmapWidth/2, mBitmapHeight/2, radius, mPaint);
        mImageView.invalidate();
    }

    @OnClick(R.id.btn_draw_rect)
    void onDrawRectBtnClick(){
        mPaint.setStyle(Paint.Style.STROKE);
        RectF mRectF = new RectF(0, 0, mBitmapWidth, mBitmapHeight);
        mCanvas.drawRect(mRectF, mPaint);
        mImageView.invalidate();
    }

    @OnClick(R.id.btn_draw_line)
    void onDrawLineBtnClick(){
        mPaint.setStyle(Paint.Style.STROKE);
//        float[] pts = {0, 0, mBitmapWidth, mBitmapHeight, mBitmapWidth, 0, 0, mBitmapHeight};
//        mCanvas.drawLines(pts, mPaint);
        mPaint.setStrokeWidth(DimensionUtils.Dp2Px(this, 10));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mCanvas.drawLine(0, 0, 300, 300, mPaint);
        mImageView.invalidate();
    }

    @OnClick(R.id.btn_draw_path)
    void onDrawPathBtnClick(){
        mPaint.setStyle(Paint.Style.STROKE);
        Path mPath = new Path();

        // 二次贝塞尔
//        mPath.quadTo(mBitmapWidth/2 , mBitmapHeight/2 , mBitmapWidth, 0);
//        mPath.quadTo(mBitmapWidth/2, mBitmapHeight/2, mBitmapWidth, mBitmapHeight);
//        mPath.quadTo(mBitmapWidth/2, mBitmapHeight/2, 0, mBitmapHeight);
//        mPath.quadTo(mBitmapWidth/2, mBitmapHeight/2, 0, 0);

        // 三次贝塞尔
        mPath.moveTo(0, 0);
        mPath.cubicTo(0, mBitmapHeight, mBitmapWidth/2, mBitmapHeight, mBitmapWidth, 0);
        mPath.addCircle(mBitmapWidth/2, mBitmapHeight/2, mBitmapWidth/2, Path.Direction.CCW);
        mCanvas.drawPath(mPath, mPaint);
        mImageView.invalidate();
    }

    /**
     * 绘制文本的时候需要对path的style进行设置
     */
    @OnClick(R.id.btn_draw_text_on_path)
    void onDrawTextOnPathBtnClick(){
        Path mPath = new Path();
        mPath.moveTo(40, 0);
        mPath.quadTo(0, mBitmapHeight, mBitmapWidth, 0);
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawPath(mPath, mPaint);

        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        String text = "this is a DrawTextOnPath Demo";
        mCanvas.drawTextOnPath(text, mPath, 100, -10, mPaint);
        mImageView.invalidate();
    }
}
