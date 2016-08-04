package com.example.android.booklisting;

/**
 * Created by ryerajan on 25-07-2016.
 */
public class Book {

    public String mBookTitle;
    public String mBookAuthor;

   public Book(String Title,String Author) {

        mBookTitle = Title;
         mBookAuthor = Author;
    }

   public String getBookAuthor() {
        return mBookAuthor;
    }
   public String getBookTitle() {
        return mBookTitle;
    }
}
