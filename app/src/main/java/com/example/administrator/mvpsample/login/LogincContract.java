package com.example.administrator.mvpsample.login;


import com.example.administrator.mvpsample.bean.UserInfo;
import com.example.administrator.mvpsample.model.LoginModel;
import com.example.baselibrary.mvp.BasePresenter;
import com.example.baselibrary.mvp.BaseView;

public interface LogincContract {
    interface LoginView extends BaseView{
        void setUserInfo(UserInfo userInfo);
    }
    abstract class LoginPresenter extends BasePresenter<LoginView>{
        public LoginPresenter(LoginView view) {
            super(view);
        }
        public abstract  void login(String userName,String psd );
    }
}
