package com.badmintonbuddy.location;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.badmintonbuddy.AreaListActivity;
import com.badmintonbuddy.BadmintonBuddyNativeActivity;
import com.badmintonbuddy.SlidingMenuActivity;
import com.badmintonbuddy.helpers.AppStatus;
import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.models.AreaResult;
import com.badmintonbuddy.tasks.AreaTask;
import com.weboapps.badmintonbuddy.R;

public class LocationActivity extends SlidingMenuActivity {
	
	private ProgressDialog mProgressDialog;
	final static String TAG = "LocationActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		setMenuDrawer(R.layout.location,"",R.drawable.header);
		
		
	}
	
	public void onLocationClick(View view) {
		
		appStatus = AppStatus.getInstance(this);
		
	//	appStatus.saveSharedBoolValue(appStatus.IS_FIRST_TIME, true);
		
		String[] args = new String[1];

		args[0] = appStatus.getSharedStringValue(appStatus.AUTH_KEY);

		if ( appStatus.isOnline() ) {
			this.showDialog(0);
			new AreaTask(this).execute(args);
		} else {
			LogUtils.LOGV(TAG, "App is not online!");
			Toast toast = Toast.makeText(LocationActivity.this, "App is not online!", 8000);
			toast.show();
		}
		
	}
	
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog = ProgressDialog.show(this, null, null);
		//dialog.setContentView(R.layout.loader);
		// dialog.setTitle("Please Wait...");

		dialog.setMessage("fetching court areas...");


		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {

			}
		});

		mProgressDialog = dialog;
		return dialog;
	}

	public void onAuthenticationResult(AreaResult result) {
		try{
			removeDialog(0);
			if(result == null){
				Toast toast = Toast.makeText(LocationActivity.this, "Something went wrong, try again!", 8000);
				toast.show();
			}

			if(result.getSuccess()){
				//appStatus.saveSharedBoolValue(appStatus.IS_FIRST_TIME, true);

				Intent i = new Intent(LocationActivity.this, AreaListActivity.class);
				i.putExtra("area_list", result);
				startActivity(i);
				finish();
			}else{
				Toast toast = Toast.makeText(LocationActivity.this, "Something went wrong, try again!", 8000);
				toast.show();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			Intent i = new Intent(LocationActivity.this, BadmintonBuddyNativeActivity.class);
			startActivity(i);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
