package com.xht.android.myweibo.fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.google.gson.Gson;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.activity.MainActivity;
import com.xht.android.myweibo.activity.SendWeiBoActivity;
import com.xht.android.myweibo.activity.UrlBlogActivity;
import com.xht.android.myweibo.activity.WeiBoCommentActivity;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.mode.ListNewsAdapter;
import com.xht.android.myweibo.mode.StatusEntity;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.utils.SharpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 */

/**
 * Created by Administrator on 2017/1/5.
 */


/**
 *  微博主页 获取关注用户最新发布的微博动态
 */
public class MainFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MainFragment";
    private String mParam1;
    private String mParam2;
    private MainActivity mMainActivity;
    private ListView lvGetNews;
    private ListView rcycleView;
    private PullRefreshLayout swipeRefreshLayout;
    private TextView mainFind;
    private TextView mainName;
    private ImageView mainSumbit;
    private ProgressDialog mProgDoal;
    private ListNewsAdapter mRecycleAdapter;
    private List<StatusEntity.StatusesBean> mListStatuses;
    private ArrayList<String>  listPics;
    private ArrayList<String>  listRetPics;
    private String subUrl;
    private String retUrl;
    public MainFragment() {
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
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
        listPics=new ArrayList<String>();
        listRetPics=new ArrayList<String>();
        //获取授权的本用户的数据
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.xht.android.myweibo.udpate");
        getActivity().registerReceiver(mBroadcastReceiver, filter);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        rcycleView = (ListView) view. findViewById(R.id.rcycleView);
        mainFind = (TextView)view. findViewById(R.id.mainFind);
        mainName = (TextView)view. findViewById(R.id.mainName);
        mainSumbit = (ImageView)view. findViewById(R.id.mainSumbit);
        rcycleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                listPics.clear();
                listRetPics.clear();
                String text = mListStatuses.get(position).getText();
                if (text.contains("http")) {
                    int indexOf = text.lastIndexOf("http");
                    subUrl = text.substring(indexOf, text.length() - 1);
                    if (subUrl.endsWith(". ")){
                        int lastIndexOf = subUrl.lastIndexOf(".");
                        subUrl = subUrl.substring(0, lastIndexOf-1);
                    }
                    if (subUrl.contains(" ")){
                        String[] split = subUrl.split(" ");
                        subUrl=split[0];
                    }
                    bundle.putString("URL",subUrl);
                    IntentUtils.startActivityNumber(getActivity(), bundle, UrlBlogActivity.class);
                }else{
                    //无  URL 之  微博详情 评论 页面
                    bundle.putString("text",text);
                    StatusEntity.StatusesBean publicLine = mListStatuses.get(position);
                    String bmiddle_pic = publicLine.getBmiddle_pic();
                    List<StatusEntity.StatusesBean.PicUrlsBean> picUrls = publicLine.getPic_urls();
                    LogHelper.i(TAG,"----size---0-----"+picUrls.size());
                    LogHelper.i(TAG,"----size---0-----"+listPics.toString());
                    for (int i = 0; i < picUrls.size(); i++) {
                        String thumbnail_pic = picUrls.get(i).getThumbnail_pic();
                        LogHelper.i(TAG,"-----"+thumbnail_pic);
                        listPics.add(thumbnail_pic);
                        //listPics
                        // TODO
                    }
                    LogHelper.i(TAG,"----size---0-----"+picUrls.toString());
                    bundle.putStringArrayList("picList",  listPics);
                    StatusEntity.StatusesBean.RetweetedStatusBean retweeted_status = publicLine.getRetweeted_status();
                    if (retweeted_status!=null){
                        bundle.putString("chuangfa","yes");
                        bundle.putString("ret_text",retweeted_status.getText());
                        List<?> pic_urls_retweeted =  retweeted_status.getPic_urls();
                        LogHelper.i(TAG,"---size----"+pic_urls_retweeted.size());
                        LogHelper.i(TAG,"---size----"+pic_urls_retweeted.toString());
                        if (pic_urls_retweeted!=null&& pic_urls_retweeted.size()>0) {

                            for (int i = 0; i < pic_urls_retweeted.size(); i++) {
                                String thumbnail_pic = pic_urls_retweeted.get(i).toString();
                                int index = thumbnail_pic.indexOf("=");
                                String thumbnailChange = thumbnail_pic.substring(index+1, thumbnail_pic.length() - 1);
                                LogHelper.i(TAG,"----------i--------"+thumbnailChange);
                                listRetPics.add(thumbnailChange);
                            }
                            bundle.putString("ret_thumbnail",pic_urls_retweeted.get(0).toString());
                        }
                    }else{
                        bundle.putString("chuangfa","not");
                    }
                    bundle.putStringArrayList("retPicsList",listRetPics);
                    bundle.putString("urlHD",publicLine.getUser().getProfile_image_url());
                    bundle.putString("name",publicLine.getUser().getName());
                    bundle.putString("time",publicLine.getCreated_at());
                    bundle.putLong("id",publicLine.getId());
                    bundle.putString("mid",publicLine.getMid());
                    bundle.putString("sources",publicLine.getSource());
                    if (!TextUtils.isEmpty(bmiddle_pic))
                    {
                        bundle.putString("bmiddle_pic",bmiddle_pic);
                    }
                    LogHelper.i(TAG,"----text-----"+text,"--"+publicLine.getUser().getProfile_image_url()+publicLine.getUser().getName()+publicLine.getId()+"--mid-"+publicLine.getMid());
                    IntentUtils.startActivityNumber(getActivity(),bundle,WeiBoCommentActivity.class);
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
                        getWeiBoDatasMain();
                        mRecycleAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        getWeiBoDatasMain();
        /**
         * 发微博的按钮  TODO
         */
        mainFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                IntentUtils.startActivityNumberForResult(getActivity(),bundle,SendWeiBoActivity.class);
            }
        });
        return view;
    }
    /**
     * 获取发布的最新的微博新闻
     */
    public void  getWeiBoDatasMain(){
        if (mListStatuses!=null){
            mListStatuses.clear();
        }
        createProgressDialog("正在刷新数据...");
        NetWorkHelper.getInstance(getActivity()).getNewsDatas(new INetListener() {
                    @Override
                    public void onSuccess(String result) {
                        StatusEntity statusEntity = new Gson().fromJson(result.toString(), StatusEntity.class);
                        mListStatuses = statusEntity.getStatuses();
                        mRecycleAdapter = new ListNewsAdapter(getActivity(), mListStatuses);
                        rcycleView.setAdapter(mRecycleAdapter);
                        dismissProgressDialog();
                    }
                    @Override
                    public void onError(String result) {
                        dismissProgressDialog();
                    }
                });
    }
    /**
     * 创建对话框
     * @param title
     */
    private void createProgressDialog(String title) {
        if (mProgDoal == null) {
            mProgDoal = new ProgressDialog(getActivity());
        }
        mProgDoal.setTitle(title);
        mProgDoal.setIndeterminate(true);
        mProgDoal.setCancelable(false);
        mProgDoal.show();
    }
    /**
     * 隐藏对话框
     */
    private void dismissProgressDialog() {
        if (mProgDoal != null) {
            mProgDoal.dismiss();
            mProgDoal = null;
        }
    }

    /**
     * 使用广播接受信息，更新页面
     */
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.xht.android.myweibo.udpate"))
            {
                getWeiBoDatasMain();
            }
        }
    };
}

