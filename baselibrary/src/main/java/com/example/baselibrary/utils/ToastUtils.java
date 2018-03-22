package com.example.baselibrary.utils;

import android.widget.Toast;

import com.example.baselibrary.base.BaseApplication;

public class ToastUtils {
	private static Toast toast;
	private static BaseApplication mApp;
	public static void init(BaseApplication app){
		mApp=app;
	}
	public static synchronized void showText(String text, int duration) {
		if (toast == null) {
			toast = Toast.makeText(mApp, text, duration);
		} else {
			toast.setText(text);
			toast.setDuration(duration);
		}
		toast.show();
	}

	public static synchronized void showText(int textId, int duration) {
		String text =mApp.getResources().getString(textId);
		showText(text, duration);
	}

	public static synchronized void showText(String text) {
		showText(text, Toast.LENGTH_SHORT);
	}

	public static synchronized void showText(int text) {
		showText(text, Toast.LENGTH_SHORT);
	}

	public static synchronized void showTextLong(String text) {
		showText(text, Toast.LENGTH_LONG);
	}

	public static synchronized void showTextLong(int text) {
		showText(text, Toast.LENGTH_LONG);
	}
}
