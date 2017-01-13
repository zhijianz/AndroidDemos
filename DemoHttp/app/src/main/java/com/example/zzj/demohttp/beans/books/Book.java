package com.example.zzj.demohttp.beans.books;


import com.example.zzj.demohttp.utils.ValidateUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/28.
 */

public class Book {
    public String title;
    public String catalog;
    public String tags;
    public String sub1;     // 书名简介
    public String sub2;     // 内容简介
    public String img;
    public String reading;      // 阅读人数
    public String online;   // 网购地址
    public String bytime;   // 发布时间

    public List<String> getCatalogs(){
        if (ValidateUtil.empty(catalog)){
            return Collections.emptyList();
        }else {
            return Arrays.asList(catalog.split(" "));
        }
    }

    public List<String> getTags(){
        if (ValidateUtil.empty(tags)){
            return Collections.emptyList();
        }else {
            return Arrays.asList(tags.split(" "));
        }
    }

    public Map<String, String> getOnlineList(){
        if (ValidateUtil.empty(online)){
            return Collections.emptyMap();
        }
        String[] temp = online.split(" ");
        if (ValidateUtil.empty(temp)){
            return Collections.emptyMap();
        }
        Map<String, String> result = new HashMap<>();
        for (String s : temp) {
            if (ValidateUtil.empty(s)){
                continue;
            }
            String[] sub = s.split(":");
            if (ValidateUtil.empty(sub) || sub.length != 2){
                continue;
            }
            if (!ValidateUtil.empty(sub[0]) && !ValidateUtil.empty(sub[1])){
                result.put(sub[0], sub[1]);
            }
        }
        return result;
    }
}
