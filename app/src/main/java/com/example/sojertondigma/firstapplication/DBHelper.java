package com.example.sojertondigma.firstapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "data";

    public DBHelper(Context context) {
        super(context, "savelesson", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        db.execSQL("create table savelesson ("
                + "id integer primary key autoincrement,"
                + "SUBJECT text,"
                + "PREPOD text,"
                + "ROOM text,"
                + "TIME_FROM text,"
                + "TIME_TILL text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}