package com.example.zzj.demoview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ScrollActivity extends AppCompatActivity {

    public static void navigateFrom(Context context){
        Intent intent = new Intent(context, ScrollActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
    }
}
