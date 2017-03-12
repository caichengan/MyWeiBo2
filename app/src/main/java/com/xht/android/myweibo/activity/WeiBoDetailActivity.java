package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.xht.android.myweibo.R;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.LogHelper;

/**
 * Created by an on 2017/3/12.
 */
public class WeiBoDetailActivity extends Activity{
    private static final String TAG = "WeiBoDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weibo_detail);

        long id = getIntent().getBundleExtra("bundle").getLong("id");

        LogHelper.i(TAG,"---id--"+id);

        getWeiBoDetail(id);

    }

    /**
     * 获取微博详情页面信息
     * @param id
     */
    private void getWeiBoDetail(long id) {

        NetWorkHelper.getInstance(this).getWeiBoDetailDatas(id, new INetListener() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(String result) {

            }
        });

    }
}
