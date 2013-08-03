package com.badmintonbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.badmintonbuddy.helpers.AppStatus;
import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.models.AreaResult;
import com.badmintonbuddy.models.BuddyResult;
import com.badmintonbuddy.tasks.BuddyTask;
import com.weboapps.badmintonbuddy.R;

public class AreaListActivity extends SlidingMenuActivity {

	final static String TAG = "AreaListActivity";
	AppStatus appStatus;
	AreaResult mAreaResult=null;
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arealist);
		setMenuDrawer(R.layout.arealist,"My location",R.color.green_light);

		mAreaResult=(AreaResult) getIntent().getSerializableExtra("area_list");

		appStatus = AppStatus.getInstance(this);
		ListView mListView=(ListView) findViewById(R.id.listView);

		AreaListAdapter mAreaListAdapter=new AreaListAdapter(AreaListActivity.this,mAreaResult.getArea());
		mListView.setAdapter(mAreaListAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				getBuddies(position);

			}
		});
	}

	private void getBuddies(int position){
		try{
			String[] args = new String[2];

			args[0] = appStatus.getSharedStringValue(appStatus.AUTH_KEY);
			args[1] = ""+mAreaResult.getArea().get(position).getId();

			if ( appStatus.isOnline() ) {
				this.showDialog(0);

				new BuddyTask(this).execute(args);
			} else {
				LogUtils.LOGV(TAG, "App is not online!");
				Toast toast = Toast.makeText(AreaListActivity.this, "App is not online!", 8000);
				toast.show();
			}
		}catch(Exception e){

		}
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog = ProgressDialog.show(this, null, null);
		//dialog.setContentView(R.layout.loader);
		// dialog.setTitle("Please Wait...");

		dialog.setMessage("fetching Buddies...");


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

	public void onAuthenticationResult(BuddyResult result) {

		try{
			removeDialog(0);
			if(result == null ){
				Toast toast = Toast.makeText(AreaListActivity.this, "Something went wrong, try again!", 8000);
				toast.show();
			}

			if(result.getSuccess()){
				Intent i = new Intent(AreaListActivity.this, NearbyBuddyActivity.class);
				i.putExtra("buddy_list", result);
				startActivity(i);
			}else{
				Toast toast = Toast.makeText(AreaListActivity.this, "Something went wrong, try again!", 8000);
				toast.show();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
