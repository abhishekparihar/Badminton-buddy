package com.badmintonbuddy;

import java.util.List;

import org.json.JSONObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.tasks.SendMsgTask;
import com.weboapps.badmintonbuddy.R;

public class SendMessageActivity extends SlidingMenuActivity {
	
	 List<String> mSelectedList=null;
	 EditText mEditText=null;
	private ProgressDialog mProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_message);
		setMenuDrawer(R.layout.send_message, "Send message", R.color.green_light);
		mEditText=(EditText)findViewById(R.id.editText1);
		
		mSelectedList=(List<String>)getIntent().getSerializableExtra("buddy_list");
		LogUtils.LOGV("TAG", "here");
	}
	
	
	public void onSendNowClick(View view){
		try{
			String[] args = new String[3];

			args[0] = appStatus.getSharedStringValue(appStatus.AUTH_KEY);
			args[1] = mEditText.getText().toString();
			args[2] = ""+mSelectedList;

			if ( appStatus.isOnline() ) {
				showDialog(0);

				new SendMsgTask(this).execute(args); 
			} else {
				LogUtils.LOGV(TAG, "App is not online!");
				Toast toast = Toast.makeText(SendMessageActivity.this, "App is not online!", 8000);
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

		dialog.setMessage("sending message...");


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


	public void onAuthenticationResult(String response) {
		removeDialog(0);
		
		try {
			JSONObject jsonObject = new JSONObject(response);

			Boolean result = jsonObject.getBoolean("success");
			String strMessage = jsonObject.getString("messages");
			
			Toast toast = Toast.makeText(SendMessageActivity.this, strMessage, 8000);
			toast.show();
			
			if(result){
				Intent i = new Intent(SendMessageActivity.this, BadmintonBuddyNativeActivity.class);
				startActivity(i);
				finish();
			}

			LogUtils.LOGE("LoginWebService", result.toString());
		} catch (Exception e) {
			LogUtils.LOGE("LoginWebService", "LoginResult Error: " + e.getMessage());
		}
		
	}


}
