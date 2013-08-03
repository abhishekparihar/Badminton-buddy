package com.badmintonbuddy;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.weboapps.badmintonbuddy.R;

public class MyMessageActivity extends SlidingMenuActivity {
	
	TextView textViewTotalMessage;
	ListView listViewMyMessages;
	MyMessageListAdapter myMessageListAdapter;
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
		myMessageListAdapter = new MyMessageListAdapter(this);
		
	}

}
