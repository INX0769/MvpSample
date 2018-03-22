package com.example.baselibrary.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import com.example.baselibrary.views.DataLoadingProgressDialog;

import java.util.ArrayList;

public class BaseFragment extends Fragment {

	private Activity parentActivity;
	private ProgressDialog progressDialog;
	private ArrayList<String> mFragList = new ArrayList<String>();
	private ViewGroup mViewNoData;
	private View mViewSon;
	protected boolean isDestroy = false;
	private BaseActivity mBaseActivity;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		parentActivity = getActivity();
		if(context instanceof BaseActivity){
			mBaseActivity=(BaseActivity)context;
		}
	}
	

	public String getFragmentTag(){
		return this.hashCode()+"";
	}

	@Override
	public void onDetach() {
		super.onDetach();
		parentActivity = null;
	}

	@Override
	public void onResume() {
		super.onResume();
	}
	
	

	@Override
	public void onPause() {
		super.onPause();
	}


	public void showProgressDialog() {
		if (parentActivity == null) {
			return;
		}
		if (null == progressDialog) {
			progressDialog = new DataLoadingProgressDialog(parentActivity, false);
		}
		if (!isDestroy && !progressDialog.isShowing()) {
			progressDialog.show();
		}
	}

	public void dismissProgressDialog() {
		if (!isDestroy && parentActivity != null && null != progressDialog && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	public <T extends View> T findViewById(@IdRes int id) {
		return getView().findViewById(id);
	}

	public void switchFrag(int layout, Fragment fragment) {
		FragmentManager manager = getChildFragmentManager();
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

	public void switchFrag(int layout, Fragment fragment, String tag) {
		FragmentManager manager = getChildFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		for (int i = 0; i < mFragList.size(); i++) {
			Fragment fra = manager.findFragmentByTag(mFragList.get(i));
			if (fra != null)
				ft.hide(fra);
		}
		Fragment frag = manager.findFragmentByTag(tag);
		if (frag == null) {
			mFragList.add(tag);
			ft.add(layout, fragment, tag);
		} else {
			ft.show(frag);
		}
		ft.commit();
	}

	@Override
	public void onDestroy() {
		isDestroy = true;
		super.onDestroy();
	}

}
