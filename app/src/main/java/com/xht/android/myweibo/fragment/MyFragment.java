package com.xht.android.myweibo.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.activity.LoadOneImgActivity;
import com.xht.android.myweibo.activity.MyBlogsActivity;
import com.xht.android.myweibo.activity.SplashActivity;
import com.xht.android.myweibo.activity.UserActivity;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.mode.UserEntity;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/1/7.
 */

/**
 * Created by Administrator on 2017/1/5.
 */


/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 * <br>
 *    我的页面
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private WeiboParameters weiboParameters;
    private SharpUtils sharpUtils;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView orderListView;
    private PullRefreshLayout swipeRefreshLayout;
    private static final String TAG = "MyFragment";

    private UserEntity userEntity;
    private ImageView myHead;
    private TextView myName;
    private TextView myIntroduce;
    private TextView menber;
    private LinearLayout lin01;
    private TextView myWeibo;
    private LinearLayout Myfollow;
    private TextView myFriends;
    private TextView myLocation;
    private LinearLayout MyFriends;
    private TextView myFollowers;
    private LinearLayout MyFollowers;
    private LinearLayout myBlogs;
    private LinearLayout addNewFriends;
    private LinearLayout myTaoBao;
    private LinearLayout myNews;
    private LinearLayout mySetting;

    public MyFragment() {
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
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
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

        sharpUtils=SharpUtils.getInstance(getActivity());
        weiboParameters=new WeiboParameters(Constants.APP_KEY);
    }
    public void getUserDatas(){
        NetWorkHelper.getInstance(getActivity()).getUserDatas(new INetListener() {
            @Override
            public void onSuccess(String result) {
                LogHelper.i(TAG, "--------onFinish" + result.toString());
                //{"id":3736157741,"idstr":"3736157741","class":1,"screen_name":"破荒之安","name":"破荒之安","province":"100","city":"1000","location":"其他",
                // "description":"","url":"","profile_image_url":"http://tva1.sinaimg.cn/crop.256.0.768.768.50/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"u/3736157741","domain":"","weihao":"","gender":"m",
                // "followers_count":21,"friends_count":129,"pagefriends_count":1,"statuses_count":33,"favourites_count":1,"created_at":"Thu Aug 22 17:14:19 +0800 2013","following":false,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"","insecurity":{"sexual_content":false},"status":{"created_at":"Sun Jan 22 20:34:26 +0800 2017","id":4066818144367095,"mid":"4066818144367095","idstr":"4066818144367095","text":"发送一条纯文字微博","textLength":18,"source_allowclick":1,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/3imOna\" rel=\"nofollow\">SDK微博应用demo</a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[],"geo":null,"annotations":[{"sdk_request":true}],"reposts_count":0,"comments_count":0,"attitudes_count":0,"isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_feature":0,"hasActionTypeCard":0,"darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0,"positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva1.sinaimg.cn/crop.256.0.768.768.180/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","avatar_hd":"http://tva1.sinaimg.cn/crop.256.0.768.768.1024/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":false,"online_status":0,
                // "bi_followers_count":9,"lang":"zh-cn","star":0,"mbtype":0,"mbrank":0,"block_word":0,"block_app":0,"credit_score":80,"user_ability":1024,"urank":9}
                userEntity = new Gson().fromJson(result.toString(), UserEntity.class);
                if (userEntity != null) {

                    Glide.with(getActivity()).load(userEntity.getProfile_image_url()).placeholder(R.mipmap.p_head_fail).into(myHead);

                    myName.setText(userEntity.getScreen_name());
                    String description = userEntity.getDescription();
                    if (!TextUtils.isEmpty(description)) {
                        myIntroduce.setText("简介:" + description);
                    } else {
                        myIntroduce.setText("简介:暂无介绍");
                    }

                    myLocation.setText(userEntity.getLocation());

                    myWeibo.setText(userEntity.getStatuses_count() + "");
                    myFollowers.setText(userEntity.getFollowers_count() + "");
                    myFriends.setText(userEntity.getFriends_count() + "");

                    // "口吐鲜血。经查该男子已连续上网9小…… http://t.cn/RihpM46 ​"
                }
             }

            @Override
            public void onError(String result) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my, container, false);
        initialize(view);
        getUserDatas();
        return view;
    }

    private void initialize(View view) {
        myHead = (ImageView) view.findViewById(R.id.myHead);
        myName = (TextView) view.findViewById(R.id.myName);
        myIntroduce = (TextView) view.findViewById(R.id.myIntroduce);
        menber = (TextView) view.findViewById(R.id.menber);
        lin01 = (LinearLayout) view.findViewById(R.id.lin01);
        myWeibo = (TextView) view.findViewById(R.id.myWeibo);
        Myfollow = (LinearLayout) view.findViewById(R.id.Myfollow);
        myFriends = (TextView) view.findViewById(R.id.myFriends);
        myLocation = (TextView) view.findViewById(R.id.myLocation);
        MyFriends = (LinearLayout) view.findViewById(R.id.MyFriends);
        myFollowers = (TextView) view.findViewById(R.id.myFollowers);
        MyFollowers = (LinearLayout) view.findViewById(R.id.MyFollowers);
        myBlogs = (LinearLayout) view.findViewById(R.id.myBlogs);
        addNewFriends = (LinearLayout) view.findViewById(R.id.addNewFriends);
        myTaoBao = (LinearLayout) view.findViewById(R.id.myTaoBao);
        myNews = (LinearLayout) view.findViewById(R.id.myNews);
        mySetting = (LinearLayout) view.findViewById(R.id.mySetting);

        lin01.setOnClickListener(this);
        myHead.setOnClickListener(this);
        myBlogs.setOnClickListener(this);
        mySetting.setOnClickListener(this);
        myBlogs.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        switch (v.getId()){

            case R.id.lin01:

                bundle.putLong("id",userEntity.getId());
               // IntentUtils.startActivityNumber(getActivity(),bundle, UserActivity.class);
                break;
            case R.id.myHead:
                bundle.putString("url",userEntity.getProfile_image_url());
                IntentUtils.startActivityNumber(getActivity(),bundle, LoadOneImgActivity.class);

                break;
            case R.id.myBlogs:
                bundle.putLong("id",userEntity.getId());
                IntentUtils.startActivityNumber(getActivity(),bundle,MyBlogsActivity.class);
                break;
            case R.id.mySetting:

               settingDialog();

                break;
        }
    }

    private void settingDialog() {


        //通过builder构造器来构造

        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());



        builder.setMessage("确定要清除登录数据，重新授权登陆？");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getActivity(), "点击确定", Toast.LENGTH_SHORT).show();

                //取消授权
                cancelOauth();
            }

        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                System.out.println("点击了取消按钮");

            }

        });

        builder.show();


    }

    /**
     * 取消用户授权
     */
    private void cancelOauth() {

        NetWorkHelper.getInstance(getActivity()).cancelOauth(new INetListener() {
            @Override
            public void onSuccess(String result) {
                LogHelper.i(TAG,"---11---"+result.toString());
               // {"result":"true"}
                try {
                    JSONObject obj=new JSONObject(result.toString());

                    String objString = obj.getString("result");
                    LogHelper.i(TAG,"------"+objString);

                    if (objString.equals("true")){

                        sharpUtils.cancelOauth();

                        IntentUtils.startActivityInfo(getActivity(), SplashActivity.class);
                        getActivity().finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String result) {

            }
        });

    }
}

