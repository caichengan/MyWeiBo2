package com.xht.android.myweibo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.google.gson.Gson;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.activity.MainActivity;
import com.xht.android.myweibo.activity.UrlBlogActivity;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.mode.ListNewsAdapter;
import com.xht.android.myweibo.mode.StatusEntity;
import com.xht.android.myweibo.net.BaseNetWork;
import com.xht.android.myweibo.net.BaseURL;
import com.xht.android.myweibo.net.HttpResponse;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 */

/**
 * Created by Administrator on 2017/1/5.
 */


/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 * <br>
 *    微博主页
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "MainFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivity mMainActivity;
    private ListView lvGetNews;
    private ListView rcycleView;
    private PullRefreshLayout swipeRefreshLayout;
    private TextView mainFind;
    private TextView mainName;
    private ImageView mainSumbit;
    private AsyncWeiboRunner asyncWeiboRunner;
    private WeiboParameters weiboParameters;
    private SharpUtils sharpUtils;

    private RecyclerView.LayoutManager mLayoutManager;
   // private RecycleAdapter mRecycleAdapter;
    private ListNewsAdapter mRecycleAdapter;
    private List<StatusEntity.StatusesBean> mListStatuses;
    private String subUrl;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GenJinFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        mMainActivity= (MainActivity) getActivity();

        asyncWeiboRunner=new AsyncWeiboRunner(getActivity());
        weiboParameters=new WeiboParameters(Constants.APP_KEY);



        sharpUtils=SharpUtils.getInstance(getActivity());
        //获取数据
        String uid = sharpUtils.getToken().getUid();
        String token = sharpUtils.getToken().getToken();
        String phoneNum = sharpUtils.getToken().getPhoneNum();
        LogHelper.i(TAG,"------uid-"+uid+"----token-"+token.toString()+"----phoneNum-"+phoneNum+"------");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main, container, false);

        //lvGetNews = (ListView)view. findViewById(R.id.lvGetNews);
        rcycleView = (ListView) view. findViewById(R.id.rcycleView);
        mainFind = (TextView)view. findViewById(R.id.mainFind);
        mainName = (TextView)view. findViewById(R.id.mainName);
        mainSumbit = (ImageView)view. findViewById(R.id.mainSumbit);

        rcycleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              /*  String name = mLists.get(position)..getName();
                String url = mLists.get(position).getUser().getUrl();
                Toast.makeText(getActivity(), ""+name+"---url--"+url, Toast.LENGTH_SHORT).show();*/


                Bundle bundle = new Bundle();
                String text = mListStatuses.get(position).getText();
                if (text.contains("http")) {
                    int indexOf = text.lastIndexOf("http");
                    subUrl = text.substring(indexOf, text.length() - 1);
                    LogHelper.i(TAG, "------subUrl--" + subUrl);

                    bundle.putString("URL",subUrl);
                    IntentUtils.startActivityNumber(getActivity(), bundle, UrlBlogActivity.class);

                }


            }
        });


        swipeRefreshLayout = (PullRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
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

       // init();
        getNewsDatas();
        return view;
    }

  /*  private void init(){
        mLayoutManager = new LinearLayoutManager(getActivity());
        rcycleView.setLayoutManager(mLayoutManager);

    }*/

    /**
     * 获取发布的最新的微博新闻
     */
    private void getNewsDatas() {
        LogHelper.i(TAG,"-----onResult-----");


        new BaseNetWork(getActivity(), BaseURL.FRIENDS_TIMELINE_URL){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                if (success) {


                    LogHelper.i(TAG, "-----response-----"+response.responer.toString());

                    StatusEntity statusEntity = new Gson().fromJson(response.responer.toString(), StatusEntity.class);
                    mListStatuses = statusEntity.getStatuses();


                    mRecycleAdapter = new ListNewsAdapter(getActivity(), mListStatuses);
                    rcycleView.setAdapter(mRecycleAdapter);

                }else{
                    LogHelper.i(TAG,"onFinish"+response.message);
                }

            }
            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,sharpUtils.getToken().getToken());
                return weiboParameters;
            }
        }.get();


    }



}

