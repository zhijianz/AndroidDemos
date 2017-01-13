package com.example.zzj.demorecyclerview.common;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/1/13.
 */

public class Utils {
    public static boolean isEmpty(String s){
        return s == null || s.equals("");
    }

    public static int getScreenWidth(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 13){
            Point size = new Point();
            display.getSize(size);
            return size.x;
        }else {
            return display.getWidth();
        }
    }
}
