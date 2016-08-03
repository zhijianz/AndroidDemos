package com.example.zzj.demoview.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Display;

/**
 * Created by Administrator on 2016/6/13.
 */
public class DimensionUtils {
    public static int getScreenWidth(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        return display.getWidth();
    }

    public static int getScreenHeight(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        return display.getHeight();
    }

    public static int Dp2Px(Context context, float dpValue){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + .5f);
    }
}
