package com.xht.android.myweibo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.utils.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/7.
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView orderListView;
    private PullRefreshLayout swipeRefreshLayout;
    private ListView lvMessage;
    private TextView text;

    private static final  String TOPIC="#.+?#";//话题
    private static final  String NAME="@([\u4e00-\u9fa5A-z0-9_]*)";//人名
    private static final  String URL="http://.*";//Url
    private static final  String EMOTION="\\[[\u4E00-\u9Fa50-zA-Z0-9]*\\]";//表情

    private static final String str="#我们活动是否会很方便和计划#到货入库加班费和解放军erfh,@shsf的，所代表的是比较好，http://.conhu.vijn";
    private SpannableString spannableString;


    public MessageFragment() {
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


        spannableString = new SpannableString(str);
        //获取数据



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_message, container, false);

       // lvMessage = (ListView)view. findViewById(R.id.lvGetNews);

        text = (TextView) view.findViewById(R.id.text);
        spannableString = new SpannableString(str);
        HightLignt(str, Pattern.compile(TOPIC));
        HightLignt(str, Pattern.compile(URL));

        text.setText(spannableString);

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

        return view;
    }


    /** 高亮显示
     *
     * @param str
     * @param pattern
     */
    public  void HightLignt(String str,Pattern pattern){




        List<HashMap<String,String>> list=getStartAndEnd(str,pattern);
        for (HashMap<String,String> map:list){

            /**
             * 文本高亮显示 TODO
             */
            ForegroundColorSpan foregroundColorSpan=new ForegroundColorSpan(Color.GREEN);
            spannableString.setSpan(foregroundColorSpan,Integer.parseInt(map.get("start")),
                    Integer.parseInt(map.get("end")), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     *  匹配正则表达式， 获取头跟尾
     * @param str    字符串
     * @param pattern 正则表达式
     * @return
     */
    public static List<HashMap<String,String>> getStartAndEnd(String str,Pattern pattern){

        List<HashMap<String,String>> list=new ArrayList<>();

        Matcher matcher=pattern.matcher(str);
        while (matcher.find())  {

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("start",matcher.start()+"");
            map.put("end",matcher.end()+"");

            list.add(map);
        }

        return list;
    }


}

