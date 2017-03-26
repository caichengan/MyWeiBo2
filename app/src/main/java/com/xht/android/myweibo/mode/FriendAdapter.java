package com.xht.android.myweibo.mode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.view.CircleTransform;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by an on 2017/3/16.
 */

public class FriendAdapter extends BaseAdapter {
    private Context mContext;
    private List<FriendEntity.UsersBean> friendUser;
    private ViewHolder holder;

    public FriendAdapter(Context mContext, List<FriendEntity.UsersBean> friendList) {
        this.mContext = mContext;
        this.friendUser = friendList;

    }

    @Override
    public int getCount() {
        if (friendUser.size() > 0) {
            return friendUser.size();
        }
        return 0;

    }

    @Override
    public Object getItem(int position) {
        return friendUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.item_friend_message, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {

            holder= (ViewHolder) convertView.getTag();
        }

        FriendEntity.UsersBean usersBean = friendUser.get(position);

        Glide.with(mContext).load(usersBean.getProfile_image_url()).transform(new CircleTransform(mContext)).error(R.mipmap.p_head_fail).
                crossFade().placeholder(R.mipmap.ic_launcher).into(holder.messageListHead);

        holder.messageListName.setText(usersBean.getName());
        holder.message.setText(usersBean.getDescription());


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.messageListHead)
        ImageView messageListHead;
        @InjectView(R.id.messageListName)
        TextView messageListName;
        @InjectView(R.id.messageListTime)
        TextView messageListTime;
        @InjectView(R.id.message)
        TextView message;
        @InjectView(R.id.messageUser)
        RelativeLayout messageUser;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
