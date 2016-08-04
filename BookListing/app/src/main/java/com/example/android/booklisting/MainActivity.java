package com.example.android.booklisting;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    /** Tag for the log messages */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    /** URL to query the BOOKS dataset for Google Books information */
    private static  String BOOKS_REQUEST_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);


    }
    public void onSearchClick(View view){

        EditText search_text= (EditText)findViewById(R.id.search_item);
        try {
            BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=" + URLEncoder.encode(search_text.getText().toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException uee) {
            Log.e(LOG_TAG, "URL Fails", uee.getCause());

        }

        new BookAsyncTask().execute();

    }
    private void updateUi(ArrayList<Book> booksLists) {

      if(booksLists.isEmpty())
        {
            Log.e(LOG_TAG,"Rambabu Array List Empty");
            ListView listView = (ListView) findViewById( R.id.list );
            listView.setEmptyView( findViewById( R.id.empty_list_view ) );
            BookAdapter adapter = new BookAdapter(this, booksLists);
            listView.setAdapter(adapter);
        }
        else {
            BookAdapter adapter = new BookAdapter(this, booksLists);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        }


    }
        private class BookAsyncTask extends AsyncTask<URL, Void, ArrayList<Book>> {

            @Override
            protected ArrayList<Book> doInBackground(URL... urls) {
                // Create URL object
                URL url = createUrl(BOOKS_REQUEST_URL);

                // Perform HTTP request to the URL and receive a JSON response back
                String jsonResponse = "";
                try {
                    jsonResponse = makeHttpRequest(url);
                } catch (IOException e) {
                    Log.e(LOG_TAG, "makeHttpRequest Fails", e.getCause());
                }

                // Extract relevant fields from the JSON response and create an {@link Event} object
                ArrayList<Book> books_list = extractFeatureFromJson(jsonResponse);

                // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
                return books_list;
            }


            @Override
            protected void onPostExecute(ArrayList<Book> bookslist) {
                if (bookslist == null) {
                    return;
                }

                updateUi(bookslist);
            }

            /**
             * Returns new URL object from the given string URL.
             */
            private URL createUrl(String stringUrl) {
                URL url = null;
                try {
                    url = new URL(stringUrl);
                } catch (MalformedURLException exception) {
                    Log.e(LOG_TAG, "Error with creating URL", exception);
                    return null;
                }
                return url;
            }

            /**
             * Make an HTTP request to the given URL and return a String as the response.
             */
            private String makeHttpRequest(URL url) throws IOException {
                String jsonResponse = "";
                HttpURLConnection urlConnection = null;
                InputStream inputStream = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(10000 /* milliseconds */);
                    urlConnection.setConnectTimeout(15000 /* milliseconds */);
                    urlConnection.connect();
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } catch (IOException e) {
                    // TODO: Handle the exception
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (inputStream != null) {
                        // function must handle java.io.IOException here
                        inputStream.close();
                    }
                }
                return jsonResponse;
            }

            /**
             * Convert the {@link InputStream} into a String which contains the
             * whole JSON response from the server.
             */
            private String readFromStream(InputStream inputStream) throws IOException {
                StringBuilder output = new StringBuilder();
                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    while (line != null) {
                        output.append(line);
                        line = reader.readLine();
                    }
                }
                return output.toString();
            }


            private ArrayList<Book> extractFeatureFromJson(String bookslistJSON) {

                // If there are results in the features array
                final ArrayList<Book> words = new ArrayList<Book>();
                words.clear();
                try {
                    JSONObject baseJsonResponse = new JSONObject(bookslistJSON);
                    int totalItems=baseJsonResponse.getInt("totalItems");
                    Log.e(LOG_TAG, "rambabu totalItems:" + totalItems);
                    if(totalItems <= 0)
                    {
                        return words;
                    }

                    JSONArray featureArray = baseJsonResponse.getJSONArray("items");
                    if( featureArray.length() > 0)
                    {
                        for (int k = 0; k < featureArray.length() ; k++) {
                            // Extract out the first feature (which is an bookslistJSON)

                            Log.e(LOG_TAG, "rambabu value K:" + k);
                            JSONObject firstFeature = featureArray.getJSONObject(k);
                            JSONObject vol_info = firstFeature.getJSONObject("volumeInfo");
                            if(vol_info.has("authors") && vol_info.has("title") ) {

                                String title_name = vol_info.getString("title");
                                JSONArray author = vol_info.getJSONArray("authors");
                                Log.e(LOG_TAG,"rambabu author: " + author.length());
                                String authors = "";
                                if (author.length() > 1) {
                                    for (int i = 0; i < author.length(); i++)
                                        authors = author.getString(i) + "," + authors;

                                } else if (author.length() == 1) {
                                    authors = author.getString(0);
                                } else {

                                    continue;
                                }
                                String author_name = authors;

                                words.add(new Book(title_name, author_name));
                            }

                        }
                        return words;
                    }
                    else{

                       return words;
                   }

                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Problem parsing the bookslistJSON JSON results", e);
                }
                return null;
            }
        }
}
