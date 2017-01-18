package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;

/**
 * Created by Administrator on 2017/1/12.
 */

public class SplashActivity extends Activity {

    private static final String TAG = "SplashActivity";

    private SharedPreferences sharedPreferences;
    private SsoHandler ssoHandler;
    private AuthInfo mAuthInfo;
    private SharpUtils sharpUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
       ImageView splashImg= (ImageView) findViewById(R.id.splashImg);

        sharpUtils = SharpUtils.getInstance(getApplicationContext());

        mAuthInfo=new AuthInfo(SplashActivity.this, Constants.APP_KEY,Constants.REDIRECT_URL,Constants.SCOPE);
        ssoHandler=new SsoHandler(SplashActivity.this,mAuthInfo);

        AnimationSet set=new AnimationSet(false);

        ScaleAnimation scaleAnimation=new ScaleAnimation(0.5f, 1.0f ,0.5f, 1.0f);
        scaleAnimation.setRepeatMode(TRIM_MEMORY_BACKGROUND);
        scaleAnimation.setDuration(3000);

        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1.0f);
        alphaAnimation.setDuration(3000);

        set.addAnimation(scaleAnimation);
        set.addAnimation(rotateAnimation);
        set.addAnimation(alphaAnimation);
        splashImg.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                sharedPreferences = getSharedPreferences("weibo", Context.MODE_APPEND);
                LogHelper.i(TAG, "-----else---uid----" );
                CheckLogin();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }


    private void CheckLogin() {
        /**
         * 授权登陆
         */

        if (sharpUtils.isLogin()){
            IntentUtils.startActivityInfo(SplashActivity.this, MainActivity.class);
            finish();
        }else {

            ssoHandler.authorize(new WeiboAuthListener() {
                @Override
                public void onComplete(Bundle bundle) {

                    LogHelper.i(TAG, "------onCreate-----" + bundle.toString());
                    //Bundle[{_weibo_transaction=1484193257929, access_token=2.00jSXqEEd2icCB0d4c50e0e5fmNkKC,
                    // refresh_token=2.00jSXqEEd2icCB1d0d18f108mFEssC, expires_in=157679999, _weibo_appPackage=com.sina.weibo,
                    // com.sina.weibo.intent.extra.NICK_NAME=安仔zi, userName=安仔zi, uid=3736157741, com.sina.weibo.intent.extra.USER_ICON=null}]
                    String userName = bundle.getString("userName");
                    String refresh_token = bundle.getString("refresh_token");
                    String access_token = bundle.getString("access_token");
                    String uid = bundle.getString("uid");
                    LogHelper.i(TAG, "-------------" + userName + refresh_token + access_token + uid);

                    Oauth2AccessToken token = Oauth2AccessToken.parseAccessToken(bundle);

                    sharpUtils.saveToken(token);

                    IntentUtils.startActivityInfo(SplashActivity.this, MainActivity.class);
                    finish();


                }

                @Override
                public void onWeiboException(WeiboException e) {
                }

                @Override
                public void onCancel() {
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (ssoHandler!=null){
            ssoHandler.authorizeCallBack(requestCode,resultCode,data);
        }

    }


}
