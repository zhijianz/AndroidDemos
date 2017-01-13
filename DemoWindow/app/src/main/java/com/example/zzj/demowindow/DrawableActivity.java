package com.example.zzj.demowindow;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawableActivity extends AppCompatActivity {

    @BindView(R.id.drawable) ImageView mDrawable;

    ExpandingCircleAnimationDrawable mCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.bind(this);

        mCircle = new ExpandingCircleAnimationDrawable(200);
        mDrawable.setImageDrawable(mCircle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCircle.start();
    }

    @Override
    protected void onPause() {
        mCircle.stop();
        super.onPause();
    }
}
