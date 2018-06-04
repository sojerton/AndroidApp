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
import android.widget.TextView;


public class AddLessonActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.sojertondigma.firstapplication.MESSAGE";
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

       /* mAddedBtn = findViewById(R.id.addedBtn);
        mAddedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TextView addTimeFrom = (TextView) mTimeFrom.getText();
                //mSubject = findViewById(R.id.editTextSubject);
                //TextView Subject = (TextView) mSubject.getText();


                finish();
            }
        });*/


        Log.d(TAG, "AddLessonActivity onCreate");
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, MainActivity.class);

        EditText editText = (EditText) findViewById(R.id.editTextSubject);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
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
