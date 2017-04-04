package com.xht.android.myweibo.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.mode.ListNewsAdapter;
import com.xht.android.myweibo.mode.StatusEntity;
import com.xht.android.myweibo.mode.UserEntity;
import com.xht.android.myweibo.utils.BitmapHelper;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by an on 2017/3/2.
 */

public class NetWorkHelper {


    private static NetWorkHelper sNetWorkHelper;
    private static Context mContext;
    private static WeiboParameters weiboParameters;
    private static SharpUtils sharpUtils;

    private static final String TAG = "NetWorkHelper";
    private String text1;
    private String rePortText1 ;
    private String data;
    private String statu;

    public static synchronized NetWorkHelper getInstance(Context mContext) {
        NetWorkHelper.mContext=mContext;
        weiboParameters=new WeiboParameters(Constants.APP_KEY);
        sharpUtils = SharpUtils.getInstance(mContext);
        if (sNetWorkHelper == null) {
            sNetWorkHelper = new NetWorkHelper();
        }
        return sNetWorkHelper;
    }
    private NetWorkHelper() {
    }


    /**
     * 获取头像和姓名
     * @param iNetListener
     */
    public void getUserDatas(final INetListener iNetListener) {
        new BaseNetWork(mContext, BaseURL.USER_FORMATION) {
            @Override
            protected void onFinish(HttpResponse response, boolean success) {
                if (success) {
                    iNetListener.onSuccess(response.responer.toString());
                }else{
                    iNetListener.onError(response.responer);
                }
            }

            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN, sharpUtils.getToken().getToken());
                weiboParameters.put(WBConstants.GAME_PARAMS_UID, sharpUtils.getToken().getUid());
                return weiboParameters;
            }
        }.get();
    }

    /**
     * 获取头像和姓名
     * @param iNetListener
     */
    public void getOtherUserDatas(final Long id, final INetListener iNetListener) {
        new BaseNetWork(mContext, BaseURL.USER_FORMATION) {
            @Override
            protected void onFinish(HttpResponse response, boolean success) {
                if (success) {
                    iNetListener.onSuccess(response.responer.toString());
                }else{
                    iNetListener.onError(response.responer);
                }
            }

            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN, sharpUtils.getToken().getToken());
                weiboParameters.put(WBConstants.GAME_PARAMS_UID,id);
                return weiboParameters;
            }
        }.get();
    }


    /**
     * 转发一条微博
     * @param accessToken 授权登陆
     * @param rePortText 转发的评论
     * @param id  转发的微博的ID
     * @param mInetListener  接口
     */
    public  void getReportWeiBo1(final String accessToken, final String rePortText, final long id, final INetListener mInetListener){

        new BaseNetWork(mContext, BaseURL.WEIBO_REPOST){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                LogHelper.i(TAG,"----转发-----"+success);
                if (success) {
                   // mInetListener.onSuccess(response.responer.toString());

                }else{
                 //  mInetListener.onError(response.message);
                }
            }
            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,accessToken);
                weiboParameters.put("id",id);
                return weiboParameters;
            }
        }.post();
    }


    /**
     * 获取首页数据
     * @param iNetListener
     */
    public void getNewsDatas(final INetListener iNetListener) {
        new BaseNetWork(mContext, BaseURL.FRIENDS_TIMELINE_URL){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {
                LogHelper.i(TAG,"------mainsucess---"+success);
                LogHelper.i(TAG,"------mainsucess---"+response.responer.toString());
                if (success) {
                    iNetListener.onSuccess(response.responer.toString());
                }else{
                    iNetListener.onError(response.responer.toString());
                }

            }
            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                return weiboParameters;
            }
        }.get();

    }

    /**获取微博评论列表
     *
     * @param id
     * @param iNetListener
     */

    public void getComment(final long id, final INetListener iNetListener) {
        new BaseNetWork(mContext, BaseURL.WEIBO_SHOW){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                LogHelper.i(TAG,"----success---"+success);
                if (success) {

                    LogHelper.i(TAG,"-----33---");
                    LogHelper.i(TAG,"-----33---"+response.responer.toString());
                    iNetListener.onSuccess(response.responer.toString());

                }else{
                    iNetListener.onError("");
                }

            }
            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                weiboParameters.put("id",id);
                return weiboParameters;
            }
        }.get();
    }

    /**
     * 获取微博
     * @param id
     * @param iNetListener
     */
    public void getWeiBoDetailDatas(final long id, final INetListener iNetListener) {
        new BaseNetWork(mContext, BaseURL.WEIBO_DETAIL){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                LogHelper.i(TAG,"------dd---"+success);
                if (success) {
                    iNetListener.onSuccess(response.responer.toString());

                }else{
                    iNetListener.onError("服务器繁忙...");
                }

            }
            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                weiboParameters.put("id",id);
                return weiboParameters;
            }
        }.get();

    }

    /**
     * 评论一条微博
     * @param text
     * @param id
     * @param iNetListener
     */
    public void postComment(final String text, final Long id, final INetListener iNetListener) {
        new BaseNetWork(mContext,BaseURL.WEIBO_COMMENT){
            @Override
            protected void onFinish(HttpResponse response, boolean b) {

                LogHelper.i(TAG,"-------评论成功--"+b);
                if (b){

                    iNetListener.onSuccess("评论成功");
                }else{
                    iNetListener.onSuccess("服务器繁忙...");
                }
            }

            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                weiboParameters.put("id",id);
                try {
                    text1 = java.net.URLDecoder.decode(text, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();

                }
                weiboParameters.put("comment",text1);
                return weiboParameters;
            }
        }.post();

    }

    /**
     * 转发一条微博
     * @param
     * @param edReport
     * @param changId
     * @param iNetListener
     */
    public void getReportWeiBo(final String edReport, final long changId, final INetListener iNetListener) {

        new BaseNetWork(mContext,BaseURL.WEIBO_REPOST){

            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                LogHelper.i(TAG,"---sucess----"+success);
                if (success){
                    iNetListener.onSuccess(response.responer.toString());
                }else{
                    iNetListener.onError(response.responer.toString());
                }

            }

            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                weiboParameters.put("id",changId);

                try {
                  rePortText1=  URLDecoder.decode(edReport,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();

                }
                weiboParameters.put("status",rePortText1);
                return weiboParameters;
            }
        }.post();

    }

    /**
     * 获取用户朋友列表
     */
    public void getFriendsList(final int count, final INetListener iNetListener) {

        new BaseNetWork(mContext,BaseURL.WEIBO_FRIENDS){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                LogHelper.i(TAG,"---"+success);
                LogHelper.i(TAG,"--------"+response.responer.toString());
                if (success){
                    iNetListener.onSuccess(response.responer.toString());
                }else {
                    iNetListener.onError(response.responer.toString());
                }

            }

            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                weiboParameters.put("uid",sharpUtils.getToken().getUid());
                weiboParameters.put("count",count);
                return weiboParameters;
            }
        }.get();

    }

    /**
     * 发送消息给对方
     */
    public void postMessage(final JSONObject datas, final long id,INetListener iNetListener) {

        new BaseNetWork(mContext,BaseURL.WEIBO_REPLY){
            @Override
            protected void onFinish(HttpResponse response, boolean b) {

                LogHelper.i(TAG,"-------bb---"+b);

                if (b){

                }else{

                }

            }

            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                weiboParameters.put("type","text");

                try {

                    data = URLDecoder.decode(datas.toString(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();

                }

                weiboParameters.put("data",data);
                weiboParameters.put("receiver_id",id);
                return weiboParameters;
            }
        }.post();

    }

    /**
     * 获取所有博客
     */
    public void getallBlogs(final int count, final long id, final INetListener iNetListener) {
        new BaseNetWork(mContext,BaseURL.WEIBO_ALL){
            @Override
            protected void onFinish(HttpResponse response, boolean b) {

                LogHelper.i(TAG,"----b----"+b);

                LogHelper.i(TAG,"-----user---"+response.responer.toString());
                if (b){
                    iNetListener.onSuccess(response.responer.toString());
                }else{
                    iNetListener.onError(response.responer.toString());
                }

            }

            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                weiboParameters.put("count",count);
                weiboParameters.put("uid",id);
                return weiboParameters;
            }
        }.get();

    }

    /**
     * 发送一条微博只有文字
     * @param status
     */
    public void postSendWeiBoChartacter(final String status, final INetListener iNetListener) {
        new BaseNetWork(mContext,BaseURL.WEIBO_UPDATE){
            @Override
            protected void onFinish(HttpResponse response, boolean b) {

                LogHelper.i(TAG,"----"+b);
                LogHelper.i(TAG,"--------"+response.responer.toString());
                if (b){
                    iNetListener.onSuccess("发布成功");

                }else{
                    iNetListener.onError("服务器繁忙,请稍后再试....");
                }
            }

            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                try {

                    statu = URLDecoder.decode(status.toString(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();

                }
                LogHelper.i(TAG,"------"+statu);
                weiboParameters.put("status",statu);
                return weiboParameters;
            }
        }.post();

    }

    /**
     * 上传图片加文字
     */
    public void postSendWeiBoPhoto(final String status,final List<String> stringURLS, final INetListener iNetListener) {

        new BaseNetWork(mContext,BaseURL.WEIBO_UPLOAD){
            @Override
            protected void onFinish(HttpResponse response, boolean b) {

                LogHelper.i(TAG,"----b--"+b);
                if (b){

                    iNetListener.onSuccess("发布成功");
                }else{
                    iNetListener.onError("服务器繁忙,请稍后再试....");
                }

            }

            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                try {

                    statu = URLDecoder.decode(status.toString(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                LogHelper.i(TAG,"------"+statu);
                weiboParameters.put("status",statu);

                for (int i = 0; i < stringURLS.size(); i++) {
                    Bitmap bitmap = BitmapFactory.decodeFile(stringURLS.get(i));

                    weiboParameters.put("pic", bitmap);

                }

                return weiboParameters;
            }
        }.post();

    }

    /**
     * 取消用户授权
     */
    public void cancelOauth(final INetListener inetListener) {

        new BaseNetWork(mContext,BaseURL.USER_CANCELOAUYH){
            @Override
            protected void onFinish(HttpResponse response, boolean b) {
                LogHelper.i(TAG,"---"+b);
                LogHelper.i(TAG,"---"+response.responer.toString());
                inetListener.onSuccess(response.responer);

            }
            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                return weiboParameters;
            }
        }.get();

    }
}
