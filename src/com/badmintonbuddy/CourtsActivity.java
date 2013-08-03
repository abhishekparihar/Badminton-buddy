package com.badmintonbuddy;

import java.util.List;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.models.CourtResult;
import com.badmintonbuddy.models.CourtResult.Court;
import com.badmintonbuddy.tasks.CourtsTask;
import com.weboapps.badmintonbuddy.R;

public class CourtsActivity extends SlidingMenuActivity {
	
	ListView mListView;
	CourtsListAdapter mCourtsListAdapter;
	private ProgressDialog mProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.courts);
		setMenuDrawer(R.layout.courts, "Courts", R.color.green_light);
		
		
		
		initialize();
		
		getCourts();
	}
	
	private void getCourts() {
		try{
			String[] args = new String[1];

			args[0] = appStatus.getSharedStringValue(appStatus.AUTH_KEY);

			if ( appStatus.isOnline() ) {
				showDialog(0);

				new CourtsTask(this).execute(args);
			} else {
				LogUtils.LOGV(TAG, "App is not online!");
				Toast toast = Toast.makeText(CourtsActivity.this, "App is not online!", 8000);
				toast.show();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void initialize() {
		mListView = (ListView)findViewById(R.id.listViewCourts);
		
	}
	
	
	public class CourtsListAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		
		 Context context;
		 List<Court> courts;
		
		public CourtsListAdapter(CourtsActivity courtsActivity,
				List<Court> court) {
			
			mInflater = LayoutInflater.from(CourtsActivity.this);
			context=courtsActivity;
			courts=court;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return courts.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return courts.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textViewCourtName = null;
			TextView textViewCourtArea =null;
			TextView textViewPhone = null;
			
			if (convertView == null) 
				convertView = mInflater.inflate(R.layout.courts_list_item, null);
			
			textViewCourtName = (TextView)convertView.findViewById(R.id.textViewCourtName);
			textViewCourtArea = (TextView)convertView.findViewById(R.id.textViewCourtArea);
			textViewPhone = (TextView)convertView.findViewById(R.id.textViewPhone);
			
			Court mCourtResult =(Court)getItem(position);
			
			textViewCourtArea.setText(mCourtResult.getArea());
			textViewCourtName.setText(mCourtResult.getName());
			textViewPhone.setText(mCourtResult.getPh());
			
			return convertView;
		}
	}


	public void onAuthenticationResult(CourtResult result) {
		try{
			removeDialog(0);
			if(result == null ){
				Toast toast = Toast.makeText(CourtsActivity.this, "Something went wrong, try again!", 8000);
				toast.show();
			}

			if(result.getSuccess()){
				mCourtsListAdapter  = new CourtsListAdapter(this,result.getCourts());
				mListView.setAdapter(mCourtsListAdapter);
			}else{
				Toast toast = Toast.makeText(CourtsActivity.this, "Something went wrong, try again!", 8000);
				toast.show();
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog = ProgressDialog.show(this, null, null);
		//dialog.setContentView(R.layout.loader);
		// dialog.setTitle("Please Wait...");

		dialog.setMessage("fetching courts...");


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

}
