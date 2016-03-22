package com.zhixin.richard.s01e03.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.zhixin.richard.s01e03.AppContext;
import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.activity.MainActivity;
import com.zhixin.richard.s01e03.activity.user.adapter.LoginPagerAdapter;
import com.zhixin.richard.s01e03.api.ApiHttpClient;
import com.zhixin.richard.s01e03.api.remote.UserApi;
import com.zhixin.richard.s01e03.base.BaseActivity;
import com.zhixin.richard.s01e03.constants.user.UserConstants;
import com.zhixin.richard.s01e03.model.Result;
import com.zhixin.richard.s01e03.model.shop.Shop;
import com.zhixin.richard.s01e03.model.user.User;
import com.zhixin.richard.s01e03.utils.ImageDownLoad;
import com.zhixin.richard.s01e03.utils.MyJsonObjectRequest;
import com.zhixin.richard.s01e03.utils.TDevice;
import com.zhixin.richard.s01e03.utils.TLog;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.cookie.Cookie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    public static final int REQUEST_CODE_INIT = 0;
    private static final String BUNDLE_KEY_REQUEST_CODE="BUNDLE_KEY_REQUEST_CODE";
    protected static final String TAG = LoginActivity.class.getSimpleName();
    private static final String LOGIN_SCREEN = "LoginScreen";
    private int requestCode = REQUEST_CODE_INIT;

    private View mLoginFormView;
    private Button loginButton,backButton;
    private ViewPager viewPager;
    private List<View> content;
    private LayoutInflater inflater;
    private LoginPagerAdapter loginPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    private void initView(){
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        mLoginFormView = findViewById(R.id.login_form);
        viewPager = (ViewPager)this.findViewById(R.id.viewpager);
        inflater = LayoutInflater.from(this);
        content = new ArrayList<View>();
        for (int i = 1; i <= 4; i++) {
            View view = inflater.inflate(R.layout.item, null);
            content.add(view);
        }
        loginPagerAdapter = new LoginPagerAdapter(content);
        viewPager.setAdapter(loginPagerAdapter);
        loginPagerAdapter.notifyDataSetChanged();
        handlerLogin();
    }

    private boolean prepareForLogin() {
//        AppContext.showToastShort(R.string.tip_no_internet);
//        showWaitDialog();
//        if (!TDevice.hasInternet()) {
//            Toast.makeText(this, "no net!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    private void handlerLogin() {
        showWaitDialog();
        if(!prepareForLogin()){
            hideWaitDialog();
            return;
        }

        UserApi.login("admin", "check", jsonHandler);
    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                handlerLogin();
                break;
        }
    }
    private JsonHttpResponseHandler jsonHandler = new JsonHttpResponseHandler(){
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            AsyncHttpClient client = ApiHttpClient.getHttpClient();
            HttpContext httpContext = client.getHttpContext();
            CookieStore cookies = (CookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE);
            if (cookies != null) {
                String tmpcookies = "";
                for (Cookie c : cookies.getCookies()) {
                    TLog.log(TAG,
                            "cookie:" + c.getName() + " " + c.getValue());
                    tmpcookies += (c.getName() + "=" + c.getValue()) + ";";
                }
                TLog.log(TAG, "cookies:" + tmpcookies);
                AppContext.instance().setProperty("cookie", tmpcookies);
                ApiHttpClient.setCookie(ApiHttpClient.getCookie(AppContext
                        .instance()));
            }
            try {
                Result result = null;
                //用来解析时间
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

                //用来解析ArrayList
//                Type type = new TypeToken<User>(){}.getType();
//                gson.fromJson(response.toString(),type);

                result = gson.fromJson(response.getJSONObject("result").toString(), Result.class);
                if(result.getErrorCode()== UserConstants.login_code_right){
                    //保存登录信息
                    User user = gson.fromJson(response.getJSONObject("user").toString(),User.class);
                    Shop shop = gson.fromJson(response.getJSONObject("shop").toString(),Shop.class);
                    AppContext.instance().saveLoginInfo(user,shop);
                    hideWaitDialog();
                    handleLoginSuccess(user,shop);
                }else{
                    AppContext.instance().cleanLoginInfo();
                    hideWaitDialog();
                    AppContext.showToast(result.getErrorMessage());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(response);
         }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            hideWaitDialog();
            AppContext.showToast(R.string.tip_login_error_for_network);
        }
    };


    private void handleLoginSuccess(User user,Shop shop) {
        Intent intent = new Intent(this,MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",user);
        bundle.putSerializable("shop", shop);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private boolean getLogin() {
        String url = "http://172.20.10.3:8080/zhixinaishangka/login/loginApi!login";
        Map<String, String> params = new HashMap<String, String>();
        params.put("store", "17701320787");
        params.put("name", "admin");
        params.put("password", "123");
        params.put("type", "客户端登录");
        String p = appendParameter(url, params);
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        MyJsonObjectRequest jsonObjectRequest = new MyJsonObjectRequest(url, p, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Result result = new Result();
                    Gson gson = new Gson();
                    result = gson.fromJson(response.getJSONObject("result").toString(), Result.class);
                    System.out.println(result);
                    Log.d("response", response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
                hideWaitDialog();
                AppContext.showToast(R.string.tip_login_error_for_network);
            }
        }) {

        };
        mQueue.add(jsonObjectRequest);
        return false;
    }

    private String appendParameter(String url, Map<String, String> params) {
        Uri uri = Uri.parse(url);
        Uri.Builder builder = uri.buildUpon();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder.build().getQuery();
    }


}

