package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.mode.CommentEntity;
import com.xht.android.myweibo.mode.CommentItem;
import com.xht.android.myweibo.mode.GridViewAdapter;
import com.xht.android.myweibo.mode.ListViewCommentAdapter;
import com.xht.android.myweibo.mode.TranspondGridViewAdapter;
import com.xht.android.myweibo.mode.UserEntity;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;
import com.xht.android.myweibo.utils.TimeFormatUtils;
import com.xht.android.myweibo.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by an on 2017/3/9.
 * <p>
 * 评论界面
 */
public class WeiBoCommentActivity extends Activity implements View.OnClickListener {

    private static final String TOPIC = "#.+?#";//话题
    private static final String NAME = "@([\u4e00-\u9fa5A-z0-9_]*)";//人名
    private static final String URL = "http://.*";//Url

    private static final String TAG = "WeiBoCommentActivity";

    private String bmiddle_pic;
    private String ret_thumbnail;
    private Long ret_id;
    private Long id;
    private String mytext;
    private List<CommentItem> lsitComment;
    private ListViewCommentAdapter adapter;
    private String userName;
    private String userHeadURL;
    private SharpUtils sharpUtils;
    private String text;
    private String urlHD;
    private String name;
    private String chuangfa;
    private String ret_text;
    private ImageView imgBack;
    private ListView listViewComment;
    private EditText edComment;
    private TextView btnComment;
    private LinearLayout linEdComment;
    private LinearLayout linReport;
    private LinearLayout linComment;
    private LinearLayout linAttidude;
    private LinearLayout linText;
    private ImageView newListHead;
    private TextView newListName;
    private TextView newListTime;
    private TextView newSources;
    private RelativeLayout linUser;
    private TextView uresContent;
    private LinearLayout linContent;
    private ImageView imgPicture;
    private GridView gridView;
    private GridView trGridView;
    private ImageView imgPicture1;
    private ImageView imgPicture2;
    private LinearLayout linPic;
    private TextView transpond;
    private ImageView imgChangePic;
    private ImageView imgChangePic1;
    private ImageView imgChangePic2;
    private LinearLayout ChangePic;
    private LinearLayout linTranspond;
    private ArrayList<String> retPicsList;
    private ArrayList<String> picLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initialize();
        View view=View.inflate(WeiBoCommentActivity.this,R.layout.item_comment,null);
        listViewComment.addHeaderView(view);
        init(view);
        getUserDatas();

        sharpUtils = SharpUtils.getInstance(this);
        /**
         * bundle.putString("text",text);
         bundle.putString("urlHD",publicLine.getUser().getProfile_image_url());
         bundle.putString("name",publicLine.getUser().getName());
         bundle.putString("time",publicLine.getCreated_at());
         bundle.putLong("id",publicLine.getId());
         */
        Bundle bundle = getIntent().getBundleExtra("bundle");
        text = bundle.getString("text");
        urlHD = bundle.getString("urlHD");
        String mid = bundle.getString("mid");
        name = bundle.getString("name");
        userName = bundle.getString("userName");
        String time = bundle.getString("time");
        String sources = bundle.getString("sources");
        bmiddle_pic = bundle.getString("bmiddle_pic");
        chuangfa = bundle.getString("chuangfa");
        ret_text = bundle.getString("ret_text");
        ret_thumbnail = bundle.getString("ret_thumbnail");
        id = bundle.getLong("id");
        ret_id = bundle.getLong("ret_id");

        picLists = bundle.getStringArrayList("picList");
        LogHelper.i(TAG,"------"+ picLists.toString());
        LogHelper.i(TAG, "---id-----" + id + text + urlHD + time + bmiddle_pic + name);
        LogHelper.i(TAG, "---mid-----" + mid);

        Glide.with(this).load(urlHD).crossFade().
                placeholder(R.mipmap.p_head_fail).into(newListHead);

        newListName.setText(name);
        newListTime.setText(TimeFormatUtils.parseYYMMDD(time));
        newSources.setText(Html.fromHtml(sources));

        //高亮内容
        SpannableString spannable = new SpannableString(text);
        Utils.HightLignt(spannable, text, Pattern.compile(TOPIC));
        Utils.HightLignt(spannable, text, Pattern.compile(NAME));
        uresContent.setText(spannable);

        if (picLists.size()>0){
            linPic.setVisibility(View.VISIBLE);
            GridViewAdapter trGridAdapter=new GridViewAdapter(this, picLists);
            gridView.setAdapter(trGridAdapter);

        }else{

            linPic.setVisibility(View.GONE);
        }


        if ("yes".equals(chuangfa)) {
            linTranspond.setVisibility(View.VISIBLE);
            retPicsList = bundle.getStringArrayList("retPicsList");
            SpannableString spannable1 = new SpannableString(ret_text);
            Utils.HightLignt(spannable1, ret_text, Pattern.compile(TOPIC));
            Utils.HightLignt(spannable1, ret_text, Pattern.compile(NAME));
            Utils.HightLignt(spannable1, ret_text, Pattern.compile(URL));
            transpond.setText(spannable1);


            if (retPicsList.size()>0){
                ChangePic.setVisibility(View.VISIBLE);
                trGridView.setVisibility(View.VISIBLE);
                TranspondGridViewAdapter trGridAdapter=new TranspondGridViewAdapter(this, retPicsList);
                trGridView.setAdapter(trGridAdapter);
            }else{
                ChangePic.setVisibility(View.GONE);
                trGridView.setVisibility(View.GONE);
            }
        } else {
            linTranspond.setVisibility(View.GONE);
        }

        lsitComment = new ArrayList<CommentItem>();
        adapter = new ListViewCommentAdapter(WeiBoCommentActivity.this,lsitComment);
        listViewComment.setAdapter(adapter);
        String commentId = sharpUtils.getCommentId();
        if (commentId.contains(""+id+",")) {
            getCommentDatas(id);
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(WeiBoCommentActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                bundle.putString("url", picLists.get(position));
                bundle.putString("type", "0");
                LogHelper.i(TAG, "----" + bmiddle_pic);
                bundle.putStringArrayList("picLists", picLists);
                bundle.putInt("position",position);
                IntentUtils.startActivityNumber(WeiBoCommentActivity.this, bundle, LoadImgActivity.class);
            }
        });
        trGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(WeiBoCommentActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                bundle.putString("url", retPicsList.get(position));
                bundle.putString("type","1");
                LogHelper.i(TAG, "-----", ret_thumbnail);
                bundle.putStringArrayList("picLists",retPicsList);
                bundle.putInt("position",position);
                IntentUtils.startActivityNumber(WeiBoCommentActivity.this, bundle, LoadImgActivity.class);
            }
        });
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
        LogHelper.i(TAG,"---id2-"+id);//4084808316076685

        NetWorkHelper.getInstance(this).getComment(id, new INetListener() {
            @Override
            public void onSuccess(String result) {
//4084812149336659
                LogHelper.i(TAG,"-----进来的评论----"+result.toString());
                CommentEntity commentEntity = new Gson().fromJson(result, CommentEntity.class);
                List<CommentEntity.CommentsBean> comments = commentEntity.getComments();
                for (int i = 0; i < comments.size(); i++) {
                    CommentEntity.CommentsBean commentsBean = comments.get(i);
                    CommentItem commentItem=new CommentItem();
                    commentItem.setName(commentsBean.getUser().getName());
                    commentItem.setContext(commentsBean.getText());
                    commentItem.setUrlHD(commentsBean.getUser().getProfile_image_url());

                    LogHelper.i(TAG,"----jinlai--"+commentsBean.getUser().getName()+commentsBean.getUser().getProfile_image_url());
                    lsitComment.add(commentItem);
                }
                for (int i = 0; i < lsitComment.size(); i++) {
                    CommentItem cItem = lsitComment.get(i);
                    String name = cItem.getName();
                    String context = cItem.getContext();
                    String urlHD = cItem.getUrlHD();
                    LogHelper.i(TAG,"-----"+name+context+urlHD);
                }
                if (lsitComment.size()>0){
                    adapter.notifyDataSetChanged();
                }
                //listViewComment.setAdapter(adapter);
            }
            @Override
            public void onError(String result) {
            }
        });

    }
    //获取头像和用户名
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

                List<CommentItem> itemList=new ArrayList<CommentItem>();
                CommentItem item=new CommentItem();
                item.setContext(text);
                item.setName(userName);
                item.setUrlHD(userHeadURL);
                item.setTime(System.currentTimeMillis()+"");
                //添加评论
                itemList.add(item);
                lsitComment.addAll(itemList);
                adapter.notifyDataSetChanged();
                linEdComment.setVisibility(View.GONE);
                linText.setVisibility(View.VISIBLE);
                LogHelper.i(TAG,"----id--"+id);
                sharpUtils.saveCommentId(""+id+",");

                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction("com.xht.android.myweibo.udpate");
                sendBroadcast(intent);
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

                if (TextUtils.isEmpty(edCommentText)){
                    Toast.makeText(this, "请输入有效评论", Toast.LENGTH_SHORT).show();
                    return;
                }
                postCommentDatas(edCommentText);

                break;
            case R.id.linAttidude://点击点赞
                Toast.makeText(this, "赞一个", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linReport://点击转发
                Toast.makeText(this, "转发", Toast.LENGTH_SHORT).show();

               ReportWeiBoMethod();

                break;
            default:
                break;
        }
    }

    /**
     * 转发微博
     * @param
     */
    private void ReportWeiBoMethod() {

        Bundle bundle=new Bundle();
        bundle.putString("text",text);
        bundle.putString("ret_text",ret_text);
        bundle.putString("ret_thumbnail",ret_thumbnail+"");
        bundle.putString("imgUrl",bmiddle_pic+"");
        bundle.putString("chuangfa",chuangfa);
        bundle.putLong("ret_id",ret_id);
        bundle.putLong("id",id);


        LogHelper.i(TAG,"------"+picLists.size());

        if (chuangfa.equals("yes")){
            bundle.putStringArrayList("retPicLists", retPicsList);
        }


        bundle.putStringArrayList("picList", picLists);

        LogHelper.i(TAG,"----text---"+text+ret_text+ret_thumbnail);
        LogHelper.i(TAG,"----id---"+bmiddle_pic+chuangfa+ret_id+id+"");
       IntentUtils.startActivityNumber(this,bundle,ReportWeiBoActivity.class);

    }

    private void initialize() {

        imgBack = (ImageView) findViewById(R.id.imgBack);
        listViewComment = (ListView) findViewById(R.id.listViewComment);
        edComment = (EditText) findViewById(R.id.edComment);
        btnComment = (TextView) findViewById(R.id.btnComment);
        linEdComment = (LinearLayout) findViewById(R.id.linEdComment);
        linReport = (LinearLayout) findViewById(R.id.linReport);
        linComment = (LinearLayout) findViewById(R.id.linComment);
        linAttidude = (LinearLayout) findViewById(R.id.linAttidude);
        linText = (LinearLayout) findViewById(R.id.linText);
    }

    private void init(View view) {

        newListHead = (ImageView) view.findViewById(R.id.newListHead);
        newListName = (TextView) view.findViewById(R.id.newListName);
        newListTime = (TextView) view.findViewById(R.id.newListTime);
        newSources = (TextView) view.findViewById(R.id.newSources);
        linUser = (RelativeLayout) view.findViewById(R.id.linUser);
        uresContent = (TextView) view.findViewById(R.id.uresContent);
        linContent = (LinearLayout) view.findViewById(R.id.linContent);
        gridView = (GridView) view.findViewById(R.id.gridView);
        trGridView = (GridView) view.findViewById(R.id.trGridView);

        linPic = (LinearLayout) view.findViewById(R.id.linPic);
        transpond = (TextView) view.findViewById(R.id.transpond);
        ChangePic = (LinearLayout) view.findViewById(R.id.ChangePic);
        linTranspond = (LinearLayout) view.findViewById(R.id.linTranspond);
    }
}
