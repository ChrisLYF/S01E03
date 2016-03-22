package com.zhixin.richard.s01e03;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.zhixin.richard.s01e03.api.ApiHttpClient;
import com.zhixin.richard.s01e03.base.BaseApplication;
import com.zhixin.richard.s01e03.base.Constants;
import com.zhixin.richard.s01e03.model.shop.Shop;
import com.zhixin.richard.s01e03.model.user.User;
import com.zhixin.richard.s01e03.utils.MethodsCompat;
import com.zhixin.richard.s01e03.utils.StringUtils;

import java.io.File;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by Administrator on 2016-03-07.
 */
public class AppContext extends BaseApplication {
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;
    public static final int PAGE_SIZE = 20;// 默认分页大小
    private static final int CACHE_TIME = 60 * 60000;// 缓存失效时间
    private static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";
    private static final String KEY_SOFTKEYBOARD_HEIGHT = "KEY_SOFTKEYBOARD_HEIGHT";
    private static final String KEY_LOAD_IMAGE = "KEY_LOAD_IMAGE";
    private static final String KEY_NOTIFICATION_SOUND = "KEY_NOTIFICATION_SOUND";
    private static final String LAST_QUESTION_CATEGORY_IDX = "LAST_QUESTION_CATEGORY_IDX";
    private static final String KEY_DAILY_ENGLISH = "KEY_DAILY_ENGLISH";
    private static final String KEY_GET_LAST_DAILY_ENG = "KEY_GET_LAST_DAILY_ENG";
    private static final String KEY_NOTIFICATION_DISABLE_WHEN_EXIT = "KEY_NOTIFICATION_DISABLE_WHEN_EXIT";
    private static final String KEY_TWEET_DRAFT = "key_tweet_draft";
    private static final String KEY_QUESTION_TITLE_DRAFT = "key_question_title_draft";
    private static final String KEY_QUESTION_CONTENT_DRAFT = "key_question_content_draft";
    private static final String KEY_QUESTION_TYPE_DRAFT = "key_question_type_draft";
    private static final String KEY_QUESTION_LMK_DRAFT = "key_question_lmk_draft";

    private boolean login = false; // 登录状态
    private int loginUid = 0; // 登录用户的id

    private String saveImagePath;// 保存图片路径

    private Handler unLoginHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                // UIHelper.ToastMessage(AppContext.this,
                // getString(R.string.msg_login_error));
                // UIHelper.showLoginDialog(AppContext.this);
            }
        }
    };

    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        ApiHttpClient.setHttpClient(client);
        ApiHttpClient.setCookie(ApiHttpClient.getCookie(this));


        //设置图片的下载
        initImageLoader(this);
    }

    public static void initImageLoader(Context context) {
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().postProcessor(new BitmapProcessor() {
            @Override
            public Bitmap process(Bitmap bitmap) {
                Bitmap target = getRoundedCornerBitmapBig(bitmap);
                if (bitmap != target) {
                    bitmap.recycle();
                }
                return target;
            }
        }).cacheInMemory(true).cacheInMemory(true).bitmapConfig(Bitmap.Config.ARGB_8888).build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .defaultDisplayImageOptions(displayImageOptions).build();
        ImageLoader.getInstance().init(configuration);
    }

    private void init2() {
        saveImagePath = getProperty(AppConfig.SAVE_IMAGE_PATH);
        if (StringUtils.isEmail(saveImagePath)) {
            setProperty(AppConfig.SAVE_IMAGE_PATH, AppConfig.DEFAULT_SAVE_IMAGE_PATH);
            saveImagePath = AppConfig.DEFAULT_SAVE_IMAGE_PATH;
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    public int getNetWorkType() {
        int netType = 0;//没有网络
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!StringUtils.isEmail(extraInfo)) {
                if (extraInfo.equalsIgnoreCase("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null) {
            info = new PackageInfo();
        }
        return info;
    }

    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (StringUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * 用户是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return login;
    }

    public int getLoginUid() {
        return this.loginUid;
    }

    public void Logout() {
        ApiHttpClient.cleanCookie();
        this.cleanCookie();
        this.login = false;
        this.loginUid = 0;
        Intent intent = new Intent(Constants.INTENT_ACTION_LOGOUT);
        sendBroadcast(intent);
    }

    public void initLoginInfo() {
        User loginUser = getLoginInfo();
        if (loginUser != null && loginUser.getId() > 0
                && !TextUtils.isEmpty(ApiHttpClient.getCookie(this))) {// &&
            // loginUser.isRememberMe()
            this.loginUid = loginUser.getId();
            this.login = true;
        } else {
            this.Logout();
        }
    }

    public void saveLoginInfo(final User user, final Shop shop) {
        this.loginUid = user.getId();
        this.login = true;
        setProperties(new Properties() {
            {
                setProperty("user.id", String.valueOf(user.getId()));
                setProperty("user.loginName", user.loginName);
                setProperty("user.name", user.name);
                setProperty("user.password", user.password);
                setProperty("user.address", user.address);
                setProperty("user.phone", user.phone);
                setProperty("user.loginCount", String.valueOf(user.loginCount));
                setProperty("user.shop", user.shop);
                setProperty("user.sex", user.sex);

                setProperty("shop.id", String.valueOf(shop.getId()));
                setProperty("shop.storeId", String.valueOf(shop.storeId));
                setProperty("shop.storeName", shop.storeName);
                setProperty("shop.fId", String.valueOf(shop.fId));
                setProperty("shop.fName", shop.fName);
                setProperty("shop.name", shop.name);
                setProperty("shop.address", shop.address);
                setProperty("shop.phone", shop.phone);
            }
        });
    }

    public void cleanLoginInfo() {
        this.loginUid = 0;
        this.login = false;
        removeProperty("user.uid", "user.name", "user.face", "user.account",
                "user.pwd", "user.location", "user.followers", "user.fans",
                "user.score", "user.isRememberMe");
    }

    public User getLoginInfo() {
        User user = new User();
        user.setId(StringUtils.toInt(getProperty("user.id"), 0));
        user.setName(getProperty("user.name"));
        user.loginName = getProperty("user.loginName");
        user.address = getProperty("user.address");
        user.phone = getProperty("user.phone");
        return user;
    }

    public Shop getLoginInfoShop(){
        Shop shop = new Shop();
        shop.id = StringUtils.toInt(getProperty("shop.id"), 0);
        shop.storeId = StringUtils.toInt(getProperty("shop.storeId"), 0);
        setProperty("shop.storeName", shop.storeName);
        shop.storeName = getProperty("shop.storeName");
        shop.fId = StringUtils.toInt(getProperty("shop.fId"), 0);
        shop.fName = getProperty("shop.fName");
        shop.name = getProperty("shop.name");
        shop.address = getProperty("shop.address");
        shop.phone = getProperty("shop.phone");
        return shop;
    }

    public static Bitmap getRoundedCornerBitmapBig(Bitmap bitmap) {
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPX = bitmap.getWidth() / 2;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return outBitmap;
    }


    public void cleanCookie() {
        removeProperty(AppConfig.CONF_COOKIE);
    }

    public boolean isCheckUp() {
        String perf_checkup = getProperty(AppConfig.CONF_CHECKUP);
        // 默认是开启
        if (StringUtils.isEmpty(perf_checkup))
            return true;
        else
            return StringUtils.toBool(perf_checkup);
    }

    public void setConfigCheckUp(boolean b) {
        setProperty(AppConfig.CONF_CHECKUP, String.valueOf(b));
    }


    public boolean isScroll() {
        String perf_scroll = getProperty(AppConfig.CONF_SCROLL);
        // 默认是关闭左右滑动
        if (StringUtils.isEmpty(perf_scroll))
            return false;
        else
            return StringUtils.toBool(perf_scroll);
    }

    public void setConfigScroll(boolean b) {
        setProperty(AppConfig.CONF_SCROLL, String.valueOf(b));
    }

    public boolean isCacheDataFailure(String cachefile) {
        boolean failure = false;
        File data = getFileStreamPath(cachefile);
        if (data.exists()
                && (System.currentTimeMillis() - data.lastModified()) > CACHE_TIME)
            failure = true;
        else if (!data.exists())
            failure = true;
        return failure;
    }


    public void clearAppCache() {
        // 清除webview缓存
        // File file = CacheManager.getCacheFileBaseDir();
        // if (file != null && file.exists() && file.isDirectory()) {
        // for (File item : file.listFiles()) {
        // item.delete();
        // }
        // file.delete();
        // }
        deleteDatabase("webview.db");
        deleteDatabase("webview.db-shm");
        deleteDatabase("webview.db-wal");
        deleteDatabase("webviewCache.db");
        deleteDatabase("webviewCache.db-shm");
        deleteDatabase("webviewCache.db-wal");
        // 清除数据缓存
        clearCacheFolder(getFilesDir(), System.currentTimeMillis());
        clearCacheFolder(getCacheDir(), System.currentTimeMillis());
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            clearCacheFolder(MethodsCompat.getExternalCacheDir(this),
                    System.currentTimeMillis());
        }
        // 清除编辑器保存的临时内容
        Properties props = getProperties();
        for (Object key : props.keySet()) {
            String _key = key.toString();
            if (_key.startsWith("temp"))
                removeProperty(_key);
        }
    }


    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }


    public boolean containsProperty(String key) {
        Properties props = getProperties();
        return props.containsKey(key);
    }

    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    public String getProperty(String key) {
        return AppConfig.getAppConfig(this).get(key);
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    public static AppContext instance() {
        return instance;
    }

    public String getSaveImagePath() {
        return saveImagePath;
    }

    public void setSaveImagePath(String saveImagePath) {
        this.saveImagePath = saveImagePath;
    }
}
