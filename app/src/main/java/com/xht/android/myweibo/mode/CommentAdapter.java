package com.xht.android.myweibo.mode;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xht.android.myweibo.R;
import com.xht.android.myweibo.activity.WeiBoCommentActivity;

import java.util.List;

/**
 * Created by an on 2017/3/10.
 */

public class CommentAdapter extends BaseAdapter {

    private Context mContext;
    private List<CommentEntity> mCommentList;
    public CommentAdapter(Context mContext, List<CommentEntity> commentList) {
        this.mCommentList=commentList;
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        if (mCommentList.size()>0){
            return mCommentList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mCommentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){

            convertView=View.inflate(mContext,R.layout.item_list_comment,null);

        }else{

        }




        return convertView;
    }
}
