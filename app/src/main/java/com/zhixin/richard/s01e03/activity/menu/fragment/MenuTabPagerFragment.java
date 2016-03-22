package com.zhixin.richard.s01e03.activity.menu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.activity.menu.MenusTab;
import com.zhixin.richard.s01e03.activity.menu.adapter.MenuPagerAdapter;
import com.zhixin.richard.s01e03.base.BaseFragment;

/**
 * Created by Administrator on 2016-03-16.
 */
public class MenuTabPagerFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    private ViewPager mMenuPager;
    private LinearLayout mLinearLayout;
    private MenuPagerAdapter mMenuAdapter;
    private ImageView[] dots;
    private int dotIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mMenuPager = (ViewPager) view.findViewById(R.id.viewpager_main_menu);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.linearlayout_main_menu_tips);
        if (mMenuPager.getAdapter() == null && mMenuAdapter == null) {
            mMenuAdapter = new MenuPagerAdapter(getChildFragmentManager(), getContext(), mMenuPager);
        }
        mMenuPager.setAdapter(mMenuAdapter);
        mMenuPager.setOnPageChangeListener(this);
        initView();
        return view;
    }

    private void initView() {
        int count = MenusTab.values().length;
        if (0 < count) {
            mLinearLayout.removeAllViews();
            if (count == 1) {
                mLinearLayout.setVisibility(View.GONE);
            } else if (count > 1) {
                mLinearLayout.setVisibility(View.VISIBLE);
                for (int i = 0; i < count; i++) {
                    ImageView image = new ImageView(getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
                    params.setMargins(10, 0, 10, 0);
                    image.setBackgroundResource(R.drawable.dot_normal);
                    mLinearLayout.addView(image, params);
                }
            }
        }

        if (count > 1) {
            dots = new ImageView[count];
            for (int i = 0; i < count; i++) {
                dots[i] = (ImageView) mLinearLayout.getChildAt(i);
                dots[i].setEnabled(true);
                dots[i].setTag(i);
            }
            dotIndex = 0;
            dots[dotIndex].setBackgroundResource(R.drawable.dot_focused);
        }
    }

    private void setCurDot(int position) {
        if (position < 0 || position > MenusTab.values().length || dotIndex == position) {
            return;
        }
        dots[position].setBackgroundResource(R.drawable.dot_focused);
        dots[dotIndex].setBackgroundResource(R.drawable.dot_normal);
        dotIndex = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        setCurDot(position);
    }

    @Override
    public void onPageSelected(int position) {
        setCurDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
