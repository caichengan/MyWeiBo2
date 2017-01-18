package com.xht.android.myweibo.mode;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xht.android.myweibo.R;
import com.xht.android.myweibo.utils.AsyncImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 */

public class ListNewsAdapter extends BaseAdapter {

    private Context mContext;
    private List<PublicLine>  mListDatas;
    private static final String TAG = "ListNewsAdapter";


    private AsyncImageLoader asyncImageLoader;


    public ListNewsAdapter(FragmentActivity activity, List<PublicLine> mPublicList) {

        this.mContext=activity;
        this.mListDatas=mPublicList;
        asyncImageLoader=new AsyncImageLoader(mContext);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){

            holder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.item_news_listview,null);

            holder.newListHead = (ImageView) convertView.findViewById(R.id.newListHead);
            holder.newListName = (TextView)convertView. findViewById(R.id.newListName);
            holder. newListTime = (TextView)convertView. findViewById(R.id.newListTime);
            holder. newListContent = (TextView)convertView. findViewById(R.id.uresContent);
            holder. userReport = (TextView)convertView. findViewById(R.id.userReport);
            holder. userCommit = (TextView)convertView. findViewById(R.id.userCommit);
            holder. userAttidude = (TextView)convertView. findViewById(R.id.userAttidude);

            holder. lin01 = (RelativeLayout) convertView.findViewById(R.id.lin01);
            holder.lin02 = (LinearLayout) convertView.findViewById(R.id.lin02);
            holder.lin03 = (LinearLayout) convertView.findViewById(R.id.lin03);
            holder.lin04 = (LinearLayout) convertView.findViewById(R.id.lin04);
            holder.lin05 = (LinearLayout) convertView.findViewById(R.id.lin05);

           convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();


        }



        holder. lin01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder. lin02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder. lin03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder. lin04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder. lin05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        int size = mListDatas.size();
            PublicLine publicLine = mListDatas.get(position);
            String created_at = publicLine.getCreated_at();
            String text = publicLine.getText();
            String original_pic = publicLine.getOriginal_pic();
            List<String> pic_urls = publicLine.getPic_urls();
            String thumbnail_pic = publicLine.getThumbnail_pic();

        String reposts_count = publicLine.getReposts_count();
        String comments_count = publicLine.getComments_count();
        String attitudes_count = publicLine.getAttitudes_count();

        PublicLine.UserBean userBean = publicLine.getmUser();

        String name = userBean.getName();


        asyncImageLoader.loadImage(holder.newListHead,userBean.getProfile_image_url());

        holder.newListName.setText(name+"");
        holder. newListTime.setText(created_at+"");
        holder. newListContent.setText(text+"");

        holder. userReport.setText(reposts_count+"");
        holder. userCommit.setText(comments_count+"");

        holder. userAttidude .setText(attitudes_count+"");

    /*    String text = statusesBean.getText();//微博的内容

        int reposts_count = statusesBean.getReposts_count();//转发数
        int comments_count = statusesBean.getComments_count();//评论数
        int attitudes_count = statusesBean.getAttitudes_count();//表态数

        String thumbnail_pic = statusesBean.getThumbnail_pic();
        List<PublicLine.StatusesBean.PicUrlsBean> pic_urls = statusesBean.getPic_urls();

        //thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。//TODO

        String avatar_large = user.getAvatar_large();//
        String screen_name = user.getScreen_name();//友好显示名称
        String createTime = user.getCreated_at();//发微博的时间
        String userName = user.getName();//用户昵称
        String userUrl = user.getProfile_image_url();//用户头像地址*/

     /*   holder. userReport.setText(reposts_count+"");
        holder. userCommit.setText(comments_count+"");

        LogHelper.i(TAG,"-------userAttidude---"+userName+text+attitudes_count++);


        holder.newListName.setText(userName+"");
        holder. newListTime.setText(""+createTime);
        holder. newListContent.setText(text+"");
        holder. userAttidude.setText(attitudes_count+"");*/


        return convertView;
    }



    class ViewHolder{
        ImageView newListHead;
        TextView newListName;
        TextView newListTime;
        TextView newListContent;
        TextView userReport;
        TextView userCommit;
        TextView  userAttidude ;

        RelativeLayout lin01;
        LinearLayout lin02;
        LinearLayout lin03;
        LinearLayout lin04;
        LinearLayout lin05;
    }


}
