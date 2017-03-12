package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.xht.android.myweibo.R;
import com.xht.android.myweibo.utils.IntentUtils;

/**
 * Created by Administrator on 2017/2/23.
 *
 * 加载url的微博主文
 */
public class UrlBlogActivity extends Activity{
    private WebView webUrl;
    private ImageView homeBack;
    private ProgressDialog mProgDoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_url);
        webUrl = (WebView) findViewById(R.id.webUrl);
      //  homeBack = (ImageView) findViewById(R.id.homeBack);
        String mURLString = getIntent().getBundleExtra("bundle").getString("URL");
        String mRETString = getIntent().getBundleExtra("bundle").getString("RET");

        if (!TextUtils.isEmpty(mURLString))
        {
            webUrl.loadUrl(mURLString);
        }else{
            webUrl.loadUrl(mRETString);
        }

        WebSettings webSettings = webUrl.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
         webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webUrl.setWebViewClient(new WebViewClient() {
        });

       /* homeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivityInfo(UrlBlogActivity.this,MainActivity.class);
                finish();
            }
        });
*/
        }
    /**
     * 创建对话框
     *
     * @param title
     */
    private void createProgressDialog(String title) {
        if (mProgDoal == null) {
            mProgDoal = new ProgressDialog(this);
        }
        mProgDoal.setTitle(title);
        mProgDoal.setIndeterminate(true);
        mProgDoal.setCancelable(false);
        mProgDoal.show();
    }
    /**
     * 隐藏对话框
     */
    private void dismissProgressDialog() {
        if (mProgDoal != null) {
            mProgDoal.dismiss();
            mProgDoal = null;
        }
    }


   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);

    }*/
}


