package com.example.zzj.demowindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zzj.demowindow.dialogs.CustomDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getWindow().getDecorView().findViewById(android.R.id.content);
    }

    @OnClick(R.id.custom_dialog)
    void onCustomDialogClick(){
        new CustomDialog(this).show();
    }
}
