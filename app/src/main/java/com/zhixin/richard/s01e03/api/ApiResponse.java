package com.zhixin.richard.s01e03.api;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016-03-17.
 */
public class ApiResponse {
    protected Object _data;
    protected String _message;
    protected int _errorCode;
    protected boolean _isOK;
    protected long _total;
    private String _serverTime;
    private boolean isCancled;


    public ApiResponse(JSONObject json) {
        if (json != null) {
            set_data(json.optJSONObject("data") == null ? json.optJSONArray("data") : json.optJSONObject("data"));
            set_message(json.optString("result_desc"));
            set_errorCode(json.optInt("result_code"));
            set_isOK(get_errorCode() == 0);
            set_serverTime(json.optString("timestamp"));
        }
    }

    public void update(ApiResponse response) {
        _message = response.get_message();
    }

    public Object get_data() {
        return _data;
    }

    public void set_data(Object _data) {
        this._data = _data;
    }

    public String get_message() {
        return _message;
    }

    public void set_message(String _message) {
        this._message = _message;
    }

    public int get_errorCode() {
        return _errorCode;
    }

    public void set_errorCode(int _errorCode) {
        this._errorCode = _errorCode;
    }

    public boolean is_isOK() {
        return _isOK;
    }

    public void set_isOK(boolean _isOK) {
        this._isOK = _isOK;
    }

    public long get_total() {
        return _total;
    }

    public void set_total(long _total) {
        this._total = _total;
    }

    public String get_serverTime() {
        return _serverTime;
    }

    public void set_serverTime(String _serverTime) {
        this._serverTime = _serverTime;
    }

    public boolean isCancled() {
        return isCancled;
    }

    public void setIsCancled(boolean isCancled) {
        this.isCancled = isCancled;
    }
}
