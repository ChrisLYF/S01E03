package com.zhixin.richard.s01e03.api.remote;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.zhixin.richard.s01e03.api.ApiHttpClient;

/**
 * Created by Administrator on 2016-03-21.
 */
public class MemberApi  extends BaseApi {
    public static void getMemberList(JsonHttpResponseHandler mHandler){
        String url = "member/memberApi!getMemberList";
        ApiHttpClient.post(url, mHandler);
    }
}
