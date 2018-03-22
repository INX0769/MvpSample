package com.example.administrator.mvpsample.model;

import android.os.Handler;

import com.example.baselibrary.mvp.BaseModel;
import com.example.baselibrary.utils.StringHelper;


public class LoginModel implements BaseModel {
    private Handler mHandler = new Handler();

    public void getData(final String userName, final String psd, final BaseModel.DataCallBack<String> callBack) {
        if (callBack == null)
            return;
        if (StringHelper.isEmpty(userName))
            callBack.onError("用户名不能为空！");
        else if (StringHelper.isEmpty(psd))
            callBack.onError("密码不能为空！");
        else
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    callBack.onSuccess("登录成功！");
                }
            }, 1000);//模拟登录操作
    }
}
