package com.xht.android.myweibo.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.RelativeLayout;

import com.xht.android.myweibo.R;

/**
 * Created by Administrator on 2016/10/17.
 */

public class ChoosePicDialog extends Dialog {

    android.view.View.OnClickListener myListener;

    public ChoosePicDialog(Context context, android.view.View.OnClickListener listener) {
        super(context, R.style.dialog_pic_choose);
        myListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_choose_pic);
        Window window = getWindow();
        window.setWindowAnimations(R.style.dialog_pic_choose);
        window.setGravity(Gravity.BOTTOM);

        RelativeLayout goToCamera = (RelativeLayout)findViewById(R.id.goToCamera);
        RelativeLayout goToAlbum = (RelativeLayout)findViewById(R.id.goToAlbum);
       /*goToCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        goToCamera.setOnClickListener(myListener);
        goToAlbum.setOnClickListener(myListener);
    }


}
