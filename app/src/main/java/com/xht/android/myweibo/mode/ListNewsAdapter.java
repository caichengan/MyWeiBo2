package com.xht.android.myweibo.mode;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Spanned;
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
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.TimeFormatUtils;
import com.xht.android.myweibo.view.CircleTransform;

import java.util.List;

/*
*
 * Created by Administrator on 2017/1/13.

*/


public class ListNewsAdapter extends BaseAdapter {

    private Context mContext;
    private List<StatusEntity.StatusesBean>   mListDatas;
    private static final String TAG = "ListNewsAdapter";




    public ListNewsAdapter(FragmentActivity activity, List<StatusEntity.StatusesBean> mListStatuses) {
        this.mContext=activity;
        this.mListDatas=mListStatuses;
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
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.item_news_listview,null);

            holder. newListHead = (ImageView) convertView.findViewById(R.id.newListHead);
            holder.newListName = (TextView) convertView.findViewById(R.id.newListName);
            holder.newListTime = (TextView) convertView.findViewById(R.id.newListTime);
            holder. newSources = (TextView) convertView.findViewById(R.id.newSources);
            holder.uresContent = (TextView) convertView.findViewById(R.id.uresContent);
            holder.imgPicture = (ImageView) convertView.findViewById(R.id.imgPicture);
            holder. imgPicture1 = (ImageView) convertView.findViewById(R.id.imgPicture1);
            holder. imgPicture2 = (ImageView) convertView.findViewById(R.id.imgPicture2);

            holder.linPic = (LinearLayout) convertView.findViewById(R.id.linPic);
            holder.linContent = (LinearLayout) convertView.findViewById(R.id.linContent);
            holder.transpond = (TextView) convertView.findViewById(R.id.transpond);//转发内容
            holder.imgChangePic = (ImageView) convertView.findViewById(R.id.imgChangePic);//转发图片
            holder. imgChangePic1 = (ImageView) convertView.findViewById(R.id.imgChangePic1);
            holder.imgChangePic2 = (ImageView) convertView.findViewById(R.id.imgChangePic2);
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
        StatusEntity.StatusesBean publicLine = mListDatas.get(position);

        holder.newListName.setText(publicLine.getUser().getScreen_name());
        //


        //[a-zA-z]+://[^\s]*
        String text = publicLine.getText();
        holder.uresContent.setText(text);



  /*      // 要验证的字符串
        String str =publicLine.getText();
        // 邮箱验证规则
        String regEx = "^((http|https)://)?([\\w-]+\\.)+[\\w-]+(/[\\w -./?%&=]*)?$ ";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        if (rs){
        }
        System.out.println(rs);*/




        holder.newSources.setText(Html.fromHtml(publicLine.getSource()));
        holder.newListTime.setText(TimeFormatUtils.parseYYMMDD(publicLine.getCreated_at()));

       /* holder.newListTime.setText(TimeFormatUtils.parseYYMMDD(publicLine.getCreated_at()));
        holder.uresContent.setText(publicLine.getText());
        holder.newSources.setText(Html.fromHtml(publicLine.getSource()));*/
        //thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。//TODO

        int reposts_count = publicLine.getReposts_count();//转发数
        int comments_count = publicLine.getComments_count();//评论数
        int attitudes_count = publicLine.getAttitudes_count();//表点赞

        String bmiddle_pic = publicLine.getBmiddle_pic();



        String source = publicLine.getSource();
        Spanned spanned = Html.fromHtml(source);

        holder. userReport.setText(reposts_count+"");
        holder. userCommit.setText(comments_count+"");
        holder. userAttidude .setText(attitudes_count+"");

        Glide.with(mContext).load(mListDatas.get(position).getUser().getProfile_image_url()).
                transform(new CircleTransform(mContext)).error(R.mipmap.ic_launcher).placeholder(R.mipmap.p_head_fail).into(holder.newListHead);

        List<?> pic_urls = mListDatas.get(position).getPic_urls();

        LogHelper.i(TAG,"----------pic_urls.size()-------"+"-------"+ pic_urls.size());
        if (pic_urls!=null&& pic_urls.size()>0) {

            for (int i = 0; i < pic_urls.size(); i++) {


                String pics= pic_urls.get(i).toString();

                int index = pics.indexOf("=");

                String substring = pics.substring(index+1, pics.length() - 1);

                LogHelper.i(TAG,"----------sub-------"+"-------"+ substring);
            }


          /*  String picUrl = pic_urls.get(0).toString();
            int index = picUrl.indexOf("=");
            String[] split = picUrl.replace("{", "").replace("}", "").split("=");
            LogHelper.i(TAG,"----------0--------"+position+"-------"+ split[0]);
            LogHelper.i(TAG,"----------1--------"+position+"-------"+ split[1]);*/
            holder.linPic.setVisibility(View.VISIBLE);

            Glide.with(mContext)
                    .load(bmiddle_pic)
                    .placeholder(R.mipmap.p_head_fail)
                    .crossFade()
                    .into(holder.imgPicture);
        }else {
            holder.linPic.setVisibility(View.GONE);
        }

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
                Toast.makeText(mContext, "头像", Toast.LENGTH_SHORT).show();

                bundle.putLong("uid",mListDatas.get(position).getUser().getId());


               // IntentUtils.startActivityNumber((Activity) mContext,bundle,UserActivity.class);

            }
        });
        return convertView;
    }

    class ViewHolder{
         ImageView newListHead;
         TextView newListName;
         TextView newListTime;
         TextView newSources;
         RelativeLayout linUser;
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
