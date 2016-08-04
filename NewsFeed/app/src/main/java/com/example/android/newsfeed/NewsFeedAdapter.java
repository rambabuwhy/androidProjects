package com.example.android.newsfeed;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 * Created by ryerajan on 28-07-2016.
 */
public class NewsFeedAdapter  extends ArrayAdapter<NewsFeed> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Constructs a new {@link NewsFeedAdapter}.
     *
     * @param context of the app
     * @param news_feeds is the list of newsFeeds, which is the data source of the adapter
     */
    public NewsFeedAdapter(Context context, List<NewsFeed> news_feeds) {
        super(context, 0, news_feeds);
    }

    /**
     * Returns a list item view that displays information about the newsFeeds at the given position
     * in the list of newsFeeds.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.newsfeed_list, parent, false);
        }
        // Find the newsFeeds at the given position in the list of newsFeeds
        NewsFeed currentnews = getItem(position);

        // Find the TextView with view ID magnitude
        TextView titleView = (TextView) listItemView.findViewById(R.id.news_title);

        titleView.setText(currentnews.getNewsTitle());

        // Find the TextView with view ID magnitude
        TextView sectionView = (TextView) listItemView.findViewById(R.id.news_section);

        sectionView.setText(currentnews.getSectionName());

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }



}
