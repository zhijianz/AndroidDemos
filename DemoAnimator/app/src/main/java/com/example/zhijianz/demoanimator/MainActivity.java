package com.example.zhijianz.demoanimator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_view_animator)
    void onViewAnimatorClick(){
        startActivity(new Intent(this, ViewAnimationActivity.class));
    }

    @OnClick(R.id.btn_frame_animator)
    void onFrameAnimatorClick(){
        startActivity(new Intent(this, FrameAnimationActivity.class));
    }

    @OnClick(R.id.btn_layout_anim)
    void onLayoutAnimClick(){
        startActivity(new Intent(this, LayoutAnimActivity.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @OnClick(R.id.btn_attribute_animator)
    void onAttributeAnimClick(){
        startActivity(new Intent(this, AttributeAnimActivity.class));
    }

    @OnClick(R.id.btn_all_interpolators)
    void onAllInterpolatorClick(){
        startActivity(new Intent(this, AllInterpolatorsActivity.class));
    }

    @OnClick(R.id.btn_web)
    void onWebClick(){
        startActivity(new Intent(this, WebActivity.class));
    }
}
