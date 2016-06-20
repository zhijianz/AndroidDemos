package com.example.zhijianz.demoanimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {

    @Bind(R.id.wv_test)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        mWebView.loadUrl("www.baidu.com");
    }
}
