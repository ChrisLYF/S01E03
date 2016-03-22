package com.zhixin.richard.s01e03.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;
import com.zhixin.richard.s01e03.AppContext;
import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.activity.member.MemberActivity;
import com.zhixin.richard.s01e03.activity.user.LoginActivity;
import com.zhixin.richard.s01e03.utils.StringUtils;
import com.zhixin.richard.s01e03.utils.TDevice;

import junit.runner.Version;

/**
 * Created by Administrator on 2016-03-08.
 */
public class SplashActivity extends Activity {
    private static final String SPLASH_SCREEN = "SplashScreen";
    public static final int MAX_WATTING_TIME = 300;//停留3m
    protected Version mVersion;
    protected boolean mShouldGOTo = true;

    @Override
    protected void onRestart() {
        super.onRestart();
        MobclickAgent.onPageStart(SPLASH_SCREEN);//统计页面
        MobclickAgent.onResume(this);//统计时长
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageStart(SPLASH_SCREEN);//保证onPageEnd在onpause
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否显示Splash界面，基于NoticeService是否启动
        if(TDevice.isServiceRunning(this,"com.zhixin.richard.s01e03.service.NoticeService")){
            redirectTo();
            return;
        }
        checkUpdate();
        final View view = View.inflate(this, R.layout.activity_splash,null);
        setContentView(view);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f,1.0f);
        alphaAnimation.setDuration(MAX_WATTING_TIME);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mShouldGOTo) {
                    redirectTo();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AppContext appContext = (AppContext)getApplication();
        String cookie = appContext.getProperty("cookie");
        if(StringUtils.isEmail(cookie)){
            String cookie_name=appContext.getProperty("cookie_name");
            String cookie_value = appContext.getProperty("cookie_value");
            if(!StringUtils.isEmail(cookie_name)&&!StringUtils.isEmail(cookie_value)){
                cookie = cookie_name+"="+cookie_value;
                appContext.setProperty("cookie",cookie);
                appContext.removeProperty("cookie_domian","cookie_name","cookie_value","cookie_version","cookie_path");
            }
        }
    }

    private void checkUpdate() {
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                switch (updateStatus) {
                    case UpdateStatus.Yes://has update;
                        mShouldGOTo = false;
                        UmengUpdateAgent.showUpdateDialog(getApplicationContext(), updateInfo);
                        break;
                    case UpdateStatus.No://has no update
                        break;
                    case UpdateStatus.NoneWifi:// none wifi
                        break;
                    case UpdateStatus.Timeout://time out
                        break;
                }
            }
        });
        UmengUpdateAgent.setUpdateAutoPopup(false);
        UmengUpdateAgent.update(getApplicationContext());
    }

    private void redirectTo() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
