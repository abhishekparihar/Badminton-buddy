package com.badmintonbuddy.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.badmintonbuddy.SendMessageActivity;
import com.badmintonbuddy.models.BuddyResult;
import com.badmintonbuddy.services.LoginWebService;

public class SendMsgTask extends AsyncTask<String, Integer, BuddyResult> {
	private SendMessageActivity context;

	public SendMsgTask(SendMessageActivity context) {
		this.context = context;
	}

	protected BuddyResult doInBackground(String... login_params) {
			return LoginWebService.sendMessage(context, login_params);
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
