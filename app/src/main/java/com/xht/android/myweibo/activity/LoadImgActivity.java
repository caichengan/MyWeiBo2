package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.utils.LogHelper;

/**
 * Created by an on 2017/3/2.
 */

public class LoadImgActivity extends Activity {
    private static final String TAG = "LoadImgActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loadimg);
        String urlImg = getIntent().getBundleExtra("bundle").getString("url");
        LogHelper.i(TAG,"---------"+urlImg);
        ImageView loadImg= (ImageView) findViewById(R.id.loadImg);
        Glide.with(this)
                .load(urlImg)
                .placeholder(R.mipmap.p_head_fail)
                .crossFade()
                .into(loadImg);
    }
}
