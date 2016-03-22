package com.zhixin.richard.s01e03.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zhixin.richard.s01e03.utils.TLog;

/**
 * Created by Administrator on 2016-03-14.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TLog.log("onReceive ->收到定时获取消息");
        //TODO NoticeUtils.requestNotice(context);
    }
}
