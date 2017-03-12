package com.xht.android.myweibo.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
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

import com.baoyz.widget.PullRefreshLayout;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.activity.MainActivity;
import com.xht.android.myweibo.activity.UrlBlogActivity;
import com.xht.android.myweibo.activity.WeiBoCommentActivity;
import com.xht.android.myweibo.activity.WeiBoDetailActivity;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.mode.ListNewsAdapter;
import com.xht.android.myweibo.mode.StatusEntity;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
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
    private WeiboParameters weiboParameters;
    private SharpUtils sharpUtils;
    private ProgressDialog mProgDoal;

    private RecyclerView.LayoutManager mLayoutManager;
   // private RecycleAdapter mRecycleAdapter;
    private ListNewsAdapter mRecycleAdapter;
    private List<StatusEntity.StatusesBean> mListStatuses;
    private String subUrl;
    private String retUrl;

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
        weiboParameters=new WeiboParameters(Constants.APP_KEY);
        sharpUtils=SharpUtils.getInstance(getActivity());
        //获取授权的本用户的数据

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
                String text = mListStatuses.get(position).getText();
                if (text.contains("http")) {
                    int indexOf = text.lastIndexOf("http");
                    subUrl = text.substring(indexOf, text.length() - 1);
                    LogHelper.i(TAG, "------subUrl--" + subUrl);

                    if (subUrl.endsWith(". ")){
                        int lastIndexOf = subUrl.lastIndexOf(".");

                        subUrl = subUrl.substring(0, lastIndexOf-1);
                        LogHelper.i(TAG, "------subUrl2--" + subUrl);
                    }

                    if (subUrl.contains(" ")){
                        String[] split = subUrl.split(" ");

                        subUrl=split[0];
                        LogHelper.i(TAG,"-----sub3-"+subUrl);
                    }


                    bundle.putString("URL",subUrl);
                    IntentUtils.startActivityNumber(getActivity(), bundle, UrlBlogActivity.class);

                }else{

                    //无  URL 之  微博详情 评论 页面
                    bundle.putString("text",text);
                    StatusEntity.StatusesBean publicLine = mListStatuses.get(position);
                    String bmiddle_pic = publicLine.getBmiddle_pic();

                    StatusEntity.StatusesBean.RetweetedStatusBean retweeted_status = publicLine.getRetweeted_status();
                    if (retweeted_status!=null){

                        bundle.putString("chuangfa","yes");
                        bundle.putString("ret_text",retweeted_status.getText());
                        List<?> pic_urls_retweeted = retweeted_status.getPic_urls();
                        if (pic_urls_retweeted!=null&& pic_urls_retweeted.size()>0) {
                            String thumbnail_pic = pic_urls_retweeted.get(0).toString();

                            int index = thumbnail_pic.indexOf("=");
                            String thumbnailChange = thumbnail_pic.substring(index+1, thumbnail_pic.length() - 1);
                            LogHelper.i(TAG,"----------retweetedStatusPicUrls--------"+thumbnailChange);

                            bundle.putString("ret_thumbnail",thumbnailChange);
                        }
                    }else{
                        bundle.putString("chuangfa","not");
                    }

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


                //TODO  转发  URL

              /*  StatusEntity.StatusesBean.RetweetedStatusBean retweeted_status = mListStatuses.get(position).getRetweeted_status();
                if (retweeted_status!=null){
                    String mRetweeted =retweeted_status .getText();

                    if (!TextUtils.isEmpty(mRetweeted)) {
                        LogHelper.i(TAG,"---------------------"+mRetweeted.toString());
                        if (mRetweeted.contains("http")) {
                            int indexOf = mRetweeted.lastIndexOf("http");
                            retUrl = mRetweeted.substring(indexOf, mRetweeted.length() - 1);
                            LogHelper.i(TAG, "-----retwUrl--" + retUrl);
                            bundle.putString("RET", retUrl);
                            IntentUtils.startActivityNumber(getActivity(), bundle, UrlBlogActivity.class);

                        }
                    }else {
                        App.getInstance().showToast("无URL");
                    }
                }*/
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

        return view;
    }




    /**
     * 获取发布的最新的微博新闻
     */

    public void  getWeiBoDatasMain(){

        if (mListStatuses!=null){
            mListStatuses.clear();
        }

        createProgressDialog("正在加载数据...");
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
     *
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

}

