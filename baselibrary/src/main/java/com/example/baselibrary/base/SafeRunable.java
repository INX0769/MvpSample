package com.example.baselibrary.base;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * 基于传入的上下文来判断是否已回收
 */
public abstract class SafeRunable implements Runnable {
    private WeakReference<Context> wr;

    public SafeRunable(Context link) {
        wr=new WeakReference<Context>(link);
    }

    @Override
    public void run() {
        if (wr.get()==null)
            return;
        runOnSafe();
    }

    protected abstract void runOnSafe();
}