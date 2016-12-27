package com.example.zzj.demoview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class AutoFitActivity extends AppCompatActivity {

    @Bind(R.id.tv_normal) TextView mNormal;
    @Bind(R.id.tv_fit) TextView mFit;

    public static void navigateFrom(Context context){
        Intent intent = new Intent(context, AutoFitActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_fit);
        ButterKnife.bind(this);
    }

    @OnTextChanged(R.id.input)
    void onInputTextChange(CharSequence text){
        mFit.setText(text);
        mNormal.setText(text);
    }
}
