package com.badmintonbuddy.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.badmintonbuddy.AreaListActivity;
import com.badmintonbuddy.location.LocationActivity;
import com.badmintonbuddy.models.AreaResult;
import com.badmintonbuddy.models.BuddyResult;
import com.badmintonbuddy.services.LoginWebService;

public class BuddyTask extends AsyncTask<String, Integer, BuddyResult> {
	private AreaListActivity context;

	public BuddyTask(AreaListActivity context) {
		this.context = context;
	}

	protected BuddyResult doInBackground(String... login_params) {
			return LoginWebService.getBuddies(context, login_params);
	}

	protected void onPostExecute(BuddyResult result) {
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
