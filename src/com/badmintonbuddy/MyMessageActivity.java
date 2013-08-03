package com.badmintonbuddy;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.models.MyMessagesResult;
import com.badmintonbuddy.tasks.MyMsgTask;
import com.google.myjson.Gson;
import com.weboapps.badmintonbuddy.R;

public class MyMessageActivity extends SlidingMenuActivity {

	TextView textViewTotalMessage;
	ListView listViewMyMessages;
	MyMessageListAdapter myMessageListAdapter;
	ImageView imageViewSendMessage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_message);
		setMenuDrawer(R.layout.my_message, "My messages", R.color.green_light);
		initializeList();
	}

	private void initializeList() {
		textViewTotalMessage = (TextView)findViewById(R.id.textViewTotalMessage);
		listViewMyMessages = (ListView)findViewById(R.id.listViewMyMessages);

		imageViewSendMessage = (ImageView)findViewById(R.id.imageViewSendMessage);

		imageViewSendMessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// open send message activity

			}
		});

		getMyMessages();

	}

	private void getMyMessages() {
		try{
			String[] args = new String[1];

			args[0] = appStatus.getSharedStringValue(appStatus.AUTH_KEY);

			if ( appStatus.isOnline() ) {
				showDialog(0);

				new MyMsgTask(this).execute(args);
			} else {
				LogUtils.LOGV(TAG, "App is not online!");
				Toast toast = Toast.makeText(MyMessageActivity.this, "App is not online!", 8000);
				toast.show();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void onAuthenticationResult(String response) {
		removeDialog(0);

		try {
			JSONObject jsonObject = new JSONObject(response);

			Boolean result = jsonObject.getBoolean("success");
			if(!result){
				String strMessage = jsonObject.getString("messages");
				Toast toast = Toast.makeText(MyMessageActivity.this, strMessage, 8000);
				toast.show();
			}else{
				MyMessagesResult messagesResult = new Gson().fromJson(response, MyMessagesResult.class);
				myMessageListAdapter = new MyMessageListAdapter(this,messagesResult);

				ListView mListView=(ListView)findViewById(R.id.listViewMyMessages);
				mListView.setAdapter(myMessageListAdapter);

				TextView mTextView=(TextView)findViewById(R.id.textViewTotalMessage);
				mTextView.setText(""+messagesResult.getMessages().size()+" messages");

			}
			LogUtils.LOGE("LoginWebService", result.toString());
		} catch (Exception e) {
			LogUtils.LOGE("LoginWebService", "LoginResult Error: " + e.getMessage());
		}
	}


}
