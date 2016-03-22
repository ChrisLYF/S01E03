package com.zhixin.richard.s01e03.model.shop;

import com.zhixin.richard.s01e03.model.Base;

import java.util.Date;

/**
 * Created by Administrator on 2016-03-17.
 */
public class Shop extends Base {
    public int id;
    public int storeId;// storeId
    public String storeName;// Store名称
    public int fId;// 总店ID
    public String fName;// 总店名称
    public String name;
    public String address;
    public String phone;
    public int msgCount;// 短信发送的总数
    public int msgSend;
    public int msgLeft;
    public boolean headFlag; // 总店标志
    public String operator;
    public String operateShop;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public int getMsgSend() {
        return msgSend;
    }

    public void setMsgSend(int msgSend) {
        this.msgSend = msgSend;
    }

    public int getMsgLeft() {
        return msgLeft;
    }

    public void setMsgLeft(int msgLeft) {
        this.msgLeft = msgLeft;
    }

    public boolean isHeadFlag() {
        return headFlag;
    }

    public void setHeadFlag(boolean headFlag) {
        this.headFlag = headFlag;
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
}
