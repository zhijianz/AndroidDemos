package com.example.zzj.demowindow.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/1/6.
 */

public class BasicDialog extends Dialog {
    private boolean isShowing = false;

    public BasicDialog(Context context) {
        super(context);
    }

    protected void initDialog(){
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().getDecorView().setBackgroundDrawable(null);
        getWindow().getAttributes().gravity = Gravity.CENTER;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    @Override
    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public void show() {
        super.show();
        isShowing = true;
    }

    @Override
    public void hide() {
        super.hide();
        isShowing = false;
    }
}
