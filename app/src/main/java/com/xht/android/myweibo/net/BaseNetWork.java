package com.xht.android.myweibo.net;

import android.content.Context;
import android.text.TextUtils;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.utils.LogHelper;

/**
 *
 */

public abstract class BaseNetWork {

    private static final String TAG = "BaseNetWork";

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

            LogHelper.i(TAG,"---s---"+s.toString());

            //{"id":1649173367,"idstr":"1649173367","class":1,"screen_name":"每日经济新闻","name":"每日经济新闻","province":"31","city":"1000","location":"上海","description":"","url":"http://www.nbd.com.cn","profile_image_url":"http://tva1.sinaimg.cn/crop.17.13.195.195.50/624c6377gw1f2o7dkri5nj206d05vq38.jpg","cover_image":"http://ww2.sinaimg.cn/crop.0.0.920.300/624c6377gw1ermhn6xl48j20pk08c74w.jpg","cover_image_phone":"http://ww2.sinaimg.cn/crop.0.0.0.0/624c6377jw1ew5dltzh5gj20e60e83ys.jpg","profile_url":"mrjjxw","domain":"mrjjxw","weihao":"","gender":"m","followers_count":0,"friends_count":0,"pagefriends_count":0,"statuses_count":0,"favourites_count":422,"created_at":"Wed Sep 23 12:15:23 +0800 2009","following":true,"allow_all_act_msg":true,"geo_enabled":true,"verified":true,"verified_type":3,"remark":"","insecurity":{"sexual_content":false},"status":{},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva1.sinaimg.cn/crop.17.13.195.195.180/624c6377gw1f2o7dkri5nj206d05vq38.jpg","avatar_hd":"http://tva1.sinaimg.cn/crop.17.13.195.195.1024/624c6377gw1f2o7dkri5nj206d05vq38.jpg","verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","verified_state":0,"verified_level":3,"verified_type_ext":0,"has_service_tel":false,"verified_reason_modified":"","verified_contact_name":"","verified_contact_email":"nbdnews@nbd.com.cn","verified_contact_mobile":"86-21-60900099","follow_me":false,"online_status":0,"bi_followers_count":684,"lang":"zh-cn","star":0,"mbtype":2,"mbrank":5,"block_word":0,"block_app":0,"dianping":"stock","credit_score":80,"user_ability":98822,"urank":41}
            HttpResponse response=new HttpResponse();

            if (!TextUtils.isEmpty(s)){
                response.responer=s.toString().trim();
                success=true;
            }else{
                response.responer="服务器繁忙,请稍后再试....";
                success=false;
            }

            onFinish(response,success);
           /* JsonParser parser=new JsonParser();
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
            }*/


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
