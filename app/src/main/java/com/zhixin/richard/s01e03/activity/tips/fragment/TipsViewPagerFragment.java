package com.zhixin.richard.s01e03.activity.tips.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.activity.tips.adapter.TipsTabPagerAdapter;
import com.zhixin.richard.s01e03.base.BaseFragment;
import com.zhixin.richard.s01e03.ui.pagertab.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2016-03-15.
 */
public class TipsViewPagerFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    private PagerSlidingTabStrip mTabStrip;
    private ViewPager mViewPager;
    private TipsTabPagerAdapter mTabAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        mTabStrip = (PagerSlidingTabStrip)view.findViewById(R.id.main_tabs);
        mViewPager = (ViewPager)view.findViewById(R.id.main_tab_pager);

        if(mViewPager.getAdapter()==null&&mTabAdapter == null){
            mTabAdapter = new TipsTabPagerAdapter(getChildFragmentManager(),getActivity(),mViewPager);
        }
        mViewPager.setOffscreenPageLimit(mTabAdapter.getCacheCount());
        mViewPager.setAdapter(mTabAdapter);
        mViewPager.setOnPageChangeListener(this);
        mTabStrip.setViewPager(mViewPager);
        return view;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mTabStrip.onPageScrollStateChanged(state);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mTabStrip.onPageScrolled(position,positionOffset,positionOffsetPixels);
        mTabAdapter.onPageScrolled(position);
    }

    @Override
    public void onPageSelected(int position) {
        mTabStrip.onPageSelected(position);
        mTabAdapter.onPageSelected(position);
    }























}
