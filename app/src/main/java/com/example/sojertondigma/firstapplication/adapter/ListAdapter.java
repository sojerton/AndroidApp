package com.example.sojertondigma.firstapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sojertondigma.firstapplication.DBHelper;
import com.example.sojertondigma.firstapplication.R;
import com.example.sojertondigma.firstapplication.Schedule;
import com.example.sojertondigma.firstapplication.UpdateLessonActivity;

import java.util.Collection;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    protected LayoutInflater inflater;
    private List<Schedule> mScheduleList;

    /*
        public class ViewHolder extends ListView.ViewHolder {

            public TextView subjectTextView;
            public TextView prepodTextView;
            public TextView roomTextView;
            public TextView timeFromTextView;
            public TextView timeTillTextView;

            public View layout;

            public ViewHolder(View itemView) {
                super(itemView);
                layout = itemView;
                subjectTextView = itemView.findViewById(R.id.subject);
                //prepodTextView = itemView.findViewById(R.id.prepod);
                roomTextView = itemView.findViewById(R.id.room);
                timeFromTextView = itemView.findViewById(R.id.timeFrom);
                timeTillTextView = itemView.findViewById(R.id.timeTill);
            }
        }
    */
    public void add(Collection<Schedule> schedule) {
        mScheduleList.addAll(schedule);
        notifyDataSetChanged();
    }

    public void remove(final int position) {
        final Schedule schedule = mScheduleList.get(position);
        DBHelper dbHelper = new DBHelper(mContext);
        dbHelper.deleteSchedule(schedule.getId(), mContext);
        mScheduleList.remove(position);
        dbHelper.close();
    }

    public void update(final int position) {
        final Schedule schedule = mScheduleList.get(position);
        goToUpdateActivity(schedule.getId());
    }

    public ListAdapter(Context context, List<Schedule> myDataset) {
        mContext = context;
        mScheduleList = myDataset;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.display_schedule_view, parent, false);
        }
        final Schedule schedule = mScheduleList.get(position);
        final String[] prepod = schedule.getmPrepod().split(" ");

        TextView mSubject = v.findViewById(R.id.subject);
        ImageButton mPrepod = v.findViewById(R.id.prepodBtn);
        TextView mRoom = v.findViewById(R.id.room);
        TextView mTimeFrom = v.findViewById(R.id.timeFrom);
        TextView mTimeTill = v.findViewById(R.id.timeTill);

        mSubject.setText(schedule.getmSubject());
        mRoom.setText(schedule.getmRoom());
        mTimeFrom.setText(schedule.getmTimeFrom());
        mTimeTill.setText(schedule.getmTimeTill());
        mPrepod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View mView = inflater.inflate(R.layout.dialog_prepod, null);
                final ImageView mPrepodImage = mView.findViewById(R.id.prepodImage);
                final TextView mFirstName = mView.findViewById(R.id.firstName);
                final TextView mLastName = mView.findViewById(R.id.lastName);
                final TextView mOtchestvo = mView.findViewById(R.id.otchestvo);

                try {
                    mFirstName.setText(prepod[0]);
                    mLastName.setText(prepod[1]);
                    mOtchestvo.setText(prepod[2]);
                } catch (Exception e) {}

                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return v;
    }
/*
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Schedule schedule = mScheduleList.get(position);
        holder.subjectTextView.setText(schedule.getmSubject());
        //holder.prepodTextView.setText(schedule.getmPrepod());
        holder.roomTextView.setText(schedule.getmRoom());
        holder.timeFromTextView.setText(schedule.getmTimeFrom());
        holder.timeTillTextView.setText(schedule.getmTimeTill());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Confirmation.");
                builder.setMessage("Are your sure you want to delete this schedule?");
                builder.setPositiveButton("Cacnel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToUpdateActivity(schedule.getId());
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbHelper = new DBHelper(mContext);
                        dbHelper.deleteSchedule(schedule.getId(), mContext);

                        mScheduleList.remove(position);
                        mRecyclerView.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mScheduleList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.create().show();
            }
        });
    }
*/

    private void goToUpdateActivity(long scheduleId) {
        Intent goToUpdate = new Intent(mContext, UpdateLessonActivity.class);
        goToUpdate.putExtra("USER_ID", scheduleId);
        mContext.startActivity(goToUpdate);
    }

    @Override
    public Schedule getItem(int position) {
        return mScheduleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return mScheduleList.size();
    }
}