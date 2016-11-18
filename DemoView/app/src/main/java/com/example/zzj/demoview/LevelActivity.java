package com.example.zzj.demoview;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.zzj.demoview.views.PersonLevelView;
import com.example.zzj.demoview.views.TimerProgressView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LevelActivity extends AppCompatActivity {

    @Bind(R.id.plv_level)
    PersonLevelView levelView;
    @Bind(R.id.protect)
    ImageView protect;
    @Bind(R.id.timer)
    TimerProgressView timer;

    public static void naviageFrom(Context context){
        Intent intent = new Intent(context, LevelActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        ButterKnife.bind(this);
        levelView.setGrowth(100, 200);
    }

    @OnClick(R.id.plv_level)
    void onLevelClick(){
        levelView.invalidate();
    }

    @OnClick(R.id.fl_protect)
    void onProtectClic(){
        if (timer.isTiming()){
            timer.stopTimer();
            AnimationDrawable drawable = (AnimationDrawable) protect.getBackground();
            drawable.stop();
        }else{
            timer.startTimer();
            AnimationDrawable drawable = (AnimationDrawable) protect.getBackground();
            drawable.start();
        }
    }
}
