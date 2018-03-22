package com.example.administrator.mvpsample.history;


import com.example.administrator.mvpsample.bean.HistoryBean;
import com.example.baselibrary.mvp.BasePresenter;
import com.example.baselibrary.mvp.BaseView;

import java.util.List;

public interface HistoryContract {
    interface HistoryView extends BaseView {
        void setHistory(List<HistoryBean> data);
    }
    abstract class HistoryPresenter extends BasePresenter<HistoryView> {
        public HistoryPresenter(HistoryView view) {
            super(view);
        }
        public abstract  void getHistory(String date);
    }
}
