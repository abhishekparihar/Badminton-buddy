package com.badmintonbuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.models.BuddyResult;
import com.weboapps.badmintonbuddy.R;

public class NearbyBuddyActivity extends SlidingMenuActivity {
	
	ListView mListView;
	NeabyBuddyListAdapter mNeabyBuddyListAdapter;
	private BuddyResult mBuddyResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearby_buddies);
		setMenuDrawer(R.layout.nearby_buddies);
		
		mBuddyResult = (BuddyResult) getIntent().getSerializableExtra("buddy_list");

		
		initializeWidgets();
	}
	
	private void initializeWidgets() {
		mListView = (ListView)findViewById(R.id.listBuddy);
		mNeabyBuddyListAdapter = new NeabyBuddyListAdapter(this,mBuddyResult);
		mListView.setAdapter(mNeabyBuddyListAdapter);
		
	}

	public void sendMessageClick(View view) {
		LogUtils.LOGV("NearbyBuddyActivity", ""+mNeabyBuddyListAdapter.mSelectedList.size());
	}

}
