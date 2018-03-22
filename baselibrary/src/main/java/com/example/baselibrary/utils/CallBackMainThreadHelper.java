package com.example.baselibrary.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Handler;
import android.os.Looper;

public class CallBackMainThreadHelper {
	private static Handler mainHandler = new Handler();// 静态创建一个handler，默认在主线程
	private static List<MainTask> tasks = new ArrayList<MainTask>();

	/**
	 * 同步主线程，调用此方法需要在主线程
	 * 
	 * @throws IllegalAccessException
	 */
	public static boolean syncMainThread() {
		if (mainHandler != null
				&& mainHandler.getLooper() == Looper.getMainLooper()) {
			// 当前是主线程则不需要同步
			return true;
		} else if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
			mainHandler = new Handler();
			return true;
		} 
		return false;
	}

	/**
	 * 清除
	 */
	public static void clear() {
		if (tasks != null) {
			tasks.clear();
		}
	}

	/**
	 * 添加任务
	 * 
	 * @param task
	 */
	public static void addTask(MainTask task) {
		if (tasks == null) {
			tasks = new ArrayList<MainTask>();
		}
		tasks.add(task);
		execute();
	}

	/**
	 * 清除任务
	 */
	public static void removeTask(MainTask task) {
		if (tasks != null && task != null && tasks.contains(task)) {
			tasks.remove(task);
		}
	}

	public static void removeTask(String tag) {
		Iterator<MainTask> iter = tasks.iterator();
		while (iter.hasNext()) {
			MainTask task = iter.next();
			if (task != null && task.tag() != null && task.tag().equals(tag)) {
				iter.remove();
			}
		}
	}

	/**
	 * 执行任务栈的所有任务
	 */
	private static void execute() {
		if (mainHandler == null || tasks == null || tasks.size() == 0) {
			return;
		}
		mainHandler.post(new Runnable() {

			@Override
			public void run() {
				MainTask task;
				if (tasks.size() > 0 && (task = tasks.get(0)) != null) {
					task.onMainThread();
					if (tasks.contains(task)) {
						tasks.remove(task);
					}
					if (tasks.size() > 0) {
						execute();
					}
				}
			}
		});
	}

	public interface MainTask {
		void onMainThread();

		String tag();
	}

	public static abstract class DefaultTask implements MainTask {
		private String tag = "default";

		@Override
		public String tag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}
	}
}
