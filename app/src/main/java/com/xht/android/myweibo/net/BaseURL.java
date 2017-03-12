package com.xht.android.myweibo.net;

public class BaseURL {

    public static final String URLBASE="https://api.weibo.com/2/statuses/";
    //https://api.weibo.com/2/users/show.json
    public static final String URLUSER="https://api.weibo.com/2/users/";
    //获取朋友信息
    public static final String FRIENDS="https://api.weibo.com/2/friendships/";

    //评论https://api.weibo.com/2/comments/create.json
    public static final String URLCOMMENTS="https://api.weibo.com/2/comments/";


    //获获取最新的公共微博
    public static final String PUBLIC_TIMELINE=URLBASE+"public_timeline.json";

    //获取当前登录用户及其所关注（授权）用户的最新微博
    public static final String FRIENDS_TIMELINE_URL=URLBASE+"friends_timeline.json";

    //statuses/user_timeline 	获取某个用户最新发表的微博列表
    public static final String USER_TIMELINE=URLBASE+"user_timeline.json";


    //users/show 	获取用户信息users/show
    public static final String USER_FORMATION=URLUSER+"show.json";

    //获取用户的关注列表
    public static final String FRIENDS_FLLOW=FRIENDS+"friends.json";

    // statuses/update
    //发布一条新微博
    public static final String WEIBO_UPDATE=URLBASE+"update.json";

    // statuses/show
    //根据微博ID获取单条微博内容 URL
    //https://api.weibo.com/2/statuses/show.json
    public static final String WEIBO_DETAIL=URLBASE+"show.json";

    // statuses/upload
    //上传图片并发布一条新微博
    public static final String WEIBO_UPLOAD=URLBASE+"upload.json";

    // statuses/repost
    //转发一条微博
    public static final String WEIBO_REPOST=URLBASE+"repost.json";


    // comments/create
    //对一条微博进行评论
    public static final String WEIBO_COMMENT=URLCOMMENTS+"create.json";

    // comments/show
    //根据微博ID返回某条微博的评论列表
    public static final String WEIBO_SHOW="https://api.weibo.com/2/comments/show.json";



}
