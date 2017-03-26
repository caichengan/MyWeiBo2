package com.xht.android.myweibo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.utils.LogHelper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by an on 2017/3/2.
 */
public class LoadOneImgActivity extends FragmentActivity {
    private static final String TAG = "LoadImgActivity";
    @InjectView(R.id.load)
    ImageView loadImg;
    private ArrayList<String> picLists;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadone);
        ButterKnife.inject(this);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String urlImg = bundle.getString("url");
        String type = bundle.getString("type");
        LogHelper.i(TAG, "---------" + urlImg);

        position = bundle.getInt("position");
        if (urlImg.contains("thumbnail")){
            urlImg=urlImg.replace("thumbnail","bmiddle");
        }
        Glide.with(this)
                .load(urlImg)
                .placeholder(R.mipmap.p_head_fail)
                .crossFade()
                .into(loadImg);
    }
}
