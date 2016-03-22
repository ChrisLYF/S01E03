package com.zhixin.richard.s01e03.model;

/**
 * Created by Administrator on 2016-03-15.
 */
public class Entity extends Base{
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected String cacheKey;

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }
}
