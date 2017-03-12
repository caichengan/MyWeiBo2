package com.xht.android.myweibo.mode;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xht.android.myweibo.R;

import java.util.List;

/**
 * Created by an on 2017/3/12.
 */

public class ListViewCommentAdapter extends BaseAdapter {

    private Context mContext;
    private  List<CommentItem> lsitComment ;


    public ListViewCommentAdapter(Context mContext,  List<CommentItem> lsitComment ) {

        this.mContext=mContext;
        this.lsitComment=lsitComment;

    }


    @Override
    public int getCount() {
        if (lsitComment.size()>0){
            return lsitComment.size();
        }
        return 0;

    }

    @Override
    public Object getItem(int position) {
        return lsitComment.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public  void addList(List<CommentItem> lsit){
        lsitComment.addAll(lsit);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.item_list_comment,null);

            initialize(convertView,holder);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();

        }

        CommentItem commentItem = lsitComment.get(position);

        holder.comment.setText(commentItem.getContext());
        holder.commentListName.setText(commentItem.getName());

        Glide.with(mContext).load(commentItem.getUrlHD()).crossFade().placeholder(R.mipmap.p_head_fail).
                into(holder.commentListHead);




        return convertView;
    }

    private void initialize(View view,ViewHolder holder) {

        holder.commentListHead = (ImageView) view.findViewById(R.id.commentListHead);
        holder.commentListName = (TextView) view.findViewById(R.id.commentListName);
        holder.commentListTime = (TextView) view.findViewById(R.id.commentListTime);
        holder.comment = (TextView) view.findViewById(R.id.comment);
        holder. commentUser = (RelativeLayout) view.findViewById(R.id.commentUser);
    }

    class ViewHolder{
         ImageView commentListHead;
         TextView commentListName;
         TextView commentListTime;
         TextView comment;
         RelativeLayout commentUser;
    }
}
