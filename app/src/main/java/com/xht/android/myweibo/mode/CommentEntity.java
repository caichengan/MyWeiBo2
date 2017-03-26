package com.xht.android.myweibo.mode;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by an on 2017/2/12.
 */

public class CommentEntity {


    /**
     * comments : [{"created_at":"Sun Mar 12 19:17:21 +0800 2017","id":4084555751591797,"rootid":4084555751591797,"floor_number":43,"text":"可爱","source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/1xkBWZ\" rel=\"nofollow\">未通过审核应用<\/a>","user":{"id":3736157741,"idstr":"3736157741","class":1,"screen_name":"破荒之安","name":"破荒之安","province":"100","city":"1000","location":"其他","description":"","url":"","profile_image_url":"http://tva1.sinaimg.cn/crop.256.0.768.768.50/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"u/3736157741","domain":"","weihao":"","gender":"m","followers_count":21,"friends_count":132,"pagefriends_count":2,"statuses_count":37,"favourites_count":1,"created_at":"Thu Aug 22 17:14:19 +0800 2013","following":false,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva1.sinaimg.cn/crop.256.0.768.768.180/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","avatar_hd":"http://tva1.sinaimg.cn/crop.256.0.768.768.1024/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":false,"online_status":0,"bi_followers_count":9,"lang":"zh-cn","star":0,"mbtype":0,"mbrank":0,"block_word":0,"block_app":0,"credit_score":80,"user_ability":1024,"urank":9},"mid":"4084555751591797","idstr":"4084555751591797","status":{"created_at":"Sun Mar 12 19:00:04 +0800 2017","id":4084551402148195,"mid":"4084551402148195","idstr":"4084551402148195","text":"#NBA酷图#洛瑞晒出了家里的两个萌娃！","textLength":35,"source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/6vtZb0\" rel=\"nofollow\">微博 weibo.com<\/a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[{"thumbnail_pic":"http://wx2.sinaimg.cn/thumbnail/7049c17bly1fdk4h21ersj20ku0q1adh.jpg"}],"thumbnail_pic":"http://wx2.sinaimg.cn/thumbnail/7049c17bly1fdk4h21ersj20ku0q1adh.jpg","bmiddle_pic":"http://wx2.sinaimg.cn/bmiddle/7049c17bly1fdk4h21ersj20ku0q1adh.jpg","original_pic":"http://wx2.sinaimg.cn/large/7049c17bly1fdk4h21ersj20ku0q1adh.jpg","geo":null,"user":{"id":1883881851,"idstr":"1883881851","class":1,"screen_name":"NBA","name":"NBA","province":"11","city":"1000","location":"北京","description":"NBA中国公司成立于2008年1月，主要管理NBA在大中华地区的联赛相关业务。此前, NBA于1992年在香港设立大中华地区的首个办事处。NBA与中国篮球界的合作及交流源于数十年前，包括1985年首次邀请中国国家队前往美国比赛。目前NBA与中国众多全国及地方电视台和数字媒体结盟，其中包括与NBA合作30年，向全国转播NBA赛事的中央电视台。联盟每年为球迷举办数百场基层篮球活动、社区活动, 并与多家本地及国际企业建立市场合作伙伴关系。 NBA中国总部位于北京, 并在上海、香港与台北设有办公室。所有NBA正版产品将通过NBA官方旗舰店(nbastore.cn)、NBA天猫旗舰店(nba.tmall.com)、NBA京东旗舰店（nba.jd.com）和NBA微信商城（微信公众号：NBASTORE）以及NBA官方授权商的销售平台进行销售。2004年，NBA在北京和上海举办了两场休斯敦火箭队与萨克拉门托国王队的比赛，使其成为了第一个在中国境内举办比赛的美国职业体育联盟。随着2016年国际系列赛的举行，联盟在中国已举办了22场季前赛。","url":"http://china.nba.com/","profile_image_url":"http://tva2.sinaimg.cn/crop.50.50.126.126.50/7049c17bjw8eujf8ahs4zj206b06bt8n.jpg","cover_image":"http://ww4.sinaimg.cn/crop.0.0.920.300/7049c17bgw1epvr30cb20j20pk08cgnj.jpg"}}}]
     * previous_cursor : 0
     */

    private long previous_cursor;
    /**
     * created_at : Sun Mar 12 19:17:21 +0800 2017
     * id : 4084555751591797
     * rootid : 4084555751591797
     * floor_number : 43
     * text : 可爱
     * source_allowclick : 0
     * source_type : 1
     * source : <a href="http://app.weibo.com/t/feed/1xkBWZ" rel="nofollow">未通过审核应用</a>
     * user : {"id":3736157741,"idstr":"3736157741","class":1,"screen_name":"破荒之安","name":"破荒之安","province":"100","city":"1000","location":"其他","description":"","url":"","profile_image_url":"http://tva1.sinaimg.cn/crop.256.0.768.768.50/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"u/3736157741","domain":"","weihao":"","gender":"m","followers_count":21,"friends_count":132,"pagefriends_count":2,"statuses_count":37,"favourites_count":1,"created_at":"Thu Aug 22 17:14:19 +0800 2013","following":false,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva1.sinaimg.cn/crop.256.0.768.768.180/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","avatar_hd":"http://tva1.sinaimg.cn/crop.256.0.768.768.1024/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":false,"online_status":0,"bi_followers_count":9,"lang":"zh-cn","star":0,"mbtype":0,"mbrank":0,"block_word":0,"block_app":0,"credit_score":80,"user_ability":1024,"urank":9}
     * mid : 4084555751591797
     * idstr : 4084555751591797
     * status : {"created_at":"Sun Mar 12 19:00:04 +0800 2017","id":4084551402148195,"mid":"4084551402148195","idstr":"4084551402148195","text":"#NBA酷图#洛瑞晒出了家里的两个萌娃！","textLength":35,"source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/6vtZb0\" rel=\"nofollow\">微博 weibo.com<\/a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[{"thumbnail_pic":"http://wx2.sinaimg.cn/thumbnail/7049c17bly1fdk4h21ersj20ku0q1adh.jpg"}],"thumbnail_pic":"http://wx2.sinaimg.cn/thumbnail/7049c17bly1fdk4h21ersj20ku0q1adh.jpg","bmiddle_pic":"http://wx2.sinaimg.cn/bmiddle/7049c17bly1fdk4h21ersj20ku0q1adh.jpg","original_pic":"http://wx2.sinaimg.cn/large/7049c17bly1fdk4h21ersj20ku0q1adh.jpg","geo":null,"user":{"id":1883881851,"idstr":"1883881851","class":1,"screen_name":"NBA","name":"NBA","province":"11","city":"1000","location":"北京","description":"NBA中国公司成立于2008年1月，主要管理NBA在大中华地区的联赛相关业务。此前, NBA于1992年在香港设立大中华地区的首个办事处。NBA与中国篮球界的合作及交流源于数十年前，包括1985年首次邀请中国国家队前往美国比赛。目前NBA与中国众多全国及地方电视台和数字媒体结盟，其中包括与NBA合作30年，向全国转播NBA赛事的中央电视台。联盟每年为球迷举办数百场基层篮球活动、社区活动, 并与多家本地及国际企业建立市场合作伙伴关系。 NBA中国总部位于北京, 并在上海、香港与台北设有办公室。所有NBA正版产品将通过NBA官方旗舰店(nbastore.cn)、NBA天猫旗舰店(nba.tmall.com)、NBA京东旗舰店（nba.jd.com）和NBA微信商城（微信公众号：NBASTORE）以及NBA官方授权商的销售平台进行销售。2004年，NBA在北京和上海举办了两场休斯敦火箭队与萨克拉门托国王队的比赛，使其成为了第一个在中国境内举办比赛的美国职业体育联盟。随着2016年国际系列赛的举行，联盟在中国已举办了22场季前赛。","url":"http://china.nba.com/","profile_image_url":"http://tva2.sinaimg.cn/crop.50.50.126.126.50/7049c17bjw8eujf8ahs4zj206b06bt8n.jpg","cover_image":"http://ww4.sinaimg.cn/crop.0.0.920.300/7049c17bgw1epvr30cb20j20pk08cgnj.jpg"}}
     */

    private List<CommentsBean> comments;

    public long getPrevious_cursor() {
        return previous_cursor;
    }

    public void setPrevious_cursor(long previous_cursor) {
        this.previous_cursor = previous_cursor;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        private String created_at;
        private long id;
        private long rootid;
        private long floor_number;
        private String text;
        private long source_allowclick;
        private long source_type;
        private String source;
        /**
         * id : 3736157741
         * idstr : 3736157741
         * class : 1
         * screen_name : 破荒之安
         * name : 破荒之安
         * province : 100
         * city : 1000
         * location : 其他
         * description :
         * url :
         * profile_image_url : http://tva1.sinaimg.cn/crop.256.0.768.768.50/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg
         * cover_image_phone : http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg
         * profile_url : u/3736157741
         * domain :
         * weihao :
         * gender : m
         * followers_count : 21
         * friends_count : 132
         * pagefriends_count : 2
         * statuses_count : 37
         * favourites_count : 1
         * created_at : Thu Aug 22 17:14:19 +0800 2013
         * following : false
         * allow_all_act_msg : false
         * geo_enabled : true
         * verified : false
         * verified_type : -1
         * remark :
         * insecurity : {"sexual_content":false}
         * ptype : 0
         * allow_all_comment : true
         * avatar_large : http://tva1.sinaimg.cn/crop.256.0.768.768.180/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg
         * avatar_hd : http://tva1.sinaimg.cn/crop.256.0.768.768.1024/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg
         * verified_reason :
         * verified_trade :
         * verified_reason_url :
         * verified_source :
         * verified_source_url :
         * follow_me : false
         * online_status : 0
         * bi_followers_count : 9
         * lang : zh-cn
         * star : 0
         * mbtype : 0
         * mbrank : 0
         * block_word : 0
         * block_app : 0
         * credit_score : 80
         * user_ability : 1024
         * urank : 9
         */

        private UserBean user;
        private String mid;
        private String idstr;
        /**
         * created_at : Sun Mar 12 19:00:04 +0800 2017
         * id : 4084551402148195
         * mid : 4084551402148195
         * idstr : 4084551402148195
         * text : #NBA酷图#洛瑞晒出了家里的两个萌娃！
         * textLength : 35
         * source_allowclick : 0
         * source_type : 1
         * source : <a href="http://app.weibo.com/t/feed/6vtZb0" rel="nofollow">微博 weibo.com</a>
         * favorited : false
         * truncated : false
         * in_reply_to_status_id :
         * in_reply_to_user_id :
         * in_reply_to_screen_name :
         * pic_urls : [{"thumbnail_pic":"http://wx2.sinaimg.cn/thumbnail/7049c17bly1fdk4h21ersj20ku0q1adh.jpg"}]
         * thumbnail_pic : http://wx2.sinaimg.cn/thumbnail/7049c17bly1fdk4h21ersj20ku0q1adh.jpg
         * bmiddle_pic : http://wx2.sinaimg.cn/bmiddle/7049c17bly1fdk4h21ersj20ku0q1adh.jpg
         * original_pic : http://wx2.sinaimg.cn/large/7049c17bly1fdk4h21ersj20ku0q1adh.jpg
         * geo : null
         * user : {"id":1883881851,"idstr":"1883881851","class":1,"screen_name":"NBA","name":"NBA","province":"11","city":"1000","location":"北京","description":"NBA中国公司成立于2008年1月，主要管理NBA在大中华地区的联赛相关业务。此前, NBA于1992年在香港设立大中华地区的首个办事处。NBA与中国篮球界的合作及交流源于数十年前，包括1985年首次邀请中国国家队前往美国比赛。目前NBA与中国众多全国及地方电视台和数字媒体结盟，其中包括与NBA合作30年，向全国转播NBA赛事的中央电视台。联盟每年为球迷举办数百场基层篮球活动、社区活动, 并与多家本地及国际企业建立市场合作伙伴关系。 NBA中国总部位于北京, 并在上海、香港与台北设有办公室。所有NBA正版产品将通过NBA官方旗舰店(nbastore.cn)、NBA天猫旗舰店(nba.tmall.com)、NBA京东旗舰店（nba.jd.com）和NBA微信商城（微信公众号：NBASTORE）以及NBA官方授权商的销售平台进行销售。2004年，NBA在北京和上海举办了两场休斯敦火箭队与萨克拉门托国王队的比赛，使其成为了第一个在中国境内举办比赛的美国职业体育联盟。随着2016年国际系列赛的举行，联盟在中国已举办了22场季前赛。","url":"http://china.nba.com/","profile_image_url":"http://tva2.sinaimg.cn/crop.50.50.126.126.50/7049c17bjw8eujf8ahs4zj206b06bt8n.jpg","cover_image":"http://ww4.sinaimg.cn/crop.0.0.920.300/7049c17bgw1epvr30cb20j20pk08cgnj.jpg"}
         */

        private StatusBean status;

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getRootid() {
            return rootid;
        }

        public void setRootid(long rootid) {
            this.rootid = rootid;
        }

        public long getFloor_number() {
            return floor_number;
        }

        public void setFloor_number(long floor_number) {
            this.floor_number = floor_number;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public long getSource_allowclick() {
            return source_allowclick;
        }

        public void setSource_allowclick(long source_allowclick) {
            this.source_allowclick = source_allowclick;
        }

        public long getSource_type() {
            return source_type;
        }

        public void setSource_type(long source_type) {
            this.source_type = source_type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getIdstr() {
            return idstr;
        }

        public void setIdstr(String idstr) {
            this.idstr = idstr;
        }

        public StatusBean getStatus() {
            return status;
        }

        public void setStatus(StatusBean status) {
            this.status = status;
        }

        public static class UserBean {
            private long id;
            private String idstr;
            @SerializedName("class")
            private long classX;
            private String screen_name;
            private String name;
            private String province;
            private String city;
            private String location;
            private String description;
            private String url;
            private String profile_image_url;
            private String cover_image_phone;
            private String profile_url;
            private String domain;
            private String weihao;
            private String gender;
            private long followers_count;
            private long friends_count;
            private long pagefriends_count;
            private long statuses_count;
            private long favourites_count;
            private String created_at;
            private boolean following;
            private boolean allow_all_act_msg;
            private boolean geo_enabled;
            private boolean verified;
            private long verified_type;
            private String remark;
            /**
             * sexual_content : false
             */

            private InsecurityBean insecurity;
            private long ptype;
            private boolean allow_all_comment;
            private String avatar_large;
            private String avatar_hd;
            private String verified_reason;
            private String verified_trade;
            private String verified_reason_url;
            private String verified_source;
            private String verified_source_url;
            private boolean follow_me;
            private long online_status;
            private long bi_followers_count;
            private String lang;
            private long star;
            private long mbtype;
            private long mbrank;
            private long block_word;
            private long block_app;
            private long credit_score;
            private long user_ability;
            private long urank;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getIdstr() {
                return idstr;
            }

            public void setIdstr(String idstr) {
                this.idstr = idstr;
            }

            public long getClassX() {
                return classX;
            }

            public void setClassX(long classX) {
                this.classX = classX;
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

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public String getCover_image_phone() {
                return cover_image_phone;
            }

            public void setCover_image_phone(String cover_image_phone) {
                this.cover_image_phone = cover_image_phone;
            }

            public String getProfile_url() {
                return profile_url;
            }

            public void setProfile_url(String profile_url) {
                this.profile_url = profile_url;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getWeihao() {
                return weihao;
            }

            public void setWeihao(String weihao) {
                this.weihao = weihao;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public long getFollowers_count() {
                return followers_count;
            }

            public void setFollowers_count(long followers_count) {
                this.followers_count = followers_count;
            }

            public long getFriends_count() {
                return friends_count;
            }

            public void setFriends_count(long friends_count) {
                this.friends_count = friends_count;
            }

            public long getPagefriends_count() {
                return pagefriends_count;
            }

            public void setPagefriends_count(long pagefriends_count) {
                this.pagefriends_count = pagefriends_count;
            }

            public long getStatuses_count() {
                return statuses_count;
            }

            public void setStatuses_count(long statuses_count) {
                this.statuses_count = statuses_count;
            }

            public long getFavourites_count() {
                return favourites_count;
            }

            public void setFavourites_count(long favourites_count) {
                this.favourites_count = favourites_count;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public boolean isFollowing() {
                return following;
            }

            public void setFollowing(boolean following) {
                this.following = following;
            }

            public boolean isAllow_all_act_msg() {
                return allow_all_act_msg;
            }

            public void setAllow_all_act_msg(boolean allow_all_act_msg) {
                this.allow_all_act_msg = allow_all_act_msg;
            }

            public boolean isGeo_enabled() {
                return geo_enabled;
            }

            public void setGeo_enabled(boolean geo_enabled) {
                this.geo_enabled = geo_enabled;
            }

            public boolean isVerified() {
                return verified;
            }

            public void setVerified(boolean verified) {
                this.verified = verified;
            }

            public long getVerified_type() {
                return verified_type;
            }

            public void setVerified_type(long verified_type) {
                this.verified_type = verified_type;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public InsecurityBean getInsecurity() {
                return insecurity;
            }

            public void setInsecurity(InsecurityBean insecurity) {
                this.insecurity = insecurity;
            }

            public long getPtype() {
                return ptype;
            }

            public void setPtype(long ptype) {
                this.ptype = ptype;
            }

            public boolean isAllow_all_comment() {
                return allow_all_comment;
            }

            public void setAllow_all_comment(boolean allow_all_comment) {
                this.allow_all_comment = allow_all_comment;
            }

            public String getAvatar_large() {
                return avatar_large;
            }

            public void setAvatar_large(String avatar_large) {
                this.avatar_large = avatar_large;
            }

            public String getAvatar_hd() {
                return avatar_hd;
            }

            public void setAvatar_hd(String avatar_hd) {
                this.avatar_hd = avatar_hd;
            }

            public String getVerified_reason() {
                return verified_reason;
            }

            public void setVerified_reason(String verified_reason) {
                this.verified_reason = verified_reason;
            }

            public String getVerified_trade() {
                return verified_trade;
            }

            public void setVerified_trade(String verified_trade) {
                this.verified_trade = verified_trade;
            }

            public String getVerified_reason_url() {
                return verified_reason_url;
            }

            public void setVerified_reason_url(String verified_reason_url) {
                this.verified_reason_url = verified_reason_url;
            }

            public String getVerified_source() {
                return verified_source;
            }

            public void setVerified_source(String verified_source) {
                this.verified_source = verified_source;
            }

            public String getVerified_source_url() {
                return verified_source_url;
            }

            public void setVerified_source_url(String verified_source_url) {
                this.verified_source_url = verified_source_url;
            }

            public boolean isFollow_me() {
                return follow_me;
            }

            public void setFollow_me(boolean follow_me) {
                this.follow_me = follow_me;
            }

            public long getOnline_status() {
                return online_status;
            }

            public void setOnline_status(long online_status) {
                this.online_status = online_status;
            }

            public long getBi_followers_count() {
                return bi_followers_count;
            }

            public void setBi_followers_count(long bi_followers_count) {
                this.bi_followers_count = bi_followers_count;
            }

            public String getLang() {
                return lang;
            }

            public void setLang(String lang) {
                this.lang = lang;
            }

            public long getStar() {
                return star;
            }

            public void setStar(long star) {
                this.star = star;
            }

            public long getMbtype() {
                return mbtype;
            }

            public void setMbtype(long mbtype) {
                this.mbtype = mbtype;
            }

            public long getMbrank() {
                return mbrank;
            }

            public void setMbrank(long mbrank) {
                this.mbrank = mbrank;
            }

            public long getBlock_word() {
                return block_word;
            }

            public void setBlock_word(long block_word) {
                this.block_word = block_word;
            }

            public long getBlock_app() {
                return block_app;
            }

            public void setBlock_app(long block_app) {
                this.block_app = block_app;
            }

            public long getCredit_score() {
                return credit_score;
            }

            public void setCredit_score(long credit_score) {
                this.credit_score = credit_score;
            }

            public long getUser_ability() {
                return user_ability;
            }

            public void setUser_ability(long user_ability) {
                this.user_ability = user_ability;
            }

            public long getUrank() {
                return urank;
            }

            public void setUrank(long urank) {
                this.urank = urank;
            }

            public static class InsecurityBean {
                private boolean sexual_content;

                public boolean isSexual_content() {
                    return sexual_content;
                }

                public void setSexual_content(boolean sexual_content) {
                    this.sexual_content = sexual_content;
                }
            }
        }

        public static class StatusBean {
            private String created_at;
            private long id;
            private String mid;
            private String idstr;
            private String text;
            private long textLength;
            private long source_allowclick;
            private long source_type;
            private String source;
            private boolean favorited;
            private boolean truncated;
            private String in_reply_to_status_id;
            private String in_reply_to_user_id;
            private String in_reply_to_screen_name;
            private String thumbnail_pic;
            private String bmiddle_pic;
            private String original_pic;
            private Object geo;
            /**
             * id : 1883881851
             * idstr : 1883881851
             * class : 1
             * screen_name : NBA
             * name : NBA
             * province : 11
             * city : 1000
             * location : 北京
             * description : NBA中国公司成立于2008年1月，主要管理NBA在大中华地区的联赛相关业务。此前, NBA于1992年在香港设立大中华地区的首个办事处。NBA与中国篮球界的合作及交流源于数十年前，包括1985年首次邀请中国国家队前往美国比赛。目前NBA与中国众多全国及地方电视台和数字媒体结盟，其中包括与NBA合作30年，向全国转播NBA赛事的中央电视台。联盟每年为球迷举办数百场基层篮球活动、社区活动, 并与多家本地及国际企业建立市场合作伙伴关系。 NBA中国总部位于北京, 并在上海、香港与台北设有办公室。所有NBA正版产品将通过NBA官方旗舰店(nbastore.cn)、NBA天猫旗舰店(nba.tmall.com)、NBA京东旗舰店（nba.jd.com）和NBA微信商城（微信公众号：NBASTORE）以及NBA官方授权商的销售平台进行销售。2004年，NBA在北京和上海举办了两场休斯敦火箭队与萨克拉门托国王队的比赛，使其成为了第一个在中国境内举办比赛的美国职业体育联盟。随着2016年国际系列赛的举行，联盟在中国已举办了22场季前赛。
             * url : http://china.nba.com/
             * profile_image_url : http://tva2.sinaimg.cn/crop.50.50.126.126.50/7049c17bjw8eujf8ahs4zj206b06bt8n.jpg
             * cover_image : http://ww4.sinaimg.cn/crop.0.0.920.300/7049c17bgw1epvr30cb20j20pk08cgnj.jpg
             */

            private UserBean user;
            /**
             * thumbnail_pic : http://wx2.sinaimg.cn/thumbnail/7049c17bly1fdk4h21ersj20ku0q1adh.jpg
             */

            private List<PicUrlsBean> pic_urls;

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getIdstr() {
                return idstr;
            }

            public void setIdstr(String idstr) {
                this.idstr = idstr;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public long getTextLength() {
                return textLength;
            }

            public void setTextLength(long textLength) {
                this.textLength = textLength;
            }

            public long getSource_allowclick() {
                return source_allowclick;
            }

            public void setSource_allowclick(long source_allowclick) {
                this.source_allowclick = source_allowclick;
            }

            public long getSource_type() {
                return source_type;
            }

            public void setSource_type(long source_type) {
                this.source_type = source_type;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public boolean isFavorited() {
                return favorited;
            }

            public void setFavorited(boolean favorited) {
                this.favorited = favorited;
            }

            public boolean isTruncated() {
                return truncated;
            }

            public void setTruncated(boolean truncated) {
                this.truncated = truncated;
            }

            public String getIn_reply_to_status_id() {
                return in_reply_to_status_id;
            }

            public void setIn_reply_to_status_id(String in_reply_to_status_id) {
                this.in_reply_to_status_id = in_reply_to_status_id;
            }

            public String getIn_reply_to_user_id() {
                return in_reply_to_user_id;
            }

            public void setIn_reply_to_user_id(String in_reply_to_user_id) {
                this.in_reply_to_user_id = in_reply_to_user_id;
            }

            public String getIn_reply_to_screen_name() {
                return in_reply_to_screen_name;
            }

            public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
                this.in_reply_to_screen_name = in_reply_to_screen_name;
            }

            public String getThumbnail_pic() {
                return thumbnail_pic;
            }

            public void setThumbnail_pic(String thumbnail_pic) {
                this.thumbnail_pic = thumbnail_pic;
            }

            public String getBmiddle_pic() {
                return bmiddle_pic;
            }

            public void setBmiddle_pic(String bmiddle_pic) {
                this.bmiddle_pic = bmiddle_pic;
            }

            public String getOriginal_pic() {
                return original_pic;
            }

            public void setOriginal_pic(String original_pic) {
                this.original_pic = original_pic;
            }

            public Object getGeo() {
                return geo;
            }

            public void setGeo(Object geo) {
                this.geo = geo;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public List<PicUrlsBean> getPic_urls() {
                return pic_urls;
            }

            public void setPic_urls(List<PicUrlsBean> pic_urls) {
                this.pic_urls = pic_urls;
            }

            public static class UserBean {
                private long id;
                private String idstr;
                @SerializedName("class")
                private long classX;
                private String screen_name;
                private String name;
                private String province;
                private String city;
                private String location;
                private String description;
                private String url;
                private String profile_image_url;
                private String cover_image;

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public String getIdstr() {
                    return idstr;
                }

                public void setIdstr(String idstr) {
                    this.idstr = idstr;
                }

                public long getClassX() {
                    return classX;
                }

                public void setClassX(long classX) {
                    this.classX = classX;
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

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
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

                public String getCover_image() {
                    return cover_image;
                }

                public void setCover_image(String cover_image) {
                    this.cover_image = cover_image;
                }
            }

            public static class PicUrlsBean {
                private String thumbnail_pic;

                public String getThumbnail_pic() {
                    return thumbnail_pic;
                }

                public void setThumbnail_pic(String thumbnail_pic) {
                    this.thumbnail_pic = thumbnail_pic;
                }
            }
        }
    }
}
