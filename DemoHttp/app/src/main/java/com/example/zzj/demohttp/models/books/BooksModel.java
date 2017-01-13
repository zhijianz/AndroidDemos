package com.example.zzj.demohttp.models.books;

import android.util.Log;

import com.example.zzj.demohttp.beans.books.BookResult;
import com.example.zzj.demohttp.beans.books.Catalog;
import com.example.zzj.demohttp.utils.ValidateUtil;
import com.example.zzj.demohttp.utils.http.HttpHelper;
import com.example.zzj.demohttp.utils.http.OnResponseCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/28.
 */

public class BooksModel {
    private static final String TAG = "BooksModel";
    public static final String APP_KEY = "aba6692f7be763f5b8e80a2e39d2bfb3";

    public static String getBookCatalogURL(){
        String url = "http://apis.juhe.cn/goodbook/catalog?key=%s&dtype=json";
        return String.format(url, APP_KEY);
    }

    public static String getBookContentURL(int catalog, int offset, int pageSize){
        String url = "http://apis.juhe.cn/goodbook/query?key=%s&catalog_id=%d&pn=%d&rn=%d&dtype=json";
        return String.format(url, APP_KEY, catalog, offset, pageSize);
    }

    public void queryCatalog(){
        HttpHelper.get(getBookCatalogURL(), new OnResponseCallback() {
            @Override
            public void onResponse(Response response) {
                if (response == null){
                    Log.e(TAG, "[queryCatalog], null response");
                    return;
                }
                try {
                    String result = response.body().string();
                    if (!ValidateUtil.empty(result)){
                        Gson gson = new Gson();
                        BookResult<List<Catalog>> bookResult = gson.fromJson(result, new TypeToken<BookResult<List<Catalog>>>(){}.getType());
                        EventBus.getDefault().post(bookResult);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "[queryCatalog], exception: " + e);
                }
            }
        });
    }
}
