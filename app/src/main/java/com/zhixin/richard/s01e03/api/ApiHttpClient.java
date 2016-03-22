package com.zhixin.richard.s01e03.api;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhixin.richard.s01e03.AppContext;

import java.util.Locale;

/**
 * Created by Administrator on 2016-03-06.
 */
public class ApiHttpClient {
    public final static String HOST = "www.oschina.net";
    private static String API_URL;
    public static String ASSET_URL = "http://media-cache.pinterest.com/%s";
    public static String ATTRIB_ASSET_URL = "http://passets-ec.pinterest.com/%s";
    public static String DEFAULT_API_URL;
    public static final String DELETE = "DELETE";
    public static String DEV_API_URL = "http://192.168.155.1:8080/Control1/%s";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static AsyncHttpClient client;
    static {
        API_URL = DEV_API_URL;
    }

    public ApiHttpClient() {
    }

    public static AsyncHttpClient getHttpClient() {
        return client;
    }

    public static void cancelAll(Context context) {
        client.cancelRequests(context, true);
    }

    public static void clearUserCookies(Context context) {
        // (new HttpClientCookieStore(context)).a();
    }

    public static void delete(String partUrl, AsyncHttpResponseHandler handler) {
        client.delete(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("DELETE").append(partUrl).toString());
    }

    public static void get(String partUrl, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("GET ").append(partUrl).toString());
    }

    public static void post(String partUrl,JsonHttpResponseHandler handler){
        client.post(getAbsoluteApiUrl(partUrl),handler);
        log(new StringBuilder("POST ").append(partUrl).toString());
    }

    public static void get(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), params, handler);
        log(new StringBuilder("GET ").append(partUrl).append(params).toString());
    }
    public static void post(String partUrl,RequestParams params,JsonHttpResponseHandler handler){
        client.post(getAbsoluteApiUrl(partUrl),params,handler);
        log(new StringBuilder("POST ").append(partUrl).append(params).toString());
    }

    public static void get(String partUrl,RequestParams params,JsonHttpResponseHandler handler){
        client.get(getAbsoluteApiUrl(partUrl), params, handler);
        log(new StringBuilder("GET ").append(partUrl).append(params).toString());
    }


    public static String getAbsoluteApiUrl(String partUrl) {
        String url = String.format(API_URL, partUrl);
        Log.d("BASE_CLIENT", "request:" + url);
        return url;
    }

    public static String getApiUrl() {
        return API_URL;
    }

    public static String getAssetUrl(String url) {
        if (url.indexOf("/") == 0) {
            url = url.substring(1);
        }
        if (!url.contains("http")) {
            url = String.format(ASSET_URL, url);
        }
        return url;
    }

    public static void setUserAgent(String userAgent) {
        client.setUserAgent(userAgent);
    }
    public static void setCookie(String cookie) {
        client.addHeader("Cookie", cookie);
    }
    public static void setHttpClient(AsyncHttpClient c) {
        client = c;
        client.addHeader("Accept-Language", Locale.getDefault().toString());
        client.addHeader("Host", HOST);
        client.addHeader("Connection", "Keep-Alive");
        //setUserAgent("OSChina.NET/1.0.0.4_29/Android/4.4.4/Nexus 4/1cd6bd26-fe78-4fbd-8bcf-1dd4d121ef1d");
    }

    private static String appCookie;

    public static void cleanCookie() {
        appCookie = "";
    }

    public static String getCookie(AppContext appContext) {
        if (appCookie == null || appCookie == "") {
            appCookie = appContext.getProperty("cookie");
        }
        return appCookie;
    }
    public static void log(String log) {
        Log.d("BaseApi", log);
    }
}

