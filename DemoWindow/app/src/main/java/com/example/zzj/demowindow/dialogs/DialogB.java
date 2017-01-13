package com.example.zzj.demowindow.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2017/1/6.
 */

public class DialogB extends BasicDialog {
    public DialogB(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(200, 200);
        button.setLayoutParams(params);
        button.setBackgroundColor(Color.WHITE);
        button.setText("DialogB");
        setContentView(button);
    }
}
