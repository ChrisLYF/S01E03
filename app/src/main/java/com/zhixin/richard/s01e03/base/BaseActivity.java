package com.zhixin.richard.s01e03.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.ui.VisibilityControl;
import com.zhixin.richard.s01e03.ui.dialog.DialogControl;
import com.zhixin.richard.s01e03.ui.dialog.DialogHelper;
import com.zhixin.richard.s01e03.ui.dialog.WaitDialog;

/**
 * Created by Administrator on 2016-03-09.
 */
public class BaseActivity extends ActionBarActivity implements DialogControl, VisibilityControl, View.OnClickListener {
    public static final String INTENT_ACTION_EXIT_APP = "INTENT_ACTION_EXIT_APP";
    private boolean _isVisible;
    private WaitDialog _waitDialog;
    private android.support.v7.app.ActionBar mActionBar;
    private TextView mTvActionTitle;

    private BroadcastReceiver mExistReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!hasActionBar()) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        onBeforeSetContentLayout();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        mActionBar = getSupportActionBar();
        mInflater = getLayoutInflater();
        if (hasActionBar()) {
            initActionBar(mActionBar);
            mActionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_checkbox_pressed));
        }
        init(savedInstanceState);
        IntentFilter filter = new IntentFilter(INTENT_ACTION_EXIT_APP);
        registerReceiver(mExistReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mExistReceiver);
        mExistReceiver = null;
        super.onDestroy();
    }

    protected void onBeforeSetContentLayout() {
    }

    protected boolean hasActionBar() {
        return true;
    }

    protected int getLayoutId() {
        return 0;
    }

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected int getActionBarTitle() {
        return R.string.app_name;
    }

    public void setActionBarTitle(String title) {
        if (hasActionBar()) {
            if (mTvActionTitle != null) {
                mTvActionTitle.setText(title);
            }
            mActionBar.setTitle(title);
        }
    }

    protected boolean hasBackButton() {
        return false;
    }

    protected int getActionBarCustomView() {
        return 0;
    }

    protected void init(Bundle savedInstanceState) {
    }

    protected void initActionBar(android.support.v7.app.ActionBar actionBar) {
        if (actionBar == null)
            return;
        if (hasBackButton()) {
            actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
            int layoutRes = getActionBarCustomView();
            View view = inflateView(layoutRes == 0 ? R.layout.actionbar_custom_backtitle
                    : layoutRes);
            view.setPadding(0, 0, 0, 0);
            view.setBackgroundColor(Color.GREEN);
            View back = view.findViewById(R.id.btn_back);

            if (back == null) {
                throw new IllegalArgumentException(
                        "can not find R.id.btn_back in customView");
            }
            back.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            mTvActionTitle = (TextView) view
                    .findViewById(R.id.tv_actionbar_title);
            if (mTvActionTitle == null) {
                throw new IllegalArgumentException(
                        "can not find R.id.tv_actionbar_title in customView");
            }
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                mTvActionTitle.setText(titleRes);
            }
            android.support.v7.app.ActionBar.LayoutParams params = new android.support.v7.app.ActionBar.
                    LayoutParams(android.support.v7.app.ActionBar.LayoutParams.FILL_PARENT,
                    android.support.v7.app.ActionBar.LayoutParams.FILL_PARENT);
            params.setMarginEnd(0);
            params.setMarginStart(0);
            params.setMargins(0, 0, 0, 0);
            actionBar.setCustomView(view, params);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setHideOffset(0);
        } else {
            actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayUseLogoEnabled(false);
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                actionBar.setTitle(titleRes);
            }
        }
    }

    public void setActionBarTitle(int resId) {
        if (resId != 0) {
            setActionBarTitle(getString(resId));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        _isVisible = false;
        hideWaitDialog();
        super.onPause();
    }

    @Override
    protected void onResume() {
        _isVisible = true;
        super.onResume();
    }

    public void showToast(int msgResid, int icon, int gravity) {
        showToast(getString(msgResid), icon, gravity);
    }

    public void showToast(String message, int icon, int gravity) {

    }

    @Override
    public WaitDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    @Override
    public WaitDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public WaitDialog showWaitDialog(String message) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelper.getWaitDialog(this, message);
            }
            if (_waitDialog != null) {
                _waitDialog.setMessage(message);
                _waitDialog.show();
            }
            return _waitDialog;
        }
        return null;
    }

    @Override
    public void hideWaitDialog() {
        if (_isVisible && _waitDialog != null) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public boolean isVisible() {
        return _isVisible;
    }
}
