package com.example.baselibrary.mvp;

import java.util.HashMap;
import java.util.Map;

public abstract class BasePresenter<View extends BaseView> {
    protected View mView;
    private Map<String,BaseModel> mModels;

    public BasePresenter(View view) {
        this.mView = view;
        this.mModels=new HashMap<>();
        initModels(mModels);
    }


    protected abstract void initModels( Map<String, BaseModel> mModels);
    protected <Models extends BaseModel> Models getModel(String key) {
        if (mModels == null)
            return null;
        return (Models) mModels.get(key);
    }
}
