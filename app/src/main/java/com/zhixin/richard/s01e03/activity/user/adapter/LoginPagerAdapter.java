package com.zhixin.richard.s01e03.activity.user.adapter;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.utils.ImageDownLoad;

import java.util.List;

/**
 * Created by Administrator on 2016-03-14.
 */
public class LoginPagerAdapter extends PagerAdapter {
    private List<View> content;

    public LoginPagerAdapter(List<View> content) {
        this.content = content;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = content.get(position);
        final ImageView imageView = (ImageView) view
                .findViewById(R.id.imageView1);
        imageView.setImageResource(R.drawable.actionbar_back_icon_normal);
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(content.get(position));
    }
}
