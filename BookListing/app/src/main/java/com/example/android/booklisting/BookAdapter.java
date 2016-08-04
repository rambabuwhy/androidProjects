package com.example.android.booklisting;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ryerajan on 25-07-2016.
 */
public class BookAdapter extends ArrayAdapter<Book> {



    public BookAdapter(Context context, ArrayList<Book> words) {
        super(context, 0, words);


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Book currentBook = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.book_title);
        // Get the Title from the currentBook object and set this text on
        // the TextView.
        miwokTextView.setText(currentBook.getBookTitle());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.book_author);
        // Get the Author from the currentBook object and set this text on
        // the TextView.
        defaultTextView.setText(currentBook.getBookAuthor());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
