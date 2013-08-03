package com.badmintonbuddy.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.badmintonbuddy.CourtsActivity;
import com.badmintonbuddy.models.CourtResult;
import com.badmintonbuddy.services.LoginWebService;

public class CourtsTask extends AsyncTask<String, Integer, CourtResult> {
	private CourtsActivity context;

	public CourtsTask(CourtsActivity context) {
		this.context = context;
	}

	protected CourtResult doInBackground(String... login_params) {
			return LoginWebService.getCourts(context, login_params);
	}

	protected void onPostExecute(CourtResult result) {
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
