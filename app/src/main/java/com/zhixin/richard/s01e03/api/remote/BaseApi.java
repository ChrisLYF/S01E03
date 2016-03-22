package com.zhixin.richard.s01e03.api.remote;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhixin.richard.s01e03.api.ApiHttpClient;
import com.zhixin.richard.s01e03.utils.TDevice;

/**
 * Created by Administrator on 2016-03-07.
 */
public class BaseApi {
    public static final int DEF_PAGE_SIZE = TDevice.getPageSize();
    private static final String CLIENT_ID = "F6QtiYRetdUEwsYKYvNR";
    private static final String REDIRECT_URI = "http://my.oschina.net/u/142883";
    public static void authorize(AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("client_id", CLIENT_ID);
        params.put("response_type", "code");
        params.put("redirect_uri", REDIRECT_URI);
        ApiHttpClient.get("action/oauth2/authorize", params, handler);
    }
}
