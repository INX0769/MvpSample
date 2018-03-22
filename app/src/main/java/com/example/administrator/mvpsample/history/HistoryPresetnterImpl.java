package com.example.administrator.mvpsample.history;

import com.example.administrator.mvpsample.bean.HistoryBean;
import com.example.administrator.mvpsample.model.HistoryModel;
import com.example.baselibrary.mvp.BaseModel;

import java.util.List;
import java.util.Map;


public class HistoryPresetnterImpl extends HistoryContract.HistoryPresenter {
    public HistoryPresetnterImpl(HistoryContract.HistoryView view) {
        super(view);
    }

    @Override
    protected void initModels(Map<String, BaseModel> mModels) {
        mModels.put("history",new HistoryModel());
    }

    @Override
    public void getHistory(String date) {
        mView.showLoading();
        HistoryModel historyModel = getModel("history");
        historyModel.getData(date, new BaseModel.DataCallBack<List<HistoryBean>>() {
            @Override
            public void onSuccess(List<HistoryBean> historyBeans) {
                mView.dismissLoading();
                mView.setHistory(historyBeans);
                mView.showToast("请求数据成功!");
            }

            @Override
            public void onCahceSuccess(List<HistoryBean> historyBeans) {

            }

            @Override
            public void onError(String msg) {
                mView.dismissLoading();
                mView.showToast(msg);
            }
        });
    }
}
