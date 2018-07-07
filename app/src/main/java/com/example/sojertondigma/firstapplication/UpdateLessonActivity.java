package com.example.sojertondigma.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class UpdateLessonActivity extends AppCompatActivity {
    private Button mUpdateBtn;
    private ImageButton mUpdateLessonCloseBtn;
    private EditText editTextSubject;
    private EditText editTextPrepod;
    private EditText editTextRoom;
    private EditText editTextTimeFrom;
    private EditText editTextTimeTill;
    private DBHelper dbHelper;
    private long receivedScheduleId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lesson);

        editTextSubject = findViewById(R.id.editTextSubjectUpdate);
        editTextPrepod = findViewById(R.id.editTextPrepodUpdate);
        editTextRoom = findViewById(R.id.editTextRoomUpdate);
        editTextTimeFrom = findViewById(R.id.editTextTimeFromUpdate);
        editTextTimeTill = findViewById(R.id.editTextTimeTillUpdate);
        mUpdateBtn = findViewById(R.id.updatedBtn);
        mUpdateLessonCloseBtn = findViewById(R.id.updateLessonCloseBtn);

        dbHelper = new DBHelper(this);

        try {
            receivedScheduleId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Schedule queriedSchedule = dbHelper.getSchedule(receivedScheduleId);

        editTextSubject.setText(queriedSchedule.getmSubject());
        editTextPrepod.setText(queriedSchedule.getmPrepod());
        editTextRoom.setText(queriedSchedule.getmRoom());
        editTextTimeFrom.setText(queriedSchedule.getmTimeFrom());
        editTextTimeTill.setText(queriedSchedule.getmTimeTill());

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSchedule();
            }
        });

        mUpdateLessonCloseBtn.setEnabled(true);
        mUpdateLessonCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void updateSchedule() {
        String subject = editTextSubject.getText().toString().trim();
        String prepod = editTextPrepod.getText().toString().trim();
        String room = editTextRoom.getText().toString().trim();
        String timeFrom = editTextTimeFrom.getText().toString().trim();
        String timeTill = editTextTimeTill.getText().toString().trim();

        if (subject.isEmpty()) {
            Toast.makeText(this, "Необходимо указать предмет", Toast.LENGTH_SHORT).show();
        }

        if (prepod.isEmpty()) {
            Toast.makeText(this, "Необходимо указать учителя", Toast.LENGTH_SHORT).show();
        }

        if (room.isEmpty()) {
            Toast.makeText(this, "Необходимо указать кабинет", Toast.LENGTH_SHORT).show();
        }

        if (timeFrom.isEmpty()) {
            Toast.makeText(this, "Необходимо указать время", Toast.LENGTH_SHORT).show();
        }

        if (timeTill.isEmpty()) {
            Toast.makeText(this, "Необходимо указать время", Toast.LENGTH_SHORT).show();
        }

        Schedule updateSchedule = new Schedule(subject, prepod, room, timeFrom, timeTill);
        dbHelper.updateSchedule(receivedScheduleId, this, updateSchedule);
        goBackHome();
    }

    private void goBackHome() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
