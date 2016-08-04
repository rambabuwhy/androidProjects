package com.example.android.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by ryerajan on 28-07-2016.
 */


public class NewsFeedLoader extends AsyncTaskLoader<List<NewsFeed>> {

    /** Tag for log messages */
    private static final String LOG_TAG = NewsFeedLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link NewsFeedLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public NewsFeedLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<NewsFeed> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of newsFeeds.
        List<NewsFeed> newsFeeds = QueryUtils.fetchNewsFeedData(mUrl);
        return newsFeeds;
    }
}
