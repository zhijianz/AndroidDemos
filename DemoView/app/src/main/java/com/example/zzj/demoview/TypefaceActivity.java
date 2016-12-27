package com.example.zzj.demoview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TypefaceActivity extends AppCompatActivity {
    public static void navigateFrom(Context context){
        Intent intent = new Intent(context, TypefaceActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeface);
    }
}
