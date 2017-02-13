package com.xht.android.myweibo.net;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

/**
 *
 */

public abstract class BaseNetWork {

    private AsyncWeiboRunner mAsynWeiBoRunner;
    boolean success = false;
    private String url;
    public  BaseNetWork(Context context,String url){
        mAsynWeiBoRunner=new AsyncWeiboRunner(context);
        this.url=url;
    }

    private RequestListener mRequestListener=new RequestListener() {
        @Override
        public void onComplete(String s) {



            HttpResponse response=new HttpResponse();
            JsonParser parser=new JsonParser();
            JsonElement jsonElement=parser.parse(s);
            if (jsonElement.isJsonObject()){
                JsonObject object=jsonElement.getAsJsonObject();
                if (object.has("error_code")){

                    response.code=object.get("error_code").getAsInt();

                }
                if (object.has("reeor")){

                    response.message=object.get("error").getAsString();
                }
                if (object.has("statuses")){
                    response.responer=object.get("statuses").toString().trim();
                    success=true;
                }else
                if (object.has("users")){
                    response.responer=object.get("users").toString();
                    success=true;
                }else
                if (object.has("comments")){
                    response.responer=object.get("comments").toString();
                    success=true;
                }


            }

            onFinish(response,success);

        }

        @Override
        public void onWeiboException(WeiboException e) {

            HttpResponse response=new HttpResponse();

            onFinish(response,false);
        }
    };

    protected abstract void onFinish(HttpResponse response, boolean b);

    public void get(){

        mAsynWeiBoRunner.requestAsync(url,onPararts(),"GET",mRequestListener);
    }
    public void post(){
        mAsynWeiBoRunner.requestAsync(url,onPararts(),"POST",mRequestListener);

    }
    public void delete(){
        mAsynWeiBoRunner.requestAsync(url,onPararts(),"DELETE",mRequestListener);


    }

    public  abstract WeiboParameters onPararts();
}
