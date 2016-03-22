package com.zhixin.richard.s01e03.model.member;

import com.zhixin.richard.s01e03.model.Entity;
import com.zhixin.richard.s01e03.model.ListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-03-22.
 */
public class MemberList extends Entity implements ListEntity {
    private int pageSize;
    private int newsCount;

    private List<Member> memberList = new ArrayList<Member>();


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(int newsCount) {
        this.newsCount = newsCount;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    @Override
    public List<?> getList() {
        return memberList;
    }
}
