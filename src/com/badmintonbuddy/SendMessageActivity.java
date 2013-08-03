package com.badmintonbuddy;

import android.os.Bundle;
import android.view.View;

import com.weboapps.badmintonbuddy.R;

public class SendMessageActivity extends SlidingMenuActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_message);
		setMenuDrawer(R.layout.send_message, "Send message", R.color.green_light);
	}
	
	
	public void onSendNowClick(View view){
		
	}

}
