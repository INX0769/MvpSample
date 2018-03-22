package com.example.baselibrary.mvp;

public interface BaseView {
    void showLoading();
    void dismissLoading();
    void showToast(String msg);
}
