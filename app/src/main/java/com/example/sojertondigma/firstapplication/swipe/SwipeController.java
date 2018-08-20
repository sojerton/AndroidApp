package com.example.sojertondigma.firstapplication.swipe;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.sojertondigma.firstapplication.MainActivity;
import com.example.sojertondigma.firstapplication.Schedule;
import com.example.sojertondigma.firstapplication.adapter.ListAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.example.sojertondigma.firstapplication.DBHelper;
import com.example.sojertondigma.firstapplication.R;

import java.util.List;

import swipemenulistview.SwipeMenu;
import swipemenulistview.SwipeMenuCreator;
import swipemenulistview.SwipeMenuItem;
import swipemenulistview.SwipeMenuListView;
import swipemenulistview.SwipeMenuView;

public class SwipeController extends Fragment {

    private ListAdapter listAdapter;
    private SwipeMenuListView listView;
    private DBHelper dbHelper;

    public SwipeController() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        dbHelper = new DBHelper(v.getContext());
        listView = v.findViewById(R.id.list_view);
        listAdapter = new ListAdapter(getActivity(), dbHelper.scheduleList());
        listView.setAdapter(listAdapter);
        listView.setOpenInterpolator(new BounceInterpolator());
        listView.setCloseInterpolator(new BounceInterpolator());
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18,
                        0x5E)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.mipmap.ic_delete);
                menu.setStrechMode(SwipeMenu.SwipeStrechMode.SWIPE_STRECH_MODE_BOTH);
                menu.addLeftMenuItem(item1);

                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                item2.setWidth(dp2px(90));
                item2.setIcon(R.mipmap.ic_delete);
                menu.setStrechMode(SwipeMenu.SwipeStrechMode.SWIPE_STRECH_MODE_BOTH);
                menu.addRightMenuItem(item2);

            }
        };
        listView.setMenuCreator(creator);
        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, boolean isRightMenu, SwipeMenu menu, SwipeMenuItem menuItem) {
                //ApplicationInfo item = mAppList.get(position);
                Log.d(SwipeController.this.getClass().getName(), "current click item position is = " + position + ", is right menu = " + isRightMenu + ", menu item id is " + menuItem.getId());
                return false;
            }
        });

        listView.setmOnStrechEndCalledListener(new SwipeMenuView.OnStrechEndCalledListener() {
            @Override
            public void onMenuItemStrechEndCalled(int position, boolean isRightMenu, SwipeMenu menu, SwipeMenuItem menuItem) {
                Log.d(SwipeController.this.getClass().getName(), "current Strech item position is = " + position + ", is right menu = " + isRightMenu + ", menu item id is " + menuItem.getId());
            }
        });

        return v;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}