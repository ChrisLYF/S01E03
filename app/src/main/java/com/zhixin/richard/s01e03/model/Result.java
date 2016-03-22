package com.zhixin.richard.s01e03.model;

/**
 * Created by Administrator on 2016-03-07.
 */
public class Result {
    private int errorCode;
    private String errorMessage;


    public boolean OK() {
        return errorCode == 1;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Result{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
