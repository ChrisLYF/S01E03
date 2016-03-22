package com.zhixin.richard.s01e03.activity.tips.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.zhixin.richard.s01e03.activity.tips.TipsTab;
import com.zhixin.richard.s01e03.activity.tips.fragment.TipsBirthdayFragment;
import com.zhixin.richard.s01e03.base.BaseTabFragment;
import com.zhixin.richard.s01e03.ui.pagertab.SlidingTabPagerAdapter;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016-03-15.
 */
public class TipsTabPagerAdapter extends SlidingTabPagerAdapter {
   public TipsTabPagerAdapter(FragmentManager fragmentManager, Context context, ViewPager viewPager){
       super(fragmentManager, TipsTab.values().length, context.getApplicationContext(), viewPager);
       TipsTab[] values = TipsTab.values();
       for(int i =0;i<values.length;i++){

           //添加的fragmentManager
           Fragment fragment = null;
           List<Fragment> list = fragmentManager.getFragments();
           if(list!=null){
               Iterator<Fragment> iterator = list.iterator();
               while(iterator.hasNext()){
                   fragment = iterator.next();
                   if(fragment.getClass() == values[i].getClz()){
                       break;
                   }
               }
           }

           //填充Fragment
           BaseTabFragment tabFragment = (BaseTabFragment)fragment;
           if(tabFragment==null){
               try{
                   tabFragment = (BaseTabFragment)values[i].getClz().newInstance();
               }catch (InstantiationException e){
                   e.printStackTrace();
               }catch (IllegalAccessException e){
                   e.printStackTrace();
               }
           }
           tabFragment.a(this);

           if(!tabFragment.isAdded()){
               Bundle args = new Bundle();
               args.putInt(TipsBirthdayFragment.BUNDLE_KEY_CATALOG,values[i].getCatalog());
               tabFragment.setArguments(args);
           }
           fragments[values[i].getIdx()] = tabFragment;
       }
   }

    public final int getCacheCount() {
        return 2;
    }

    public final int getCount() {
        return TipsTab.values().length;
    }

    public final CharSequence getPageTitle(int i) {
        TipsTab tab = TipsTab.getTabByIdx(i);
        int idx = 0;
        CharSequence title = "";
        if (tab != null)
            idx = tab.getTitle();
        if (idx != 0)
            title = context.getText(idx);
        return title;
    }
    





































}
