package com.xht.android.myweibo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xht.android.myweibo.R;
import com.xht.android.myweibo.fragment.MainFragment;
import com.xht.android.myweibo.fragment.MessageFragment;
import com.xht.android.myweibo.fragment.MyFragment;
import com.xht.android.myweibo.mode.UserInfo;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends FragmentActivity {


    private static final String TAG = "MainActivity";
    public static final String BRO_ACT_S = "com.xht.android.myweibo.bro_act";

    public static final String PHONENUM_KEY = "phone_key";
    public static final String UID_KEY = "userId_key";
    public static final String UNAME_KEY = "userName_key";
    public static final String ACCESS_TOKEN = "access_token";
    public static UserInfo mUserInfo = new UserInfo();




    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1, mRadioButton2, mRadioButton3;
    private String uid;
    private String refresh_token;
    private String access_token;
    private String userName;

    public static UserInfo getInstance() {
        return mUserInfo;
    }




    /**
     * 四个切换的界面
     */
    private MainFragment mMainFragment;
    private MessageFragment mMessageFragment;
    private MyFragment mMyFragment;

    private ViewPager mViewPager;
    FragmentPagerAdapter adapter;
    private List<Fragment> mFragments=new ArrayList<>();
    int[] tabIds={R.id.radio_first,R.id.radio_txl,R.id.radio_my};
    int currentSelect=0;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

     /*   TextView mCustomView = new TextView(this);
        mCustomView.setText("微博");
        mCustomView.setTextColor(Color.RED);
        mCustomView.setTextSize(20);
        final ActionBar aBar = getActionBar();
        aBar.setCustomView(mCustomView,
                new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ActionBar.LayoutParams lp = (ActionBar.LayoutParams) mCustomView.getLayoutParams();
        lp.gravity = lp.gravity & ~Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK | Gravity.CENTER_HORIZONTAL;
        aBar.setCustomView(mCustomView, lp);
        int change = ActionBar.DISPLAY_SHOW_CUSTOM;
        aBar.setDisplayOptions(change);
*/



   /*     //获取数据
        IntentFilter intentFilter = new IntentFilter(BRO_ACT_S);
        registerReceiver(mReceiver, intentFilter);*/


            mRadioGroup = (RadioGroup) findViewById(R.id.menu_Switch);
            mRadioButton1 = (RadioButton) findViewById(R.id.radio_first);
            mRadioButton2 = (RadioButton) findViewById(R.id.radio_txl);
            mRadioButton3 = (RadioButton) findViewById(R.id.radio_my);

            mMainFragment = MainFragment.newInstance("", "");
            mMessageFragment = MessageFragment.newInstance("", "");
            mMyFragment = MyFragment.newInstance("", "");

            mFragments.add(mMainFragment);
            mFragments.add(mMessageFragment);
            mFragments.add(mMyFragment);


            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    switch (checkedId) {
                        case R.id.radio_first:

                            if (currentSelect != 0) {
                                mViewPager.setCurrentItem(0);
                                currentSelect = 0;
                            }
                            break;
                        case R.id.radio_txl:
                            if (currentSelect != 1) {
                                mViewPager.setCurrentItem(1);
                                currentSelect = 1;
                            }

                            break;
                        case R.id.radio_my:

                            if (currentSelect != 2) {
                                mViewPager.setCurrentItem(2);
                                currentSelect = 2;
                            }
                            break;

                        default:
                            break;
                    }
                }
            });


            mViewPager = (ViewPager) findViewById(R.id.contain1);
            adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return mFragments.get(position);
                }

                @Override
                public int getCount() {
                    return mFragments.size();
                }
            };

            mViewPager.setAdapter(adapter);


            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int arg0) {

                    mViewPager.setCurrentItem(arg0);
                    if (currentSelect != arg0) {

                        mRadioGroup.check(tabIds[arg0]);
                        currentSelect = arg0;
                    }

                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onPageScrollStateChanged(int arg0) {

                }
            });
        }






}
