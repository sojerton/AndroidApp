package com.example.sojertondigma.firstapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScheduleDisplay extends LinearLayout {

    private TextView mTimeFrom, mTimeTill, mSubject, mPrepod, mRoom;

    public ScheduleDisplay(Context context) {
        super(context);
        mSubject = new TextView(context);
        mPrepod = new TextView(context);


        addView(mSubject);
        addView(mPrepod);

        //initializeViews(context);
    }

    public ScheduleDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public ScheduleDisplay(Context context,
                       AttributeSet attrs,
                       int defStyle) {
        super(context, attrs, defStyle);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.display_schedule_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mTimeFrom = (TextView) this
                .findViewById(R.id.timeFrom);

    }

}
