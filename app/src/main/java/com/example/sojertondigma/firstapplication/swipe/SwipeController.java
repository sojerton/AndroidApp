package com.example.sojertondigma.firstapplication.swipe;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.support.v7.widget.RecyclerView;

import static android.support.v7.widget.helper.ItemTouchHelper.*;

public class SwipeController extends Callback {

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(3, LEFT | RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
