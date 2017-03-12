package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.xht.android.myweibo.R;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.IntentUtils;
import com.xht.android.myweibo.utils.LogHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by an on 2017/3/2.
 */
public class ReportWeiBoActivity extends Activity {


    @InjectView(R.id.edReportEdit)
    EditText edReportEdit;
    @InjectView(R.id.mReportText)
    TextView mReportText;
    @InjectView(R.id.reportImg)
    ImageView reportImg;
    @InjectView(R.id.btnReport)
    Button btnReport;
    @InjectView(R.id.btnCancel)
    Button btnCancel;
    @InjectView(R.id.reportName)
    TextView reportName;
    @InjectView(R.id.name)
    LinearLayout name;


    private WeiboParameters weiboParameters;
    private static final String TAG = "ReportWeiBoActivity";
    private ProgressDialog mProgDoal;
    private Long id;
    private String text;
    private String accessToken;
    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        ButterKnife.inject(this);

        weiboParameters = new WeiboParameters(Constants.APP_KEY);
     /*   bundle.putString("accessToken", SharpUtils.getInstance(mContext).getToken().getToken());
        bundle.putLong("id", mListDatas.get(position).getId());
        bundle.putString("text", mListDatas.get(position).getText());
        bundle.putString("imgUrl", ""+picUrls.get(0).toString());*/

        Bundle bundle = getIntent().getBundleExtra("bundle");
        accessToken = bundle.getString("accessToken");
        text = bundle.getString("text");
        imgUrl = bundle.getString("imgUrl");
        id = bundle.getLong("id");

        mReportText.setText(text);
        if (!imgUrl.equals("null")) {

            reportImg.setVisibility(View.VISIBLE);
            LogHelper.i(TAG, "--------imgUrl------" + imgUrl);
            Glide.with(this)
                    .load(imgUrl)
                    .placeholder(R.mipmap.p_head_fail)
                    .crossFade()
                    .into(reportImg);
        } else {
            reportImg.setVisibility(View.GONE);
        }

        reportImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(imgUrl)) {

                    Bundle bundle = new Bundle();
                    bundle.putString("url", imgUrl);
                    Toast.makeText(ReportWeiBoActivity.this, "--------" + imgUrl, Toast.LENGTH_SHORT).show();
                    IntentUtils.startActivityNumber(ReportWeiBoActivity.this, bundle, LoadImgActivity.class);

                }

            }
        });

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
        NetWorkHelper.getInstance(this).getReportWeiBo(accessToken, edReport, id, new INetListener() {
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



   /* private void getWeiBoDatas(final String accessToken, final String text, final Long id) {
        LogHelper.i(TAG,"-----onResult-----");
        createProgressDialog("正在提交数据...");
        new BaseNetWork(this, BaseURL.WEIBO_REPOST){
            @Override
            protected void onFinish(HttpResponse response, boolean success) {

                if (success) {
                    LogHelper.i(TAG, "-----response-----"+response.responer.toString());

                    dismissProgressDialog();

                }else{
                    LogHelper.i(TAG,"onFinish"+response.message);
                    dismissProgressDialog();
                }

            }
            @Override
            public WeiboParameters onPararts() {
                weiboParameters.put(WBConstants.AUTH_ACCESS_TOKEN,accessToken);
                weiboParameters.put(WBConstants.AUTH_PARAMS_AID,id);
                return weiboParameters;
            }
        }.post();

    }*/

    /**
     * 创建对话框
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
}
