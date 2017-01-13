package com.xht.android.myweibo.net;

public class BaseURL {


    //保存公司修改的公司名称
    public static final String SAVE_COPANY_URL="http://www.xiaohoutai.com.cn:8888/XHT/appCompanyController/modifyCompanyName";

    //获获取最新的公共微博
    public static final String DATAS_ORDER_URL="https://api.weibo.com/2/statuses/public_timeline.json";

    //获取当前登录用户及其所关注（授权）用户的最新微博
    public static final String FRIENDS_TIMELINE_URL="https://api.weibo.com/2/statuses/friends_timeline.json";

    //statuses/user_timeline 	获取用户发布的微博
    public static final String USER_TIMELINE=" https://api.weibo.com/2/statuses/user_timeline.json";


}
