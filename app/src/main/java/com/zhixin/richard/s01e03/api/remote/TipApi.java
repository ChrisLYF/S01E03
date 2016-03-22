package com.zhixin.richard.s01e03.api.remote;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.zhixin.richard.s01e03.api.ApiHttpClient;

/**
 * Created by Administrator on 2016-03-18.
 */
public class TipApi extends BaseApi {
    public static void getGoodTips(JsonHttpResponseHandler mHandler){
        String url = "shop/shopApi!getGoodTip";
        ApiHttpClient.get(url,mHandler);
    }
}
