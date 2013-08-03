package com.badmintonbuddy.location;

import android.os.Bundle;

import com.badmintonbuddy.SlidingMenuActivity;
import com.weboapps.badmintonbuddy.R;

public class LocationActivity extends SlidingMenuActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		setMenuDrawer(R.layout.location);
	}
	
	public void onLocationClick() {
		
	}

}
