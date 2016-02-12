package com.quizscores.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.quizscores.model.Student;

/**
 * Created by jinge on 7/15/15.
 */
public class DatabaseConnector {
    private final String DATABASE_NAME = "Students";
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

    public void clear(){
        open();
        database.delete("scores",null,null);
        close();
    }
    /*
    insert all the values of a calculator object to the database
     */
    public void insertValues(Student student){
        ContentValues newData= new ContentValues();
        int num= student.getNum();
        newData.put("num",((Integer)num).toString());
        double[] scores= student.getScores();
        newData.put("quiz1",((Double)scores[0]).toString());
        newData.put("quiz2",((Double)scores[1]).toString());
        newData.put("quiz3",((Double)scores[2]).toString());
        newData.put("quiz4",((Double)scores[3]).toString());
        newData.put("quiz5", ((Double) scores[4]).toString());
        open();
        database.insert("scores", null, newData);
//        Log.i(((Integer) num).toString(), ((Double)scores[0]).toString());
        close();
    }

    /*
    get all the values from the database
     */
    public Cursor getAll(){
        return database.query("scores", null,null,null,null,null,null);
    }

    /*
    get the specific one from the databse with the purchase value
     */
    public Cursor getOne(int num){
        return database.query("scores",null,"num="+num,null,null,null,null);
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
            String createQuery = "CREATE TABLE scores" +
                    "(_id integer primary key autoincrement, num TEXT, quiz1 TEXT, quiz2 TEXT," +
                    "quiz3 TEXT, quiz4 TEXT, quiz5 TEXT);";

            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        }
    }

}
