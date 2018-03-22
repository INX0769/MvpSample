package com.example.baselibrary.base;


import android.app.Application;
import android.os.Handler;

import com.example.baselibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseApplication extends Application {
    private static BaseApplication instance;
    private static List<BaseActivity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        ToastUtils.init(this);
    }

    public static BaseApplication get() {
        return instance;
    }

    private static void checkListNull() {
        if (activityList == null) {
            activityList = new ArrayList<>();
        }
    }

    public static void add(BaseActivity activity) {
        checkListNull();
        activityList.add(activity);
    }

    public static void remove(BaseActivity activity) {
        checkListNull();
        activityList.remove(activity);
    }

    public static void clearActivities() {
        checkListNull();
        for (BaseActivity activity : activityList) {
            if (activity != null && !activity.isDestroy()) {
                activity.finish();
            }
        }
        activityList.clear();
    }

    public static void runOnMainThread() {

    }
}
