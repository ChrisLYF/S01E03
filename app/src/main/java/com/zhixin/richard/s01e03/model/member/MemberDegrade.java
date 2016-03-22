package com.zhixin.richard.s01e03.model.member;

import java.util.Date;

/**
 * Created by Administrator on 2016-03-21.
 */
public class MemberDegrade {
    public int id;// 等级序号
    public String name;// 等级名称
    public int needPoint;// 所需积分
    public double exchangeScale;// 积分兑换比例
    public double normalGoodScale;// 普通商品打折比例
    public double serveGoodScale;// 服务类商品打折比例
    public int count;// 当前会员数
    public Date createTime;// 创建时间
    public String operator;// 操作人员
    public String operateShop;// 操作店铺

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeedPoint() {
        return needPoint;
    }

    public void setNeedPoint(int needPoint) {
        this.needPoint = needPoint;
    }

    public double getExchangeScale() {
        return exchangeScale;
    }

    public void setExchangeScale(double exchangeScale) {
        this.exchangeScale = exchangeScale;
    }

    public double getNormalGoodScale() {
        return normalGoodScale;
    }

    public void setNormalGoodScale(double normalGoodScale) {
        this.normalGoodScale = normalGoodScale;
    }

    public double getServeGoodScale() {
        return serveGoodScale;
    }

    public void setServeGoodScale(double serveGoodScale) {
        this.serveGoodScale = serveGoodScale;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        return "MemberDegrade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", needPoint=" + needPoint +
                ", exchangeScale=" + exchangeScale +
                ", normalGoodScale=" + normalGoodScale +
                ", serveGoodScale=" + serveGoodScale +
                ", count=" + count +
                ", createTime=" + createTime +
                ", operator='" + operator + '\'' +
                ", operateShop='" + operateShop + '\'' +
                '}';
    }
}
