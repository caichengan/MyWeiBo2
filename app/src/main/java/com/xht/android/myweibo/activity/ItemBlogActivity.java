package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.mode.GridViewAdapter;
import com.xht.android.myweibo.mode.ItemBlogBean;
import com.xht.android.myweibo.mode.TranspondGridViewAdapter;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.view.CircleTransform;
import com.xht.android.myweibo.view.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by an on 2017/3/26.
 */
public class ItemBlogActivity extends Activity {

    private static final String TAG = "ItemBlogActivity";
    @InjectView(R.id.imgBack)
    ImageView imgBack;
    @InjectView(R.id.blogHead)
    ImageView blogHead;
    @InjectView(R.id.blogName)
    TextView blogName;
    @InjectView(R.id.blogText)
    TextView blogText;
    @InjectView(R.id.gridView)
    NoScrollGridView gridView;
    @InjectView(R.id.blogRetText)
    TextView blogRetText;
    @InjectView(R.id.RetGridView)
    NoScrollGridView RetGridView;
    private ItemBlogBean itemBlogBean;

    private ProgressDialog mProgDoal;
    private ArrayList<String> listPics;
    private ArrayList<String> retListPics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_itemblog);
        ButterKnife.inject(this);

        long mId = getIntent().getBundleExtra("bundle").getLong("id");

        LogHelper.i(TAG, "-------" + mId);



        getBlogsDatas(mId);
        initClick();

    }

    /**
     * 点击事件
     */
    private void initClick() {

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Bundle bundle=new Bundle();

                bundle.putStringArrayList("picLists", listPics);
                bundle.putInt("position",position);
                IntentUtils.startActivityNumber(ItemBlogActivity.this, bundle, LoadImgActivity.class);


            }
        });

        RetGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle=new Bundle();
                bundle.putStringArrayList("picLists", retListPics);
                bundle.putInt("position",position);
                IntentUtils.startActivityNumber(ItemBlogActivity.this, bundle, LoadImgActivity.class);

            }
        });
    }
    private void getBlogsDatas(long mId) {
        createProgressDialog("正在加载数据...");
        NetWorkHelper.getInstance(this).getWeiBoDetailDatas(mId, new INetListener() {
            @Override
            public void onSuccess(String result) {
                LogHelper.i(TAG, "----id-----" + result.toString());
                //{"created_at":"Sat Mar 25 20:39:22 +0800 2017","id":4089287437604558,"mid":"4089287437604558","idstr":"4089287437604558","text":"不错","source_allowclick":1,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/1xkBWZ\" rel=\"nofollow\">未通过审核应用</a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[],"geo":null,"user":{"id":3736157741,"idstr":"3736157741","class":1,"screen_name":"破荒之安","name":"破荒之安","province":"100","city":"1000","location":"其他","description":"","url":"","profile_image_url":"http://tva1.sinaimg.cn/crop.256.0.768.768.50/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"u/3736157741","domain":"","weihao":"","gender":"m","followers_count":21,"friends_count":138,"pagefriends_count":3,"statuses_count":48,"favourites_count":1,"created_at":"Thu Aug 22 17:14:19 +0800 2013","following":false,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva1.sinaimg.cn/crop.256.0.768.768.180/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","avatar_hd":"http://tva1.sinaimg.cn/crop.256.0.768.768.1024/deb13e2djw8e9gwo0vgn7j20zk0lcjui.jpg","verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":false,"online_status":0,"bi_followers_count":9,"lang":"zh-cn","star":0,"mbtype":0,"mbrank":0,"block_word":0,"block_app":0,"credit_score":80,"user_ability":1024,"urank":9},
                // "retweeted_status":{"created_at":"Sat Mar 25 20:35:19 +0800 2017","id":4089286414166343,"mid":"4089286414166343","idstr":"4089286414166343","text":"【西安地铁约谈三号线问题电缆施工监理单位】针对三号线奥凯电缆更换整改问题，西安地铁公司24日上午约谈了涉事7家施工单位及2家监理单位上级单位相关负责人，要求各方本着对群众生命高度负责的态度，认领责任，全力配合整改。@新华视点 ​","textLength":219,"source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/6ghA0p\" rel=\"nofollow\">搜狗高速浏览器</a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[{"thumbnail_pic":"http://wx2.sinaimg.cn/thumbnail/624c6377gy1fdzd5p9clvj20j60j6tb5.jpg"}],"thumbnail_pic":"http://wx2.sinaimg.cn/thumbnail/624c6377gy1fdzd5p9clvj20j60j6tb5.jpg","bmiddle_pic":"http://wx2.sinaimg.cn/bmiddle/624c6377gy1fdzd5p9clvj20j60j6tb5.jpg","original_pic":"http://wx2.sinaimg.cn/large/624c6377gy1fdzd5p9clvj20j60j6tb5.jpg","geo":null,"user":{"id":1649173367,"idstr":"1649173367","class":1,"screen_name":"每日经济新闻","name":"每日经济新闻","province":"31","city":"1000","location":"上海","description":"中国主流财经全媒体。每经网nbd.com.cn","url":"http://www.nbd.com.cn","profile_image_url":"http://tva1.sinaimg.cn/crop.17.13.195.195.50/624c6377gw1f2o7dkri5nj206d05vq38.jpg","cover_image":"http://ww2.sinaimg.cn/crop.0.0.920.300/624c6377gw1ermhn6xl48j20pk08c74w.jpg","cover_image_phone":"http://ww2.sinaimg.cn/crop.0.0.0.0/624c6377jw1ew5dltzh5gj20e60e83ys.jpg","profile_url":"mrjjxw","domain":"mrjjxw","weihao":"","gender":"m","followers_count":29464072,"friends_count":2165,"pagefriends_count":163,"statuses_count":132049,"favourites_count":434,"created_at":"Wed Sep 23 12:15:23 +0800 2009","following":true,"allow_all_act_msg":true,"geo_enabled":true,"verified":true,"verified_type":3,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva1.sinaimg.cn/crop.17.13.195.195.180/624c6377gw1f2o7dkri5nj206d05vq38.jpg","avatar_hd":"http://tva1.sinaimg.cn/crop.17.13.195.195.1024/624c6377gw1f2o7dkri5nj206d05vq38.jpg","verified_reason":"《每日经济新闻》官方微�

                itemBlogBean = new Gson().fromJson(result.toString(), ItemBlogBean.class);

                initDatas();
                dismissProgressDialog();

            }

            @Override
            public void onError(String result) {

                Toast.makeText(ItemBlogActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    /**
     * 填充数据
     */
    private void initDatas() {

        String text = itemBlogBean.getText();
        String name = itemBlogBean.getUser().getName();
        List<ItemBlogBean.PicUrlsBean> picUrls = itemBlogBean.getPic_urls();

        listPics = new ArrayList<>();
        retListPics = new ArrayList<>();
        if (picUrls!=null&& picUrls.size()>0){

            for (int i = 0; i < picUrls.size(); i++) {
                String thumbnail_pic = picUrls.get(i).getThumbnail_pic();

                listPics.add(thumbnail_pic);
            }
            GridViewAdapter adapter=new GridViewAdapter(ItemBlogActivity.this, listPics);
            gridView.setAdapter(adapter);
        }

        String profile_image_url = itemBlogBean.getUser().getProfile_image_url();

        blogName.setText(name);
        blogText.setText(text);
        Glide.with(this).load(profile_image_url).transform(new CircleTransform(this)).crossFade().
                placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(blogHead);
        //转发内容
        ItemBlogBean.RetweetedStatusBean retweeted_status = itemBlogBean.getRetweeted_status();
        if (retweeted_status!=null) {
            String retText = retweeted_status.getText();
            blogRetText.setText(retText);
            List<ItemBlogBean.RetweetedStatusBean.RetPicUrlsBean> retPicLists = retweeted_status.getPic_urls();
            if (retPicLists != null && retPicLists.size() > 0) {
                for (int i = 0; i < retPicLists.size(); i++) {
                    String thumbnail_pic = retPicLists.get(i).getThumbnail_pic();
                    retListPics.add(thumbnail_pic);
                }
                TranspondGridViewAdapter transpondGridViewAdapter=new TranspondGridViewAdapter(this, retListPics);
                RetGridView.setAdapter(transpondGridViewAdapter);
            }
        }
    }

    /**
     * 创建对话框
     *
     * @param title
     */
    private void createProgressDialog(String title) {
        if (mProgDoal == null) {
            mProgDoal = new ProgressDialog(this);
        }
        mProgDoal.setTitle(title);
        mProgDoal.setIndeterminate(true);
        mProgDoal.setCancelable(false);
        mProgDoal.show();
    }

    /**
     * 隐藏对话框
     */
    private void dismissProgressDialog() {
        if (mProgDoal != null) {
            mProgDoal.dismiss();
            mProgDoal = null;
        }
    }
}
