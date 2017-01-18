package com.xht.android.myweibo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.activity.MainActivity;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.mode.ListNewsAdapter;
import com.xht.android.myweibo.mode.PublicLine;
import com.xht.android.myweibo.net.APIListener;
import com.xht.android.myweibo.net.VolleyHelpApi;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
 *    我的客户中的订单信息
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "OrderFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private MainActivity mMainActivity;
    private ListView lvGetNews;
    private PullRefreshLayout swipeRefreshLayout;
    private TextView mainFind;
    private TextView mainName;
    private ImageView mainSumbit;
    private ListNewsAdapter listNewsAdapter;
    private AsyncWeiboRunner asyncWeiboRunner;
    private WeiboParameters weiboParameters;
    private SharpUtils sharpUtils;

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

        sharpUtils=SharpUtils.getInstance(getActivity());
        //获取数据
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main, container, false);

        lvGetNews = (ListView)view. findViewById(R.id.lvGetNews);


        mainFind = (TextView)view. findViewById(R.id.mainFind);
        mainName = (TextView)view. findViewById(R.id.mainName);
        mainSumbit = (ImageView)view. findViewById(R.id.mainSumbit);

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



        getNewsDatas();
        return view;
    }

    /**
     * 获取最新的微博新闻
     */
    private void getNewsDatas() {
        LogHelper.i(TAG,"-----onResult-----");

        weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
        VolleyHelpApi.getInstance().getDatasNews(asyncWeiboRunner, weiboParameters, new APIListener() {
            @Override
            public void onResult(Object result) {
                LogHelper.i(TAG,"-----onResult-----"+result.toString());

                String string=(String) result;

                JSONObject object=JSONObject.fromObject(string);
                LogHelper.i(TAG,"-----JSONObject-----"+object.toString());

                JSONArray staArray = object.optJSONArray("statuses");
                LogHelper.i(TAG,"-----statuses-----"+staArray.toString());
                LogHelper.i(TAG,"-----statuses-----");

                // java.lang.NoClassDefFoundError: Failed resolution of: Lorg/apache/commons/collections/map/ListOrderedMap;

                List<PublicLine> mPublicList=new ArrayList<PublicLine>();
                for (int i=0;i<staArray.size();i++){
                    PublicLine itemPub=new PublicLine();
                    JSONObject jsonObject= (JSONObject) staArray.get(i);
                    String  created_at =  jsonObject.optString("created_at");
                    String text = jsonObject.optString("text");
                    String thumbnail_pic =  jsonObject.optString("thumbnail_pic");
                    String original_pic = jsonObject.optString("original_pic");


                    LogHelper.i(TAG,"------thumbnail_pic-------"+thumbnail_pic);
                    LogHelper.i(TAG,"--------original_pic-----"+original_pic);

                    itemPub.setCreated_at(created_at);
                    itemPub.setOriginal_pic(original_pic);
                    itemPub.setText(text);
                    itemPub.setThumbnail_pic(thumbnail_pic);


                    JSONArray pic_urls = (JSONArray) jsonObject.get("pic_urls");
                    List<String> mListPic=new ArrayList<String>();
                  /*  if (pic_urls.size()>0) {
                        for (int j = 0; j < pic_urls.size(); j++) {
                            JSONObject obj = (JSONObject) pic_urls.get(i);
                            mListPic.add((String) obj.get("thumbnail_pic"));
                        }
                        itemPub.setPic_urls(mListPic);
                    }
*/
                   JSONObject objectUser= (JSONObject) jsonObject.get("user");

                    PublicLine.UserBean itemUser=new PublicLine.UserBean();


                    String id = objectUser.optString("id");
                    String idstr=objectUser.optString("idstr");
                    String screen_name =objectUser.optString("screen_name");
                    String  name=objectUser.optString("name");
                    String  province=objectUser.optString("province");
                    String  location=objectUser.optString("location");
                    String  url=objectUser.optString("url");
                    String  profile_image_url =objectUser.optString("profile_image_url");
                    String  profile_url =objectUser.optString("profile_url");
                    String  followers_count =objectUser.optString("followers_count");
                    String  friends_count=objectUser.optString("friends_count");
                    String  pagefriends_count =objectUser.optString("pagefriends_count");
                    String  statuses_count =objectUser.optString("statuses_count");
                    String  favourites_count =objectUser.optString("favourites_count");
                    String  createdTime =objectUser.optString("created_at");
                    int  verified_type =objectUser.getInt("verified_type");
                    Boolean  follow_me  = objectUser.getBoolean("follow_me");
                    String  avatar_large  =objectUser.optString("avatar_large");
                    String  avatar_hd =objectUser.optString("avatar_hd");

                    LogHelper.i(TAG,"------profile_image_url-------"+profile_image_url);

                    itemUser.setId(id);
                    itemUser.setIdstr(idstr);
                    itemUser.setScreen_name(screen_name);
                    itemUser.setName(name);
                    itemUser.setProvince(province);
                    itemUser.setLocation(location);
                    itemUser.setUrl(url);
                    itemUser.setProfile_image_url(profile_image_url);
                    itemUser.setProfile_url(profile_url);
                    itemUser.setFollowers_count(followers_count);
                    itemUser.setFriends_count(friends_count);
                    itemUser.setPagefriends_count(pagefriends_count);
                    itemUser.setStatuses_count(statuses_count);
                    itemUser.setFavourites_count(favourites_count);
                    itemUser.setCreatedTime(createdTime);
                    itemUser.setVerified_type(verified_type);
                    itemUser.setFollow_me(follow_me);

                    itemUser.setAvatar_large(avatar_large);
                    itemUser.setAvatar_hd(avatar_hd);

                    itemPub.setmUser(itemUser);

                    mPublicList.add(itemPub);

                }

                LogHelper.i(TAG,"-----screen_name-----");
                listNewsAdapter = new ListNewsAdapter(getActivity(),mPublicList);
                lvGetNews.setAdapter(listNewsAdapter);


   /**  * "user":{"id":1760539282,"idstr":"1760539282","class":1,"screen_name":"杭州与伦敦的距离","name":"杭州与伦敦的距离","
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
     * "user_ability":0,"urank":2}
     * ,"reposts_count":0,"comments_count":0,"attitudes_count":0,
     * "isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_feature":0,"hasActionTypeCard":0,
     * "darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0,"positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2}],
     * "hasvisible":false,"previous_cursor":0,"next_cursor":0,"total_number":20,"interval":0}
     *
     **/



              /*  JsonParser parser=new JsonParser();
                JsonObject asJsonObject = parser.parse(json).getAsJsonObject();
                JsonArray stArray = asJsonObject.get("statuses").getAsJsonArray();

                List<PublicLine> arrayList=new ArrayList<PublicLine>();
                Type type=new TypeToken<ArrayList<PublicLine>>(){}.getType();
                arrayList=new Gson().fromJson(stArray,type);
                LogHelper.i(TAG,"------"+arrayList.size());*/

/**
 *
 * {"statuses":[{"created_at":"Mon Jan 16 16:22:34 +0800 2017","id":4064580433542141,
 * "text":"可能，或许真该减肥了 ​","textLength":20,
 "favorited":false,"truncated":false,
 * "pic_urls":[{"thumbnail_pic":"http://ww4.sinaimg.cn/thumbnail/68efb292gw1fbsjq55xh1j20c10euwf0.jpg"}],
 * "thumbnail_pic":"http://ww4.sinaimg.cn/thumbnail/68efb292gw1fbsjq55xh1j20c10euwf0.jpg",
 * "bmiddle_pic":"http://ww4.sinaimg.cn/bmiddle/68efb292gw1fbsjq55xh1j20c10euwf0.jpg",
 * "original_pic":"http://ww4.sinaimg.cn/large/68efb292gw1fbsjq55xh1j20c10euwf0.jpg",
 *
 */











            }

            @Override
            public void onError(Object e) {
                LogHelper.i(TAG,"-----onError-----"+e.toString());
            }
        });




    }

    @Override
    public void onResume() {
        super.onResume();


    }


}

