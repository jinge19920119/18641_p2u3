package com.artist.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jinge on 7/16/15.
 */
public class DatabaseConnector {
    private static final String DATABASE_NAME = "MiYangFans";
    private SQLiteDatabase database; // database object
    private DatabaseOpenHelper databaseOpenHelper; // database helper

    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context)

    {
        databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }
    /*
    open the connection
     */
    public void open()
    {
        database = databaseOpenHelper.getWritableDatabase();
    }

    /*
    close the connection
     */
    public void close()
    {
        if (database != null)
            database.close();
    }

    public void insert(String emailAddress){
        ContentValues data= new ContentValues();
        data.put("address", emailAddress);
        open();
        database.insert("email", null, data);
        close();
    }

    public Cursor getAll (){
        Cursor cursor= database.query("email", null,null, null, null, null, null);
        return cursor;
    }

    /*
    inner class which is used as database openHelper
     */
    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        public DatabaseOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // query to create a new table named contacts
            String createQuery = "CREATE TABLE email" +
                    "(_id integer primary key autoincrement, address TEXT);";

            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        }
    }
}
