package com.xht.android.myweibo.mode;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by an on 2017/3/16.
 */

public class FriendEntity {


    /**
     * users : [{"id":3976097388,"idstr":"3976097388","class":1,"screen_name":"王晗Archer","name":"王晗Archer","province":"11","city":"14","location":"北京 昌平区","description":"爱诺天王，爱小牛，爱DOTA，也爱二次元","url":"","profile_image_url":"http://tva1.sinaimg.cn/crop.0.0.511.511.50/ecfe6e6cjw8fd623sn2q9j20e70e875v.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"u/3976097388","domain":"","weihao":"","gender":"m","followers_count":190,"friends_count":185,"pagefriends_count":3,"statuses_count":922,"favourites_count":8,"created_at":"Mon Jan 13 12:25:50 +0800 2014","following":true,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":220,"remark":"","insecurity":{"sexual_content":false},"status_id":4085618977862927,"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva1.sinaimg.cn/crop.0.0.511.511.180/ecfe6e6cjw8fd623sn2q9j20e70e875v.jpg","avatar_hd":"http://tva1.sinaimg.cn/crop.0.0.511.511.1024/ecfe6e6cjw8fd623sn2q9j20e70e875v.jpg","verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":true,"online_status":0,"bi_followers_count":92,"lang":"zh-cn","star":0,"mbtype":0,"mbrank":0,"block_word":0,"block_app":0,"credit_score":80,"user_ability":0,"urank":21,"action_log":{"follow_action":{"act_code":"1338","uicode":"","luicode":"","fid":"","lfid":"","cardid":"2310930026_1_ _3976097388","lcardid":"","act_type":1,"source":"friends_follows","ext":"act:follow|buid:3736157741|terminal:1052893010+|reason: |uid:3736157741|follow:3976097388"},"card_action":{"act_code":"1338","uicode":"","luicode":"","fid":"","lfid":"","cardid":"2310930026_1_ _3976097388","lcardid":"","act_type":1,"source":"friends_follows","ext":"act:skip|buid:3736157741|terminal:1052893010+|reason: |uid:3736157741|follow:3976097388|skiptype:profile"}}}]
     * next_cursor : 5
     * previous_cursor : 0
     * total_number : 129
     */

    private int next_cursor;
    private int previous_cursor;
    private int total_number;
    /**
     * id : 3976097388
     * idstr : 3976097388
     * class : 1
     * screen_name : 王晗Archer
     * name : 王晗Archer
     * province : 11
     * city : 14
     * location : 北京 昌平区
     * description : 爱诺天王，爱小牛，爱DOTA，也爱二次元
     * url :
     * profile_image_url : http://tva1.sinaimg.cn/crop.0.0.511.511.50/ecfe6e6cjw8fd623sn2q9j20e70e875v.jpg
     * cover_image_phone : http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg
     * profile_url : u/3976097388
     * domain :
     * weihao :
     * gender : m
     * followers_count : 190
     * friends_count : 185
     * pagefriends_count : 3
     * statuses_count : 922
     * favourites_count : 8
     * created_at : Mon Jan 13 12:25:50 +0800 2014
     * following : true
     * allow_all_act_msg : false
     * geo_enabled : true
     * verified : false
     * verified_type : 220
     * remark :
     * insecurity : {"sexual_content":false}
     * status_id : 4085618977862927
     * ptype : 0
     * allow_all_comment : true
     * avatar_large : http://tva1.sinaimg.cn/crop.0.0.511.511.180/ecfe6e6cjw8fd623sn2q9j20e70e875v.jpg
     * avatar_hd : http://tva1.sinaimg.cn/crop.0.0.511.511.1024/ecfe6e6cjw8fd623sn2q9j20e70e875v.jpg
     * verified_reason :
     * verified_trade :
     * verified_reason_url :
     * verified_source :
     * verified_source_url :
     * follow_me : true
     * online_status : 0
     * bi_followers_count : 92
     * lang : zh-cn
     * star : 0
     * mbtype : 0
     * mbrank : 0
     * block_word : 0
     * block_app : 0
     * credit_score : 80
     * user_ability : 0
     * urank : 21
     * action_log : {"follow_action":{"act_code":"1338","uicode":"","luicode":"","fid":"","lfid":"","cardid":"2310930026_1_ _3976097388","lcardid":"","act_type":1,"source":"friends_follows","ext":"act:follow|buid:3736157741|terminal:1052893010+|reason: |uid:3736157741|follow:3976097388"},"card_action":{"act_code":"1338","uicode":"","luicode":"","fid":"","lfid":"","cardid":"2310930026_1_ _3976097388","lcardid":"","act_type":1,"source":"friends_follows","ext":"act:skip|buid:3736157741|terminal:1052893010+|reason: |uid:3736157741|follow:3976097388|skiptype:profile"}}
     */

    private List<UsersBean> users;

    public int getNext_cursor() {
        return next_cursor;
    }

    public void setNext_cursor(int next_cursor) {
        this.next_cursor = next_cursor;
    }

    public int getPrevious_cursor() {
        return previous_cursor;
    }

    public void setPrevious_cursor(int previous_cursor) {
        this.previous_cursor = previous_cursor;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean {
        private long id;
        private String idstr;
        @SerializedName("class")
        private int classX;
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
        private int followers_count;
        private int friends_count;
        private int pagefriends_count;
        private int statuses_count;
        private int favourites_count;
        private String created_at;
        private boolean following;
        private boolean allow_all_act_msg;
        private boolean geo_enabled;
        private boolean verified;
        private int verified_type;
        private String remark;
        /**
         * sexual_content : false
         */

        private InsecurityBean insecurity;
        private long status_id;
        private int ptype;
        private boolean allow_all_comment;
        private String avatar_large;
        private String avatar_hd;
        private String verified_reason;
        private String verified_trade;
        private String verified_reason_url;
        private String verified_source;
        private String verified_source_url;
        private boolean follow_me;
        private int online_status;
        private int bi_followers_count;
        private String lang;
        private int star;
        private int mbtype;
        private int mbrank;
        private int block_word;
        private int block_app;
        private int credit_score;
        private int user_ability;
        private int urank;
        /**
         * follow_action : {"act_code":"1338","uicode":"","luicode":"","fid":"","lfid":"","cardid":"2310930026_1_ _3976097388","lcardid":"","act_type":1,"source":"friends_follows","ext":"act:follow|buid:3736157741|terminal:1052893010+|reason: |uid:3736157741|follow:3976097388"}
         * card_action : {"act_code":"1338","uicode":"","luicode":"","fid":"","lfid":"","cardid":"2310930026_1_ _3976097388","lcardid":"","act_type":1,"source":"friends_follows","ext":"act:skip|buid:3736157741|terminal:1052893010+|reason: |uid:3736157741|follow:3976097388|skiptype:profile"}
         */

        private ActionLogBean action_log;

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

        public int getClassX() {
            return classX;
        }

        public void setClassX(int classX) {
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

        public int getFollowers_count() {
            return followers_count;
        }

        public void setFollowers_count(int followers_count) {
            this.followers_count = followers_count;
        }

        public int getFriends_count() {
            return friends_count;
        }

        public void setFriends_count(int friends_count) {
            this.friends_count = friends_count;
        }

        public int getPagefriends_count() {
            return pagefriends_count;
        }

        public void setPagefriends_count(int pagefriends_count) {
            this.pagefriends_count = pagefriends_count;
        }

        public int getStatuses_count() {
            return statuses_count;
        }

        public void setStatuses_count(int statuses_count) {
            this.statuses_count = statuses_count;
        }

        public int getFavourites_count() {
            return favourites_count;
        }

        public void setFavourites_count(int favourites_count) {
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

        public int getVerified_type() {
            return verified_type;
        }

        public void setVerified_type(int verified_type) {
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

        public long getStatus_id() {
            return status_id;
        }

        public void setStatus_id(long status_id) {
            this.status_id = status_id;
        }

        public int getPtype() {
            return ptype;
        }

        public void setPtype(int ptype) {
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

        public int getOnline_status() {
            return online_status;
        }

        public void setOnline_status(int online_status) {
            this.online_status = online_status;
        }

        public int getBi_followers_count() {
            return bi_followers_count;
        }

        public void setBi_followers_count(int bi_followers_count) {
            this.bi_followers_count = bi_followers_count;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public int getMbtype() {
            return mbtype;
        }

        public void setMbtype(int mbtype) {
            this.mbtype = mbtype;
        }

        public int getMbrank() {
            return mbrank;
        }

        public void setMbrank(int mbrank) {
            this.mbrank = mbrank;
        }

        public int getBlock_word() {
            return block_word;
        }

        public void setBlock_word(int block_word) {
            this.block_word = block_word;
        }

        public int getBlock_app() {
            return block_app;
        }

        public void setBlock_app(int block_app) {
            this.block_app = block_app;
        }

        public int getCredit_score() {
            return credit_score;
        }

        public void setCredit_score(int credit_score) {
            this.credit_score = credit_score;
        }

        public int getUser_ability() {
            return user_ability;
        }

        public void setUser_ability(int user_ability) {
            this.user_ability = user_ability;
        }

        public int getUrank() {
            return urank;
        }

        public void setUrank(int urank) {
            this.urank = urank;
        }

        public ActionLogBean getAction_log() {
            return action_log;
        }

        public void setAction_log(ActionLogBean action_log) {
            this.action_log = action_log;
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

        public static class ActionLogBean {
            /**
             * act_code : 1338
             * uicode :
             * luicode :
             * fid :
             * lfid :
             * cardid : 2310930026_1_ _3976097388
             * lcardid :
             * act_type : 1
             * source : friends_follows
             * ext : act:follow|buid:3736157741|terminal:1052893010+|reason: |uid:3736157741|follow:3976097388
             */

            private FollowActionBean follow_action;
            /**
             * act_code : 1338
             * uicode :
             * luicode :
             * fid :
             * lfid :
             * cardid : 2310930026_1_ _3976097388
             * lcardid :
             * act_type : 1
             * source : friends_follows
             * ext : act:skip|buid:3736157741|terminal:1052893010+|reason: |uid:3736157741|follow:3976097388|skiptype:profile
             */

            private CardActionBean card_action;

            public FollowActionBean getFollow_action() {
                return follow_action;
            }

            public void setFollow_action(FollowActionBean follow_action) {
                this.follow_action = follow_action;
            }

            public CardActionBean getCard_action() {
                return card_action;
            }

            public void setCard_action(CardActionBean card_action) {
                this.card_action = card_action;
            }

            public static class FollowActionBean {
                private String act_code;
                private String uicode;
                private String luicode;
                private String fid;
                private String lfid;
                private String cardid;
                private String lcardid;
                private int act_type;
                private String source;
                private String ext;

                public String getAct_code() {
                    return act_code;
                }

                public void setAct_code(String act_code) {
                    this.act_code = act_code;
                }

                public String getUicode() {
                    return uicode;
                }

                public void setUicode(String uicode) {
                    this.uicode = uicode;
                }

                public String getLuicode() {
                    return luicode;
                }

                public void setLuicode(String luicode) {
                    this.luicode = luicode;
                }

                public String getFid() {
                    return fid;
                }

                public void setFid(String fid) {
                    this.fid = fid;
                }

                public String getLfid() {
                    return lfid;
                }

                public void setLfid(String lfid) {
                    this.lfid = lfid;
                }

                public String getCardid() {
                    return cardid;
                }

                public void setCardid(String cardid) {
                    this.cardid = cardid;
                }

                public String getLcardid() {
                    return lcardid;
                }

                public void setLcardid(String lcardid) {
                    this.lcardid = lcardid;
                }

                public int getAct_type() {
                    return act_type;
                }

                public void setAct_type(int act_type) {
                    this.act_type = act_type;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getExt() {
                    return ext;
                }

                public void setExt(String ext) {
                    this.ext = ext;
                }
            }

            public static class CardActionBean {
                private String act_code;
                private String uicode;
                private String luicode;
                private String fid;
                private String lfid;
                private String cardid;
                private String lcardid;
                private int act_type;
                private String source;
                private String ext;

                public String getAct_code() {
                    return act_code;
                }

                public void setAct_code(String act_code) {
                    this.act_code = act_code;
                }

                public String getUicode() {
                    return uicode;
                }

                public void setUicode(String uicode) {
                    this.uicode = uicode;
                }

                public String getLuicode() {
                    return luicode;
                }

                public void setLuicode(String luicode) {
                    this.luicode = luicode;
                }

                public String getFid() {
                    return fid;
                }

                public void setFid(String fid) {
                    this.fid = fid;
                }

                public String getLfid() {
                    return lfid;
                }

                public void setLfid(String lfid) {
                    this.lfid = lfid;
                }

                public String getCardid() {
                    return cardid;
                }

                public void setCardid(String cardid) {
                    this.cardid = cardid;
                }

                public String getLcardid() {
                    return lcardid;
                }

                public void setLcardid(String lcardid) {
                    this.lcardid = lcardid;
                }

                public int getAct_type() {
                    return act_type;
                }

                public void setAct_type(int act_type) {
                    this.act_type = act_type;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getExt() {
                    return ext;
                }

                public void setExt(String ext) {
                    this.ext = ext;
                }
            }
        }
    }
}
