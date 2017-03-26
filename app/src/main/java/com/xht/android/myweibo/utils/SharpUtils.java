package com.xht.android.myweibo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Created by Administrator on 2017/1/13.
 */

public class SharpUtils {
    private static SharedPreferences mSharedferences;
    private static SharedPreferences.Editor editor;
    private static SharpUtils instance;
    private static final String Sp_NAME="weibo";
    private static final  String ACCESS_TOKEN="ACCESS_TOKEN";
    private static final  String ID="ID";
    private static final String IS_LOGIN="IS_LOGIN";

    public static SharpUtils getInstance(Context mContext){
        if (instance==null){
            synchronized (SharpUtils.class){
                instance=new SharpUtils();
                mSharedferences=mContext.getSharedPreferences(Sp_NAME, Activity.MODE_APPEND);

               editor=    mSharedferences.edit();
            }
        }
        return instance;
    }

    public void saveToken(Oauth2AccessToken token){
        editor.putString(ACCESS_TOKEN,new Gson().toJson(token)).commit();
        editor.putBoolean(IS_LOGIN,true).commit();

    }

    public void saveCommentId(String id){
        editor.putString(ID,id).commit();

    }
    public String getCommentId(){
       return mSharedferences.getString(ID,"");
    }


    public boolean isLogin(){
        return  mSharedferences.getBoolean(IS_LOGIN,false);
    }
    public Oauth2AccessToken getToken(){
        String json = mSharedferences.getString(ACCESS_TOKEN, "");
        if (TextUtils.isEmpty(json)){
            return null;
        }
        return new Gson().fromJson(json,Oauth2AccessToken.class);

    }

}
