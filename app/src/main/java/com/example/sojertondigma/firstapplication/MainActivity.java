package com.example.sojertondigma.firstapplication;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView myTextView = findViewById(R.id.text_veiw);
        //myTextView.setText("Hello");

        Button myBtn = findViewById(R.id.btnAddLesson);
        myBtn.setEnabled(true);

        CheckBox myChb = findViewById(R.id.chb);
        myChb.setChecked(true);

        setTitle("Расписание");
        //ActionBar bar = getActionBar();
        //bar.setBackgroundDrawable(new ColorDrawable(R.color.red));

    }


}
