package com.samsrutidash.habittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);
        Log.v("DB", "OK");
        Log.v("Query: ", DBContract.Table1.CREATE_TABLE);
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");

        Log.d("Reading: ", "Reading all contacts..");
        db.addHabit(new HabitDetails("Game", 3));
        db.addHabit(new HabitDetails("Sleep", 5));

        Cursor c = db.getDetails(2);
        HabitDetails H = new HabitDetails(c.getString(1), Integer.parseInt(c.getString(2)));
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        Log.v("Update: ", H.getHabitTitle() + " " + H.getHabitFrequency());


    }
}
