package com.zhixin.richard.s01e03.activity.member.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.zhixin.richard.s01e03.activity.member.MemberActivity;
import com.zhixin.richard.s01e03.activity.member.adapter.MemberListAdapter;
import com.zhixin.richard.s01e03.api.remote.MemberApi;
import com.zhixin.richard.s01e03.base.BaseListFragment;
import com.zhixin.richard.s01e03.base.ListBaseAdapter;
import com.zhixin.richard.s01e03.model.ListEntity;
import com.zhixin.richard.s01e03.model.member.Member;
import com.zhixin.richard.s01e03.model.member.MemberList;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-03-22.
 */
public class MemberListFragment extends BaseListFragment implements View.OnClickListener{
    protected static final String TAG = MemberListFragment.class.getSimpleName();
    private static final String CACHE_KEY_PREFIX = "memberfragment";

    @Override
    protected ListBaseAdapter getListAdapter() {
        return new MemberListAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return CACHE_KEY_PREFIX;
    }

    @Override
    protected ListEntity parseList(JSONObject jsonObject) throws Exception {
        MemberList memberList = new MemberList();
        memberList.setMemberList(Member.parses(jsonObject));
        return memberList;
    }

    @Override
    protected ListEntity readList(Serializable seri) {
        return ((MemberList) seri);
    }

    @Override
    protected void sendRequestData() {
        //TODO 修改获取列表的方法 NewsApi.getNewsList(mCatalog, mCurrentPage, mHandler);
        MemberApi.getMemberList(mHandler);
    }

    public static final String BUNDLE_KEY_DISPLAY_TYPE = "BUNDLE_KEY_DISPLAY_TYPE";
    public static final int DISPLAY_LIST = 0;
    public static final int DISPLAY_DETAIL = 1;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Member member = (Member) mAdapter.getItem(position - 1);
        if (member != null) {
            //TODO:修改
            //UIHelper.showNewsRedirect(view.getContext(), tipsLeftMoney);
            Intent intent = new Intent(getContext(), MemberActivity.class);
            intent.putExtra("member",member);
            intent.putExtra(BUNDLE_KEY_DISPLAY_TYPE, DISPLAY_DETAIL);
            startActivity(intent);
        }
    }
}
