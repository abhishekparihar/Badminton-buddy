package com.badmintonbuddy.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.badmintonbuddy.MyMessageActivity;
import com.badmintonbuddy.services.LoginWebService;

public class MyMsgTask extends AsyncTask<String, Integer, String> {
	private MyMessageActivity context;

	public MyMsgTask(MyMessageActivity context) {
		this.context = context;
	}

	protected String doInBackground(String... login_params) {
			return LoginWebService.myMessages(context, login_params);
	}

	protected void onPostExecute(String result) {
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
