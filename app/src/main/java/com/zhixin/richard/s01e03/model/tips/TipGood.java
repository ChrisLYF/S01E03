package com.zhixin.richard.s01e03.model.tips;

import java.util.Date;

/**
 * Created by Administrator on 2016-03-15.
 */
public class TipGood {
    public int id;
    public String title;
    public Date createTime;
    public String shopName;
    public String operator;
    public String operateShop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateShop() {
        return operateShop;
    }

    public void setOperateShop(String operateShop) {
        this.operateShop = operateShop;
    }

    @Override
    public String toString() {
        return "TipGood{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", shopName='" + shopName + '\'' +
                ", operator='" + operator + '\'' +
                ", operateShop='" + operateShop + '\'' +
                '}';
    }
}
