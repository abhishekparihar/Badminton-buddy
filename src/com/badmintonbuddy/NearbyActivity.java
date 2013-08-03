package com.badmintonbuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.weboapps.badmintonbuddy.R;

public class NearbyActivity extends SlidingMenuActivity {
	
	ListView mListView;
	NeabyBuddyListAdapter mNeabyBuddyListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearby_buddies);
		setMenuDrawer(R.layout.nearby_buddies,"Nearby Buddies",R.color.green_light);
		
		initializeWidgets();
	}
	
	private void initializeWidgets() {
		mListView = (ListView)findViewById(R.id.listBuddy);
		mNeabyBuddyListAdapter = new NeabyBuddyListAdapter(this);
		
	}

	public void sendMessageClick(View view) {
		
	}

}
