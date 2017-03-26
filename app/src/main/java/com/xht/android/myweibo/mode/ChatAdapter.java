package com.xht.android.myweibo.mode;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.view.CircleTransform;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by an on 2017/3/17.
 */

public class ChatAdapter extends BaseAdapter {

    private Context mContext;
    private List<ChatBean> listChatDatas;
    private ViewHolder holder;

    public ChatAdapter(Context mContext, List<ChatBean> listChatDatas) {
        this.mContext = mContext;
        this.listChatDatas = listChatDatas;
    }

    @Override
    public int getCount() {
        if (listChatDatas.size() > 0) {
            return listChatDatas.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listChatDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_chat, null);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }


        ChatBean chatBean = listChatDatas.get(position);
        String type = chatBean.getType();
        if (type.equals("0")) {
            holder.linMyself.setVisibility(View.VISIBLE);
            holder.linOther.setVisibility(View.INVISIBLE);

            holder.myContent.setText(chatBean.getmContent());

            holder.myName.setText(chatBean.getmName());
            Glide.with(mContext).load(chatBean.getImgURLHD()).transform(new CircleTransform(mContext)).crossFade().
                    error(R.mipmap.p_head_fail).placeholder(R.mipmap.ic_launcher).into(holder.myHead);


        }

        if (type.equals("1")) {
            holder.linOther.setVisibility(View.VISIBLE);
            holder.linMyself.setVisibility(View.INVISIBLE);

            holder.otherName.setText(chatBean.getmName());
            holder.otherContent.setText(chatBean.getmContent());
            Glide.with(mContext).load(chatBean.getImgURLHD()).transform(new CircleTransform(mContext)).crossFade().
                    error(R.mipmap.p_head_fail).placeholder(R.mipmap.ic_launcher).into(holder.otherHead);

        }


        return convertView;
    }




    static class ViewHolder {
        @InjectView(R.id.myContent)
        TextView myContent;
        @InjectView(R.id.myName)
        TextView myName;
        @InjectView(R.id.myHead)
        ImageView myHead;
        @InjectView(R.id.linMyself)
        RelativeLayout linMyself;
        @InjectView(R.id.otherHead)
        ImageView otherHead;
        @InjectView(R.id.otherName)
        TextView otherName;
        @InjectView(R.id.otherContent)
        TextView otherContent;
        @InjectView(R.id.linOther)
        RelativeLayout linOther;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
