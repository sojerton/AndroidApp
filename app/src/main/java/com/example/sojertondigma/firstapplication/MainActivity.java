package com.example.sojertondigma.firstapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.annotation.Nullable;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    final String TAG = "lifecycle";
    Button myBtn;
    private Toolbar toolbar;
    TextView subject, prepod, room, timeFrom, timeTill;
    SharedPreferences sPref;

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

        loadText();
        Log.d(TAG, "MainActivity onCreate");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent == null) {return;}
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

        item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        linLayoutV.addView(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void saveText(){
        if(subject!=null) {
            sPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString("SUBJECT", subject.getText().toString());
            ed.putString("PREPOD", prepod.getText().toString());
            ed.putString("ROOM", room.getText().toString());
            ed.putString("TIME_FROM", timeFrom.getText().toString());
            ed.putString("TIME_TILL", timeTill.getText().toString());
            ed.commit();
        }
    }

    void loadText(){
        sPref = getPreferences(MODE_PRIVATE);
        //String savedText = sPref.getString("SUBJECT", "unknown");
        //subject.setText(savedText);

        LinearLayout linLayoutV = (LinearLayout) findViewById(R.id.linLayoutV);
        LayoutInflater ltInflater = getLayoutInflater();
        View item = ltInflater.inflate(R.layout.display_schedule_view, linLayoutV, false);

        String savedSubject = sPref.getString("SUBJECT", "unknown");
        subject = (TextView) item.findViewById(R.id.subject);
        subject.setText(savedSubject);

        String savedPrepod = sPref.getString("PREPOD", "unknown");
        prepod = (TextView) item.findViewById(R.id.prepod);
        prepod.setText(savedPrepod);

        String savedRoom = sPref.getString("ROOM", "unknown");
        room = (TextView) item.findViewById(R.id.room);
        room.setText(savedRoom);

        String savedTimeFrom = sPref.getString("TIME_FROM", "unknown");
        timeFrom = (TextView) item.findViewById(R.id.timeFrom);
        timeFrom.setText(savedTimeFrom);

        String savedTimeTill = sPref.getString("TIME_TILL", "unknown");
        timeTill = (TextView) item.findViewById(R.id.timeTill);
        timeTill.setText(savedTimeTill);

        item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        linLayoutV.addView(item);
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
        saveText();
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
        if (id == R.id.create_class){

        }else if (id == R.id.found_class){

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
