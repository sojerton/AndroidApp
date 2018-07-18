package com.example.sojertondigma.firstapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sojertondigma.firstapplication.DBHelper;
import com.example.sojertondigma.firstapplication.R;
import com.example.sojertondigma.firstapplication.Schedule;
import com.example.sojertondigma.firstapplication.UpdateLessonActivity;

import java.util.Collection;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<Schedule> mScheduleList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView subjectTextView;
        public TextView prepodTextView;
        public TextView roomTextView;
        public TextView timeFromTextView;
        public TextView timeTillTextView;
        public Button deleteBtn;

        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            subjectTextView = itemView.findViewById(R.id.subject);
            //prepodTextView = itemView.findViewById(R.id.prepod);
            roomTextView = itemView.findViewById(R.id.room);
            timeFromTextView = itemView.findViewById(R.id.timeFrom);
            timeTillTextView = itemView.findViewById(R.id.timeTill);
            //deleteBtn = itemView.findViewById(R.id.deleteSchedule);
        }
    }

    public void add(Collection<Schedule> schedule) {
        mScheduleList.addAll(schedule);
        notifyDataSetChanged();
    }

    public void remove(final int position) {
        final Schedule schedule = mScheduleList.get(position);
        DBHelper dbHelper = new DBHelper(mContext);
        dbHelper.deleteSchedule(schedule.getId(), mContext);

        mScheduleList.remove(position);
        mRecyclerView.removeViewAt(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mScheduleList.size());
        notifyDataSetChanged();

        //mScheduleList.remove(position);
        //notifyItemRemoved(position);
    }

    public RecyclerAdapter(List<Schedule> myDataset, Context context, RecyclerView recyclerView) {
        mScheduleList = myDataset;
        mContext = context;
        mRecyclerView = recyclerView;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.display_schedule_view, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Schedule schedule = mScheduleList.get(position);
        holder.subjectTextView.setText(schedule.getmSubject());
        //holder.prepodTextView.setText(schedule.getmPrepod());
        holder.roomTextView.setText(schedule.getmRoom());
        holder.timeFromTextView.setText(schedule.getmTimeFrom());
        holder.timeTillTextView.setText(schedule.getmTimeTill());

        /*holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
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
        });*/
    }


    private void goToUpdateActivity(long scheduleId) {
        Intent goToUpdate = new Intent(mContext, UpdateLessonActivity.class);
        goToUpdate.putExtra("USER_ID", scheduleId);
        mContext.startActivity(goToUpdate);
    }


    @Override
    public int getItemCount() {
        return mScheduleList.size();
    }
}
