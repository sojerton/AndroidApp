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

public class AddLessonActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "lifecycle";
    private ImageButton mAddLessonCloseBtn;
    private Button mAddedBtn;

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

        EditText editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextSubject.requestFocus();

        Log.d(TAG, "AddLessonActivity onCreate");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        intent.putExtra("subject", editTextSubject.getText().toString());
        EditText editTextPrepod = (EditText) findViewById(R.id.editTextPrepod);
        intent.putExtra("prepod", editTextPrepod.getText().toString());
        EditText editTextRoom = (EditText) findViewById(R.id.editTextRoom);
        intent.putExtra("room", editTextRoom.getText().toString());
        EditText editTextTimeFrom = (EditText) findViewById(R.id.editTextTimeFrom);
        intent.putExtra("timeFrom", editTextTimeFrom.getText().toString());
        EditText editTextTimeTil = (EditText) findViewById(R.id.editTextTimeTill);
        intent.putExtra("timeTill", editTextTimeTil.getText().toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        setResult(RESULT_OK, intent);
        finish();
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

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "AddLessonActivity onRestart");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "AddLessonActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "AddLessonActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "AddLessonActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "AddLessonActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "AddLessonActivity onDestroy");
    }

}
