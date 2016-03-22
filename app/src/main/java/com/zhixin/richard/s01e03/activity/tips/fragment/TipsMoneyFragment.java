package com.zhixin.richard.s01e03.activity.tips.fragment;

import android.view.View;
import android.widget.AdapterView;

import com.zhixin.richard.s01e03.activity.tips.adapter.TipsMoneyAdapter;
import com.zhixin.richard.s01e03.base.BaseListFragment;
import com.zhixin.richard.s01e03.base.ListBaseAdapter;
import com.zhixin.richard.s01e03.model.ListEntity;
import com.zhixin.richard.s01e03.model.tips.TipMoney;
import com.zhixin.richard.s01e03.model.tips.TipMoneyList;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by Administrator on 2016-03-15.
 */
public class TipsMoneyFragment extends BaseListFragment {

    protected static final String TAG = TipsMoneyFragment.class.getSimpleName();
    private static final String CACHE_KEY_PREFIX = "newslist_";

    @Override
    protected ListBaseAdapter getListAdapter() {
        return new TipsMoneyAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return CACHE_KEY_PREFIX;
    }

    @Override
    protected ListEntity parseList(InputStream is) throws Exception {
        ListEntity list =null;
        return list;
    }

    @Override
    protected ListEntity readList(Serializable seri) {
        return ((TipMoneyList) seri);
    }

    @Override
    protected void sendRequestData() {
//        NewsApi.getNewsList(mCatalog, mCurrentPage, mHandler);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        TipMoney tipMoney = (TipMoney) mAdapter.getItem(position - 1);
        if (tipMoney != null) {
//            UIHelper.showNewsRedirect(view.getContext(), news);
        }
    }
}
