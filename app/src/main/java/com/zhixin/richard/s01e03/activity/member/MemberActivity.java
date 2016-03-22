package com.zhixin.richard.s01e03.activity.member;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.activity.member.fragment.MemberFragment;
import com.zhixin.richard.s01e03.activity.member.fragment.MemberListFragment;
import com.zhixin.richard.s01e03.base.BaseActivity;
import com.zhixin.richard.s01e03.base.BaseFragment;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016-03-16.
 */
public class MemberActivity extends BaseActivity {
    public static final int DISPLAY_LIST = 0;
    public static final int DISPLAY_DETAIL = 1;

    public static final String BUNDLE_KEY_DISPLAY_TYPE = "BUNDLE_KEY_DISPLAY_TYPE";

    private WeakReference<BaseFragment> mFragment;

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Intent intent = getIntent();
        int displayType = intent.getIntExtra(BUNDLE_KEY_DISPLAY_TYPE,DISPLAY_LIST);
        int actionBarTitile = 0;
        BaseFragment fragment = null;
        switch (displayType){
            case DISPLAY_LIST:
                actionBarTitile = R.string.actionbar_title_news;
                fragment = new MemberListFragment();
                break;
            case DISPLAY_DETAIL:
                actionBarTitile = R.string.actionbar_title_detail;
                fragment = new MemberFragment();
                break;
            default:
                break;
        }
        setActionBarTitle(actionBarTitile);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mFragment = new WeakReference<BaseFragment>(fragment);
        transaction.replace(R.id.fl_member_container, fragment);
        setActionBarTitle(R.string.actionbar_title_detail);
        transaction.commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member;
    }

    @Override
    protected int getActionBarTitle() {
        return R.string.frame_title_favorite_post;
    }


    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }
}
