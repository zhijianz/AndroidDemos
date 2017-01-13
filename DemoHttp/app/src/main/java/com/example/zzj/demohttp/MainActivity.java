package com.example.zzj.demohttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.zzj.demohttp.beans.books.Book;
import com.example.zzj.demohttp.beans.books.BookResult;
import com.example.zzj.demohttp.beans.books.Catalog;
import com.example.zzj.demohttp.callbacks.books.BooksEventCallback;
import com.example.zzj.demohttp.models.books.BooksModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BooksEventCallback.OnQueryBooksCallback{
    private static final String TAG = "MainActivity";

    @BindView(R.id.result) TextView mResultTV;
    BooksModel mBooksModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mBooksModel = new BooksModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.get)
    void onGetClick(){
        if (mBooksModel == null){
            mBooksModel = new BooksModel();
        }
        mBooksModel.queryCatalog();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void onQueryBooksCatalog(BookResult<List<Catalog>> result){
        if (result.resultCode != 200){
            Log.e(TAG, "onQueryBooksCatalog, resultCode: " + result.resultCode + "errorCode: " + result.errorCode + "reason: " + result.reason);
            return;
        }
        Gson gson = new Gson();
        String resultStr = gson.toJson(result.data, new TypeToken<List<Catalog>>(){}.getType());
        mResultTV.setText(resultStr);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void onQueryBookList(BookResult<List<Book>> result) {

    }
}
