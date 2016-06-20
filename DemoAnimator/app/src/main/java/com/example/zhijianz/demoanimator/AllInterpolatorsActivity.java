package com.example.zhijianz.demoanimator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllInterpolatorsActivity extends AppCompatActivity {

    @Bind(R.id.v_actor)
    View mActorView;

    private int diffX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_interpolators);
        ButterKnife.bind(this);
        diffX = DimensionUtil.getScreenWidth(this) / 3 * 2;
    }

    @OnClick(R.id.btn_linear_interpolator)
    public void onLinearInterpolatorClick() {
        mActorView.setTranslationX(0);
        mActorView.animate().setInterpolator(new LinearInterpolator()).translationX(diffX).setDuration(2000);
    }

    @OnClick(R.id.btn_accelerate_decelerate_interpolator)
    public void onAccelerateDecelerateInterpolatorClick() {
        mActorView.setTranslationX(0);
        mActorView.animate().setInterpolator(new AccelerateDecelerateInterpolator()).translationX(diffX).setDuration(2000);
    }

    @OnClick(R.id.btn_accelerate_interpolator)
    public void onAccelerateInterpolator() {
        mActorView.setTranslationX(0);
        mActorView.animate().setInterpolator(new AccelerateInterpolator()).translationX(diffX).setDuration(2000);
    }

    @OnClick(R.id.btn_decelerate_interpolator)
    public void onDecelerateInterpolator() {
        mActorView.setTranslationX(0);
        mActorView.animate().setInterpolator(new DecelerateInterpolator()).translationX(diffX).setDuration(2000);
    }

    @OnClick(R.id.btn_anticipate_interpolator)
    public void onAnticipateInterpolatorClick() {
        mActorView.setTranslationX(0);
        mActorView.animate().setInterpolator(new AnticipateInterpolator()).translationX(diffX).setDuration(2000);
    }

    @OnClick(R.id.btn_overshoot_interpolator)
    public void onOvershootInterpolatorClick(){
        mActorView.setTranslationX(0);
        float diff = DimensionUtil.getScreenWidth(this) / 3;
        mActorView.animate().setInterpolator(new OvershootInterpolator()).translationX(diff).setDuration(1500);
    }

    @OnClick(R.id.btn_anticipate_overshoot_interpolator)
    public void onAnticipateOvershootInterpolatorClick() {
        mActorView.setTranslationX(0);
        mActorView.animate().setInterpolator(new AnticipateOvershootInterpolator()).translationX(diffX).setDuration(2000);
    }

    @OnClick(R.id.btn_bounce_interpolator)
    public void onBounceInterpolatorClick() {
        mActorView.setTranslationX(0);
        mActorView.animate().setInterpolator(new BounceInterpolator()).translationX(diffX).setDuration(2000);
    }

    @OnClick(R.id.btn_cycle_interpolator)
    public void onCycleInterpolatorClick() {
        mActorView.setX(DimensionUtil.getScreenWidth(this) / 2 - mActorView.getWidth() / 2);
//        float diff = DimensionUtil.getScreenWidth(this) / 2;
//        mActorView.animate().setInterpolator(new CycleInterpolator(5)).translationX(diff).setDuration(2000);
        mActorView.animate().scaleX(1.4f).scaleY(1.4f).setInterpolator(new CycleInterpolator(5)).setDuration(1500);
    }

    @OnClick(R.id.btn_custom_cycle_interpolator)
    public void onCustomCycleInterpolatorClick(){
        mActorView.setX(DimensionUtil.getScreenWidth(this) / 2 - mActorView.getWidth() / 2);
        mActorView.animate().scaleX(1.4f).scaleY(1.4f).setInterpolator(new CustomCycleInterpolator(5)).setDuration(1500);
    }

    @OnClick(R.id.btn_custom_interpolator)
    public void onCustomInterpolatorClick(){
        mActorView.setTranslationX(0);
        float diff = DimensionUtil.getScreenWidth(this) / 2;
        mActorView.animate().setInterpolator(new CustomInterpolator()).translationX(diff).setDuration(2000);
    }

    public class CustomInterpolator implements Interpolator{
        @Override
        public float getInterpolation(float input) {
            if (input < 0.25){
                return input;
            }else if (input >= 0.25 && input < 0.75){
                return 0.25f;
            }else {
                return (float) Math.pow(input, 2);
            }
        }
    }

    public class CustomCycleInterpolator extends CycleInterpolator{

        public CustomCycleInterpolator(float cycles) {
            super(cycles);
        }

        @Override
        public float getInterpolation(float input) {
            return (1-input)*super.getInterpolation(input);
        }
    }
}
