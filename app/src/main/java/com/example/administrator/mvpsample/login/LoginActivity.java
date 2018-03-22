package com.example.administrator.mvpsample.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.mvpsample.R;
import com.example.administrator.mvpsample.bean.UserInfo;
import com.example.administrator.mvpsample.history.HistoryActivity;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.utils.StringHelper;
import com.example.baselibrary.utils.ToastUtils;

public class LoginActivity extends BaseActivity<LogincContract.LoginPresenter> implements LogincContract.LoginView{
    private TextView tv_userinfo;
    private EditText et_userName;
    private EditText et_psd;
    private Button btn_login;
    private Button btn_history;
    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void dismissLoading() {
        dismissProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showText(msg);
    }

    @Override
    public LogincContract.LoginPresenter initPresenter() {
        return new LoginPresenterImpl(this) ;
    }

    @Override
    protected void recoverData(Bundle data) {
        //恢复数据
    }

    @Override
    protected void saveData(Bundle data) {
        //保存数据
    }

    @Override
    protected void initData(boolean isRecover) {

    }

    @Override
    protected void initView() {
        tv_userinfo=findViewById(R.id.tv_userinfo);
        et_userName=findViewById(R.id.et_userName);
        et_psd=findViewById(R.id.et_psd);
        btn_login=findViewById(R.id.btn_login);
        btn_history=findViewById(R.id.btn_history);

        setOnClicks(btn_login,btn_history);
    }

    @Override
    protected int contentViewResId() {
        return R.layout.activity_login;
    }

    @Override
    protected long opSpanTime() {
        return 0;
    }

    @Override
    protected void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                mPresenter.login(et_userName.getText().toString(),et_psd.getText().toString());
                break;
            case R.id.btn_history:
                startActivity(new Intent(this, HistoryActivity.class));
                break;
        }
    }

    @Override
    protected ProgressDialog loadDialog() {
        return null;
    }

    @Override
    public void setUserInfo(UserInfo userInfo) {
        //设置用户信息
        if (userInfo==null) {
            tv_userinfo.setText("暂无用户信息！");
        }else{
            tv_userinfo.setText(StringHelper.checkNull(userInfo.getUserName())+"\n"+StringHelper.checkNull(userInfo.getPhone()));
        }
    }
}
