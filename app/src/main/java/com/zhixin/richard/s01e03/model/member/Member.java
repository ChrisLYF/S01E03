package com.zhixin.richard.s01e03.model.member;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhixin.richard.s01e03.utils.DateDeserializer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2016-03-21.
 */
public class Member implements Serializable{
    public String id;// 会员ID
    public String name;// 会员名称
    public String phone;// 会员电话
    public String password;// 会员密码
    public String confirmPassword;// 会员确认密码
    public String degrade;// 等级标志
    public boolean transFlag;// 积分自动转换等级标志
    public String sex;// 会员性别
    public boolean birthdayFlag;// 生日标志
    public String birthdayType;// 生日类型 阴历阳历
    public Date birthday;// 生日
    public boolean overFlag;// 过期标志
    public Date overDate;// 过期日期
    public String address;// 地址
    public String status;// 状态
    public double cardPay;// 卡费
    public double leftMoney;// 剩余钱数
    public double consumeMoney;// 共消费钱数
    public double chargeMoney; // 累计充值金额
    public double chargeTimeMoney;// 计次类商品充值金额
    public double chargeCycleMoney;// 周期类商品充值金额
    public double presentMoney;// 优惠金额
    public int totalPoint;// 总共积分数
    public int currentPoint;// 当前积分数
    public String bak;
    public Date createTime;// 创建时间
    public Date lastTimeDate;// 上次使用时间
    public String operator;// 操作人员
    public String operateShop;// 操作店铺
    public String photoPath;

    public double endConsumeMoney;// 共消费钱数，查询时使用
    public int endTotalPoint;
    public Date endCrateTime;// 截止生日，查询时使用

    public double getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(double consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public double getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(double chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public double getChargeTimeMoney() {
        return chargeTimeMoney;
    }

    public void setChargeTimeMoney(double chargeTimeMoney) {
        this.chargeTimeMoney = chargeTimeMoney;
    }

    public double getChargeCycleMoney() {
        return chargeCycleMoney;
    }

    public void setChargeCycleMoney(double chargeCycleMoney) {
        this.chargeCycleMoney = chargeCycleMoney;
    }

    public double getPresentMoney() {
        return presentMoney;
    }

    public void setPresentMoney(double presentMoney) {
        this.presentMoney = presentMoney;
    }

    public double getEndConsumeMoney() {
        return endConsumeMoney;
    }

    public void setEndConsumeMoney(double endConsumeMoney) {
        this.endConsumeMoney = endConsumeMoney;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getDegrade() {
        return degrade;
    }

    public void setDegrade(String degrade) {
        this.degrade = degrade;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getTransFlag() {
        return transFlag;
    }

    public void setTransFlag(boolean transFlag) {
        this.transFlag = transFlag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean getBirthdayFlag() {
        return birthdayFlag;
    }

    public void setBirthdayFlag(boolean birthdayFlag) {
        this.birthdayFlag = birthdayFlag;
    }

    public String getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(String birthdayType) {
        this.birthdayType = birthdayType;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean getOverFlag() {
        return overFlag;
    }

    public void setOverFlag(boolean overFlag) {
        this.overFlag = overFlag;
    }

    public Date getOverDate() {
        return overDate;
    }

    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCardPay() {
        return cardPay;
    }

    public void setCardPay(double cardPay) {
        this.cardPay = cardPay;
    }

    public double getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(double leftMoney) {
        this.leftMoney = leftMoney;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public int getEndTotalPoint() {
        return endTotalPoint;
    }

    public void setEndTotalPoint(int endTotalPoint) {
        this.endTotalPoint = endTotalPoint;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndCrateTime() {
        return endCrateTime;
    }

    public void setEndCrateTime(Date endCrateTime) {
        this.endCrateTime = endCrateTime;
    }

    public Date getLastTimeDate() {
        return lastTimeDate;
    }

    public void setLastTimeDate(Date lastTimeDate) {
        this.lastTimeDate = lastTimeDate;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public static Member parse(JSONObject jsonObject) {
        Member member = new Member();
        GsonBuilder gsonBuilder = new GsonBuilder();
        DateDeserializer ds = new DateDeserializer();
        gsonBuilder.registerTypeAdapter(Date.class, ds);
        Gson gson = gsonBuilder.create();
        try {
            member = gson.fromJson(jsonObject.toString(), Member.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return member;
    }

    public static ArrayList<Member> parses(JSONObject jsonObject) {
        ArrayList<Member> members = new ArrayList<Member>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        DateDeserializer ds = new DateDeserializer();
        gsonBuilder.registerTypeAdapter(Date.class, ds);
        Gson gson = gsonBuilder.create();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("memberlist");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                members.add(gson.fromJson(json.toString(), Member.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return members;
    }
}
