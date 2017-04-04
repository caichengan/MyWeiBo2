package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.fragment.picItemFragment;
import com.xht.android.myweibo.mode.ViewPagerAdapter;
import com.xht.android.myweibo.utils.LogHelper;

import java.util.ArrayList;

/**
 * Created by an on 2017/3/2.
 */
public class LoadImgActivity extends FragmentActivity {
    private static final String TAG = "LoadImgActivity";
    private ArrayList<String> picLists;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadimg);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String urlImg = bundle.getString("url");
        LogHelper.i(TAG,"---------"+urlImg);

        position = bundle.getInt("position");
        picLists = bundle.getStringArrayList("picLists");
        //ImageView loadImg= (ImageView) findViewById(R.id.loadImg);
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
        FragmentPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
    class  ViewPagerAdapter  extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int pos) {
            //新建一个Fragment来展示ViewPager item的内容，并传递参数
            Fragment fragment = new picItemFragment();
            Bundle args = new Bundle();
            args.putString("arg", picLists.get(pos));
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public int getCount() {
            return picLists.size();
        }
    }
}
