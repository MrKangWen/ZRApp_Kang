package com.zhaorou.zhuanquanapp.network.imp;


public interface HttpDialogLoading {

    void showLoading();

    void dismissLoading();

    void goToLogin();

    void showToast(String msg);

    void showTipsDialog(String msg);
}
