package com.zhixin.richard.s01e03.api.remote;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhixin.richard.s01e03.api.ApiHttpClient;

import org.apache.http.protocol.HTTP;

/**
 * Created by Administrator on 2016-03-07.
 */
public class UserApi extends BaseApi{
    public static void login(String username, String password,
                             AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("store","db_17701320788");
        params.put("name", username);
        params.put("password", password);
        params.put("type", "客户端登录");
        String loginurl = "login/loginApi!login";
        ApiHttpClient.get(loginurl, params, handler);
    }

    public static void login(String username,String password,JsonHttpResponseHandler handler){
        RequestParams params = new RequestParams();
        params.put("store","17701320788");
        params.put("name", username);
        params.put("password", password);
        params.put("type", "客户端登录");
        String loginurl = "login/loginApi!login";
        params.setContentEncoding(HTTP.UTF_8);
        ApiHttpClient.post(loginurl, params, handler);
    }
}
