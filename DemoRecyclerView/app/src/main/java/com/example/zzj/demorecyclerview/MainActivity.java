package com.example.zzj.demorecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zzj.demorecyclerview.gank.view.WelfareActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.item_pool)
    void onItemPoolClick(){
        Timber.d("onItemPoolClick, args: %s", "Timber");
        Intent intent = new Intent(this, WelfareActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.sticky_header)
    void onStickyHeaderClick(){
        Timber.d("onStickyHeaderClick");
    }
}
