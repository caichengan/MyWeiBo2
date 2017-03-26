package com.xht.android.myweibo.mode;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 * Created by an on 2017/3/19.
 */


  public  class  ViewPagerAdapter  extends FragmentPagerAdapter {


        private Context mContext;
        private int position;
        private ArrayList<String> picLists;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}