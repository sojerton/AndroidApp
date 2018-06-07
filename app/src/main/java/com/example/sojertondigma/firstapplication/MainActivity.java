package com.example.sojertondigma.firstapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, View.OnLongClickListener {

    final String TAG = "lifecycle";
    final String LOG_TAG = "data";
    Button myBtn, deleteBtn, delBtn;
    private Toolbar toolbar;
    TextView subject, prepod, room, timeFrom, timeTill;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.bar_main_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Расписание");

        myBtn = findViewById(R.id.btnAddLesson);
        myBtn.setEnabled(true);
        myBtn.setOnClickListener(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dbHelper = new DBHelper(this);

        //deleteBtn.setOnLongClickListener(this);
        //delBtn.setId(deleteBtn.getId());
        // delBtn.setEnabled(true);
        //delBtn.setOnLongClickListener(this);
        loadText();

        Log.d(TAG, "MainActivity onCreate");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent == null) {
            return;
        }
        LinearLayout linLayoutV = (LinearLayout) findViewById(R.id.linLayoutV);
        LayoutInflater ltInflater = getLayoutInflater();
        View item = ltInflater.inflate(R.layout.display_schedule_view, linLayoutV, false);

        String sSubject = intent.getStringExtra("subject");
        subject = (TextView) item.findViewById(R.id.subject);
        subject.setText(sSubject);

        String sPrepod = intent.getStringExtra("prepod");
        prepod = (TextView) item.findViewById(R.id.prepod);
        prepod.setText(sPrepod);

        String sRoom = intent.getStringExtra("room");
        room = (TextView) item.findViewById(R.id.room);
        room.setText(sRoom);

        String sTimeFrom = intent.getStringExtra("timeFrom");
        timeFrom = (TextView) item.findViewById(R.id.timeFrom);
        timeFrom.setText(sTimeFrom);

        String sTimeTill = intent.getStringExtra("timeTill");
        timeTill = (TextView) item.findViewById(R.id.timeTill);
        timeTill.setText(sTimeTill);

        deleteBtn = new Button(this);
        saveText();
        item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        linLayoutV.addView(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void saveText() {
        if (subject != null) {
            Log.d(LOG_TAG, "--- Insert in savelesson: ---");
            dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("SUBJECT", subject.getText().toString());
            cv.put("PREPOD", prepod.getText().toString());
            cv.put("ROOM", room.getText().toString());
            cv.put("TIME_FROM", timeFrom.getText().toString());
            cv.put("TIME_TILL", timeTill.getText().toString());
            long rowID = db.insert("savelesson", null, cv);
            Integer id = (int) (long) rowID;
            deleteBtn.setId(id);
            deleteBtn.setEnabled(true);
            deleteBtn.setOnLongClickListener(this);
            Log.d(LOG_TAG, "row inserted, ID = " + rowID);
            db.close();
            dbHelper.close();
        }
    }

    void loadText() {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("savelesson", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int subjectColIndex = c.getColumnIndex("SUBJECT");
            int prepodColIndex = c.getColumnIndex("PREPOD");
            int roomColIndex = c.getColumnIndex("ROOM");
            int timeFromColIndex = c.getColumnIndex("TIME_FROM");
            int timeTillColIndex = c.getColumnIndex("TIME_TILL");
            do {
                Log.d(LOG_TAG, "ID = " + c.getInt(idColIndex));
                LinearLayout linLayoutV = (LinearLayout) findViewById(R.id.linLayoutV);
                LayoutInflater ltInflater = getLayoutInflater();
                View item = ltInflater.inflate(R.layout.display_schedule_view, linLayoutV, false);

                String savedSubject = c.getString(subjectColIndex);
                subject = (TextView) item.findViewById(R.id.subject);
                subject.setText(savedSubject);

                String savedPrepod = c.getString(prepodColIndex);
                prepod = (TextView) item.findViewById(R.id.prepod);
                prepod.setText(savedPrepod);

                String savedRoom = c.getString(roomColIndex);
                room = (TextView) item.findViewById(R.id.room);
                room.setText(savedRoom);

                String savedTimeFrom = c.getString(timeFromColIndex);
                timeFrom = (TextView) item.findViewById(R.id.timeFrom);
                timeFrom.setText(savedTimeFrom);

                String savedTimeTill = c.getString(timeTillColIndex);
                timeTill = (TextView) item.findViewById(R.id.timeTill);
                timeTill.setText(savedTimeTill);

                item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                linLayoutV.addView(item);

            } while (c.moveToNext());
        } else c.close();
        db.close();
        dbHelper.close();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MainActivity onRestart");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();


        Log.d(TAG, "MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();


        Log.d(TAG, "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity onDestroy");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddLessonActivity.class);
        startActivityForResult(intent, 1);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.create_class) {

        } else if (id == R.id.found_class) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onLongClick(View v) {

        int id = deleteBtn.getId();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("savelesson", "id = " + id, null);
        db.close();
        return true;
    }
}

