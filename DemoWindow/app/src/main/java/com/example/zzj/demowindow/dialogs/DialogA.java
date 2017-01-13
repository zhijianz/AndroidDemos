package com.example.zzj.demowindow.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2017/1/6.
 */

public class DialogA extends BasicDialog {
    public DialogA(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(200, 200);
        button.setLayoutParams(params);
        button.setBackgroundColor(Color.RED);
        button.setText("DialogA");
        setContentView(button);
    }
}
