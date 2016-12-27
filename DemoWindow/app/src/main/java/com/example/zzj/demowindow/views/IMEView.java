package com.example.zzj.demowindow.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.zzj.demowindow.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/11.
 */

public class IMEView extends LinearLayout {
    public IMEView(Context context) {
        super(context);
        init();
    }

    public IMEView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init(){
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        setBackgroundColor(Color.WHITE);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_ime_view, this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_close)
    void onCloseClick(){
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        manager.removeView(this);
    }
}
