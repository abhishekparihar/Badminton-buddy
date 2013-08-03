package com.weboapps.badmintonbuddy.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.badmintonbuddy.SignupActivity;
import com.badmintonbuddy.models.LoginResult;
import com.badmintonbuddy.services.LoginWebService;

public class SignUpTask extends AsyncTask<String, Integer, LoginResult> {
	private SignupActivity context;

	public SignUpTask(SignupActivity context) {
		this.context = context;
	}

	protected LoginResult doInBackground(String... login_params) {
			return LoginWebService.create(context, login_params);
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
