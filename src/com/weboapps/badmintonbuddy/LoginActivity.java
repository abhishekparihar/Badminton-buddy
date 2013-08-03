package com.weboapps.badmintonbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.weboapps.badmintonbuddy.helpers.AppStatus;
import com.weboapps.badmintonbuddy.helpers.LogUtils;
import com.weboapps.badmintonbuddy.models.LoginResult;
import com.weboapps.badmintonbuddy.tasks.LoginTask;

public class LoginActivity extends Activity {

	final static String TAG = "LoginActivity";

	EditText editTextUser, editTextPassword;
	Button buttonLogin;

	private AppStatus appStatus;

	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		initializeWidgets();
	}


	private void initializeWidgets() {
		editTextUser = (EditText)findViewById(R.id.editTextUser);
		editTextPassword = (EditText)findViewById(R.id.editTextPassword);
		buttonLogin = (Button)findViewById(R.id.buttonLogin);

		appStatus = AppStatus.getInstance(this);
	}


	public void onLoginClick(View v) {
		String[] args = new String[2];

		args[0] = editTextUser.getText().toString();
		args[1] = editTextPassword.getText().toString();

		if ( appStatus.isOnline() ) {
			this.showDialog(0);
			new LoginTask(this).execute(args);
			// bIsFromSocialLogin=false;
		} else {
			LogUtils.LOGV("LoginActivity", "App is not online!");
			Toast toast = Toast.makeText(LoginActivity.this, "App is not online!", 8000);
            toast.show();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.badminton_buddy_native, menu);
		return true;
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog = ProgressDialog.show(this, null, null);
		//dialog.setContentView(R.layout.loader);
		// dialog.setTitle("Please Wait...");

		dialog.setMessage("Signing In...");


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

	public void onAuthenticationResult(LoginResult result) {
		
	}
}
