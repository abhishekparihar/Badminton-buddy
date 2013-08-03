package com.badmintonbuddy;

import android.os.Bundle;

import com.weboapps.badmintonbuddy.R;


public class TipsActivity extends SlidingMenuActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tips);
		setMenuDrawer(R.layout.tips);
	}

}
