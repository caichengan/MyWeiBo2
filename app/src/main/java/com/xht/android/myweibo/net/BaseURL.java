package com.xht.android.myweibo.net;

public class BaseURL {

    public static final String URLBASE="https://api.weibo.com/2/statuses/";
    //https://api.weibo.com/2/users/show.json
    public static final String URLUSER="https://api.weibo.com/2/users/";
    //获取朋友信息
    public static final String FRIENDS="https://api.weibo.com/2/friendships/";

    //获取消息接口
    public static final String MESSAGE="https://m.api.weibo.com/2/messages//";

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

    // statuses/upload     TODO  发布微博
    //上传图片并发布一条新微博
    public static final String WEIBO_UPLOAD=URLBASE+"upload.json";
    // statuses/upload_url_text
    //指定一个图片URL地址抓取后上传并同时发布一条新微博 URL
    //https://api.weibo.com/2/statuses/upload_url_text.json
    public static final String WEIBO_LOAD_PICS=URLBASE+"upload_url_text.json";


    // statuses/repost
    //转发一条微博
    public static final String WEIBO_REPOST=URLBASE+"repost.json";
    // comments/create
    //对一条微博进行评论
    public static final String WEIBO_COMMENT=URLCOMMENTS+"create.json";

    // comments/show
    //根据微博ID返回某条微博的评论列表
    public static final String WEIBO_SHOW="https://api.weibo.com/2/comments/show.json";

    // friendships/friends
    //获取用户的关注列表 URL
    //https://api.weibo.com/2/friendships/friends.json
    public static final String WEIBO_FRIENDS=FRIENDS+"friends.json";


    // 发送客服消息 当用户主动发消息给认证账号后，微博将会把消息数据推送给开发者，开发者可以调用客服消息接口一定次数。
    //URL  https://m.api.weibo.com/2/messages/reply.json
    /**
     access_token 	true 	string 	在粉丝服务平台 - 高级功能 - 开发者模式页面中获取，或者OAuth2.0授权后获得, 详细参考 获取粉丝服务平台开发接口的access token。
     type 	true 	string 	需要以何种类型的消息进行响应，text：纯文本、articles：图文、position：位置。
     data 	true 	string 	消息数据，具体内容严格遵循type类型对应格式，必须为json做URLEncode后的字符串格式，采用UTF-8编码。
     receiver_id 	true 	int64 	消息接收方的ID
     save_sender_box 	false 	int 	取值为0或1，不填则默认为1。取值为1时，通过本接口发送的消息会进入发送方的私信箱；取值为0时，通过本接口发送的消息不会进入发送方的私信箱。
     */
    public static final String WEIBO_REPLY=MESSAGE+"reply.json";


    public static final String WEIBO_ALL=URLBASE+"user_timeline.json";
    // statuses/user_timeline
   // 获取某个用户最新发表的微博列表 URL
//    https://api.weibo.com/2/statuses/user_timeline.json

}
