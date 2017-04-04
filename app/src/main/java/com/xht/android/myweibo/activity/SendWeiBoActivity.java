package com.xht.android.myweibo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xht.android.myweibo.R;
import com.xht.android.myweibo.mode.Constants;
import com.xht.android.myweibo.net.BaseURL;
import com.xht.android.myweibo.net.INetListener;
import com.xht.android.myweibo.net.NetWorkHelper;
import com.xht.android.myweibo.utils.AsyncImageLoader;
import com.xht.android.myweibo.utils.BitmapHelper;
import com.xht.android.myweibo.utils.BitmapUtils;
import com.xht.android.myweibo.utils.LogHelper;
import com.xht.android.myweibo.view.ChoosePicDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * Created by an on 2017/3/24.
 */
public class SendWeiBoActivity extends Activity implements  View.OnClickListener{

    private Uri mCurFromCamare;
    private Uri mCurFromCamare1;
    private Uri mCurFromCamare2;
    private Uri mCurFromCamare3;
    private Uri mCurFromCamare4;
    private Uri mCurFromCamare5;
    private Uri mCurFromCamare6;
    private Uri mCurFromCamare7;
    private Uri mCurFromCamare8;
    private AsyncImageLoader imageLoader;

    private String mTempStrT;
    private String mTempStrURL; //将要上传的图片的路径

    private ProgressDialog mProgressDialog;
    private ChoosePicDialog mChoosePicDialog;
    private static final String TAG = "PicLoadActivity";
    private String flowId;
    private String orderId;
    private String serviceId;

    private String imgFile1;
    private String imgFile2;
    private String imgFile3;
    private String imgFile4;
    private String imgFile5;
    private String imgFile6;
    private String imgFile7;
    private String imgFile8;

    private int uid;
    private String style;
    private String mTempStrUR1;
    private String mTempStrUR2;
    private String mTempStrUR3;
    private String mTempStrUR4;
    private String mTempStrUR5;
    private String mTempStrUR6;
    private String mTempStrUR7;
    private String mTempStrUR8;
    private String status;
    private List<String> resultPaths;

    private int curIVSelPic;
    private EditText edSendWeiBo;
    private ImageView mImgFile11;
    private ImageView mImgFile12;
    private ImageView mImgFile13;
    private ImageView mImgFile14;
    private ImageView mImgFile21;
    private ImageView mImgFile22;
    private ImageView mImgFile23;
    private ImageView mImgFile24;
    private Button mSend;
    private TextView mCancel;
    private List<String> stringUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sendweibo);
        initialize();
        stringUrls = new ArrayList<>();
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.mCancel:
                finish();
                break;

            case R.id.mSend:

                stringUrls.clear();
                SendMethodWeiBo();

                break;

            case R.id.mImgFile_11:

                if (mChoosePicDialog == null) {
                    mChoosePicDialog = new ChoosePicDialog(SendWeiBoActivity.this, (View.OnClickListener) this);
                }
                mChoosePicDialog.show();
                curIVSelPic = 11;

                break;
            case R.id.mImgFile_12:
                if (mChoosePicDialog == null) {
                    mChoosePicDialog = new ChoosePicDialog(SendWeiBoActivity.this, (View.OnClickListener) this);
                }
                mChoosePicDialog.show();
                curIVSelPic = 12;
                break;
            case R.id.mImgFile_13:
                if (mChoosePicDialog == null) {
                    mChoosePicDialog = new ChoosePicDialog(SendWeiBoActivity.this, (View.OnClickListener) this);
                }
                mChoosePicDialog.show();
                curIVSelPic = 13;
                break;
            case R.id.mImgFile_14:
                if (mChoosePicDialog == null) {
                    mChoosePicDialog = new ChoosePicDialog(SendWeiBoActivity.this, (View.OnClickListener) this);
                }
                mChoosePicDialog.show();
                curIVSelPic = 14;

                break;
            case R.id.mImgFile_21:
                if (mChoosePicDialog == null) {
                    mChoosePicDialog = new ChoosePicDialog(SendWeiBoActivity.this, (View.OnClickListener) this);
                }
                mChoosePicDialog.show();
                curIVSelPic = 15;
                break;
            case R.id.mImgFile_22:
                if (mChoosePicDialog == null) {
                    mChoosePicDialog = new ChoosePicDialog(SendWeiBoActivity.this, (View.OnClickListener) this);
                }
                mChoosePicDialog.show();
                curIVSelPic = 16;
                break;
            case R.id.mImgFile_23:
                if (mChoosePicDialog == null) {
                    mChoosePicDialog = new ChoosePicDialog(SendWeiBoActivity.this, (View.OnClickListener) this);
                }
                mChoosePicDialog.show();
                curIVSelPic = 17;
                break;
            case R.id.mImgFile_24:
                if (mChoosePicDialog == null) {
                    mChoosePicDialog = new ChoosePicDialog(SendWeiBoActivity.this, (View.OnClickListener) this);
                }
                mChoosePicDialog.show();
                curIVSelPic = 18;
                break;
            case R.id.goToAlbum://相册选择照片
                dismissmChooseIconDialog();
                selectPicFromAlbum();
                break;
            case R.id.goToCamera://相机选择拍照
                Log.i(TAG, "------相机选择拍照");
                dismissmChooseIconDialog();
                mCurFromCamare = Uri.parse("file://" + Constants.BZ_CAM_PATH + "/"
                        + "bzzbz_" +"_"+System.currentTimeMillis()+"c"+ ".jpg");
                MethodCamera();
                Log.i(TAG,"-----"+mCurFromCamare.toString());

                break;
            default:
                break;
        }
    }

    /**
     * 点击发送微博
     */
    private void SendMethodWeiBo() {


        String edText = edSendWeiBo.getText().toString();

        LogHelper.i(TAG,"------8ed---"+edText);


        if (!TextUtils.isEmpty(mTempStrUR1)){
            stringUrls.add(mTempStrUR1);
        }

        if (!TextUtils.isEmpty(mTempStrUR2)){
            stringUrls.add(mTempStrUR2);
        }

        if (!TextUtils.isEmpty(mTempStrUR3)){
            stringUrls.add(mTempStrUR3);
        }
        if (!TextUtils.isEmpty(mTempStrUR4)){
            stringUrls.add(mTempStrUR4);
        }
        if (!TextUtils.isEmpty(mTempStrUR5)){
            stringUrls.add(mTempStrUR5);
        }
        if (!TextUtils.isEmpty(mTempStrUR6)){
            stringUrls.add(mTempStrUR6);
        }
        if (!TextUtils.isEmpty(mTempStrUR7)){
            stringUrls.add(mTempStrUR7);
        }
        if (!TextUtils.isEmpty(mTempStrUR8)){
            stringUrls.add(mTempStrUR8);
        }
        if (TextUtils.isEmpty(edText) && stringUrls.size()<=0){
            Toast.makeText(this, "先输入文字或图片", Toast.LENGTH_SHORT).show();
            return;
        }
        LogHelper.i(TAG,"------"+stringUrls.size());
        if (stringUrls.size()>0){
            //上传微博加图片
            sendWeiBoPhoto(edText,stringUrls);
        }else{
            //只上传文字微博
            sendWeiBoCharacter(edText);
        }
    }
    /**
     * 上传文字
     */
    private void sendWeiBoCharacter(String edTexts) {
        NetWorkHelper.getInstance(this).postSendWeiBoChartacter(edTexts, new INetListener() {
            @Override
            public void onSuccess(String result) {

                Toast.makeText(SendWeiBoActivity.this, result, Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onError(String result) {

            }
        });

    }
    /**
     * 上传文字加图片  TODO
     */
    private void sendWeiBoPhoto(String text,List<String> stringURLs) {
        createProgressDialogTitle("正在发布...");
        NetWorkHelper.getInstance(this).postSendWeiBoPhoto(text,stringURLs,new INetListener() {
            @Override
            public void onSuccess(String result) {

                Toast.makeText(SendWeiBoActivity.this, result, Toast.LENGTH_SHORT).show();

                dismissProgressDialog();

                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction("com.xht.android.myweibo.udpate");
                sendBroadcast(intent);
                finish();

            }

            @Override
            public void onError(String result) {

            }
        });

    }


    /**
     * 隐藏mProgressDialog
     */
    private void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }


    /* 创建mProgressDialog
    */
    private void createProgressDialogTitle(String title) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setTitle(title);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }
    /**
     * 隐藏选择图片对话框
     */
    private void dismissmChooseIconDialog() {
        if (mChoosePicDialog != null) {
            mChoosePicDialog.dismiss();
        }
    }
    private void MethodCamera() {

        switch (curIVSelPic) {
            case 11:
                mCurFromCamare1 = mCurFromCamare;
                LogHelper.i(TAG, "------相机拍照--1--" + mCurFromCamare1);
                //file:///storage/emulated/0/ServerHelp/bzbz/img/bzzbz_o29_s114_e4_f11478239384309.jpg
                break;
            case 12:
                mCurFromCamare2 = mCurFromCamare;
                LogHelper.i(TAG, "------相机拍照--2--" + mCurFromCamare2);//TODO mCurFromCamare2 File
                //file:///storage/emulated/0/ServerHelp/bzbz/img/bzzbz_o29_s114_e4_f11478239414869.jpg
                break;
            case 13:
                mCurFromCamare3 = mCurFromCamare;
                LogHelper.i(TAG, "------相机拍照--3--" + mCurFromCamare3);
                // file:///storage/emulated/0/ServerHelp/bzbz/img/bzzbz_o29_s114_e4_f11478239438180.jpg
                break;
            case 14:
                mCurFromCamare4 = mCurFromCamare;
                LogHelper.i(TAG, "------相机拍照--4--" + mCurFromCamare4);
                // file:///storage/emulated/0/ServerHelp/bzbz/img/bzzbz_o29_s114_e4_f11478239438180.jpg
                break;
            case 15:
                mCurFromCamare5 = mCurFromCamare;
                LogHelper.i(TAG, "------相机拍照--5--" + mCurFromCamare5);
                // file:///storage/emulated/0/ServerHelp/bzbz/img/bzzbz_o29_s114_e4_f11478239438180.jpg
                break;
            case 16:
                mCurFromCamare6 = mCurFromCamare;
                LogHelper.i(TAG, "------相机拍照--6--" + mCurFromCamare6);
                // file:///storage/emulated/0/ServerHelp/bzbz/img/bzzbz_o29_s114_e4_f11478239438180.jpg
                break;
            case 17:
                mCurFromCamare7 = mCurFromCamare;
                LogHelper.i(TAG, "------相机拍照--7--" + mCurFromCamare7);
                // file:///storage/emulated/0/ServerHelp/bzbz/img/bzzbz_o29_s114_e4_f11478239438180.jpg
                break;
            case 18:
                mCurFromCamare8 = mCurFromCamare;
                LogHelper.i(TAG, "------相机拍照--8--" + mCurFromCamare8);
                // file:///storage/emulated/0/ServerHelp/bzbz/img/bzzbz_o29_s114_e4_f11478239438180.jpg
                break;

        }
        getPhotoFromCamera(mCurFromCamare, 4);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            if (requestCode == 3) {//相册返回
                Bitmap thumbnail;
                Uri fullPhotoUri = data.getData();//content://media/external/images/media/3844
                //真实路径
                String realFilePath = BitmapUtils.getRealFilePath(this, fullPhotoUri);

                    //("/sdcard/image.jpg")
                    mCurFromCamare = Uri.parse("file://" + Constants.BZ_PIC_PATH + "/"
                            + "bzzbz_"
                            + System.currentTimeMillis() + "_g" + ".jpg");

                String path = mCurFromCamare.getPath();


                LogHelper.i(TAG,"----000---"+path);

                //压缩新的路径
                String ImageUrl = BitmapUtils.compressImage(realFilePath, path, 50);
                LogHelper.i(TAG, "-------fullPhotoUri---" + fullPhotoUri.toString());
                LogHelper.i(TAG, "-------realFilePath---" + realFilePath);
                String size = BitmapUtils.getAutoFileOrFilesSize(ImageUrl);
                LogHelper.i(TAG, "---------大小---" + size);

                LogHelper.i(TAG, "-------ImageUrl---" + ImageUrl);
                LogHelper.i(TAG, "--------fullPhotoUri----" + fullPhotoUri.toString() + "------>>>>>" + realFilePath + "------>>>>>" + ImageUrl);
                //-fullPhotoUri----content://media/external/images/media/3844------>>>>>/storage/emulated/0/Pictures/Screenshots/S61101-153152.jpg
                // ------>>>>>/storage/emulated/0/ServerHelp/bzbz/camera/bzzbz_o48_s139_e4_f1_t1479002629947_g.jpg
                switch (curIVSelPic) {
                    case 11:
                        //mTempBitmap = BitmapFactory.decodeFile(mTempStrURL);
                        thumbnail = BitmapHelper.getThumbnail(this, fullPhotoUri,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);

                        //holder.mImgFile11.setImageBitmap(thumbnail);
                        mImgFile11.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile12.setVisibility(View.VISIBLE);
                        mTempStrUR1 = ImageUrl;


                        break;
                    case 12:
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, fullPhotoUri,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        //holder.mImgFile12.setImageBitmap(thumbnail);
                        mImgFile12.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile13.setVisibility(View.VISIBLE);
                        mTempStrUR2 = ImageUrl;

                        break;
                    case 13:
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, fullPhotoUri,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        // holder.mImgFile13.setImageBitmap(thumbnail);
                        mImgFile13.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile14.setVisibility(View.VISIBLE);
                        mTempStrUR3 = ImageUrl;

                        break;
                    case 14:
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, fullPhotoUri,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        // holder.mImgFile13.setImageBitmap(thumbnail);
                        mImgFile14.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile21.setVisibility(View.VISIBLE);
                        mTempStrUR4 = ImageUrl;

                        break;
                    case 15:
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, fullPhotoUri,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        // holder.mImgFile13.setImageBitmap(thumbnail);
                        mImgFile21.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile22.setVisibility(View.VISIBLE);
                        mTempStrUR5 = ImageUrl;

                        break;
                    case 16:
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, fullPhotoUri,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        // holder.mImgFile13.setImageBitmap(thumbnail);
                        mImgFile22.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile23.setVisibility(View.VISIBLE);
                        mTempStrUR6 = ImageUrl;

                        break;
                    case 17:
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, fullPhotoUri,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        // holder.mImgFile13.setImageBitmap(thumbnail);
                        mImgFile23.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile24.setVisibility(View.VISIBLE);
                        mTempStrUR7 = ImageUrl;

                        break;
                    case 18:
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, fullPhotoUri,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        // holder.mImgFile13.setImageBitmap(thumbnail);
                        mImgFile24.setBackgroundDrawable(new BitmapDrawable(thumbnail));

                        mTempStrUR8= ImageUrl;

                        break;

                }

            }
            if (requestCode == 4) {      //照相
                Bitmap thumbnail;
                mTempStrURL = mCurFromCamare.getPath();

                LogHelper.i(TAG, "-----照相-----------mTempStrURL-" + mTempStrURL + "--");//
                String autoFileOrFilesSize = BitmapUtils.getAutoFileOrFilesSize(mTempStrURL);

                LogHelper.i(TAG, "-----daxiao--" + autoFileOrFilesSize);//       ---2.38 M

                //压缩文件 KB
                String imageUrl = BitmapUtils.compressImage(mTempStrURL, mTempStrURL,50);

                String FilesSize = BitmapUtils.getAutoFileOrFilesSize(imageUrl);
                String FilesSize1 = BitmapUtils.getAutoFileOrFilesSize(mTempStrURL);
                LogHelper.i(TAG, "-----daxiao--" + FilesSize + "--" + FilesSize1);//
                LogHelper.i(TAG, "-----照相--mTempStrURL" + "--" + mTempStrURL);//

                LogHelper.i(TAG, "--------照相返回------" + mTempStrURL + "-----");//string   /storage/emulated/0/ServerHelp/bzbz/img/bzzbz_o29_s114_e4_f1_t1478759682811.jpg
                switch (curIVSelPic) {
                    case 11:
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);

                        thumbnail = BitmapHelper.getThumbnail(this, mCurFromCamare,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);


                        long bitmapsize = BitmapHelper.getBitmapsize(thumbnail);
                        int byteCount = thumbnail.getByteCount();
                        LogHelper.i(TAG, "-----大小-thumbnail-" + byteCount + "----" + bitmapsize);

                        mTempStrUR1 = mTempStrURL;
                        mImgFile11.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        LogHelper.i(TAG, "--------照相--1-返回-----mTempStrUR1-" + mTempStrUR1 + "-----" + mCurFromCamare.toString());
                        mImgFile12.setVisibility(View.VISIBLE);

                        break;
                    case 12:
                        LogHelper.i(TAG, "-------------" + curIVSelPic + "-----" + mTempStrURL);
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, mCurFromCamare,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        mTempStrUR2 = mTempStrURL;

                        LogHelper.i(TAG, "--------照相--2--返回-----mTempStrUR2-" + mTempStrUR2 + "-----" + mCurFromCamare.toString());
                        mImgFile12.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile13.setVisibility(View.VISIBLE);

                        break;
                    case 13:
                        LogHelper.i(TAG, "-------------" + curIVSelPic + "-----");
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, mCurFromCamare,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        mTempStrUR3 = mTempStrURL;
                        LogHelper.i(TAG, "--------照相--3--返回-----mTempStrUR3-" + mTempStrUR3 + "-----" + mCurFromCamare.toString());
                        mImgFile13.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile14.setVisibility(View.VISIBLE);

                        break;
                    case 14:
                        LogHelper.i(TAG, "-------------" + curIVSelPic + "-----");
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, mCurFromCamare,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        mTempStrUR4 = mTempStrURL;
                        LogHelper.i(TAG, "--------照相--4--返回-----mTempStrUR3-" + mTempStrUR4 + "-----" + mCurFromCamare.toString());
                        mImgFile14.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile21.setVisibility(View.VISIBLE);

                        break;
                    case 15:
                        LogHelper.i(TAG, "-------------" + curIVSelPic + "-----");
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, mCurFromCamare,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        mTempStrUR5 = mTempStrURL;
                        LogHelper.i(TAG, "--------照相--5--返回-----mTempStrUR5-" + mTempStrUR5 + "-----" + mCurFromCamare.toString());
                        mImgFile21.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile22.setVisibility(View.VISIBLE);

                        break;
                    case 16:
                        LogHelper.i(TAG, "-------------" + curIVSelPic + "-----");
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, mCurFromCamare,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        mTempStrUR6 = mTempStrURL;
                        LogHelper.i(TAG, "--------照相--6--返回-----mTempStrUR6-" + mTempStrUR6 + "-----" + mCurFromCamare.toString());
                        mImgFile22.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile23.setVisibility(View.VISIBLE);

                        break;
                    case 17:
                        LogHelper.i(TAG, "-------------" + curIVSelPic + "-----");
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, mCurFromCamare,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        mTempStrUR7 = mTempStrURL;
                        LogHelper.i(TAG, "--------照相-7--返回-----mTempStrUR7-" + mTempStrUR7 + "-----" + mCurFromCamare.toString());
                        mImgFile23.setBackgroundDrawable(new BitmapDrawable(thumbnail));
                        mImgFile24.setVisibility(View.VISIBLE);

                        break;
                    case 18:
                        LogHelper.i(TAG, "-------------" + curIVSelPic + "-----");
                        //mTempBitmap = BitmapFactory.decodeFile(imgPath);
                        thumbnail = BitmapHelper.getThumbnail(this, mCurFromCamare,
                                60 * (int) Constants.DENSITY, 60 * (int) Constants.DENSITY);
                        mTempStrUR8 = mTempStrURL;
                        LogHelper.i(TAG, "--------照相-8--返回-----mTempStrUR8-" + mTempStrUR8 + "-----" + mCurFromCamare.toString());
                        mImgFile24.setBackgroundDrawable(new BitmapDrawable(thumbnail));

                        break;


                }
            }
        }
    }


    /**
     * 调用相机拍照
     *
     * @param uri
     * @param requestCode
     */
    private void getPhotoFromCamera(Uri uri, int requestCode) {
        mkdirs(uri);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        LogHelper.i(TAG, "-------调用相机拍照-----------");
        startActivityForResult(intent, requestCode);//or TAKE_SMALL_PICTURE
    }

    /**
     * 创建存储照片的文件夹
     *
     * @param uri
     */
    private void mkdirs(Uri uri) {
        String path = uri.getPath();
        File file = new File(path.substring(0, path.lastIndexOf("/")));
        if (!file.exists()) {
            boolean success = file.mkdirs();
            LogHelper.i(TAG,"---创建存储照片的文件夹success = " + success);
        }

    }

    private void selectPicFromAlbum() {


        Intent intent = new Intent(Intent.ACTION_PICK, null);//从列表中选择某项并返回所有数据
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,//得到系统所有的图片
                "image/*");//图片的类型,image/*为所有类型图片

        startActivityForResult(intent, 3);
    }



    private void initialize() {

        edSendWeiBo = (EditText) findViewById(R.id.edSendWeiBo);
        mImgFile11 = (ImageView) findViewById(R.id.mImgFile_11);
        mImgFile12 = (ImageView) findViewById(R.id.mImgFile_12);
        mImgFile13 = (ImageView) findViewById(R.id.mImgFile_13);
        mImgFile14 = (ImageView) findViewById(R.id.mImgFile_14);
        mImgFile21 = (ImageView) findViewById(R.id.mImgFile_21);
        mImgFile22 = (ImageView) findViewById(R.id.mImgFile_22);
        mImgFile23 = (ImageView) findViewById(R.id.mImgFile_23);
        mImgFile24 = (ImageView) findViewById(R.id.mImgFile_24);
        mCancel = (TextView) findViewById(R.id.mCancel);
        mSend = (Button) findViewById(R.id.mSend);

        mImgFile11.setOnClickListener(this);
        mImgFile12.setOnClickListener(this);
        mImgFile13.setOnClickListener(this);
        mImgFile14.setOnClickListener(this);
        mImgFile21.setOnClickListener(this);
        mImgFile22.setOnClickListener(this);
        mImgFile23.setOnClickListener(this);
        mImgFile24.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mSend.setOnClickListener(this);
    }
}
