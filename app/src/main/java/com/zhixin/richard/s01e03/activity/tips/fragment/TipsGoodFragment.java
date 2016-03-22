package com.zhixin.richard.s01e03.activity.tips.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhixin.richard.s01e03.AppContext;
import com.zhixin.richard.s01e03.activity.tips.adapter.TipsGoodAdapter;
import com.zhixin.richard.s01e03.activity.user.LoginActivity;
import com.zhixin.richard.s01e03.api.remote.TipApi;
import com.zhixin.richard.s01e03.base.BaseListFragment;
import com.zhixin.richard.s01e03.base.ListBaseAdapter;
import com.zhixin.richard.s01e03.constants.user.UserConstants;
import com.zhixin.richard.s01e03.model.ListEntity;
import com.zhixin.richard.s01e03.model.Result;
import com.zhixin.richard.s01e03.model.tips.TipGood;
import com.zhixin.richard.s01e03.model.tips.TipGoodList;
import com.zhixin.richard.s01e03.utils.DateDeserializer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2016-03-15.
 */
public class TipsGoodFragment extends BaseListFragment {
    protected static final String TAG = TipsGoodFragment.class.getSimpleName();
    private static final String CACHE_KEY_PREFIX = "tipsGoodlist_";

    @Override
    protected ListBaseAdapter getListAdapter() {
        return new TipsGoodAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return CACHE_KEY_PREFIX;
    }

    @Override
    protected ListEntity parseList(JSONObject jsonObject) throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        DateDeserializer ds = new DateDeserializer();
        gsonBuilder.registerTypeAdapter(Date.class, ds);
        Gson gson = gsonBuilder.create();
        ArrayList<TipGood> tipGoods = new ArrayList<TipGood>();
        Result result = gson.fromJson(jsonObject.get("result").toString(), Result.class);
        if (result.getErrorCode() == UserConstants.login_code_right) {
            JSONArray jsonArray = jsonObject.getJSONArray("tipgoodlist");
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = (JSONObject) jsonArray.get(i);
                    tipGoods.add(gson.fromJson(json.toString(), TipGood.class));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AppContext.showToast(result.getErrorMessage());
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        TipGoodList tipGoodList = new TipGoodList();
        tipGoodList.setTipGoodList(tipGoods);
        ListEntity list = tipGoodList;
        return list;
    }

    @Override
    protected ListEntity readList(Serializable seri) {
        return ((TipGoodList) seri);
    }

    @Override
    protected void sendRequestData() {
        //TODO 修改获取列表的方法 NewsApi.getNewsList(mCatalog, mCurrentPage, mHandler);
        TipApi.getGoodTips(mHandler);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        TipGood tipGood = (TipGood) mAdapter.getItem(position - 1);
        if (tipGood != null) {
            //TODO:修改
            //UIHelper.showNewsRedirect(view.getContext(), tipsLeftMoney);
        }
    }
}
