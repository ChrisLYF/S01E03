package com.zhixin.richard.s01e03.activity.menu.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.zhixin.richard.s01e03.activity.menu.MenusTab;
import com.zhixin.richard.s01e03.base.BaseTabFragment;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016-03-16.
 */
public class MenuPagerAdapter extends FragmentPagerAdapter  implements ViewPager.OnPageChangeListener{
    protected  final Context context;
    protected final BaseTabFragment[] fragments;
    private final ViewPager viewPager;
    private int lastPostion;

    public MenuPagerAdapter(FragmentManager manager, Context context, ViewPager viewPager) {
        super(manager);
        lastPostion = 0;
        //创建多个Fragment
        fragments = new BaseTabFragment[MenusTab.values().length];
        this.context = context;
        this.viewPager= viewPager;
        lastPostion = 0;

        MenusTab[] values = MenusTab.values();
        for (int i = 0; i < values.length; i++) {
            Fragment fragment = null;
            List<Fragment> list = manager.getFragments();
            if (list != null) {
                Iterator<Fragment> iterator = list.iterator();
                while (iterator.hasNext()) {
                    fragment = iterator.next();
                    if (fragment.getClass() == values[i].getClz()) {
                        break;
                    }
                }
            }
            BaseTabFragment tabFragment = (BaseTabFragment) fragment;
            if (tabFragment == null) {
                try {
                    tabFragment = (BaseTabFragment) values[i].getClz().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
//            if (!tabFragment.isAdded()) {
//                Bundle args = new Bundle();
//                args.putInt("1",
//                        values[i].getCatalog());
//                tabFragment.setArguments(args);
//            }
            fragments[values[i].getIdx()] = tabFragment;
        }
    }

    public final int getCacheCount() {
        return 2;
    }

    public final int getCount() {
        return MenusTab.values().length;
    }

    private BaseTabFragment getFragmentByPosition(int i){
        BaseTabFragment fragment = null;
        if(i>=0&&i<fragments.length){
            fragment = fragments[i];
        }
        return fragment;
    }

    private void onLeave(int i){
        BaseTabFragment fragment = getFragmentByPosition(lastPostion);
        lastPostion = i;
        if(fragment!=null){
            fragment.g();
        }
    }

    @Override
    public BaseTabFragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onPageSelected( int i ){
        BaseTabFragment fragment = getFragmentByPosition(i);
        if(fragment !=null){
            fragment.f();
            onLeave(i);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("TAG",position+"");
        BaseTabFragment fragment = getFragmentByPosition(position);
        if(fragment!=null){
            fragment.h();
            onLeave(position);
        }
    }
}
