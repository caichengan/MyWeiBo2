package com.xht.android.myweibo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xht.android.myweibo.R;

/**
 * Created by an on 2017/3/19.
 */

public  final class picItemFragment extends Fragment {


        private static final String KEY_CONTENT = "TestFragment:Content";
        private String mContent = "";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
                mContent = savedInstanceState.getString(KEY_CONTENT);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


            //动态找到布局文件，再从这个布局中find出TextView对象
            View contextView = inflater.inflate(R.layout.item_viewpager, container, false);
            ImageView mImageView = (ImageView) contextView.findViewById(R.id.loadViewPager);

            //获取Activity传递过来的参数
            Bundle mBundle = getArguments();
            String picOne = mBundle.getString("arg");

            if (picOne.contains("thumbnail")){
                picOne= picOne.replace("thumbnail","bmiddle");
            }

            Glide.with(this).load(picOne).placeholder(R.mipmap.ic_launcher).crossFade().into(mImageView);

            return contextView;


        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }


        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putString(KEY_CONTENT, mContent);
        }
    }

