package com.example.android.newsfeed;

/**
 * Created by ryerajan on 28-07-2016.
 */
public class NewsFeed {

    /** Magnitude of the earthquake */
    private String mNewsTitle;

    /** Location of the earthquake */
    private String mSectionName;



    /** Website URL of the earthquake */
    private String mUrl;

    /**
     * Constructs a new {@link NewsFeed} object.

     */
    public NewsFeed(String title, String sectionName, String url) {
        mNewsTitle = title;
        mSectionName = sectionName;
        mUrl = url;
    }

    /**
     * Returns the magnitude of the earthquake.
     */
    public String getNewsTitle() {
        return mNewsTitle;
    }

    /**
     * Returns the location of the earthquake.
     */
    public String getSectionName() {
        return mSectionName;
    }

    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl() {
        return mUrl;
    }
}
