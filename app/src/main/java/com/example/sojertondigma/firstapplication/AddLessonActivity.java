package com.example.sojertondigma.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddLessonActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "lifecycle";
    private ImageButton mAddLessonCloseBtn;
    private Button mAddedBtn;
    private EditText editTextSubject;
    private EditText editTextPrepod;
    private EditText editTextRoom;
    private EditText editTextTimeFrom;
    private EditText editTextTimeTill;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        mAddLessonCloseBtn = findViewById(R.id.addLessonCloseBtn);
        mAddLessonCloseBtn.setEnabled(true);
        mAddLessonCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAddedBtn = findViewById(R.id.addedBtn);
        mAddedBtn.setOnClickListener(this);

        Log.d(TAG, "AddLessonActivity onCreate");
    }

    @Override
    public void onClick(View v) {
        String mSubject = editTextSubject.getText().toString().trim();
        String mPrepod = editTextPrepod.getText().toString().trim();
        String mRoom = editTextRoom.getText().toString().trim();
        String mTimeFrom = editTextTimeFrom.getText().toString().trim();
        String mTimeTill = editTextTimeTill.getText().toString().trim();
        dbHelper = new DBHelper(this);
        if(mSubject.isEmpty()){
            Toast.makeText(this, "You must enter a subject", Toast.LENGTH_SHORT).show();
        }

        if(mPrepod.isEmpty()){
            Toast.makeText(this, "You must enter a teacher", Toast.LENGTH_SHORT).show();
        }

        if(mRoom.isEmpty()){
            Toast.makeText(this, "You must enter a classroom", Toast.LENGTH_SHORT).show();
        }

        if(mTimeFrom.isEmpty()){
            Toast.makeText(this, "You must enter a time", Toast.LENGTH_SHORT).show();
        }

        if(mTimeTill.isEmpty()){
            Toast.makeText(this, "You must enter a time", Toast.LENGTH_SHORT).show();
        }
        Schedule schedule = new Schedule(mSubject, mPrepod, mRoom, mTimeFrom, mTimeTill);
        dbHelper.saveNewSchedule(schedule);
        goBackHome();
    }

    public void goBackHome(){
        startActivity(new Intent(AddLessonActivity.this, MainActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.addLessonCloseBtn:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
