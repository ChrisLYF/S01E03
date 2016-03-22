package com.zhixin.richard.s01e03.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.base.BaseApplication;

import java.util.List;

/**
 * Created by Administrator on 2016-03-07.
 */
public class TDevice {
    public static boolean GTE_HC;
    public static boolean GTE_ICS;
    public static boolean PRE_HC;
    private static Boolean _hasBigScreen = null;
    private static Boolean _hasCamera = null;
    private static Boolean _isTablet = null;
    private static Integer _loadFactor = null;
    private static int _pageSize = -1;
    public static float displayDensity = 0.0F;

    static {
        GTE_ICS = Build.VERSION.SDK_INT >= 14;
        GTE_HC = Build.VERSION.SDK_INT >= 11;
        PRE_HC = Build.VERSION.SDK_INT >= 11 ? false : true;
    }

    public TDevice() {
    }
    public static float dpToPixel(float dp) {
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }
    public static float getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }
    public static boolean hasInternet() {
        boolean flag;
        if (((ConnectivityManager) BaseApplication.context().getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean hasBigScreen() {
        boolean flag = true;
        if (_hasBigScreen == null) {
            boolean flag1;
            if ((0xf & BaseApplication.context().getResources()
                    .getConfiguration().screenLayout) >= 3)
                flag1 = flag;
            else
                flag1 = false;
            Boolean boolean1 = Boolean.valueOf(flag1);
            _hasBigScreen = boolean1;
            if (!boolean1.booleanValue()) {
                if (getDensity() <= 1.5F)
                    flag = false;
                _hasBigScreen = Boolean.valueOf(flag);
            }
        }
        return _hasBigScreen.booleanValue();
    }

    public static float getDensity() {
        if (displayDensity == 0.0)
            displayDensity = getDisplayMetrics().density;
        return displayDensity;
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) BaseApplication.context().getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
                displaymetrics);
        return displaymetrics;
    }

    public static boolean isTablet() {
        if (_isTablet == null) {
            boolean flag;
            if ((0xf & BaseApplication.context().getResources()
                    .getConfiguration().screenLayout) >= 3)
                flag = true;
            else
                flag = false;
            _isTablet = Boolean.valueOf(flag);
        }
        return _isTablet.booleanValue();
    }

    public static int getPageSize() {
        if (_pageSize == -1)
            if (TDevice.isTablet())
                _pageSize = 50;
            else if (TDevice.hasBigScreen())
                _pageSize = 20;
            else
                _pageSize = 10;
        return _pageSize;
    }

    public static int getActionBarHeight(Context context) {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize,
                tv, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());

        if (actionBarHeight == 0
                && context.getTheme().resolveAttribute(R.attr.actionBarSize,
                tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
        }

        // OR as stated by @Marina.Eariel
        // TypedValue tv = new TypedValue();
        // if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
        // if
        // (BaseApplication.context().getTheme().resolveAttribute(android.R.attr.actionBarSize,
        // tv, true))
        // actionBarHeight =
        // TypedValue.complexToDimensionPixelSize(tv.data,context.getResources().getDisplayMetrics());
        // }else if(context.getTheme().resolveAttribute(R.attr.actionBarSize,
        // tv, true){
        // actionBarHeight =
        // TypedValue.complexToDimensionPixelSize(tv.data,context.getResources().getDisplayMetrics());
        // }
        return actionBarHeight;
    }

    /**
     * 判断指定包名的进程是否运行
     *
     * @param context
     * @param packageName
     *            指定包名
     * @return 是否运行
     */
    public static boolean isRunning(Context mContext, String packageName) {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param mContext
     * @param className
     *            判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceInfoList = activityManager.getRunningServices(300);
        if (!(serviceInfoList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceInfoList.size(); i++) {
            if (serviceInfoList.get(i).service.getClassName().equals(className)) {
                return true;
            }
        }
        return isRunning;
    }
}
