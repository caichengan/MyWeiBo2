package com.xht.android.myweibo.net;

public class BaseURL {

    public static final String URLBASE="https://api.weibo.com/2/statuses/";
    //https://api.weibo.com/2/users/show.json
    public static final String URLUSER="https://api.weibo.com/2/users/";
    //获取朋友信息
    public static final String FRIENDS="https://api.weibo.com/2/friendships/";




    //获获取最新的公共微博
    public static final String PUBLIC_TIMELINE=URLBASE+"public_timeline.json";

    //获取当前登录用户及其所关注（授权）用户的最新微博
    public static final String FRIENDS_TIMELINE_URL=URLBASE+"friends_timeline.json";

    //statuses/user_timeline 	获取用户发布的微博
    public static final String USER_TIMELINE=URLBASE+"user_timeline.json";


    //users/show 	获取用户信息users/show
    public static final String USER_FORMATION=URLUSER+"show.json";

    //users/show 	获取某个用户最新发表的微博列表
    public static final String OTHER_FORMATION=URLBASE+"user_timeline.json";

    //获取用户的关注列表
    public static final String FRIENDS_FLLOW=FRIENDS+"friends.json";


}
