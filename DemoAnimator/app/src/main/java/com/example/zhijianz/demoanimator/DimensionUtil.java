package com.example.zhijianz.demoanimator;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by zhijianz on 2016/5/2.
 */
public class DimensionUtil {
    public static int dp2px(Context ctx, float dpValue){
        float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    public static int px2dp(Context ctx, float pxValue){
        float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }

    public static int getScreenWidth(Context ctx){
        return ctx.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context ctx){
        return ctx.getResources().getDisplayMetrics().heightPixels;
    }
}
