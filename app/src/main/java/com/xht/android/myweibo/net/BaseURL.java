package com.xht.android.myweibo.net;

public class BaseURL {

    public static final String URLBASE="https://api.weibo.com/2/statuses/";


    //获获取最新的公共微博
    public static final String PUBLIC_TIMELINE=URLBASE+"public_timeline.json";

    //获取当前登录用户及其所关注（授权）用户的最新微博
    public static final String FRIENDS_TIMELINE_URL=URLBASE+"friends_timeline.json";

    //statuses/user_timeline 	获取用户发布的微博
    public static final String USER_TIMELINE=URLBASE+"user_timeline.json";


}
