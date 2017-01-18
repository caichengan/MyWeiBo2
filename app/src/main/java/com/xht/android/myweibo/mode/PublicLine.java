package com.xht.android.myweibo.mode;

import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 */

public class PublicLine {//https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00jSXqEEd2icCB0d4c50e0e5fmNkKC&aid=01Alt5CdK3_wvRnjtLkk82Ir6qdD_jFIjpI22Z5hAa8XRnKWg.&oauth_timestamp=1484554862&oauth_sign=304656b

    /**
     *
     * {"statuses":[{"created_at":"Mon Jan 16 16:22:34 +0800 2017","id":4064580433542141,
     * "text":"可能，或许真该减肥了 ​","textLength":20,
   "favorited":false,"truncated":false,
     * "pic_urls":[{"thumbnail_pic":"http://ww4.sinaimg.cn/thumbnail/68efb292gw1fbsjq55xh1j20c10euwf0.jpg"}],
     * "thumbnail_pic":"http://ww4.sinaimg.cn/thumbnail/68efb292gw1fbsjq55xh1j20c10euwf0.jpg",
     * "bmiddle_pic":"http://ww4.sinaimg.cn/bmiddle/68efb292gw1fbsjq55xh1j20c10euwf0.jpg",
     * "original_pic":"http://ww4.sinaimg.cn/large/68efb292gw1fbsjq55xh1j20c10euwf0.jpg",

     * "user":{"id":1760539282,"idstr":"1760539282","class":1,"screen_name":"杭州与伦敦的距离","name":"杭州与伦敦的距离","
     * province":"33","city":"1","location":"浙江 杭州","description":"","url":"",
     * "profile_image_url":"http://tva3.sinaimg.cn/crop.0.0.180.180.50/68efb292jw1e8qgp5bmzyj2050050aa8.jpg",
     * "profile_url":"u/1760539282","domain":"","weihao":"","gender":"f","followers_count":14,"friends_count":415,
     * "pagefriends_count":0,"statuses_count":61,"favourites_count":0,"created_at":"Sat Jun 12 16:19:32 +0800 2010",
     * "following":false,"allow_all_act_msg":true,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"",
     * "insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,
     * "avatar_large":"http://tva3.sinaimg.cn/crop.0.0.180.180.180/68efb292jw1e8qgp5bmzyj2050050aa8.jpg",
     * "avatar_hd":"http://tva3.sinaimg.cn/crop.0.0.180.180.1024/68efb292jw1e8qgp5bmzyj2050050aa8.jpg",
     * "verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"",
     * "verified_source_url":"","follow_me":false,"online_status":0,"bi_followers_count":0,
     * "lang":"zh-cn","star":0,
     * "user_ability":0,"urank":2},"reposts_count":0,"comments_count":0,"attitudes_count":0,
     * "isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_feature":0,"hasActionTypeCard":0,
     * "darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0,"positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2}],
     * "hasvisible":false,"previous_cursor":0,"next_cursor":0,"total_number":20,"interval":0}
     *
     *
     */

    /**
     * statuses : [{"created_at":"Mon Jan 16 16:22:34 +0800 2017","id":4064580433542141,"mid":"4064580433542141","idstr":"4064580433542141","text":"可能，或许真该减肥了 \u200b","textLength":20,"source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/6vtZb0\" rel=\"nofollow\">微博 weibo.com<\/a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[{"thumbnail_pic":"http://ww4.sinaimg.cn/thumbnail/68efb292gw1fbsjq55xh1j20c10euwf0.jpg"}],"thumbnail_pic":"http://ww4.sinaimg.cn/thumbnail/68efb292gw1fbsjq55xh1j20c10euwf0.jpg","bmiddle_pic":"http://ww4.sinaimg.cn/bmiddle/68efb292gw1fbsjq55xh1j20c10euwf0.jpg","original_pic":"http://ww4.sinaimg.cn/large/68efb292gw1fbsjq55xh1j20c10euwf0.jpg","geo":null,"user":{"id":1760539282,"idstr":"1760539282","class":1,"screen_name":"杭州与伦敦的距离","name":"杭州与伦敦的距离","province":"33","city":"1","location":"浙江 杭州","description":"","url":"","profile_image_url":"http://tva3.sinaimg.cn/crop.0.0.180.180.50/68efb292jw1e8qgp5bmzyj2050050aa8.jpg","profile_url":"u/1760539282","domain":"","weihao":"","gender":"f","followers_count":14,"friends_count":415,"pagefriends_count":0,"statuses_count":61,"favourites_count":0,"created_at":"Sat Jun 12 16:19:32 +0800 2010","following":false,"allow_all_act_msg":true,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva3.sinaimg.cn/crop.0.0.180.180.180/68efb292jw1e8qgp5bmzyj2050050aa8.jpg","avatar_hd":"http://tva3.sinaimg.cn/crop.0.0.180.180.1024/68efb292jw1e8qgp5bmzyj2050050aa8.jpg","verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":false,"online_status":0,"bi_followers_count":0,"lang":"zh-cn","star":0,"mbtype":0,"mbrank":0,"block_word":0,"block_app":0,"credit_score":80,"user_ability":0,"urank":2},"reposts_count":0,"comments_count":0,"attitudes_count":0,"isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_feature":0,"hasActionTypeCard":0,"darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0,"positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2}]
     * hasvisible : false
     * previous_cursor : 0
     * next_cursor : 0
     * total_number : 20
     * interval : 0
     */

    private String  created_at;
    private String  text;
    private String  thumbnail_pic;
    private String  original_pic;



    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public void setThumbnail_pic(String thumbnail_pic) {
        this.thumbnail_pic = thumbnail_pic;
    }

    public String getOriginal_pic() {
        return original_pic;
    }

    public void setOriginal_pic(String original_pic) {
        this.original_pic = original_pic;
    }

    private List<String> pic_urls;

    public List<String> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<String> pic_urls) {
        this.pic_urls = pic_urls;
    }

    private UserBean mUser;

    public UserBean getmUser() {
        return mUser;
    }

   // "reposts_count":0,"comments_count":0,"attitudes_count":0,"isLongText":false,"mlevel":0

    private String  reposts_count;
    private String  comments_count;
    private String  attitudes_count;
    private Boolean  isLongText;
    private int  mlevel;


    public String getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(String reposts_count) {
        this.reposts_count = reposts_count;
    }

    public String getComments_count() {
        return comments_count;
    }

    public void setComments_count(String comments_count) {
        this.comments_count = comments_count;
    }

    public String getAttitudes_count() {
        return attitudes_count;
    }

    public void setAttitudes_count(String attitudes_count) {
        this.attitudes_count = attitudes_count;
    }

    public Boolean getLongText() {
        return isLongText;
    }

    public void setLongText(Boolean longText) {
        isLongText = longText;
    }

    public int getMlevel() {
        return mlevel;
    }

    public void setMlevel(int mlevel) {
        this.mlevel = mlevel;
    }
    public void setmUser(UserBean mUser) {
        this.mUser = mUser;
    }

    public static class UserBean{

        private   String id;
        String idstr;
        String screen_name;
        String name;
        String province;
        String location;
        String url;
        String profile_image_url;
        String profile_url;
        String friends_count;
        String followers_count;
        String pagefriends_count;
        String statuses_count;
        String favourites_count;
        String createdTime;
        int verified_type;
        String avatar_large;
        Boolean follow_me;

        String avatar_hd;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdstr() {
            return idstr;
        }

        public void setIdstr(String idstr) {
            this.idstr = idstr;
        }

        public String getScreen_name() {
            return screen_name;
        }

        public void setScreen_name(String screen_name) {
            this.screen_name = screen_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getProfile_image_url() {
            return profile_image_url;
        }

        public void setProfile_image_url(String profile_image_url) {
            this.profile_image_url = profile_image_url;
        }

        public String getProfile_url() {
            return profile_url;
        }

        public void setProfile_url(String profile_url) {
            this.profile_url = profile_url;
        }

        public String getFriends_count() {
            return friends_count;
        }

        public void setFriends_count(String friends_count) {
            this.friends_count = friends_count;
        }

        public String getFollowers_count() {
            return followers_count;
        }

        public void setFollowers_count(String followers_count) {
            this.followers_count = followers_count;
        }

        public String getPagefriends_count() {
            return pagefriends_count;
        }

        public void setPagefriends_count(String pagefriends_count) {
            this.pagefriends_count = pagefriends_count;
        }

        public String getStatuses_count() {
            return statuses_count;
        }

        public void setStatuses_count(String statuses_count) {
            this.statuses_count = statuses_count;
        }

        public String getFavourites_count() {
            return favourites_count;
        }

        public void setFavourites_count(String favourites_count) {
            this.favourites_count = favourites_count;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public int getVerified_type() {
            return verified_type;
        }

        public void setVerified_type(int verified_type) {
            this.verified_type = verified_type;
        }

        public String getAvatar_large() {
            return avatar_large;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }

        public Boolean getFollow_me() {
            return follow_me;
        }

        public void setFollow_me(Boolean follow_me) {
            this.follow_me = follow_me;
        }



        public String getAvatar_hd() {
            return avatar_hd;
        }

        public void setAvatar_hd(String avatar_hd) {
            this.avatar_hd = avatar_hd;
        }
    }


/**
    String  created_at = (String) jsonObject.get("created_at");
    String text = (String) jsonObject.get("text");
    String thumbnail_pic = (String) jsonObject.get("thumbnail_pic");
    String original_pic = (String) jsonObject.get("original_pic");
    JSONArray pic_urls = (JSONArray) jsonObject.get("pic_urls");

    JSONObject objectUser= (JSONObject) jsonObject.get("user");
    String id = (String) (String) objectUser.get("id");
    String idstr=(String) objectUser.get("idstr");
    String screen_name =(String) objectUser.get("screen_name");
    String  name=(String) objectUser.get("name");
    String  province=(String) objectUser.get("province");
    String  location=(String) objectUser.get("location");
    String  url=(String) objectUser.get("url");
    String  profile_image_url =(String) objectUser.get("profile_image_url");
    String  profile_url =(String) objectUser.get("profile_url");
    String  followers_count =(String) objectUser.get("followers_count");
    String  friends_count=(String) objectUser.get("friends_count");
    String  pagefriends_count =(String) objectUser.get("pagefriends_count");
    String  statuses_count =(String) objectUser.get("statuses_count");
    String  favourites_count =(String) objectUser.get("favourites_count");
    String  createdTime =(String) objectUser.get("created_at");
    String  verified_type =(String) objectUser.get("verified_type");
    String  follow_me  =(String) objectUser.get("follow_me");
    String  avatar_large  =(String) objectUser.get("avatar_large");
    String  avatar_hd =(String) objectUser.get("avatar_hd");*/


}
