package com.zhixin.richard.s01e03.model.tips;

import com.zhixin.richard.s01e03.model.Entity;
import com.zhixin.richard.s01e03.model.ListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-03-15.
 */
public class TipGoodList extends Entity implements ListEntity {

    public final static int CATALOG_ALL = 1;
    public final static int CATALOG_INTEGRATION = 2;
    public final static int CATALOG_SOFTWARE = 3;


    private int catalog;
    private int pageSize;
    private int newsCount;

    private List<TipGood> tipGoodList = new ArrayList<TipGood>();

    public int getCatalog() {
        return catalog;
    }

    public void setCatalog(int catalog) {
        this.catalog = catalog;
    }

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

    public List<TipGood> getTipGoodList() {
        return tipGoodList;
    }

    public void setTipGoodList(List<TipGood> tipGoodList) {
        this.tipGoodList = tipGoodList;
    }

    @Override
    public List<?> getList() {
        return tipGoodList;
    }
}
