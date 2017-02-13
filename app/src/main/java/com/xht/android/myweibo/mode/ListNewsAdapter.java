package com.xht.android.myweibo.mode;

import android.content.Context;
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

import com.xht.android.myweibo.R;

import java.util.List;

/*
*
 * Created by Administrator on 2017/1/13.

*/


public class ListNewsAdapter extends BaseAdapter {

    private Context mContext;
    private List<PublicLine>  mListDatas;
    private static final String TAG = "ListNewsAdapter";




    public ListNewsAdapter(FragmentActivity activity, List<PublicLine> mPublicList) {

        this.mContext=activity;
        this.mListDatas=mPublicList;
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
            holder. linUser = (RelativeLayout) convertView.findViewById(R.id.linUser);
            holder.linContent = (LinearLayout) convertView.findViewById(R.id.linContent);
            holder.linReport = (LinearLayout) convertView.findViewById(R.id.linReport);
            holder.linCommit = (LinearLayout) convertView.findViewById(R.id.linCommit);
            holder.linAttidude = (LinearLayout) convertView.findViewById(R.id.linAttidude);

           convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();


        }



        holder. linUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.linContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder. linReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder. linCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder. linAttidude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        int size = mListDatas.size();
            PublicLine publicLine = mListDatas.get(position);
            String created_at = publicLine.getCreated_at();//发微博的时间
            String text = publicLine.getText();//微博的内容
            String original_pic = publicLine.getOriginal_pic();
            String thumbnail_pic = publicLine.getThumbnail_pic();
        //thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。//TODO

        int reposts_count = publicLine.getReposts_count();//转发数
        int comments_count = publicLine.getComments_count();//评论数
        int attitudes_count = publicLine.getAttitudes_count();//表点赞

        PublicLine.UserBean userBean = publicLine.getUser();
        String name = userBean.getName();//用户昵称
        String screen_name = userBean.getScreen_name();//友好显示名称
        String source = publicLine.getSource();
        Spanned spanned = Html.fromHtml(source);
        holder.newListName.setText(name+"");
        holder. newListTime.setText(created_at+"");
        holder. newListContent.setText(text+spanned);
        holder. userReport.setText(reposts_count+"");
        holder. userCommit.setText(comments_count+"");
        holder. userAttidude .setText(attitudes_count+"");

       /*  String text = statusesBean.getText();
        int reposts_count = statusesBean.getReposts_count();
        int comments_count = statusesBean.getComments_count();
        int attitudes_count = statusesBean.getAttitudes_count();
        String thumbnail_pic = statusesBean.getThumbnail_pic();
        List<PublicLine.StatusesBean.PicUrlsBean> pic_urls = statusesBean.getPic_urls();
        String avatar_large = user.getAvatar_large();//

        String createTime = user.getCreated_at();
        String userName = user.getName();
        String userUrl = user.getProfile_image_url();//用户头像地址
        holder. userReport.setText(reposts_count+"");
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
        TextView userAttidude ;

        RelativeLayout linUser;
        LinearLayout linContent;
        LinearLayout linReport;
        LinearLayout linCommit;
        LinearLayout linAttidude;
    }


}
