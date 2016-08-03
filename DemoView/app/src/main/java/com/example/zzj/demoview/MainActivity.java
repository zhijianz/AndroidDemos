package com.example.zzj.demoview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_move_view)
    void onMoveViewBtnClick(){
        MoveViewActivity.navigateFrom(this);
    }

    @OnClick(R.id.btn_canvas_draw)
    void onCanvasDrawBtnClick(){
        CanvasDrawActivity.navigateFrom(this);
    }

    @OnClick(R.id.btn_scroll_view)
    void onScrollViewBtnClick(){
        ScrollActivity.navigateFrom(this);
    }

    @OnClick(R.id.btn_level_view)
    void onLevelClick(){
        LevelActivity.naviageFrom(this);
    }
}
