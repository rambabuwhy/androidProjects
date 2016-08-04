package com.example.android.newsfeed;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<NewsFeed>> {


    private static final String LOG_TAG = NewsFeedActivity.class.getName();


    private static final String USGS_REQUEST_URL =
            "http://content.guardianapis.com/search?q=debates&api-key=test";

    /**
     * Constant value for the NewsFeed loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWSFEED_LOADER_ID = 1;


    private NewsFeedAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        // Find a reference to the {@link ListView} in the layout
        ListView newsFeedListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsFeedListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of NewsFeed as input
        mAdapter = new NewsFeedAdapter(this, new ArrayList<NewsFeed>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsFeedListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected newsfeed.
        newsFeedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current newsfeedthat was clicked on
                NewsFeed currentnewsFeed = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsFeedUri = Uri.parse(currentnewsFeed.getUrl());

                // Create a new intent to view the newsFeed URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsFeedUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWSFEED_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }



    @Override
    public Loader<List<NewsFeed>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsFeedLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsFeed>> loader, List<NewsFeed> news_Feeds) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No newsfeeds found."
        mEmptyStateTextView.setText(R.string.no_earthquakes);

        // Clear the adapter of previous newsfeed data
        mAdapter.clear();

        // If there is a valid list of {@link NewsFeed}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news_Feeds != null && !news_Feeds.isEmpty()) {
            mAdapter.addAll(news_Feeds);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsFeed>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
