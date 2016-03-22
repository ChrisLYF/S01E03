package com.zhixin.richard.s01e03.ui.dialog;

/**
 * Created by Administrator on 2016-03-09.
 */
public interface DialogControl {
    public abstract void hideWaitDialog();
    public abstract WaitDialog showWaitDialog();

    public abstract WaitDialog showWaitDialog(int resid);

    public abstract WaitDialog showWaitDialog(String text);
}
