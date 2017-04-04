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
