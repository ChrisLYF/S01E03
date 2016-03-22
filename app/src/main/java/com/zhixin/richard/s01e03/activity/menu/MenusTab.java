package com.zhixin.richard.s01e03.activity.menu;

import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.activity.menu.fragment.Menu1Fragment;
import com.zhixin.richard.s01e03.activity.menu.fragment.Menu2Fragment;
import com.zhixin.richard.s01e03.activity.menu.fragment.Menu3Fragment;
import com.zhixin.richard.s01e03.activity.tips.fragment.TipsGoodFragment;
import com.zhixin.richard.s01e03.model.tips.TipGoodList;

/**
 * Created by Administrator on 2016-03-16.
 */
public enum MenusTab {
    LASTEST(0, TipGoodList.CATALOG_ALL, R.string.frame_title_news_lastest, Menu1Fragment.class),
    LASTEST1(1, TipGoodList.CATALOG_ALL, R.string.frame_title_news_lastest, Menu2Fragment.class),
    LASTEST2(2, TipGoodList.CATALOG_ALL, R.string.frame_title_news_lastest, Menu3Fragment.class),
    ;
    private Class<?> clz;
    private int idx;
    private int title;
    private int catalog;

    private MenusTab(int idx,int catalog, int title, Class<?> clz) {
        this.idx = idx;
        this.clz = clz;
        this.setCatalog(catalog);
        this.setTitle(title);
    }

    public static MenusTab getTabByIdx(int idx) {
        for (MenusTab t : values()) {
            if (t.getIdx() == idx)
                return t;
        }
        return LASTEST;
    }
    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getCatalog() {
        return catalog;
    }

    public void setCatalog(int catalog) {
        this.catalog = catalog;
    }
}
