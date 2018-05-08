package com.example.sojertondigma.firstapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "lifecycle";
    Button myBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView myTextView = findViewById(R.id.text_veiw);
        //myTextView.setText("Hello");

        myBtn = findViewById(R.id.btnAddLesson);
        myBtn.setEnabled(true);

        myBtn.setOnClickListener(this);

        CheckBox myChb = findViewById(R.id.chb);
        myChb.setChecked(true);

        setTitle("Расписание");
        //REQUIRES API 21 LEVEL
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);


        //ActionBar bar = getActionBar();
        //bar.setBackgroundDrawable(new ColorDrawable(R.color.red));

        Log.d(TAG, "MainActivity onCreate");
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
        startActivity(intent);
    }

}
