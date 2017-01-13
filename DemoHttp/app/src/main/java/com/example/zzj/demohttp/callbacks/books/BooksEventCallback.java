package com.example.zzj.demohttp.callbacks.books;

import com.example.zzj.demohttp.beans.books.Book;
import com.example.zzj.demohttp.beans.books.BookResult;
import com.example.zzj.demohttp.beans.books.Catalog;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */

public interface BooksEventCallback {
    interface OnQueryBooksCallback{
        void onQueryBooksCatalog(BookResult<List<Catalog>> result);
        void onQueryBookList(BookResult<List<Book>> result);
    }
}
