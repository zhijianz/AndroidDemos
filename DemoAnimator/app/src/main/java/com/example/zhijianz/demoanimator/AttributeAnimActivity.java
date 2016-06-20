package com.example.zhijianz.demoanimator;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttributeAnimActivity extends AppCompatActivity {

    @Bind(R.id.v_actor)
    View mActorView;
    @Bind(R.id.btn_change_color)
    Button mChangeColorBtn;
    @Bind(R.id.btn_change_location)
    Button mChangeLocationBtn;
    @Bind(R.id.ll_animation_area)
    LinearLayout mAnimationArea;

    private ValueAnimator mColorAnimator;
    private AnimatorSet mLocationAnimator;
    private Drawable mBGDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute_anim);
        ButterKnife.bind(this);
        initLayoutTransition();
    }

    public void initLayoutTransition(){
        LayoutTransition transition = new LayoutTransition();

        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 0);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 0);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 0);

        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
        ValueAnimator changeAppearingAnimator =
                ObjectAnimator.ofPropertyValuesHolder(new Object(), pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY).setDuration(transition.getDuration(LayoutTransition.CHANGE_APPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeAppearingAnimator);

        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(0.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 360f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
//        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofFloat("rotation", 0f, 360f, 0f);
        ValueAnimator changeDisappearingAnimator = ObjectAnimator.ofPropertyValuesHolder(new Object(), pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).setDuration(transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeDisappearingAnimator);

        transition.setDuration(500);
        mAnimationArea.setLayoutTransition(transition);
    }

    @OnClick(R.id.btn_change_color)
    void onChangeColorClick() {
        if (mColorAnimator == null) {
            mColorAnimator = ObjectAnimator.ofInt(mActorView, "backgroundColor", 0x00000000, 0xffffffff);
            mColorAnimator.setDuration(3000);
            mColorAnimator.setEvaluator(new ArgbEvaluator());
            mColorAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mColorAnimator.setRepeatMode(ValueAnimator.REVERSE);
            mBGDrawable = mActorView.getBackground();
            mBGDrawable = mBGDrawable == null ? new ColorDrawable(0xffff0000) : mBGDrawable;
        }

        if (mColorAnimator.isRunning()) {
            mColorAnimator.end();
            mChangeColorBtn.setText("START CHANGE COLOR");
        } else {
            mColorAnimator.start();
            mChangeColorBtn.setText("END CHANGE COLOR");
        }
    }

    @OnClick(R.id.btn_change_location)
    void onChangeLocationClick() {
        if (mLocationAnimator == null) {
            mLocationAnimator = new AnimatorSet();
            ObjectAnimator animator = null;
            List<ObjectAnimator> animatorList = new ArrayList<>();

            PropertyValuesHolder translationXHolder = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 200);
            PropertyValuesHolder translationYHolder = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 200);
            animator = ObjectAnimator.ofPropertyValuesHolder(mActorView, translationXHolder, translationYHolder);
            animator.setInterpolator(new LinearInterpolator());
            animatorList.add(animator);

            ObjectAnimator mAnimator = ObjectAnimator.ofFloat(mActorView, "translateX", 200);
            mAnimator.start();


            mLocationAnimator.playTogether((Collection)animatorList);
            mLocationAnimator.setDuration(500);
        }
        if (mLocationAnimator.isRunning()) {
            mLocationAnimator.end();
            mChangeLocationBtn.setText("START CHANGE LOCATION");
        } else {
            mLocationAnimator.start();
            mChangeLocationBtn.setText("END CHANGE LOCATION");
        }
    }
    
    @OnClick(R.id.btn_test)
    public void onTestClick(){

        if (mAnimationArea.getChildCount() != 2){
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DimensionUtil.dp2px(this, 50f), DimensionUtil.dp2px(this, 50f));
            view.setLayoutParams(params);
            view.setBackgroundResource(R.color.colorPrimary);
            mAnimationArea.addView(view, 0);
        }else {
            View view = mAnimationArea.getChildAt(0);
            if (view.getVisibility() == View.GONE){
                view.setVisibility(View.VISIBLE);
            }else {
                view.setVisibility(View.GONE);
            }
        }
    }

    // 可以用关键帧的方式模仿出弹性的动画方式

    @OnClick(R.id.btn_rubber_band)
    public void onRubberBandClick(){
        PropertyValuesHolder scaleXPVH = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{1.0f, 1.25f, 0.75f, 1.15f, 1.0f});
        PropertyValuesHolder scaleYPVH = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{1.0f, 0.75f, 1.25f, 0.85f, 1.0f});
        ObjectAnimator.ofPropertyValuesHolder(mActorView, scaleXPVH, scaleYPVH).setDuration(800).start();
    }

    @OnClick(R.id.btn_shake_animator)
    public void onShakeClick(){
        ObjectAnimator.ofFloat(mActorView, "translationX", new float[]{0.0f, 25.0f, -25.0f, 25.0f, -25.0f, 15.0f, -15.0f, 6.0f, -6.0f, 0.0f}).setDuration(600).start();
    }

    @OnClick(R.id.btn_value_animator)
    public void onValueAnimatorClick(){
        ValueAnimator mValueAnimator = ValueAnimator.ofFloat(1, 100);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation == null){
                    return;
                }
                mActorView.setTranslationX((Float) animation.getAnimatedValue());
                mActorView.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        mValueAnimator.setDuration(2000);
        mValueAnimator.start();
    }
}
