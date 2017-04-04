package com.xht.android.myweibo.mode;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.activity.LoadImgActivity;
import com.xht.android.myweibo.activity.LoadOneImgActivity;
import com.xht.android.myweibo.activity.ReportWeiBoActivity;
import com.xht.android.myweibo.activity.UrlBlogActivity;
import com.xht.android.myweibo.activity.UserActivity;
import com.xht.android.myweibo.activity.WeiBoCommentActivity;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;
import com.xht.android.myweibo.utils.TimeFormatUtils;
import com.xht.android.myweibo.utils.Utils;
import com.xht.android.myweibo.view.CircleTransform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微博主页ListView的适配器
 */
public class ListNewsAdapter extends BaseAdapter {
    private Context mContext;
    private List<StatusEntity.StatusesBean>   mListDatas;
    private static final String TAG = "ListNewsAdapter";
    private  SharpUtils sharpUtils;;
    private static final  String TOPIC="#.+?#";//话题
    private static final  String NAME="@([\u4e00-\u9fa5A-z0-9_]*)";//人名
    private static final  String URL="http://.*";//Url
    private ArrayList<String> listPics;
    private ArrayList<String> listRetPics;
    public ListNewsAdapter(FragmentActivity activity, List<StatusEntity.StatusesBean> mListStatuses) {
        this.mContext=activity;
        this.mListDatas=mListStatuses;
        sharpUtils=SharpUtils.getInstance(mContext);
        listPics=new ArrayList<>();
        listRetPics=new ArrayList<>();
    }
    @Override
    public int getCount() {
        if (mListDatas.size()>0){
            return mListDatas.size();
        }
        return 0;
    }



    @Override
    public Object getItem(int position) {
        return mListDatas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.item_news_listview,null);
            holder. newListHead = (ImageView) convertView.findViewById(R.id.newListHead);
            holder.newListName = (TextView) convertView.findViewById(R.id.newListName);
            holder.newListTime = (TextView) convertView.findViewById(R.id.newListTime);
            holder. newSources = (TextView) convertView.findViewById(R.id.newSources);
            holder.uresContent = (TextView) convertView.findViewById(R.id.uresContent);
            holder.imgPicture = (ImageView) convertView.findViewById(R.id.imgPicture);
            holder.linPic = (LinearLayout) convertView.findViewById(R.id.linPic);
            holder.linContent = (LinearLayout) convertView.findViewById(R.id.linContent);
            holder.transpond = (TextView) convertView.findViewById(R.id.transpond);//转发内容
            holder.imgChangePic = (ImageView) convertView.findViewById(R.id.imgChangePic);//转发图片
            holder.ChangePic = (LinearLayout) convertView.findViewById(R.id.ChangePic);
            holder.linTranspond = (LinearLayout) convertView.findViewById(R.id.linTranspond);
            holder. userReport = (TextView) convertView.findViewById(R.id.userReport);
            holder.linReport = (LinearLayout) convertView.findViewById(R.id.linReport);
            holder.linUser = (RelativeLayout) convertView.findViewById(R.id.linUser);
            holder.userCommit = (TextView) convertView.findViewById(R.id.userCommit);
            holder.linCommit = (LinearLayout) convertView.findViewById(R.id.linCommit);
            holder. userAttidude = (TextView) convertView.findViewById(R.id.userAttidude);
            holder. linAttidude = (LinearLayout) convertView.findViewById(R.id.linAttidude);
           convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        int size = mListDatas.size();
        final StatusEntity.StatusesBean publicLine = mListDatas.get(position);
        holder.newListName.setText(publicLine.getUser().getScreen_name());
        final String text = publicLine.getText();
        SpannableString spannedString = new SpannableString(text);
        Utils.HightLignt(spannedString,text,Pattern.compile(TOPIC));
        Utils. HightLignt(spannedString,text,Pattern.compile(NAME));
        Utils.HightLignt(spannedString,text,Pattern.compile(URL));
        holder.uresContent.setText(spannedString);
        holder.newSources.setText(Html.fromHtml(publicLine.getSource()));
        holder.newListTime.setText(TimeFormatUtils.parseYYMMDD(publicLine.getCreated_at()));
       /* holder.newListTime.setText(TimeFormatUtils.parseYYMMDD(publicLine.getCreated_at()));
        holder.uresContent.setText(publicLine.getText());
        holder.newSources.setText(Html.fromHtml(publicLine.getSource()));*/
        //thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。//TODO
        int reposts_count = publicLine.getReposts_count();//转发数
        int comments_count = publicLine.getComments_count();//评论数
        int attitudes_count = publicLine.getAttitudes_count();//表点赞
        final String bmiddle_pic = publicLine.getBmiddle_pic();
        holder. userReport.setText(reposts_count+"");
        holder. userCommit.setText(comments_count+"");
        holder. userAttidude .setText(attitudes_count+"");
        Glide.with(mContext).load(mListDatas.get(position).getUser().getProfile_image_url()).
                transform(new CircleTransform(mContext)).error(R.mipmap.ic_launcher).placeholder(R.mipmap.p_head_fail).into(holder.newListHead);
        final List<?> pic_urls = mListDatas.get(position).getPic_urls();
        LogHelper.i(TAG,"----------pic_urls.size()-------"+"-------"+ pic_urls.size());
        if (pic_urls!=null&& pic_urls.size()>0) {
            for (int i = 0; i < pic_urls.size(); i++) {
                String pics= pic_urls.get(i).toString();
                int index = pics.indexOf("=");
                String substring = pics.substring(index+1, pics.length() - 1);
                LogHelper.i(TAG,"----------sub-------"+"-------"+ substring);
            }
            holder.linPic.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(bmiddle_pic)
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(holder.imgPicture);
        }else {
            holder.linPic.setVisibility(View.GONE);
        }
        holder.imgPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(bmiddle_pic)){
                    Bundle bundle=new Bundle();
                    bundle.putString("url",bmiddle_pic);
                    IntentUtils.startActivityNumber((Activity) mContext,bundle, LoadOneImgActivity.class);
                }
            }
        });
        StatusEntity.StatusesBean.RetweetedStatusBean retweetedStatus = mListDatas.get(position).getRetweeted_status();
        if (retweetedStatus!=null){
            holder.linTranspond.setVisibility(View.VISIBLE);
            holder.transpond.setText(retweetedStatus.getText());
            List<?> pic_urls_retweeted = retweetedStatus.getPic_urls();
            if (pic_urls_retweeted!=null&& pic_urls_retweeted.size()>0) {
                String thumbnail_pic = pic_urls_retweeted.get(0).toString();
                int index = thumbnail_pic.indexOf("=");
                String[] split = thumbnail_pic.replace("{", "").replace("}", "").split("=");
                String thumbnailChange = thumbnail_pic.substring(index+1, thumbnail_pic.length() - 1);
                LogHelper.i(TAG,"----------retweetedStatusPicUrls--------"+thumbnailChange);
                if (thumbnailChange.contains("thumbnail")){
                    thumbnailChange=  thumbnailChange.replace("thumbnail","bmiddle");
                }
                holder.linTranspond.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(thumbnailChange).
                        error(R.mipmap.ic_launcher).placeholder(R.mipmap.p_head_fail).into(holder.imgChangePic);
            }else {
                holder.linTranspond.setVisibility(View.GONE);
            }
        }else{
            holder.linTranspond.setVisibility(View.GONE);
        }
        final Bundle bundle=new Bundle();
        holder.newListHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //头像点击进入用户具体信息
                bundle.putLong("id",mListDatas.get(position).getUser().getId());
                IntentUtils.startActivityNumber((Activity) mContext,bundle,UserActivity.class);
            }
        });
        holder.linReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转发
                listPics.clear();
                listRetPics.clear();
                /**
                 * access_token 	true 	string 	采用OAuth授权方式为必填参数，OAuth授权后获得。
                 id 	true 	int64 	要转发的微博ID。
                 status 	false 	string 	添加的转发文本，必须做URLencode，内容不超过140个汉字，不填则默认为“转发微博”。
                 is_comment 	false 	int 	是否在转发的同时发表评论，0：否、1：评论给当前微博、2：评论给原微博、3：都评论，默认为0 。
                 rip 	false 	string
                 */
                List<StatusEntity.StatusesBean.PicUrlsBean> picUrls = publicLine.getPic_urls();
                bundle.putString("accessToken", SharpUtils.getInstance(mContext).getToken().getToken());
                bundle.putLong("id", mListDatas.get(position).getId());
                bundle.putString("text", mListDatas.get(position).getText());
                bundle.putString("imgUrl", "" + bmiddle_pic);
                LogHelper.i(TAG,"--------pos==url------"+bmiddle_pic);
                LogHelper.i(TAG,"--------id------"+mListDatas.get(position).getId());
                StatusEntity.StatusesBean.RetweetedStatusBean retweeted_status = publicLine.getRetweeted_status();
                if (retweeted_status!=null){
                    bundle.putString("chuangfa","yes");
                    bundle.putLong("ret_id",retweeted_status.getId());
                    bundle.putString("ret_text",retweeted_status.getText());
                    List<?> pic_urls_retweeted = retweeted_status.getPic_urls();
                    if (pic_urls_retweeted!=null&& pic_urls_retweeted.size()>0) {
                        for (int i = 0; i < pic_urls_retweeted.size(); i++) {
                            String thumbnail_pic = pic_urls_retweeted.get(i).toString();
                            int index = thumbnail_pic.indexOf("=");
                            String thumbnailChange = thumbnail_pic.substring(index+1, thumbnail_pic.length() - 1);
                            LogHelper.i(TAG,"----------i--------"+thumbnailChange);
                            listRetPics.add(thumbnailChange);
                        }
                        bundle.putStringArrayList("retPicsList",listRetPics);
                    }
                }else{
                    bundle.putString("chuangfa","not");
                }
                for (int i = 0; i < picUrls.size(); i++) {
                    String thumbnail_pic = picUrls.get(i).getThumbnail_pic();
                    LogHelper.i(TAG,"-----"+thumbnail_pic);
                    listPics.add(thumbnail_pic);
                }
                LogHelper.i(TAG,"----size---0-----"+picUrls.toString());
                bundle.putStringArrayList("picList",  listPics);
                LogHelper.i(TAG,"--------id------"+mListDatas.get(position).getId());
                IntentUtils.startActivityNumber((Activity) mContext,bundle,ReportWeiBoActivity.class);

            }
        });
        holder.linCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //评论
                listPics.clear();
                listRetPics.clear();
                Toast.makeText(mContext, "评论", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                StatusEntity.StatusesBean.RetweetedStatusBean retweeted_status = publicLine.getRetweeted_status();
                if (retweeted_status!=null){
                    bundle.putString("chuangfa","yes");
                    bundle.putLong("ret_id",retweeted_status.getId());
                    bundle.putString("ret_text",retweeted_status.getText());
                    List<?> pic_urls_retweeted = retweeted_status.getPic_urls();
                    if (pic_urls_retweeted!=null&& pic_urls_retweeted.size()>0) {
                        for (int i = 0; i < pic_urls_retweeted.size(); i++) {
                            String thumbnail_pic = pic_urls_retweeted.get(i).toString();
                            int index = thumbnail_pic.indexOf("=");
                            String thumbnailChange = thumbnail_pic.substring(index+1, thumbnail_pic.length() - 1);
                            LogHelper.i(TAG,"----------i--------"+thumbnailChange);
                            listRetPics.add(thumbnailChange);
                        }
                       // bundle.putString("ret_thumbnail",thumbnailChange);
                    }
                }else{
                    bundle.putString("chuangfa","not");
                }
                bundle.putStringArrayList("retPicsList",listRetPics);
                    bundle.putString("text",text);
                    bundle.putString("urlHD",publicLine.getUser().getProfile_image_url());
                    bundle.putString("name",publicLine.getUser().getName());
                    bundle.putString("time",publicLine.getCreated_at());
                    bundle.putLong("id",publicLine.getId());
                    bundle.putString("mid",publicLine.getMid());
                    bundle.putString("sources",publicLine.getSource());
                List<StatusEntity.StatusesBean.PicUrlsBean> picUrls = publicLine.getPic_urls();
                for (int i = 0; i < picUrls.size(); i++) {
                    String thumbnail_pic = picUrls.get(i).getThumbnail_pic();
                    LogHelper.i(TAG,"-----"+thumbnail_pic);
                    listPics.add(thumbnail_pic);
                }
                bundle.putStringArrayList("picList",  listPics);
                    if (!TextUtils.isEmpty(bmiddle_pic))
                    {
                        bundle.putString("bmiddle_pic",bmiddle_pic);
                    }
                LogHelper.i(TAG,"----text-----"+text,"--"+publicLine.getUser().getProfile_image_url()+publicLine.getUser().getName()+publicLine.getId()+"--mid-"+publicLine.getMid());
                IntentUtils.startActivityNumber((Activity) mContext,bundle,WeiBoCommentActivity.class);
            }
        });
        holder.linAttidude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点赞
                Toast.makeText(mContext, "点赞", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
    class ViewHolder{
         ImageView newListHead;
         TextView newListName;
         TextView newListTime;
         TextView newSources;
         RelativeLayout linUser;//http://wx1.sinaimg.cn/thumbnail/deb13e2dly1fdz63u8neqj20au0auaas.jpg
         TextView uresContent;
         LinearLayout linContent;
         ImageView imgPicture;
         ImageView imgPicture1;
         ImageView imgPicture2;
         LinearLayout linPic;
         TextView transpond;//ChangePic 转发图片
         ImageView imgChangePic;
         ImageView imgChangePic1;
         ImageView imgChangePic2;
         LinearLayout ChangePic;
         LinearLayout linTranspond;
         TextView userReport;
         LinearLayout linReport;
         TextView userCommit;
         LinearLayout linCommit;
         TextView userAttidude;
         LinearLayout linAttidude;
    }
}
