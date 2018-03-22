package com.example.baselibrary.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.tv.TvView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.baselibrary.mvp.BasePresenter;
import com.example.baselibrary.utils.ToastUtils;
import com.example.baselibrary.views.DataLoadingProgressDialog;

import java.util.ArrayList;

/**
 * 所有activity基类
 * 
 * @author Administrator
 * 
 */
@SuppressLint("NewApi")
public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity implements OnClickListener{

	private ProgressDialog progressDialog;
	private boolean isDestroy;
	private ArrayList<String> mFragList = new ArrayList<>();

	private long lastOpTime = 0;
	private static final String KEY_IS_SAVE="key_is_save";
	protected Presenter mPresenter;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(contentViewResId());
		initLoadDialog();
		BaseApplication.add(this);
		initData(checkSaveInstance(savedInstanceState));
		mPresenter=initPresenter();
		initView();
	}

	private boolean checkSaveInstance( Bundle savedInstanceState){
		if (savedInstanceState!=null&&savedInstanceState.getBoolean(KEY_IS_SAVE,false)){
			recoverData(savedInstanceState);
			return true;
		}
			return false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(KEY_IS_SAVE,true);
		saveData(outState);
		super.onSaveInstanceState(outState);
	}
	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		long time = System.currentTimeMillis();
		if (time - lastOpTime > opSpanTime()) {
			lastOpTime = time;
			super.startActivityForResult(intent, requestCode);
		}
	}

	@Override
	public void onClick(View v) {
		long time = System.currentTimeMillis();
		if (time - lastOpTime > opSpanTime()) {
			lastOpTime = time;
			onClickEvent(v);
		}
	}

	/**
	 * 恢复数据
	 * @param data
	 */
	protected abstract void recoverData(Bundle data);

	/**
	 * 保存数据
	 * @param data
	 */
	protected abstract void saveData(Bundle data);

	/**
	 * 	  初始化数据
	 * @param isRecover 是否恢复数据
	 */
	protected abstract void initData(boolean isRecover);

	/**
	 * 初始化View
	 */
	protected abstract void initView();

	/**
	 * 布局id
	 * @return
	 */
	protected abstract int contentViewResId();

	/**
	 * 操作间隔
	 * @return
	 */
	protected abstract long opSpanTime();

	/**
	 * 点击监听
	 * @param v
	 */
	protected abstract void onClickEvent(View v);

	/**
	 * 设置个性化loadingdialog
	 * @return
	 */
	protected abstract ProgressDialog loadDialog();

	private void initLoadDialog(){
		progressDialog=loadDialog()==null?new DataLoadingProgressDialog(this):loadDialog();
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}

	public boolean isDestroy() {
		return isDestroy;
	}

	public void showProgressDialog() {
		if (isDestroy) {
			return;
		}
		if (null == progressDialog) {
			progressDialog = new DataLoadingProgressDialog(this, false);
		}
		if (!progressDialog.isShowing()) {
			progressDialog.show();
		}
	}

	public void dismissProgressDialog() {
		if (isDestroy) {
			return;
		}

		if (BaseActivity.this != null && null != progressDialog && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isDestroy = true;
		BaseApplication.remove(this);
		dismissProgressDialog();
	}

	@Override
	public Resources getResources() {
		Resources res = super.getResources();
		Configuration config = new Configuration();
		config.setToDefaults();
		res.updateConfiguration(config, res.getDisplayMetrics());
		return res;
	}

	public void switchFrag(int layout, Fragment fragment) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		for (int i = 0; i < mFragList.size(); i++) {
			Fragment fra = manager.findFragmentByTag(mFragList.get(i));
			if (fra != null)
				ft.hide(fra);
		}
		String tag = fragment.hashCode() + "";
		Fragment frag = manager.findFragmentByTag(tag);
		if (frag == null) {
			mFragList.add(tag);
			ft.add(layout, fragment, tag);
		} else {
			ft.show(frag);
		}
		ft.commit();
	}

	protected void setOnClicks(View... views){
		for (int i = 0; views!=null&&i < views.length; i++) {
			views[i].setOnClickListener(this);
		}
	}

	protected abstract Presenter initPresenter();
}
