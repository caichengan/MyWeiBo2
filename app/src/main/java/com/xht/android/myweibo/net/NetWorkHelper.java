package com.xht.android.myweibo.net;

import android.content.Context;
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
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;

import java.io.UnsupportedEncodingException;

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
     * 转发一条微博
     * @param accessToken 授权登陆
     * @param rePortText 转发的评论
     * @param id  转发的微博的ID
     * @param mInetListener  接口
     */
    public  void getReportWeiBo(final String accessToken,String rePortText, final long id, final INetListener mInetListener){
        new BaseNetWork(mContext, BaseURL.WEIBO_REPOST){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                LogHelper.i(TAG,"----转发-----"+success);
                if (success) {
                    mInetListener.onSuccess(response.responer.toString());

                }else{
                   mInetListener.onError(response.message);
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


    public void getNewsDatas(final INetListener iNetListener) {
        new BaseNetWork(mContext, BaseURL.FRIENDS_TIMELINE_URL){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                LogHelper.i(TAG,"------mainsucess---"+success);
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
                    LogHelper.i(TAG,"-----33---"+response.responer);
                    //iNetListener.onSuccess(response.responer.toString());

                }else{
                    //iNetListener.onError(response.message.toString());
                }

            }
            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                weiboParameters.put(WBConstants.AUTH_PARAMS_CLIENT_ID,id);
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
                    //iNetListener.onSuccess(response.responer.toString());

                }else{
                    //iNetListener.onError(response.responer.toString());
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
}
