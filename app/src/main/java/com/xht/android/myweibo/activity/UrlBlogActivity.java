package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import com.xht.android.myweibo.R;
/**
 * Created by Administrator on 2017/2/23.
 */
public class UrlBlogActivity extends Activity{
    private WebView webUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_url);
        webUrl = (WebView) findViewById(R.id.webUrl);
        String mURLString = getIntent().getBundleExtra("bundle").getString("URL");
        webUrl.loadUrl(mURLString);


        }
}


