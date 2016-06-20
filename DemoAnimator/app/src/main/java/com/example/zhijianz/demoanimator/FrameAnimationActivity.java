package com.example.zhijianz.demoanimator;

import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrameAnimationActivity extends AppCompatActivity {

    private AnimationDrawable mAnimDrawable;

    @Bind(R.id.iv_frame_animation)
    ImageView mFrameIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        ButterKnife.bind(this);

//        mFrameIV.setBackgroundResource(R.drawable.frame_animation);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @OnClick(R.id.iv_frame_animation)
    void onFrameImageViewClick(){
//        if (mAnimDrawable == null){
//            mAnimDrawable = (AnimationDrawable) mFrameIV.getBackground();
//        }
//        if (mAnimDrawable == null){
//            Toast.makeText(this, "NULL DRAWABLE", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (mAnimDrawable.isRunning()){
//            Toast.makeText(this,"STOP ANIMATION",Toast.LENGTH_SHORT).show();
//            mAnimDrawable.stop();
//        }else {
//            Toast.makeText(this, "START ANIMATION", Toast.LENGTH_SHORT).show();
//            mAnimDrawable.start();
//        }

        // use AnimationDrawable with java code

        AnimationDrawable drawable = new AnimationDrawable();
        drawable.addFrame(getResources().getDrawable(R.mipmap.p1),100);
        drawable.addFrame(getResources().getDrawable(R.mipmap.p2),100);
        drawable.addFrame(getResources().getDrawable(R.mipmap.p3),100);
        drawable.addFrame(getResources().getDrawable(R.mipmap.p4),100);
        drawable.setOneShot(false);
        mFrameIV.setBackground(drawable);
        drawable.start();
    }
}
