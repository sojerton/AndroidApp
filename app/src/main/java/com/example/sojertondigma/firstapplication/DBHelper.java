package com.example.sojertondigma.firstapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "savelesson";
    private static final int DATABASE_VERSION = 1 ;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SUBJECT = "SUBJECT";
    public static final String COLUMN_PREPOD = "PREPOD";
    public static final String COLUMN_ROOM = "ROOM";
    public static final String COLUMN_TIME_FROM = "TIME_FROM";
    public static final String COLUMN_TIME_TILL = "TIME_TILL";
    final String LOG_TAG = "data";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        db.execSQL(" create table " + DATABASE_NAME + " ("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_SUBJECT + " text, "
                + COLUMN_PREPOD + " text, "
                + COLUMN_ROOM + " text, "
                + COLUMN_TIME_FROM + " text, "
                + COLUMN_TIME_TILL + " text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        this.onCreate(db);
    }

    public void saveNewSchedule(Schedule schedule) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT, schedule.getmSubject());
        values.put(COLUMN_PREPOD, schedule.getmPrepod());
        values.put(COLUMN_ROOM, schedule.getmRoom());
        values.put(COLUMN_TIME_FROM, schedule.getmTimeFrom());
        values.put(COLUMN_TIME_TILL, schedule.getmTimeTill());

        db.insert(DATABASE_NAME,null, values);
        db.close();
    }

    public List<Schedule> scheduleList(String filter){
        String query;
        if(filter.equals("")){
            query = "SELECT * FROM " + DATABASE_NAME;
        }else{
            query = "SELECT * FROM " + DATABASE_NAME + " ORDER BY " + filter;
        }
        List<Schedule> scheduleLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Schedule schedule;

        if(cursor.moveToFirst()){
            do {
                schedule = new Schedule();

                schedule.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                schedule.setSubject(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT)));
                schedule.setPrepod(cursor.getString(cursor.getColumnIndex(COLUMN_PREPOD)));
                schedule.setRoom(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM)));
                schedule.setTimeFrom(cursor.getString(cursor.getColumnIndex(COLUMN_TIME_FROM)));
                schedule.setTimeTill(cursor.getString(cursor.getColumnIndex(COLUMN_TIME_TILL)));
                scheduleLinkedList.add(schedule);
            }while(cursor.moveToNext());
        }
        return scheduleLinkedList;
    }

    public Schedule getSchedule(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DATABASE_NAME + " WHERE _id=" + id;
        Cursor cursor = db.rawQuery(query, null);

        Schedule receivedSchedule = new Schedule();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            receivedSchedule.setSubject(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT)));
            receivedSchedule.setPrepod(cursor.getString(cursor.getColumnIndex(COLUMN_PREPOD)));
            receivedSchedule.setRoom(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM)));
            receivedSchedule.setTimeFrom(cursor.getString(cursor.getColumnIndex(COLUMN_TIME_FROM)));
            receivedSchedule.setTimeTill(cursor.getString(cursor.getColumnIndex(COLUMN_TIME_TILL)));
        }
        return receivedSchedule;
    }

    public void deleteSchedule(long id, Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + DATABASE_NAME + " WHERE _id='" + id + "'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();
    }

    public void updateSchedule(long scheduleId, Context context, Schedule updatedSchedule){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + DATABASE_NAME + " SET SUBJECT ='" + updatedSchedule.getmSubject() + "', PREPOD ='" + updatedSchedule.getmPrepod() + "', ROOM ='" + updatedSchedule.getmRoom() + "', TIME_FROM ='" + updatedSchedule.getmTimeFrom() + "', TIME_TILL ='" + updatedSchedule.getmTimeTill() + "' WHERE _id'" + scheduleId + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();
    }
}