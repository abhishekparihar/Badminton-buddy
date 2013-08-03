package com.badmintonbuddy.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.badmintonbuddy.location.LocationActivity;
import com.badmintonbuddy.models.AreaResult;
import com.badmintonbuddy.services.LoginWebService;

public class AreaTask extends AsyncTask<String, Integer, AreaResult> {
	private LocationActivity context;

	public AreaTask(LocationActivity context) {
		this.context = context;
	}

	protected AreaResult doInBackground(String... login_params) {
			return LoginWebService.getAreas(context, login_params);
	}

	protected void onPostExecute(AreaResult result) {
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
