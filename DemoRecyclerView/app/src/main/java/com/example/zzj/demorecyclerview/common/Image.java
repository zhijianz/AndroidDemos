package com.example.zzj.demorecyclerview.common;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/1/13.
 */

public class Image {
    private static final String TAG = "Image";
    public static void loadImage(ImageView view, String url){
        if (view == null){
            Log.e(TAG, "loadImage with null ImageView, url: " + url);
            return;
        }
        Glide.with(view.getContext()).load(url).placeholder(new ColorDrawable(Color.TRANSPARENT)).into(view);
    }
}
