package com.example.zhijianz.demoanimator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Switch;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewAnimationActivity extends AppCompatActivity {

    private Animation mExampleAnimation;

    @Bind(R.id.v_actor)
    View mActorView;
    @Bind(R.id.sw_use_xml)
    Switch mUseXmlSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        ButterKnife.bind(this);

        mExampleAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_view_anim_example);
    }

    /**
     * 希望直接获取到动画的执行状态有点麻烦
     * 1. 比较直接的方式是给动画加上监听接口，直接在接口里面监听动画的状态
     * 2. 看了一下代码，比较绕但是比较直接的方式是使用 Animation#hasStarted() 、
     *    Animation#hasEnded() 以及 Animation#cancel()、Animation#detach() 三个接口。在源代码中可以看到
     *    Animation#hasStarted() 和 Animation#hasEnded() 分别维护了两个变量 mStarted
     *    和 mEnded。这两个标记初始化都是 false ，在调用 Animation#start()的时候会都设置
     *    成为 false 然后在执行动画的时候将 mStart 设置成 true 同时在动画结束或者是调用
     *    Animator#cancel() 或者 的时候将 mEnded 设置成 true。所以最后在动画首次启动之前
     *    mStarted = false，在启动之后就一直是 mStarted = true; 首次启动之前，mEnded = false,
     *    之后都可以使用这个标识来判断动画是否运行
     */
    @OnClick(R.id.btn_animation_set)
    void onAnimationSetClick() {
        AnimationSet animation;
        if (mUseXmlSwitch.isChecked()){
            animation = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.animation_view_anim_example);
        }else {
            float pivotX = mActorView.getWidth()/2;
            float pivotY = mActorView.getHeight()/2;
            animation = new AnimationSet(true);

            RotateAnimation mRotateAnimation = new RotateAnimation(0, 360, pivotX, pivotY);
            mRotateAnimation.setRepeatCount(Animation.INFINITE);
            animation.addAnimation(mRotateAnimation);
            TranslateAnimation mTranslateAnimation = new TranslateAnimation(0, mActorView.getWidth() * 3, 0, mActorView.getHeight() * 3);
            mTranslateAnimation.setRepeatCount(Animation.INFINITE);
            animation.addAnimation(mTranslateAnimation);
            AlphaAnimation mAlphaAnimation = new AlphaAnimation(1, 0.2f);
            mAlphaAnimation.setRepeatCount(Animation.INFINITE);
            animation.addAnimation(mAlphaAnimation);
            ScaleAnimation mScaleAnimation = new ScaleAnimation(.8f, 1.2f, .8f, 1.2f, pivotX, pivotY);
            mScaleAnimation.setRepeatCount(Animation.INFINITE);
            animation.addAnimation(mScaleAnimation);

            animation.setRepeatMode(Animation.RESTART);
            animation.setDuration(5000);
        }

        mActorView.startAnimation(animation);
    }

    @OnClick(R.id.btn_translate_animation)
    void onTranslateAnimationClick(){
        TranslateAnimation animation ;
        if (mUseXmlSwitch.isChecked()){
            animation = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.animation_translate);
        }else {
            animation = new TranslateAnimation(0, 200, 0, 200);
            animation.setDuration(2000);
            animation.setInterpolator(new LinearInterpolator());
            animation.setRepeatCount(4);
            animation.setRepeatMode(Animation.RESTART);
        }
        mActorView.startAnimation(animation);
    }

    @OnClick(R.id.btn_rotate_animation)
    void onRotateAnimationClick(){
        RotateAnimation animation;
        if (mUseXmlSwitch.isChecked()){
            animation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.animation_rotate);
        }else {
            animation = new RotateAnimation(0, 360, mActorView.getWidth()/2, mActorView.getHeight()/2);
            animation.setDuration(1000);
            animation.setRepeatMode(Animation.RESTART);
            animation.setRepeatCount(Animation.INFINITE);
            animation.setInterpolator(new BounceInterpolator());
        }
        mActorView.startAnimation(animation);
    }

    @OnClick(R.id.btn_alpha_animation)
    void onAlphaAnimationClick(){
        AlphaAnimation animation;
        if (mUseXmlSwitch.isChecked()){
            animation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.animation_alpha);
        }else {
            animation = new AlphaAnimation(0, 1);
            animation.setDuration(1000);
            animation.setRepeatCount(Animation.INFINITE);
            animation.setRepeatMode(Animation.REVERSE);
            animation.setInterpolator(new LinearInterpolator());
        }
        mActorView.startAnimation(animation);
    }

    @OnClick(R.id.btn_scale_animation)
    void onScaleAnimationClick(){
        ScaleAnimation animation;
        if (mUseXmlSwitch.isChecked()){
            animation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.animation_scale);
        }else {
            animation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, mActorView.getWidth()/2, mActorView.getHeight()/2);
            animation.setDuration(1000);
            animation.setRepeatMode(Animation.REVERSE);
            animation.setRepeatCount(Animation.INFINITE);
        }
        mActorView.startAnimation(animation);
    }
}
