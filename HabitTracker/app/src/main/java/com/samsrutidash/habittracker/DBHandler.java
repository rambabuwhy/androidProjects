package com.samsrutidash.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by samsrutidash on 6/30/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HabitTracker";
    Context context;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.Table1.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If a table with same name is already existed or modified like updating the table name or anything,
        //this will delete the previous one and will create again
        
        context.deleteDatabase(DATABASE_NAME);
        onCreate(db);
    }

    void addHabit(HabitDetails newHabit) {
        //Create a Database Connection
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.Table1.KEY_TITLE, newHabit.getHabitTitle());
        values.put(DBContract.Table1.KEY_FREQUENCY, newHabit.getHabitFrequency());
        // Inserting Row
        db.insert(DBContract.Table1.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public Cursor getDetails(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DBContract.Table1.TABLE_NAME, new String[]{DBContract.Table1.KEY_ID,
                        DBContract.Table1.KEY_TITLE, DBContract.Table1.KEY_FREQUENCY}, DBContract.Table1.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    // Updating single habit row
    public void updateHabitRow(double rowId, String newContent, String newContent1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.Table1.KEY_TITLE, newContent);
        values.put(DBContract.Table1.KEY_FREQUENCY, newContent1);
        db.update(DBContract.Table1.TABLE_NAME, values, DBContract.Table1.KEY_ID + "=" + rowId, null);

    }

    // Deleting single habit from the table
    public void deleteHabitTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + DBContract.Table1.TABLE_NAME);
    }


}
