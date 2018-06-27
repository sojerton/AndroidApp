package com.example.sojertondigma.firstapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sojertondigma.firstapplication.R;
import com.example.sojertondigma.firstapplication.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Schedule> scheduleList = new ArrayList<>();

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView subjectTextView;
        private TextView prepodTextView;
        private TextView roomTextView;
        private TextView timeFromTextView;
        private TextView timeTillTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            subjectTextView = itemView.findViewById(R.id.subject);
            prepodTextView = itemView.findViewById(R.id.prepod);
            roomTextView = itemView.findViewById(R.id.room);
            timeFromTextView = itemView.findViewById(R.id.timeFrom);
            timeTillTextView = itemView.findViewById(R.id.timeTill);
        }

        public void bind(Schedule schedule) {
            subjectTextView.setText(schedule.getmSubject());
            prepodTextView.setText(schedule.getmPrepod());
            roomTextView.setText(schedule.getmRoom());
            timeFromTextView.setText(String.valueOf(schedule.getmTimeFrom()));
            timeTillTextView.setText(String.valueOf(schedule.getmTimeTill()));
        }
    }

    public void setItems(Collection<Schedule> schedules) {
        scheduleList.addAll(schedules);
        notifyDataSetChanged();
    }

    public void clearItems() {
        scheduleList.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_schedule_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(scheduleList.get(position));
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }
}
