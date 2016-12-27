package com.example.zzj.demowindow.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.zzj.demowindow.R;

/**
 * Created by Administrator on 2016/11/18.
 */

public class CustomDialog extends Dialog {
    float mLastX = 0;
    float mLastY = 0;

    public CustomDialog(Context context) {
        super(context, R.style.CustomWindowStyle);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);
        getWindow().getAttributes().gravity = Gravity.TOP;
        getWindow().getAttributes().type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR, WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        View contentRoot = ((ViewGroup)getWindow().getDecorView()).getChildAt(0);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) contentRoot.getLayoutParams();
        params.topMargin = getContext().getResources().getDimensionPixelSize(R.dimen.top_margin);
        contentRoot.setLayoutParams(params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            mLastX = event.getRawX();
            mLastY = event.getRawY();
        }else if (action == MotionEvent.ACTION_MOVE){
            float deltaX = event.getRawX() - mLastX;
            float deltaY = event.getRawY() - mLastY;
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.x += deltaX;
            params.y += deltaY;
            View decorView = getWindow().getDecorView();
            WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            manager.updateViewLayout(decorView, params);
            mLastX = event.getRawX();
            mLastY = event.getRawY();
        }
        return super.onTouchEvent(event);
    }
}
