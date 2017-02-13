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
import com.xht.android.myweibo.net.BaseNetWork;
import com.xht.android.myweibo.net.BaseURL;
import com.xht.android.myweibo.net.HttpResponse;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    private AsyncWeiboRunner asyncWeiboRunner;
    private WeiboParameters weiboParameters;
    private SharpUtils sharpUtils;
    private List<PublicLine> mPublicList;

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


        new BaseNetWork(getActivity(), BaseURL.PUBLIC_TIMELINE){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                if (success){
                    mPublicList = new ArrayList<PublicLine>();
                    /*Type type=new TypeToken<ArrayList<PublicLine>>(){}.getType();
                    mPublicList =new Gson().fromJson(response.responer,type);
                    LogHelper.i(TAG,"--------"+response.responer.toString());
                    LogHelper.i(TAG,"---size-----"+ mPublicList.size());
                    LogHelper.i(TAG,"---111---"+ mPublicList.get(1).getCreated_at());*/
                    LogHelper.i(TAG,"-----screen_name-----");



                    try {
                        JSONArray dataJson = new JSONArray(response.responer);
                        int length = dataJson.length();
                        for (int i = 0; i < length; i++) {
                            PublicLine publicLine=new PublicLine();
                            JSONObject item = (JSONObject) dataJson.get(i);

                            publicLine.setCreated_at(item.optString("created_at"));
                            publicLine.setId(item.optLong("id"));
                            publicLine.setMid(item.optString("mid"));
                            publicLine.setIdstr(item.optString("idstr"));
                            publicLine.setText(item.optString("text"));
                            publicLine.setSource(item.optString("source"));
                            publicLine.setText(item.optString("text"));
                            publicLine.setReposts_count(item.optInt("reposts_count"));
                            publicLine.setComments_count(item.optInt("comments_count"));
                            publicLine.setAttitudes_count(item.optInt("attitudes_count"));
                            publicLine.setThumbnail_pic(item.optString("thumbnail_pic"));
                            publicLine.setBmiddle_pic(item.optString("bmiddle_pic"));
                            publicLine.setOriginal_pic(item.optString("original_pic"));
                            publicLine.setPage_type(item.optInt("page_type"));
                            PublicLine.UserBean userBean=new PublicLine.UserBean();

                            JSONObject user = (JSONObject) item.get("user");
                          /** user : {"id":3278115281,"idstr":"3278115281","class":1,"screen_name":"言身寸-林夕","name":"言身寸-林夕",
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
*/
                            userBean.setId(user.optLong("id"));
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
                            publicLine.setUser(userBean);
                            mPublicList.add(publicLine);
                        }
                        LogHelper.i(TAG,"---111---"+dataJson.get(1));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ListNewsAdapter listNewsAdapter = new ListNewsAdapter(getActivity(),mPublicList);
                    lvGetNews.setAdapter(listNewsAdapter);
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

    public static Map<String, Object> getMap(String jsonString)
    {
        org.json.JSONObject jsonObject;
        try
        {
            jsonObject = new org.json.JSONObject(jsonString); @SuppressWarnings("unchecked")
        Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext())
            {
                key = (String) keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

