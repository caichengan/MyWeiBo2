package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.mode.GridViewAdapter;
import com.xht.android.myweibo.mode.TranspondGridViewAdapter;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.view.NoScrollGridView;

import java.util.ArrayList;

/**
 * Created by an on 2017/3/2.
 */
public class ReportWeiBoActivity extends Activity {


    private WeiboParameters weiboParameters;
    private static final String TAG = "ReportWeiBoActivity";
    private ProgressDialog mProgDoal;
    private Long id;
    private String text;
    private String accessToken;
    private String imgUrl;
    private long ret_id;
    private String ret_text;
    private String ret_thumbnail;
    private String chuangfa;
    private Button btnCancel;
    private TextView reportName;
    private LinearLayout name;
    private Button btnReport;
    private EditText edReportEdit;
    private TextView mReportText;
    private NoScrollGridView reportImg;
    private TextView RetReportContent;
    private NoScrollGridView RetReportImg;
    private LinearLayout RetReport;
    private ArrayList<String> retPicLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        initialize();

        Bundle bundle = getIntent().getBundleExtra("bundle");
        text = bundle.getString("text");
        imgUrl = bundle.getString("imgUrl");
        chuangfa = bundle.getString("chuangfa");
        ret_id = bundle.getLong("ret_id");
        ret_text = bundle.getString("ret_text");
        ret_thumbnail = bundle.getString("ret_thumbnail");
        id = bundle.getLong("id");


        final ArrayList<String> picLists = bundle.getStringArrayList("picList");



        LogHelper.i(TAG, "----text---" + text + ret_text + ret_thumbnail);
        LogHelper.i(TAG, "----imgUrl---" + imgUrl);
        LogHelper.i(TAG, "----ret_thumbnail---" + ret_thumbnail);
        LogHelper.i(TAG, "-------" + chuangfa);
        LogHelper.i(TAG, "----id---" + id);
        LogHelper.i(TAG, "----ret_id---" + ret_id);
        mReportText.setText(text);

        if (picLists.size() > 0) {

            LogHelper.i(TAG, "-------" + picLists.size());
            GridViewAdapter trGridAdapter = new GridViewAdapter(this, picLists);
            reportImg.setAdapter(trGridAdapter);
        }
        if ("yes".equals(chuangfa)) {
            RetReport.setVisibility(View.VISIBLE);
            RetReportContent.setText(ret_text);
            RetReportImg.setVisibility(View.VISIBLE);
            retPicLists=bundle.getStringArrayList("retPicLists");
            //  TODO  处理转发图片

            if (retPicLists.size()>0){
                TranspondGridViewAdapter trGridAdapter=new TranspondGridViewAdapter(this, retPicLists);
                RetReportImg.setAdapter(trGridAdapter);
            }



        } else {
            RetReport.setVisibility(View.GONE);
        }
        reportImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //查看图片

                Bundle bundle=new Bundle();
                bundle.putString("url", picLists.get(position));
                bundle.putString("type", "0");
                LogHelper.i(TAG, "----" + imgUrl);
                bundle.putStringArrayList("picLists", picLists);
                bundle.putInt("position",position);
                IntentUtils.startActivityNumber(ReportWeiBoActivity.this, bundle, LoadImgActivity.class);

            }
        });


        RetReportImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //转发图片

                Bundle bundle=new Bundle();
                bundle.putString("url", retPicLists.get(position));
                bundle.putString("type","1");
                LogHelper.i(TAG, "-----", ret_thumbnail);
                bundle.putStringArrayList("picLists",retPicLists);
                bundle.putInt("position",position);
                IntentUtils.startActivityNumber(ReportWeiBoActivity.this, bundle, LoadImgActivity.class);

            }
        });

        /* RetReportImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(ret_thumbnail)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", ret_thumbnail);
                    Toast.makeText(ReportWeiBoActivity.this, "--------" + ret_thumbnail, Toast.LENGTH_SHORT).show();
                    IntentUtils.startActivityNumber(ReportWeiBoActivity.this, bundle, LoadImgActivity.class);
                }
            }
        });*/
        //取消发布
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //转发发布
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postReportWeiBo();   //TODO  转发微博
            }
        });
        /**
         * access_token 	true 	string 	采用OAuth授权方式为必填参数，OAuth授权后获得。
         id 	true 	int64 	要转发的微博ID。
         status 	false 	string 	添加的转发文本，必须做URLencode，内容不超过140个汉字，不填则默认为“转发微博”。
         is_comment 	false 	int 	是否在转发的同时发表评论，0：否、1：评论给当前微博、2：评论给原微博、3：都评论，默认为0 。
         rip 	false 	string
         */

    }

    /**
     * 转发微博
     */
    private void postReportWeiBo() {
        String edReport = edReportEdit.getText().toString();
        if (TextUtils.isEmpty(edReport)) {
            edReport = "转发微博";
        }
        long changId = 0;
        LogHelper.i(TAG, "----id1--" + id);
        LogHelper.i(TAG, "----id11--" + ret_id);
        if ("yes".equals(chuangfa)) {
            changId = ret_id;
        } else {
            changId = id;
        }

        LogHelper.i(TAG, "-----changId---" + changId);

        LogHelper.i(TAG,"--------"+edReport+"----changid--"+changId);

        NetWorkHelper.getInstance(this).getReportWeiBo( edReport, changId, new INetListener() {
            @Override
            public void onSuccess(String result) {

                Toast.makeText(ReportWeiBoActivity.this, "转发成功..", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(String result) {

                Toast.makeText(ReportWeiBoActivity.this, "" + result.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 创建对话框*
     *
     * @param title
     */
    private void createProgressDialog(String title) {
        if (mProgDoal == null) {
            mProgDoal = new ProgressDialog(this);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initialize() {

        btnCancel = (Button) findViewById(R.id.btnCancel);
        reportName = (TextView) findViewById(R.id.reportName);
        name = (LinearLayout) findViewById(R.id.name);
        btnReport = (Button) findViewById(R.id.btnReport);
        edReportEdit = (EditText) findViewById(R.id.edReportEdit);
        mReportText = (TextView) findViewById(R.id.mReportText);
        reportImg = (NoScrollGridView) findViewById(R.id.reportImg);
        RetReportContent = (TextView) findViewById(R.id.RetReportContent);
        RetReportImg = (NoScrollGridView) findViewById(R.id.RetReportImg);
        RetReportContent = (TextView) findViewById(R.id.RetReportContent);
        RetReportImg = (NoScrollGridView) findViewById(R.id.RetReportImg);
        RetReport = (LinearLayout) findViewById(R.id.RetReport);
    }
}
