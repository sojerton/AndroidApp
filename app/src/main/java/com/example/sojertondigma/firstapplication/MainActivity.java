package com.example.sojertondigma.firstapplication;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.sojertondigma.firstapplication.adapter.RecyclerAdapter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    final String TAG = "lifecycle";
    Button myBtn;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter recyclerAdapter;
    private DBHelper dbHelper;
    private String filter = "";
    TextView subject, prepod, room, timeFrom, timeTill;


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

        recyclerView = findViewById(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        populateRecyclerView();

        Log.d(TAG, "MainActivity onCreate");
    }

    private void populateRecyclerView(){
        dbHelper = new DBHelper(this);
        recyclerAdapter = new RecyclerAdapter(dbHelper.scheduleList(), this, recyclerView);
        recyclerView.setAdapter(recyclerAdapter);
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
/*
    void saveText() {
        if (subject != null) {
            Log.d(LOG_TAG, "--- Insert in savelesson: ---");
            dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("COLUMN_SUBJECT", subject.getText().toString());
            cv.put("COLUMN_PREPOD", prepod.getText().toString());
            cv.put("COLUMN_ROOM", room.getText().toString());
            cv.put("COLUMN_TIME_FROM", timeFrom.getText().toString());
            cv.put("COLUMN_TIME_TILL", timeTill.getText().toString());
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
                LinearLayout linLayoutV = (LinearLayout) findViewById(R.id.recycler_view);
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
*/
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
        recyclerAdapter.notifyDataSetChanged();
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
        startActivity(intent);
    }

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

}

