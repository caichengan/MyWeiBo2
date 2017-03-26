package com.xht.android.myweibo.mode;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.activity.UserActivity;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.TimeFormatUtils;
import com.xht.android.myweibo.view.CircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 *
 * 要控制其显示的方式，通过布局管理器   LayoutManager
 * 要控制item之间的间隔，通过   itemdecoration
 * 控制item增删的动画。请通过  ItemAnimation
 * 控制点击、长按事件，需要自己书写（）
 */

public class RecycleAdapter extends RecyclerView.Adapter {

    private static final String TAG ="RecycleAdapter" ;
    private Context mContext;
    private  List<StatusEntity.StatusesBean> mPublicList;

    private OnItemClickListener itemClickListener;



    public RecycleAdapter(FragmentActivity activity, List<StatusEntity.StatusesBean> mListStatuses) {
        this.mContext=activity;
        this.mPublicList=mListStatuses;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_listview, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RecycleViewHolder){
            RecycleViewHolder rvHolder= (RecycleViewHolder) holder;
            StatusEntity.StatusesBean publicLine = mPublicList.get(position);

            rvHolder.newListName.setText(publicLine.getUser().getScreen_name());

            rvHolder.uresContent.setText(publicLine.getText());
            rvHolder.newSources.setText(Html.fromHtml(publicLine.getSource()));
            rvHolder.newListTime.setText(TimeFormatUtils.parseYYMMDD(publicLine.getCreated_at()));


            StatusEntity.StatusesBean.RetweetedStatusBean retweetedStatus = mPublicList.get(position).getRetweeted_status();

            if (retweetedStatus!=null){
                rvHolder.linTranspond.setVisibility(View.VISIBLE);
                rvHolder.transpond.setText(retweetedStatus.getText());


                List<PicEntity> retweetedStatusPicUrls = (List<PicEntity>) retweetedStatus.getPic_urls();
                if (retweetedStatusPicUrls!=null&& retweetedStatusPicUrls.size()>0) {
                    String thumbnail_pic = retweetedStatusPicUrls.get(0).getThumbnail_pic();
                    LogHelper.i(TAG,"----------retweetedStatusPicUrls--------"+thumbnail_pic);
                    rvHolder.ChangePic.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(thumbnail_pic).
                            error(R.mipmap.ic_launcher).placeholder(R.mipmap.p_head_fail).into(rvHolder.imgChangePic);
                }else {
                    rvHolder.ChangePic.setVisibility(View.GONE);
                }

            }else{
                rvHolder.linTranspond.setVisibility(View.GONE);
                rvHolder.ChangePic.setVisibility(View.GONE);
            }


            Glide.with(mContext).load(mPublicList.get(position).getUser().getProfile_image_url()).
                    transform(new CircleTransform(mContext)).error(R.mipmap.ic_launcher).placeholder(R.mipmap.p_head_fail).into(rvHolder.newListHead);

            List<StatusEntity.StatusesBean.PicUrlsBean> picList = mPublicList.get(position).getPic_urls();
            int size = picList.size();
            if (picList!=null&& size>0){
                StatusEntity.StatusesBean.PicUrlsBean picEntity = picList.get(0);
                rvHolder.imgPicture.setVisibility(View.VISIBLE);
                LogHelper.i(TAG,"------pic-----"+position+"------"+picEntity.getThumbnail_pic());
                LogHelper.i(TAG,"------pic-----");
                Glide.with(mContext).load(picEntity.getThumbnail_pic()).into(rvHolder.imgPicture).getRequest();

               /* if (size>1){
                    PicEntity picEntity1=picList.get(1);
                    rvHolder.imgPicture1.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(picEntity1.getOriginal_pic()).into(rvHolder.imgPicture1);
                    if (size>2){
                        PicEntity picEntity2=picList.get(2);
                        rvHolder.imgPicture2.setVisibility(View.VISIBLE);
                        Glide.with(mContext).load(picEntity2.getOriginal_pic()).into(rvHolder.imgPicture2);
                    }else{
                        rvHolder.imgPicture2.setVisibility(View.INVISIBLE);
                    }
                }else{
                    rvHolder.imgPicture1.setVisibility(View.INVISIBLE);
                }
*/
            }else{
                rvHolder.linPic.setVisibility(View.GONE);
            }




            final Bundle bundle=new Bundle();
            StatusEntity.StatusesBean.UserBean user = mPublicList.get(position).getUser();

            rvHolder.linUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "内容", Toast.LENGTH_SHORT).show();

                }
            });

            rvHolder.newListHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "头像", Toast.LENGTH_SHORT).show();
                    IntentUtils.startActivityNumber((Activity) mContext,bundle,UserActivity.class);

                }
            });

            rvHolder.uresContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "内容", Toast.LENGTH_SHORT).show();
                }
            });


        }

    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        itemClickListener=mOnItemClickListener;
    }

    public  interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    @Override
    public int getItemCount() {
        return mPublicList.size();
    }



    class RecycleViewHolder extends RecyclerView.ViewHolder{

        private ImageView newListHead;
        private ImageView imgPicture;
        private ImageView imgPicture1;
        private LinearLayout linPic;
        private LinearLayout ChangePic;
        private ImageView imgPicture2;
        private ImageView imgChangePic;
        private ImageView imgChangePic1;
        private ImageView imgChangePic2;
        private TextView newListName;
        private TextView newListTime;
        private RelativeLayout linUser;
        private TextView uresContent;
        private TextView newSources;
        private LinearLayout linContent;
        private TextView transpond;
        private LinearLayout linTranspond;
        private TextView userReport;
        private LinearLayout linReport;
        private TextView userCommit;
        private LinearLayout linCommit;
        private TextView userAttidude;
        private LinearLayout linAttidude;

        private void initialize(View itemView) {

            newListHead = (ImageView) itemView.findViewById(R.id.newListHead);
            imgPicture = (ImageView) itemView.findViewById(R.id.imgPicture);
            linPic = (LinearLayout) itemView.findViewById(R.id.linPic);
            ChangePic = (LinearLayout) itemView.findViewById(R.id.ChangePic);
            imgChangePic = (ImageView) itemView.findViewById(R.id.imgChangePic);

            newListName = (TextView) itemView.findViewById(R.id.newListName);
            newSources = (TextView) itemView.findViewById(R.id.newSources);
            newListTime = (TextView) itemView.findViewById(R.id.newListTime);
            linUser = (RelativeLayout) itemView.findViewById(R.id.linUser);
            uresContent = (TextView) itemView.findViewById(R.id.uresContent);
            linContent = (LinearLayout) itemView.findViewById(R.id.linContent);
            transpond = (TextView) itemView.findViewById(R.id.transpond);
            linTranspond = (LinearLayout) itemView.findViewById(R.id.linTranspond);
            userReport = (TextView) itemView.findViewById(R.id.userReport);
            linReport = (LinearLayout) itemView.findViewById(R.id.linReport);
            userCommit = (TextView) itemView.findViewById(R.id.userCommit);
            linCommit = (LinearLayout) itemView.findViewById(R.id.linCommit);
            userAttidude = (TextView) itemView.findViewById(R.id.userAttidude);
            linAttidude = (LinearLayout) itemView.findViewById(R.id.linAttidude);
        }

        public RecycleViewHolder(final View itemView) {
            super(itemView);
            initialize(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener!=null){
                        itemClickListener.onItemClick(itemView,getLayoutPosition());
                    }
                }
            });


        }
    }
}
