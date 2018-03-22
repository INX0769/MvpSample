package com.example.administrator.mvpsample.model;

import android.os.Handler;

import com.example.administrator.mvpsample.bean.UserInfo;
import com.example.baselibrary.mvp.BaseModel;
import com.example.baselibrary.utils.StringHelper;


public class UserModel implements BaseModel {
    private Handler mHandler = new Handler();

    public void getUserInfo(final String userName, final DataCallBack<UserInfo> callBack) {
        if (callBack == null)
            return;
        if (StringHelper.isEmpty(userName))
            callBack.onError("用户名不能为空！");
        else
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    callBack.onSuccess(new UserInfo("小明", "10086"));
                }
            }, 2000);//模拟获取用户信息操作
    }
}
