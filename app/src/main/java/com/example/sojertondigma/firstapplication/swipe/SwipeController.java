package com.example.sojertondigma.firstapplication.swipe;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import com.example.sojertondigma.firstapplication.adapter.ListAdapter;
import com.example.sojertondigma.firstapplication.DBHelper;
import com.example.sojertondigma.firstapplication.R;

import swipemenulistview.SwipeMenu;
import swipemenulistview.SwipeMenuCreator;
import swipemenulistview.SwipeMenuItem;
import swipemenulistview.SwipeMenuListView;
import swipemenulistview.SwipeMenuView;

public class SwipeController extends Fragment {

    private ListAdapter listAdapter;
    private SwipeMenuListView listView;
    private DBHelper dbHelper;
    private View mVeiw;


    public SwipeController() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        mVeiw = v;
        viewInit(mVeiw);
        return v;
    }

    @Override
    public void onResume(){
        viewInit(mVeiw);
        super.onResume();
    }

    public void viewInit(View v){
        dbHelper = new DBHelper(v.getContext());
        listView = v.findViewById(R.id.list_view);
        listAdapter = new ListAdapter(getActivity(), dbHelper.scheduleList());
        listView.setAdapter(listAdapter);
        dbHelper.close();
        listView.setOpenInterpolator(new BounceInterpolator());
        listView.setCloseInterpolator(new BounceInterpolator());
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item1.setBackground(R.color.secondary_text);
                item1.setWidth(dp2px(90));
                item1.setTitle("UPDATE");
                //item1.setText(Html.fromHtml(getString(R.string.note)));
                item1.setTitleColor(Color.WHITE);
                item1.setTitleSize(15);
                menu.setStrechMode(SwipeMenu.SwipeStrechMode.SWIPE_STRECH_MODE_BOTH);
                menu.addLeftMenuItem(item1);

                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item2.setBackground(R.color.colorAccent);
                item2.setWidth(dp2px(70));
                item2.setIcon(R.mipmap.ic_delete);
                menu.setStrechMode(SwipeMenu.SwipeStrechMode.SWIPE_STRECH_MODE_BOTH);
                menu.addRightMenuItem(item2);

            }
        };
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final int position, boolean isRightMenu, SwipeMenu menu, SwipeMenuItem menuItem) {
                if(isRightMenu){
                    listAdapter.remove(position);
                    listAdapter.notifyDataSetChanged();
                }else{
                    listAdapter.update(position);
                }
                return false;
            }
        });

        listView.setmOnStrechEndCalledListener(new SwipeMenuView.OnStrechEndCalledListener() {
            @Override
            public void onMenuItemStrechEndCalled(int position, boolean isRightMenu, SwipeMenu menu, SwipeMenuItem menuItem) {
                if(!isRightMenu){
                    listAdapter.remove(position);
                    listAdapter.notifyDataSetChanged();
                }else{
                    listAdapter.update(position);
                }
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}