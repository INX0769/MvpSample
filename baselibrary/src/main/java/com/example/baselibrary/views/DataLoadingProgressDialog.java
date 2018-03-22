package com.example.baselibrary.views;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.baselibrary.R;

/**
 * 数据加载Dialog
 * 
 * @author Administrator
 * 
 */
public class DataLoadingProgressDialog extends ProgressDialog {

	private boolean mCanBack = true;

	private Context mContext;

	public DataLoadingProgressDialog(Context context) {
		super(context);
		setCanceledOnTouchOutside(false);
		getWindow().setBackgroundDrawable(new ColorDrawable());
	}

	public DataLoadingProgressDialog(Context context, boolean canBack) {
		super(context, R.style.dialog);
		this.mContext = context;
		this.mCanBack = canBack;
		setCanceledOnTouchOutside(false);
		getWindow().setBackgroundDrawable(new ColorDrawable());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_progress_dialog);
	}
	public void setCanBack(boolean isCanBack){
		this.mCanBack = isCanBack;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (!mCanBack && keyCode == KeyEvent.KEYCODE_BACK) {
			Toast.makeText(mContext, "正在加载，不能取消！", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
