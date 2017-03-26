package com.xht.android.myweibo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.google.gson.Gson;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.activity.ChatActivity;
import com.xht.android.myweibo.mode.FriendAdapter;
import com.xht.android.myweibo.mode.FriendEntity;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.view.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/1/7.
 * <p>
 * Created by Administrator on 2017/1/5.
 * <p>
 * Created by Administrator on 2017/1/5.
 */

/**
 * Created by Administrator on 2017/1/5.
 */


/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 * <br>
 *    微博信息页面
 */
public class MessageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "MessageFragment";
    @InjectView(R.id.lvMessageFriend)
    ListView lvMessageFriend;
    @InjectView(R.id.swipeRefreshLayout)
    PullRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.mClientEdit)
    ClearEditText mClientEdit;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TOPIC = "#.+?#";//话题
    private static final String NAME = "@([\u4e00-\u9fa5A-z0-9_]*)";//人名
    private static final String URL = "http://.*";//Url
    private static final String EMOTION = "\\[[\u4E00-\u9Fa50-zA-Z0-9]*\\]";//表情

    private List<FriendEntity.UsersBean> friendUser;
    private FriendAdapter adapter;
    private List<FriendEntity.UsersBean> searchList;
    private boolean search;


    public MessageFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);


       /* text = (TextView) view.findViewById(R.id.text);
        spannableString = new SpannableString(str);
        HightLignt(str, Pattern.compile(TOPIC));
        HightLignt(str, Pattern.compile(URL));

        text.setText(spannableString);*/

        ButterKnife.inject(this, view);

        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // 刷新3秒完成
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        searchList = new ArrayList<>();
        getFriendsNumber();



        mClientEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchKey = mClientEdit.getText().toString();

                searchList.clear();
                LogHelper.i(TAG,"-------"+searchKey.toString());

                if (!TextUtils.isEmpty(searchKey)) {
                    for (int i = 0; i < friendUser.size(); i++) {
                        boolean contains = friendUser.get(i).getName().contains(searchKey);
                        if (contains) {
                            searchList.add(friendUser.get(i));

                            LogHelper.i(TAG,"-----"+friendUser.get(i).getName());
                        }
                    }
                    LogHelper.i(TAG,"------"+searchList.size());

                    search = true;
                    adapter=new FriendAdapter(getActivity(),searchList);
                    lvMessageFriend.setAdapter(adapter);
                }else{
                    LogHelper.i(TAG,"---33----");
                    getFriendsNumber();
                }

            }
        });



        lvMessageFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (search){

                    Bundle bundle = new Bundle();
                    bundle.putLong("uid", searchList.get(position).getId());
                    bundle.putString("name", searchList.get(position).getName());

                    bundle.putString("imgHd", searchList.get(position).getProfile_image_url());
                    IntentUtils.startActivityNumberForResult(getActivity(), bundle, ChatActivity.class);

                }else {
                    Bundle bundle = new Bundle();
                    bundle.putLong("uid", friendUser.get(position).getId());
                    bundle.putString("name", friendUser.get(position).getName());

                    bundle.putString("imgHd", friendUser.get(position).getProfile_image_url());
                    IntentUtils.startActivityNumberForResult(getActivity(), bundle, ChatActivity.class);
                }

            }
        });
        return view;
    }

    /**
     * 搜索好友  TODO
     */

    /**
     * 获取用户朋友列表
     */
    private void getFriendsNumber() {

        NetWorkHelper.getInstance(getActivity()).getFriendsList(100, new INetListener() {
            @Override
            public void onSuccess(String result) {

                LogHelper.i(TAG, "----res--" + result.toString());
                FriendEntity friendEntity = new Gson().fromJson(result.toString(), FriendEntity.class);
                friendUser = friendEntity.getUsers();




                adapter = new FriendAdapter(getActivity(),friendUser);
                lvMessageFriend.setAdapter(adapter);
                lvMessageFriend.setFastScrollEnabled(true);
            }
            @Override
            public void onError(String result) {

            }
        });
    }

    public void setFriendAdaoter(List<FriendEntity.UsersBean> list){

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

