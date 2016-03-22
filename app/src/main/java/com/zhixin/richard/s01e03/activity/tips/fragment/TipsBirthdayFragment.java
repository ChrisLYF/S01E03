package com.zhixin.richard.s01e03.activity.tips.fragment;

import android.view.View;
import android.widget.AdapterView;

import com.zhixin.richard.s01e03.activity.tips.adapter.TipsBirthdayAdapter;
import com.zhixin.richard.s01e03.base.BaseListFragment;
import com.zhixin.richard.s01e03.base.ListBaseAdapter;
import com.zhixin.richard.s01e03.model.ListEntity;
import com.zhixin.richard.s01e03.model.tips.TipBirthdayList;
import com.zhixin.richard.s01e03.model.tips.TipBirtyday;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by Administrator on 2016-03-15.
 */
public class TipsBirthdayFragment extends BaseListFragment {
    protected static final String TAG = TipsBirthdayFragment.class.getSimpleName();
    private static final String CACHE_KEY_PREFIX = "tipsBirthdayList";

    @Override
    protected ListBaseAdapter getListAdapter() {
        return new TipsBirthdayAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return CACHE_KEY_PREFIX;
    }

    @Override
    protected ListEntity parseList(InputStream is) throws Exception {
        //TODO 这里需要将解析改变
        ListEntity list =null;
        return list;
    }

    @Override
    protected ListEntity readList(Serializable seri) {
        return ((TipBirthdayList) seri);
    }

    @Override
    protected void sendRequestData() {
       //TODO 修改获取列表的方法 NewsApi.getNewsList(mCatalog, mCurrentPage, mHandler);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        TipBirtyday tipBirtyday = (TipBirtyday) mAdapter.getItem(position - 1);
        if (tipBirtyday != null) {
            //TODO:修改
            //UIHelper.showNewsRedirect(view.getContext(), tipsLeftMoney);
        }
    }























}
