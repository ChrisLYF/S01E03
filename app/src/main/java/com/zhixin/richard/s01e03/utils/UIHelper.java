package com.zhixin.richard.s01e03.utils;

import android.content.Context;
import android.content.Intent;

import com.zhixin.richard.s01e03.base.Constants;
import com.zhixin.richard.s01e03.model.Notice;

/**
 * Created by Administrator on 2016-03-14.
 */
public class UIHelper {

    public static void sendBroadCast(Context context, Notice notice) {
        // if (!((AppContext) context.getApplicationContext()).isLogin()
        // || notice == null)
        // return;
        TLog.log("发送通知广播");
        Intent intent = new Intent(Constants.INTENT_ACTION_NOTICE);

        context.sendBroadcast(intent);
    }
}
