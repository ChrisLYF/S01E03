package com.zhixin.richard.s01e03.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zhixin.richard.s01e03.AppContext;
import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.activity.menu.fragment.MenuTabPagerFragment;
import com.zhixin.richard.s01e03.activity.tips.fragment.TipsViewPagerFragment;
import com.zhixin.richard.s01e03.base.BaseActivity;
import com.zhixin.richard.s01e03.model.shop.Shop;
import com.zhixin.richard.s01e03.model.user.User;

/**
 * Created by Administrator on 2016-03-08.
 */
public class MainActivity extends BaseActivity {
    private static final String MAIN_SCREEN = "MainScreen";
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Shop shop;
    private User user;

    private BroadcastReceiver mNoticeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //提交TipsFragment
        Fragment tipsFragment = manager.findFragmentById(R.id.realtabcontent);
        if (tipsFragment == null) {
            tipsFragment = new TipsViewPagerFragment();
        }
        transaction.add(R.id.realtabcontent, tipsFragment);

        //提交MenuFragment
        Fragment menuFragment = manager.findFragmentById(R.id.linearlayou_main_menu);
        if (menuFragment == null) {
            menuFragment = new MenuTabPagerFragment();
        }
        transaction.add(R.id.linearlayou_main_menu, menuFragment).commit();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user = (User) bundle.getSerializable("user");
        shop = (Shop) bundle.getSerializable("shop");
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    private long mLastExitTime;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastExitTime < 2000) {
            super.onBackPressed();
        } else {
            mLastExitTime = System.currentTimeMillis();
            AppContext.showToastShort(R.string.tip_click_back_again_to_exist);
        }
    }
}
