package com.xht.android.myweibo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.activity.MainActivity;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.mode.PicEntity;
import com.xht.android.myweibo.mode.RecycleAdapter;
import com.xht.android.myweibo.mode.StatusEntity;
import com.xht.android.myweibo.net.BaseNetWork;
import com.xht.android.myweibo.net.BaseURL;
import com.xht.android.myweibo.net.HttpResponse;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 */

/**
 * Created by Administrator on 2017/1/5.
 */


/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 * <br>
 *    微博主页
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "MainFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivity mMainActivity;
    private ListView lvGetNews;
    private RecyclerView rcycleView;
    private PullRefreshLayout swipeRefreshLayout;
    private TextView mainFind;
    private TextView mainName;
    private ImageView mainSumbit;
    private AsyncWeiboRunner asyncWeiboRunner;
    private WeiboParameters weiboParameters;
    private SharpUtils sharpUtils;

    private List<StatusEntity> mLists;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecycleAdapter mRecycleAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GenJinFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mMainActivity= (MainActivity) getActivity();

        asyncWeiboRunner=new AsyncWeiboRunner(getActivity());
        weiboParameters=new WeiboParameters(Constants.APP_KEY);

        mLists=new ArrayList<>();


        sharpUtils=SharpUtils.getInstance(getActivity());
        //获取数据
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main, container, false);

        //lvGetNews = (ListView)view. findViewById(R.id.lvGetNews);
        rcycleView = (RecyclerView) view. findViewById(R.id.rcycleView);
        mainFind = (TextView)view. findViewById(R.id.mainFind);
        mainName = (TextView)view. findViewById(R.id.mainName);
        mainSumbit = (ImageView)view. findViewById(R.id.mainSumbit);

        mRecycleAdapter = new RecycleAdapter(getActivity(),mLists);
        rcycleView.setAdapter(mRecycleAdapter);

        mRecycleAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "position:"+position, Toast.LENGTH_SHORT).show();
            }
        });


        swipeRefreshLayout = (PullRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // 刷新3秒完成
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        init();
        getNewsDatas();
        return view;
    }

    private void init(){
        mLayoutManager = new LinearLayoutManager(getActivity());
        rcycleView.setLayoutManager(mLayoutManager);

    }

    /**
     * 获取最新的微博新闻
     */
    private void getNewsDatas() {
        LogHelper.i(TAG,"-----onResult-----");


        new BaseNetWork(getActivity(), BaseURL.FRIENDS_TIMELINE_URL){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                if (success) {
                    List<StatusEntity> mSatuslicList = new ArrayList<StatusEntity>();
                    /*Type type=new TypeToken<ArrayList<StatusEntity>>(){}.getType();
                    mSatuslicList =new Gson().fromJson(response.responer,type);
                    LogHelper.i(TAG,"--------"+response.responer.toString());
                    LogHelper.i(TAG,"---size-----"+ mSatuslicList.size());
                    LogHelper.i(TAG,"---111---"+ mSatuslicList.get(1).getCreated_at());*/
                    LogHelper.i(TAG, "-----screen_name-----");


                    /**
                     * {"created_at":"Wed Feb 15 11:13:25 +0800 2017","id":4075374269260625,"mid":"4075374269260625","idstr":"4075374269260625","text":"转发微博",
                     * "source_allowclick":1,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/4Vdgl4\" rel=\"nofollow\">小米手机4</a>","favorited":false,
                     * "truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[],"geo":null,
                     *
                     * "user":{"id":1904178193,
                     * "idstr":"1904178193","class":1,"screen_name":"微博开放平台","name":"微博开放平台","province":"11","city":"8","location":"北京 海淀区",
                     * "description":"http://open.weibo.com/  微博开放平台的相关问题，可以私信我哦","url":"","
                     * profile_image_url":"http://tva4.sinaimg.cn/crop.0.0.180.180.50/717f7411jw1eee33m060uj2050050glv.jpg",
                     * "cover_image":"http://ww4.sinaimg.cn/crop.0.0.980.300/717f7411jw1ecmlcnledij20r808c0vk.jpg",
                     * "cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg",
                     * "profile_url":"openapi","domain":"openapi","weihao":"","gender":"f","followers_count":321991,"friends_count":157,
                     * "pagefriends_count":5,"statuses_count":2015,"favourites_count":5,"created_at":"Mon Dec 27 17:56:46 +0800 2010",
                     * "following":true,"allow_all_act_msg":false,"geo_enabled":true,"verified":true,"verified_type":2,"remark":"",
                     * "insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":false,
                     * "avatar_large":"http://tva4.sinaimg.cn/crop.0.0.180.180.180/717f7411jw1eee33m060uj2050050glv.jpg",
                     * "avatar_hd":"http://tva4.sinaimg.cn/crop.0.0.180.180.1024/717f7411jw1eee33m060uj2050050glv.jpg","verified_reason":"微博开放平台",
                     * "verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","verified_state":0,"verified_level":3,
                     * "verified_type_ext":0,"pay_remind":4,"pay_date":"20110414","verified_reason_modified":"","verified_contact_name":"",
                     * "verified_contact_email":"","verified_contact_mobile":"","follow_me":false,"online_status":0,"bi_followers_count":82,"lang":"zh-cn","star":0,
                     * "mbtype":11,"mbrank":1,"block_word":0,"block_app":1,"credit_score":80,"user_ability":512,"cardid":"vip_006","avatargj_id":"gj_vip_094","urank":25},
                     *
                     *
                     *
                     * "retweeted_status":{"created_at":"Tue Feb 14 17:44:18 +0800 2017","id":4075110249976720,"mid":"4075110249976720","idstr":"4075110249976720",
                     * "text":"【如何防止评论中的恶意攻击和垃圾骚扰】微博客户端7.1.0版本对屏蔽功能进行了升级，可以通过“屏蔽关键词”等功能，减少评论中的恶意攻击和骚扰内容，详情请戳 ↓ http://t.cn/RJNtxzH ​","textLength":172,"source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/PBP2P\" rel=\"nofollow\">微博 weibo.com</a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[],"geo":null,
                     * "user":{"id":1781387491,"idstr":"1781387491","class":1,"screen_name":"微博iPhone客户端","name":"微博iPhone客户端","province":"11","city":"8","location":"北京 海淀区","description":"欢迎来到@微博iPhone客户端 官方微博！在这里：轻松更新浏览你关注的好友、娱乐明星、专家发布的最新微博；即时获取国内外热点新闻,网络流行话题,好玩的视频和图片；随时随地分享照片、文字、地点或转发有趣的内容给好友；快捷发布签到微博、查看附近的微博用户和微博内容；通过私信与好友和粉丝进行语音聊天,私密分享图片和地理位置！微博iPhone客户端，有你更精彩！","url":"http://news.sina.com.cn/wap/wbclient.html","profile_image_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/6a2dd0e3jw1e8qgp5bmzyj2050050aa8.jpg","cover_image_phone":"http://ww2.sinaimg.cn/crop.0.0.640.640.640/a1d3feabjw1ecat8op0e1j20hs0hswgu.jpg","profile_url":"58351","domain":"weiboiphone","weihao":"58351","gender":"f","followers_count":42589988,"friends_count":114,"pagefriends_count":4,"statuses_count":1162,"favourites_count":129,"created_at":"Thu Jul 22 12:12:30 +0800 2010","following":false,"allow_all_act_msg":true,"geo_enabled":true,"verified":true,"verified_type":7,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva2.sinaimg.cn/crop.0.0.180.180.180/6a2dd0e3jw1e8qgp5bmzyj2050050aa8.jpg","avatar_hd":"http://tva2.sinaimg.cn/crop.0.0.180.180.1024/6a2dd0e3jw1e8qgp5bmzyj2050050aa8.jpg","verified_reason":"新浪微博iPhone客户端客服帐号","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","verified_state":0,"verified_level":3,"verified_type_ext":0,"pay_remind":0,"pay_date":"","verified_reason_modified":"","verified_contact_name":"","verified_contact_email":"","verified_contact_mobile":"","follow_me":false,"online_status":0,"bi_followers_count":82,"lang":"zh-cn","star":0,"mbtype":12,"mbrank":5,"block_word":0,"block_app":1,"credit_score":80,"user_ability":512,"urank":28},
                     * "reposts_count":720,"comments_count":565,"attitudes_count":1343,"isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},
                     * "biz_ids":[230940],"biz_feature":0,"expire_time":1487156386,"hasActionTypeCard":0,"darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0,"extend_info":{"ad":{"url_marked":"true"}},
                     * "positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2},
                     * "annotations":[{"client_mblogid":"356cec42-bdf2-4afa-a78f-920a6152e8a2"},{"mapi_request":true}],
                     * "reposts_count":0,"comments_count":0,"attitudes_count":1,"isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_feature":0,"hasActionTypeCard":0,"darwin_tags":[],"hot_weibo_tags":[],
                     * "text_tag_tips":[],"rid":"0_0_1_2666866406671938568","userType":0,"cardid":"vip_006","positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2}
                     */

                try {
                        JSONArray dataJson = new JSONArray(response.responer);
                        int length = dataJson.length();
                    LogHelper.i(TAG,"----len---"+length);
                        for (int i = 0; i < length; i++) {
                            StatusEntity statusEntity=new StatusEntity();
                            JSONObject item = (JSONObject) dataJson.get(i);

                            statusEntity.setCreated_at(item.optString("created_at"));
                            statusEntity.setId(item.optLong("id"));
                            statusEntity.setMid(item.optString("mid"));
                            statusEntity.setIdstr(item.optString("idstr"));
                            statusEntity.setText(item.optString("text"));
                            statusEntity.setSource(item.optString("source"));
                            statusEntity.setText(item.optString("text"));
                            statusEntity.setReposts_count(item.optInt("reposts_count"));
                            statusEntity.setComments_count(item.optInt("comments_count"));
                            statusEntity.setAttitudes_count(item.optInt("attitudes_count"));
                            statusEntity.setThumbnail_pic(item.optString("thumbnail_pic"));
                            statusEntity.setBmiddle_pic(item.optString("bmiddle_pic"));
                            statusEntity.setOriginal_pic(item.optString("original_pic"));
                            //statusEntity.setRetweeted_status(item.optString("retweeted_status"));
                            statusEntity.setPage_type(item.optInt("page_type"));


                            //statusEntity.get
                            JSONObject retweetedStatus = item.optJSONObject("retweeted_status");// 转发微博
                            if (retweetedStatus!=null) {
                                LogHelper.i(TAG, "------retweetedStatus----" + retweetedStatus.toString());

                                StatusEntity.RetweetedStatusBean retweetedBean=new StatusEntity.RetweetedStatusBean();
                                retweetedBean.setCreated_at(retweetedStatus.optString("created_at"));
                                retweetedBean.setId(retweetedStatus.optLong("id"));
                                retweetedBean.setMid(retweetedStatus.optString("mid"));
                                retweetedBean.setIdstr(retweetedStatus.optString("idstr"));
                                retweetedBean.setText(retweetedStatus.optString("text"));
                                retweetedBean.setSource(retweetedStatus.optString("source"));
                                retweetedBean.setFavorited(retweetedStatus.optBoolean("favorited"));

                                JSONArray picStatusUrls = item.optJSONArray("pic_urls");
                                List<PicEntity> picStatusList=new ArrayList<PicEntity>();
                                if (picStatusList.size()>0) {
                                    for (int j = 0; j < picStatusUrls.length(); j++) {
                                        JSONObject picJSON = (JSONObject) picStatusUrls.get(j);
                                        PicEntity picItem = new PicEntity();
                                        String thumbnail_pic = picJSON.optString("thumbnail_pic");
                                        String bmiddle_pic = thumbnail_pic.replace("thumbnail", "bmiddle");
                                        String original_pic = bmiddle_pic.replace("thumbnail", "original");
                                        picItem.setThumbnail_pic(thumbnail_pic);
                                        picItem.setBmiddle_pic(bmiddle_pic);
                                        picItem.setOriginal_pic(original_pic);
                                        picStatusList.add(picItem);
                                    }
                                    retweetedBean.setPic_urls(picStatusList);

                                }


                                StatusEntity.RetweetedStatusBean.UserBeanX userBeanX=new StatusEntity.RetweetedStatusBean.UserBeanX();
                                JSONObject RetUser = (JSONObject) retweetedStatus.get("user");
                         //  * "user":{"id":1781387491,"idstr":"1781387491","class":1,"screen_name":"微博iPhone客户端","name":"微博iPhone客户端","province":"11","city":"8","location":"北京 海淀区","description":"欢迎来到@微博iPhone客户端 官方微博！在这里：轻松更新浏览你关注的好友、娱乐明星、专家发布的最新微博；即时获取国内外热点新闻,网络流行话题,好玩的视频和图片；随时随地分享照片、文字、地点或转发有趣的内容给好友；快捷发布签到微博、查看附近的微博用户和微博内容；通过私信与好友和粉丝进行语音聊天,私密分享图片和地理位置！微博iPhone客户端，有你更精彩！",
                                // "url":"http://news.sina.com.cn/wap/wbclient.html",
                                // "profile_image_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/6a2dd0e3jw1e8qgp5bmzyj2050050aa8.jpg",
                                // "cover_image_phone":"http://ww2.sinaimg.cn/crop.0.0.640.640.640/a1d3feabjw1ecat8op0e1j20hs0hswgu.jpg",
                                // "profile_url":"58351","domain":"weiboiphone","weihao":"58351","gender":"f","followers_count":42589988,
                                // "friends_count":114,"pagefriends_count":4,"statuses_count":1162,"favourites_count":129,
                                // "created_at":"Thu Jul 22 12:12:30 +0800 2010","following":false,
                                // "allow_all_act_msg":true,"geo_enabled":true,"verified":true,"verified_type":7,"remark":"",
                                // "insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,
                                // "avatar_large":"http://tva2.sinaimg.cn/crop.0.0.180.180.180/6a2dd0e3jw1e8qgp5bmzyj2050050aa8.jpg",
                                // "avatar_hd":"http://tva2.sinaimg.cn/crop.0.0.180.180.1024/6a2dd0e3jw1e8qgp5bmzyj2050050aa8.jpg",
                                // "verified_reason":"新浪微博iPhone客户端客服帐号","verified_trade":"","verified_reason_url":"",
                                // "verified_source":"","verified_source_url":"","verified_state":0,"verified_level":3,"verified_type_ext":0,"pay_remind":0,
                                // "pay_date":"","verified_reason_modified":"","verified_contact_name":"","verified_contact_email":"",
                                // "verified_contact_mobile":"","follow_me":false,"online_status":0,
                                // "bi_followers_count":82,"lang":"zh-cn","star":0,"mbtype":12,"mbrank":5,"block_word":0,"block_app":1,"credit_score":80,"user_ability":512,"urank":28},
                                userBeanX.setId(RetUser.optLong("id"));
                                userBeanX.setIdstr(RetUser.optString("idstr"));
                                userBeanX.setScreen_name(RetUser.optString("screen_name"));
                                userBeanX.setName(RetUser.optString("name"));
                                userBeanX.setLocation(RetUser.optString("location"));
                                userBeanX.setDescription(RetUser.optString("description"));
                                userBeanX.setUrl(RetUser.optString("url"));
                                userBeanX.setProfile_image_url(RetUser.optString("profile_image_url"));
                                userBeanX.setProfile_url(RetUser.optString("profile_url"));
                                userBeanX.setCover_image_phone(RetUser.optString("cover_image_phone"));
                                userBeanX.setFollowers_count(RetUser.optLong("followers_count"));
                                userBeanX.setFriends_count(RetUser.optLong("friends_count"));
                                userBeanX.setPagefriends_count(RetUser.optLong("pagefriends_count"));
                                userBeanX.setStatuses_count(RetUser.optLong("statuses_count"));
                                userBeanX.setCreated_at(RetUser.optString("created_at"));
                                userBeanX.setRemark(RetUser.optString("remark"));
                                userBeanX.setFavourites_count(RetUser.optInt("favourites_count"));
                                userBeanX.setUser_ability(RetUser.optInt("user_ability"));
                                userBeanX.setPagefriends_count(RetUser.optInt("pagefriends_count"));
                                userBeanX.setFollow_me(RetUser.optBoolean("follow_me"));
                                userBeanX.setBi_followers_count(RetUser.optInt("bi_followers_count"));
                                userBeanX.setBi_followers_count(RetUser.optInt("bi_followers_count"));
                                userBeanX.setAvatar_hd(RetUser.optString("avatar_hd"));
                                userBeanX.setVerified_reason(RetUser.optString("verified_reason"));
                                userBeanX.setAvatar_large(RetUser.optString("avatar_large"));
                                LogHelper.i(TAG,"-------user---"+RetUser.toString());
                                retweetedBean .setUser(userBeanX);
                                statusEntity.setRetweeted_status(retweetedBean);
                            }

                            JSONArray picUrls = item.optJSONArray("pic_urls");
                            List<PicEntity> picEntityList=new ArrayList<PicEntity>();
                            for (int j = 0; j < picUrls.length(); j++) {
                               JSONObject picJSON= (JSONObject) picUrls.get(j);

                                PicEntity picItem=new PicEntity();
                                String thumbnail_pic = picJSON.optString("thumbnail_pic");
                                String bmiddle_pic = thumbnail_pic.replace("thumbnail", "bmiddle");
                                String original_pic = bmiddle_pic.replace("thumbnail", "original");
                                picItem.setThumbnail_pic(thumbnail_pic);
                                picItem.setBmiddle_pic(bmiddle_pic);
                                picItem.setOriginal_pic(original_pic);

                                picEntityList.add(picItem);
                            }
                            statusEntity.setPic_urls(picEntityList);

                            StatusEntity.UserBean userBean=new StatusEntity.UserBean();
                            JSONObject user = (JSONObject) item.get("user");
                         /* *//**//** user : {"id":3278115281,"idstr":"3278115281","class":1,"screen_name":"言身寸-林夕","name":"言身寸-林夕",
                     "province":"32","city":"1000","location":"江苏","description":"2017目标实现当中。","url":"",
                     "profile_image_url":"http://tva1.sinaimg.cn/crop.0.0.751.751.50/c36411d1jw8fcneb9i8ckj20kv0kv751.jpg",
                     "cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/a1d3feabjw1ecat4uqw77j20hs0hsacp.jpg",
                     "profile_url":"u/3278115281","domain":"","weihao":"","gender":"f","followers_count":68,"friends_count":251,
                     "pagefriends_count":10,"statuses_count":864,"favourites_count":90,"created_at":"Thu May 09 06:45:25 +0800 2013",
                     "following":false,"allow_all_act_msg":true,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"",
                     "insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":false,
                     "avatar_large":"http://tva1.sinaimg.cn/crop.0.0.751.751.180/c36411d1jw8fcneb9i8ckj20kv0kv751.jpg",
                     "avatar_hd":"http://tva1.sinaimg.cn/crop.0.0.751.751.1024/c36411d1jw8fcneb9i8ckj20kv0kv751.jpg",
                     "verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"",
                     "follow_me":false,"online_status":0,"bi_followers_count":9,"lang":"zh-cn","star":0,"mbtype":0,
                     "mbrank":0,"block_word":0,"block_app":0,"credit_score":80,"user_ability":1024,"urank":26}
                     *//**//**/
                            userBean.setId(user.optInt("id"));
                            userBean.setIdstr(user.optString("idstr"));
                            userBean.setScreen_name(user.optString("screen_name"));
                            userBean.setName(user.optString("name"));
                            userBean.setLocation(user.optString("location"));
                            userBean.setDescription(user.optString("description"));
                            userBean.setUrl(user.optString("url"));
                            userBean.setProfile_image_url(user.optString("profile_image_url"));
                            userBean.setProfile_url(user.optString("profile_url"));
                            userBean.setCover_image_phone(user.optString("cover_image_phone"));
                            userBean.setFollowers_count(user.optInt("followers_count"));
                            userBean.setFriends_count(user.optInt("friends_count"));
                            userBean.setPagefriends_count(user.optInt("pagefriends_count"));
                            userBean.setStatuses_count(user.optInt("statuses_count"));
                            userBean.setCreated_at(user.optString("created_at"));
                            userBean.setRemark(user.optString("remark"));
                            userBean.setFavourites_count(user.optInt("favourites_count"));
                            userBean.setPagefriends_count(user.optInt("pagefriends_count"));
                            userBean.setFollow_me(user.optBoolean("follow_me"));
                            userBean.setBi_followers_count(user.optInt("bi_followers_count"));
                            userBean.setAvatar_hd(user.optString("avatar_hd"));
                            userBean.setAvatar_large(user.optString("avatar_large"));

                            LogHelper.i(TAG,"-------user---"+user.toString());
                            statusEntity .setUser(userBean);
                            mSatuslicList.add(statusEntity);
                        }
                        LogHelper.i(TAG,"---111---"+dataJson.get(1));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                   /* ListNewsAdapter listNewsAdapter = new ListNewsAdapter(getActivity(),mSatuslicList);
                    lvGetNews.setAdapter(listNewsAdapter);*/

                    if (mSatuslicList!=null&& mSatuslicList.size()>0){
                        mLists.clear();
                        mLists.addAll(mSatuslicList);
                    }
                    mRecycleAdapter.notifyDataSetChanged();
                }else{
                    LogHelper.i(TAG,"onFinish"+response.message);
                }

            }
            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                return weiboParameters;
            }
        }.get();


    }


}

