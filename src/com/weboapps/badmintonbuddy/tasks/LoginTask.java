package com.weboapps.badmintonbuddy.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.weboapps.badmintonbuddy.LoginActivity;
import com.weboapps.badmintonbuddy.models.LoginResult;
import com.weboapps.badmintonbuddy.services.LoginWebService;

public class LoginTask extends AsyncTask<String, Integer, LoginResult> {
	private LoginActivity context;

	public LoginTask(LoginActivity context) {
		this.context = context;
	}

	protected LoginResult doInBackground(String... login_params) {
			return LoginWebService.login(context, login_params);
	}

	protected void onPostExecute(LoginResult result) {
		if(context!=null){
			if ( result != null )
				context.onAuthenticationResult(result);
			else {
				Toast toast = Toast.makeText(context, "Can't connect right now, try after some time!", Toast.LENGTH_SHORT);
                toast.show();
                context.removeDialog(0);
			}
		}
	}
}
