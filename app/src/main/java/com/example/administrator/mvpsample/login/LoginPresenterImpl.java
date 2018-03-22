package com.example.administrator.mvpsample.login;

import com.example.administrator.mvpsample.bean.UserInfo;
import com.example.administrator.mvpsample.model.LoginModel;
import com.example.administrator.mvpsample.model.UserModel;
import com.example.baselibrary.mvp.BaseModel;

import java.util.Map;

public class LoginPresenterImpl extends LogincContract.LoginPresenter {
    public LoginPresenterImpl(LogincContract.LoginView view) {
        super(view);
    }

    @Override
    public void login(final String userName, String psd) {
        mView.showLoading();
        LoginModel loginModel = getModel("LoginModel");
        final UserModel userModel = getModel("UserModel");
        loginModel.getData(userName, psd, new BaseModel.DataCallBack<String>() {
            @Override
            public void onSuccess(String msg) {
                mView.showToast("登录成功！");

                userModel.getUserInfo(userName, new BaseModel.DataCallBack<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo userInfo) {
                        mView.dismissLoading();
                        mView.setUserInfo(userInfo);
                    }

                    @Override
                    public void onCahceSuccess(UserInfo userInfo) {

                    }

                    @Override
                    public void onError(String msg) {
                        mView.showToast(msg);
                        mView.dismissLoading();
                    }
                });

            }

            @Override
            public void onCahceSuccess(String msg) {

            }

            @Override
            public void onError(String msg) {
                mView.showToast(msg);
                mView.dismissLoading();
            }
        });
    }

    @Override
    protected void initModels(Map<String, BaseModel> mModels) {
        mModels.put("UserModel", new UserModel());
        mModels.put("LoginModel", new LoginModel());
    }
}
