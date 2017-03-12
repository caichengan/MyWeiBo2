package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.mode.CommentItem;
import com.xht.android.myweibo.mode.ListViewCommentAdapter;
import com.xht.android.myweibo.mode.UserEntity;
import com.xht.android.myweibo.net.BaseNetWork;
import com.xht.android.myweibo.net.BaseURL;
import com.xht.android.myweibo.net.HttpResponse;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.TimeFormatUtils;
import com.xht.android.myweibo.utils.Utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by an on 2017/3/9.
 * <p>
 * 评论界面
 */
public class WeiBoCommentActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.linReport)
    LinearLayout linReport;
    @InjectView(R.id.edComment)
    EditText edComment;
    @InjectView(R.id.btnComment)
    Button btnComment;
    @InjectView(R.id.linEdComment)
    LinearLayout linEdComment;
    @InjectView(R.id.linComment)
    LinearLayout linComment;
    @InjectView(R.id.linAttidude)
    LinearLayout linAttidude;

    private static final String TOPIC = "#.+?#";//话题
    private static final String NAME = "@([\u4e00-\u9fa5A-z0-9_]*)";//人名
    private static final String URL = "http://.*";//Url

    private static final String TAG = "WeiBoCommentActivity";
    @InjectView(R.id.newListHead)
    ImageView newListHead;
    @InjectView(R.id.newListName)
    TextView newListName;
    @InjectView(R.id.newListTime)
    TextView newListTime;
    @InjectView(R.id.newSources)
    TextView newSources;
    @InjectView(R.id.linUser)
    RelativeLayout linUser;
    @InjectView(R.id.uresContent)
    TextView uresContent;
    @InjectView(R.id.linContent)
    LinearLayout linContent;
    @InjectView(R.id.imgPicture)
    ImageView imgPicture;
    @InjectView(R.id.imgPicture1)
    ImageView imgPicture1;
    @InjectView(R.id.imgPicture2)
    ImageView imgPicture2;
    @InjectView(R.id.linPic)
    LinearLayout linPic;
    @InjectView(R.id.transpond)
    TextView transpond;
    @InjectView(R.id.imgChangePic)
    ImageView imgChangePic;
    @InjectView(R.id.imgChangePic1)
    ImageView imgChangePic1;
    @InjectView(R.id.imgChangePic2)
    ImageView imgChangePic2;
    @InjectView(R.id.ChangePic)
    LinearLayout ChangePic;
    @InjectView(R.id.linTranspond)
    LinearLayout linTranspond;
    @InjectView(R.id.imgBack)
    ImageView imgBack;
    @InjectView(R.id.linText)
    LinearLayout linText;
    @InjectView(R.id.listViewComment)
    ListView listViewComment;
    private String bmiddle_pic;
    private String ret_thumbnail;
    private Long ret_id;
    private Long id;
    private String mytext;
    private List<CommentItem> lsitComment;
    private ListViewCommentAdapter adapter;
    private String userName;
    private String userHeadURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comment);

        ButterKnife.inject(this);

        getUserDatas();

        /**
         * bundle.putString("text",text);
         bundle.putString("urlHD",publicLine.getUser().getProfile_image_url());
         bundle.putString("name",publicLine.getUser().getName());
         bundle.putString("time",publicLine.getCreated_at());
         bundle.putLong("id",publicLine.getId());
         */
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String text = bundle.getString("text");
        String urlHD = bundle.getString("urlHD");
        String mid = bundle.getString("mid");
        String name = bundle.getString("name");
        userName = bundle.getString("userName");
        String time = bundle.getString("time");
        String sources = bundle.getString("sources");
        bmiddle_pic = bundle.getString("bmiddle_pic");
        String chuangfa = bundle.getString("chuangfa");
        String ret_text = bundle.getString("ret_text");
        ret_thumbnail = bundle.getString("ret_thumbnail");
        id = bundle.getLong("id");
        ret_id = bundle.getLong("ret_id");


        LogHelper.i(TAG, "---id-----" + id + text + urlHD + time + bmiddle_pic + name);
        LogHelper.i(TAG, "---mid-----" + mid);

        Glide.with(this).load(urlHD).crossFade().
                placeholder(R.mipmap.p_head_fail).into(newListHead);

        newListName.setText(name);
        newListTime.setText(TimeFormatUtils.parseYYMMDD(time));
        newSources.setText(Html.fromHtml(sources));

        SpannableString spannable = new SpannableString(text);
        Utils.HightLignt(spannable, text, Pattern.compile(TOPIC));
        Utils.HightLignt(spannable, text, Pattern.compile(NAME));
        uresContent.setText(spannable);

        if (!TextUtils.isEmpty(bmiddle_pic)) {
            imgPicture.setVisibility(View.VISIBLE);
            Glide.with(this).load(bmiddle_pic).crossFade().
                    placeholder(R.mipmap.p_head_fail).into(imgPicture);

        } else {
            linPic.setVisibility(View.GONE);
        }

        if ("yes".equals(chuangfa)) {
            linTranspond.setVisibility(View.VISIBLE);
            SpannableString spannable1 = new SpannableString(ret_text);
            Utils.HightLignt(spannable1, ret_text, Pattern.compile(TOPIC));
            Utils.HightLignt(spannable1, ret_text, Pattern.compile(NAME));
            Utils.HightLignt(spannable1, ret_text, Pattern.compile(URL));
            transpond.setText(spannable1);

            if (!TextUtils.isEmpty(ret_thumbnail)) {
                imgChangePic.setVisibility(View.VISIBLE);
                Glide.with(this).load(ret_thumbnail).crossFade().
                        placeholder(R.mipmap.p_head_fail).into(imgChangePic);
            } else {
                linTranspond.setVisibility(View.GONE);
            }


        } else {
            linTranspond.setVisibility(View.GONE);
        }

        /**
         * 获取一条微博中评论
         */
        //getCommentDatas(id);

        lsitComment = new ArrayList<CommentItem>();
        adapter = new ListViewCommentAdapter(this,lsitComment);
        listViewComment.setAdapter(adapter);

        imgChangePic.setOnClickListener(this);
        imgPicture.setOnClickListener(this);
        linAttidude.setOnClickListener(this);
        linComment.setOnClickListener(this);
        linReport.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        btnComment.setOnClickListener(this);


    }

    /**
     * 获取微博评论
     *
     * @param id
     */
    private void getCommentDatas(Long id) {

        NetWorkHelper.getInstance(this).getComment(id, new INetListener() {
            @Override
            public void onSuccess(String result) {

            }
            @Override
            public void onError(String result) {

            }
        });

    }


    private void getUserDatas() {

        NetWorkHelper.getInstance(this).getUserDatas(new INetListener() {
            @Override
            public void onSuccess(String result) {
                UserEntity  userEntity = new Gson().fromJson(result.toString(), UserEntity.class);
                if (userEntity != null) {
                    userName = userEntity.getName();
                    userHeadURL = userEntity.getProfile_image_url();

                }

            }

            @Override
            public void onError(String result) {

            }
        });

    }

    /**
     * 评论一条评论
     */
    private void postCommentDatas(final String text) {


        NetWorkHelper.getInstance(this).postComment(text,id, new INetListener() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(WeiBoCommentActivity.this, result, Toast.LENGTH_SHORT).show();

                CommentItem item=new CommentItem();
                item.setContext(text);
                item.setName(userName);
                item.setUrlHD(userHeadURL);
                item.setTime(System.currentTimeMillis()+"");
                //添加评论
                lsitComment.add(item);
                adapter.addList(lsitComment);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onError(String result) {

                Toast.makeText(WeiBoCommentActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {

            case R.id.imgBack:
                finish();
                break;

            case R.id.linEdComment://写评论的模块

                break;
            case R.id.linComment://点击评论
                Toast.makeText(this, "评论", Toast.LENGTH_SHORT).show();
                linEdComment.setVisibility(View.VISIBLE);
                linText.setVisibility(View.GONE);

                break;
            case R.id.btnComment://发送评论的按钮
                linEdComment.setVisibility(View.VISIBLE);
                btnComment.setClickable(true);
                btnComment.setVisibility(View.VISIBLE);
                String edCommentText = edComment.getText().toString();
                LogHelper.i(TAG,"----"+edCommentText);
                postCommentDatas(edCommentText);

                break;
            case R.id.linAttidude://点击点赞
                Toast.makeText(this, "赞一个", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linReport://点击转发
                Toast.makeText(this, "转发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgPicture://点击图片

                bundle.putString("url", bmiddle_pic);
                LogHelper.i(TAG, "----" + bmiddle_pic);
                IntentUtils.startActivityNumber(this, bundle, LoadImgActivity.class);
                break;
            case R.id.imgChangePic://点击转发的图片
                bundle.putString("url", ret_thumbnail);
                LogHelper.i(TAG, "-----", ret_thumbnail);
                IntentUtils.startActivityNumber(this, bundle, LoadImgActivity.class);
                break;
            default:
                break;
        }
    }
}
