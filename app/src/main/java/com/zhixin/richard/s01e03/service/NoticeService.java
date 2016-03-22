package com.zhixin.richard.s01e03.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.zhixin.richard.s01e03.broadcast.AlarmReceiver;
import com.zhixin.richard.s01e03.model.Notice;
import com.zhixin.richard.s01e03.utils.TLog;
import com.zhixin.richard.s01e03.utils.UIHelper;

import java.io.FileDescriptor;

/**
 * Created by Administrator on 2016-03-08.
 */
public class NoticeService extends Service {
    public static final String INTENT_ACTION_BROADCAST = "net.oschina.app.v2.service.BROADCAST";
    public static final String INTENT_ACTION_SHUTDOWN = "net.oschina.app.v2.service.SHUTDOWN";
    public static final String INTENT_ACTION_REQUEST = "net.oschina.app.v2.service.REQUEST";

    private static final long INTERVAL = 1000 * 20;
    private AlarmManager mAlarmMgr;
    private Notice mNotice;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
//            if (INTENT_ACTION_BROADCAST.equals(action)) {
//                if (mNotice != null) {
//                    UIHelper.sendBroadCast(context, mNotice);
//                }
//            } else if (INTENT_ACTION_SHUTDOWN.equals(action)) {
//                stopSelf();
//            }

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mAlarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);


        startRequestAlarm();
        requestNotice();


//        IntentFilter filter = new IntentFilter(INTENT_ACTION_BROADCAST);
//        filter.addAction(Constants.INTENT_ACTION_NOTICE);
//        filter.addAction(INTENT_ACTION_SHUTDOWN);
//        filter.addAction(INTENT_ACTION_REQUEST);
        //registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDestroy() {
        cancelRequestAlarm();
        unregisterReceiver(mReceiver);
        TLog.log("消息通知服务关闭了");
        super.onDestroy();
    }

    private final IBinder mBinder = new IBinder() {
        @Override
        public String getInterfaceDescriptor() throws RemoteException {
            return null;
        }

        @Override
        public boolean pingBinder() {
            return false;
        }

        @Override
        public boolean isBinderAlive() {
            return false;
        }

        @Override
        public IInterface queryLocalInterface(String descriptor) {
            return null;
        }

        @Override
        public void dump(FileDescriptor fd, String[] args) throws RemoteException {

        }

        @Override
        public void dumpAsync(FileDescriptor fd, String[] args) throws RemoteException {

        }

        @Override
        public boolean transact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return false;
        }

        @Override
        public void linkToDeath(DeathRecipient recipient, int flags) throws RemoteException {

        }

        @Override
        public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
            return false;
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    private void requestNotice() {
        TLog.log("请求是否有通知");

        //TODO 添加清除函数
    }


    private void startRequestAlarm() {
        cancelRequestAlarm();
        mAlarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 1000, INTERVAL,
                getOperationIntent());
    }

    private void cancelRequestAlarm() {
        mAlarmMgr.cancel(getOperationIntent());
    }

    private PendingIntent getOperationIntent() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent operation = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return operation;
    }

    private int lastNotifyCount;
    private void notification(Notice notice){

    }
}
