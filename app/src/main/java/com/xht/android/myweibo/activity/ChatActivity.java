package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.mode.ChatAdapter;
import com.xht.android.myweibo.mode.ChatBean;
import com.xht.android.myweibo.mode.UserEntity;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by an on 2017/3/16.
 */
public class ChatActivity extends Activity {

    @InjectView(R.id.chatImg)
    ImageView chatImg;
    @InjectView(R.id.chatUser)
    TextView chatUser;
    @InjectView(R.id.chatListView)
    ListView chatListView;
    @InjectView(R.id.chatEdit)
    EditText chatEdit;
    @InjectView(R.id.chatSend)
    TextView chatSend;


    private static final String TAG = "ChatActivity";
    @InjectView(R.id.chatMy)
    ImageView chatMy;
    private long id;
    private String userName;
    private String userHeadURL;
    private long userId;
    private List<ChatBean> listChatDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        ButterKnife.inject(this);


        chatImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        chatMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putLong("id",id);

                //IntentUtils.startActivityNumber(ChatActivity.this,bundle,UserActivity.class);

            }
        });

        getUserDatas();


        Bundle bundle = getIntent().getBundleExtra("bundle");
        id = bundle.getLong("uid");
        String name = bundle.getString("name");
        String imgHd = bundle.getString("imgHd");

        chatUser.setText(name + "");

        listChatDatas = new ArrayList();

        chatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToFriends();
                chatEdit.setText("");
            }
        });


    }

    private void getUserDatas() {
        NetWorkHelper.getInstance(this).getUserDatas(new INetListener() {
            @Override
            public void onSuccess(String result) {
                UserEntity userEntity = new Gson().fromJson(result.toString(), UserEntity.class);
                if (userEntity != null) {
                    userName = userEntity.getName();
                    userId = userEntity.getId();
                    userHeadURL = userEntity.getProfile_image_url();
                }
            }

            @Override
            public void onError(String result) {

            }
        });
    }

    /**
     * 发送消息给对方
     */
    private void sendMessageToFriends() {

        JSONObject object = new JSONObject();

        String text = chatEdit.getText().toString().trim();

        LogHelper.i(TAG, "------" + text);
        if (TextUtils.isEmpty(text)) {
            return;
        }


        ChatBean chatBean = new ChatBean();
        chatBean.setImgURLHD(userHeadURL);
        chatBean.setType("0");
        chatBean.setmContent(text + "");
        chatBean.setmName(userName);
        listChatDatas.add(chatBean);


        NetWorkHelper.getInstance(this).postMessage(object, id, new INetListener() {
            @Override
            public void onSuccess(String result) {
                LogHelper.i(TAG, "_" + result.toString());
            }

            @Override
            public void onError(String result) {

            }
        });

        ChatAdapter adapter = new ChatAdapter(this, listChatDatas);

        chatListView.setAdapter(adapter);

    }
}
