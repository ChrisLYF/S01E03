package com.zhixin.richard.s01e03.activity.tips;

import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.activity.tips.fragment.TipsBirthdayFragment;
import com.zhixin.richard.s01e03.activity.tips.fragment.TipsGoodFragment;
import com.zhixin.richard.s01e03.activity.tips.fragment.TipsMoneyFragment;
import com.zhixin.richard.s01e03.model.tips.TipGoodList;
import com.zhixin.richard.s01e03.model.tips.TipMoneyList;

/**
 * Created by Administrator on 2016-03-15.
 */
public enum TipsTab {

    BIRTYDAY(0, TipGoodList.CATALOG_ALL, R.string.frame_title_news_lastest, TipsGoodFragment.class),
    MONEY(1, TipMoneyList.CATALOG_ALL, R.string.frame_title_news_lastest, TipsMoneyFragment.class),
    GOOD(2, TipMoneyList.CATALOG_INTEGRATION,R.string.frame_title_news_blog, TipsBirthdayFragment.class);

    private TipsTab(int idx,int catalog, int title, Class<?> clz) {
        this.idx = idx;
        this.clz = clz;
        this.setCatalog(catalog);
        this.setTitle(title);
    }

    public static TipsTab getTabByIdx(int idx) {
        for (TipsTab t : values()) {
            if (t.getIdx() == idx)
                return t;
        }
        return BIRTYDAY;
    }
    private Class<?> clz;
    private int idx;
    private int title;
    private int catalog;

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
