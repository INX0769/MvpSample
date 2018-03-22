package com.example.administrator.mvpsample.history;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mvpsample.R;
import com.example.administrator.mvpsample.bean.HistoryBean;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.utils.ToastUtils;

import java.util.List;

public class HistoryActivity extends BaseActivity<HistoryContract.HistoryPresenter> implements HistoryContract.HistoryView {

    private TextView tv_result;
    private Button btn_req;

    @Override
    protected void recoverData(Bundle data) {

    }

    @Override
    protected void saveData(Bundle data) {

    }

    @Override
    protected void initData(boolean isRecover) {

    }

    @Override
    protected void initView() {
        tv_result = findViewById(R.id.tv_result);
        btn_req = findViewById(R.id.btn_req);
        setOnClicks(btn_req);
    }

    @Override
    protected int contentViewResId() {
        return R.layout.activity_history;
    }

    @Override
    protected long opSpanTime() {
        return 0;
    }

    @Override
    protected void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.btn_req:
                mPresenter.getHistory("0101");
                break;
        }
    }

    @Override
    protected ProgressDialog loadDialog() {
        return null;
    }

    @Override
    protected HistoryContract.HistoryPresenter initPresenter() {
        return new HistoryPresetnterImpl(this);
    }

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
    public void setHistory(List<HistoryBean> data) {
        tv_result.setText(data+"");
    }
}
