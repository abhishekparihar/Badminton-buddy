package com.badmintonbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.badmintonbuddy.helpers.AppStatus;
import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.models.LoginResult;
import com.badmintonbuddy.tasks.SignUpTask;
import com.weboapps.badmintonbuddy.R;

public class SignupActivity extends Activity {
	EditText editTextSignUpEmail, editTextSignUpPassword, editTextPhone, editTextSignUpName;
	Button buttonSubmit;
	private AppStatus appStatus;
	private ProgressDialog mProgressDialog;

	final static String TAG = "SignupActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		initializeWidgets();
	}


	private void initializeWidgets() {
		editTextSignUpEmail = (EditText)findViewById(R.id.editTextSignUpEmail);
		editTextSignUpPassword = (EditText)findViewById(R.id.editTextSignUpPassword);
		editTextPhone = (EditText)findViewById(R.id.editTextPhone);
		editTextSignUpName = (EditText)findViewById(R.id.editTextSignUpName);
		buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

		appStatus = AppStatus.getInstance(this);

	}

	public void onSignUpClick(View v){
		String[] args = new String[4];

		args[0] = editTextSignUpName.getText().toString();
		args[1] = editTextSignUpEmail.getText().toString();
		args[2] = editTextPhone.getText().toString();
		args[3] = editTextSignUpPassword.getText().toString();

		if ( appStatus.isOnline() ) {
			showDialog(0);
			new SignUpTask(this).execute(args);
		} else {
			LogUtils.LOGV("CreateAccount", "App is not online!");
			Toast toast = Toast.makeText(SignupActivity.this, "App is not online!", 8000);
			toast.show();
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.badminton_buddy_native, menu);
		return true;
	}


	public void onAuthenticationResult(LoginResult result) {

		try{
			removeDialog(0);
			if(result == null){
				Toast toast = Toast.makeText(SignupActivity.this, "Something went wrong, try again!", 8000);
				toast.show();
			}
			
			if(result.getSuccess()){
               appStatus.saveSharedStringValue(appStatus.AUTH_KEY, result.getAuth_key());
               appStatus.saveSharedStringValue(appStatus.PHONE_NO, result.getPhone_number());
               appStatus.saveSharedStringValue(appStatus.NAME, result.getName());
               appStatus.saveSharedStringValue(appStatus.EMAIL, result.getEmail());

               Intent i = new Intent(SignupActivity.this, BadmintonBuddyNativeActivity.class);
               startActivity(i);
               finish();
			}

			Toast toast = Toast.makeText(SignupActivity.this, result.getMessage(), 8000);
			toast.show();
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

		dialog.setMessage("Signing up...");


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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent i = new Intent(SignupActivity.this, LoginActivity.class);
			startActivity(i);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
