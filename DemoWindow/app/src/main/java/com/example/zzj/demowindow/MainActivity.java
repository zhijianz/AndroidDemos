package com.example.zzj.demowindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zzj.demowindow.views.IMEView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Button windowView1;
    LinearLayout weightView;
    TextView mBlurView;
    Button mMoveView;
    float mLastX;
    float mLastY;
    WindowManager.LayoutParams moveWindowParams;
    IMEView mImeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;      // 屏幕常亮
        attrs.flags |= WindowManager.LayoutParams.FLAG_SECURE;      // 不允许截图

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        windowView1 = new Button(this);
        windowView1.setText("window view 1");

        weightView = new LinearLayout(this);
        weightView.setOrientation(LinearLayout.HORIZONTAL);
        Button weightBtn1 = new Button(this);
        weightBtn1.setText("weight btn 1");
        weightView.addView(weightBtn1);
        Button weightBtn2 = new Button(this);
        weightBtn2.setText("weight btn 2");
        weightView.addView(weightBtn2);

        mBlurView = new TextView(this);
        mBlurView.setText("Blue Window");
        mBlurView.setTextColor(Color.WHITE);
        mBlurView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        mBlurView.setLayoutParams(params);

        mBlurView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBlurView.getParent() != null){
                    WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                    manager.removeView(mBlurView);
                }
            }
        });

        mMoveView = new Button(this);
        mMoveView.setText("drag to move");
        mMoveView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        mLastX = event.getRawX();
                        mLastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float deltaX = event.getRawX() - mLastX;
                        float deltaY = event.getRawY() - mLastY;
                        mLastX = event.getRawX();
                        mLastY = event.getRawY();
                        if (mMoveView.getParent() != null && moveWindowParams != null){
                            WindowManager manager = getWindowManager();
                            moveWindowParams.x += deltaX;
                            moveWindowParams.y += deltaY;
                            manager.updateViewLayout(mMoveView, moveWindowParams);
                            return true;
                        }
                        break;
                    default:
                        mLastX = 0;
                        mLastY = 0;
                        break;
                }
                return false;
            }
        });

        mImeView = new IMEView(this);
    }

    @OnClick(R.id.btn_add)
    void onAddClick(){
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION, 0, PixelFormat.TRANSPARENT);
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.gravity = Gravity.BOTTOM;
        manager.addView(windowView1, params);
    }

    @OnClick(R.id.btn_update)
    void onUpdateClick(){
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        ViewGroup.LayoutParams params = windowView1.getLayoutParams();
        if (params instanceof WindowManager.LayoutParams){
            WindowManager.LayoutParams p = (WindowManager.LayoutParams) params;
            p.gravity = Gravity.BOTTOM | Gravity.RIGHT;
            manager.updateViewLayout(windowView1, p);
        }
    }

    @OnClick(R.id.btn_remove)
    void onRemoveClick(){
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        ViewGroup.LayoutParams params = windowView1.getLayoutParams();
        if (params instanceof WindowManager.LayoutParams){
            manager.removeView(windowView1);
        }
    }

    @OnClick(R.id.btn_weight)
    void onWeightClick(){
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        if (weightView.getParent() != null){
            manager.removeView(weightView);
        }else {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSPARENT);
            params.gravity = Gravity.BOTTOM;
            params.horizontalWeight = 400;
            manager.addView(weightView, params);
        }
    }

    /**
     * flag1: FLAG_LAYOUT_IN_SCREEN 全屏，会被状态栏覆盖
     * flag2: FLAG_FULLSCREEN 全屏，隐藏状态栏
     * flag: FLAG_FORCE_NOT_FULLSCREEN 不占用状态栏区域
     */
    @OnClick(R.id.btn_blur)
    void onBlurClick(){
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_APPLICATION, 0, PixelFormat.TRANSPARENT);
        params.gravity = Gravity.CENTER;
        params.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        params.dimAmount = .5f;
        params.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        params.flags |= WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
        manager.addView(mBlurView, params);
    }

    /**
     * 测试：FLAG_LAYOUT_NO_LIMITS
     * 之前尝试过把window移出屏幕但是没有成功，试一下是不是这个flag会有点作用
     */
    @OnClick(R.id.btn_move)
    void onMoveClick(){
        if (mMoveView.getParent() == null){
            moveWindowParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION,
                    0, PixelFormat.TRANSPARENT);
            moveWindowParams.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            moveWindowParams.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
            moveWindowParams.gravity = Gravity.CENTER;
            getWindowManager().addView(mMoveView, moveWindowParams);
        }else {
            getWindowManager().removeView(mMoveView);
        }
    }

    /**
     * 输入法的处理还是有些问题
     */
    @OnClick(R.id.btn_ime)
    void onImeClick(){
        if (mImeView.getParent() == null){
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION,
                    0, PixelFormat.TRANSPARENT);
//            params.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//            params.flags |= WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
//            params.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            getWindowManager().addView(mImeView, params);
        }else {
            getWindowManager().removeView(mImeView);
        }
    }
}
