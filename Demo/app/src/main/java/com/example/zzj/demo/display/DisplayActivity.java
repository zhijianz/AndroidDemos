package com.example.zzj.demo.display;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.example.zzj.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class DisplayActivity extends AppCompatActivity {
    @BindView(R.id.iv_test) ImageView mTestIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_display_metric)
    void onDisplayMetricClick(){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        float density = displayMetrics.density;     // 屏幕密度
        float densityDpi = displayMetrics.densityDpi;       // 系统dpi
        int width = displayMetrics.widthPixels;     // 屏幕宽度
        int height = displayMetrics.heightPixels;       // 屏幕高度

        Timber.d("density: %f", density);
        Timber.d("Dpi: %f", densityDpi);
        Timber.d("display resolution: %d * %d", width, height);
    }

    @OnClick(R.id.btn_bitmap)
    void onBitmapClick(){
        BitmapDrawable drawable = (BitmapDrawable) mTestIV.getDrawable();
        if (drawable != null){
            Bitmap bitmap = drawable.getBitmap();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int size = bitmap.getByteCount();
            int iWidth = mTestIV.getWidth();
            int iHeight = mTestIV.getHeight();

            Timber.d("Bitmap, width: %d, height: %d, size: %d", width, height, size);
            Timber.d("ImageView, width: %d, height: %d", iWidth, iHeight);
        }
    }
}
