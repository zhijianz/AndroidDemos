package com.example.zzj.demohttp.utils;

import java.util.Collection;

/**
 * Created by Administrator on 2016/12/28.
 */

public class ValidateUtil {
    public static <T> boolean empty(T[] a){
        return a == null || a.length == 0;
    }

    public static <T> boolean empty(Collection<T> c){
        return c == null || c.isEmpty();
    }

    public static boolean empty(CharSequence s){
        return s == null || s.length() == 0;
    }
}
