package com.badmintonbuddy;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.weboapps.badmintonbuddy.R;


public class TipsActivity extends SlidingMenuActivity {
	
	ViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tips);
		setMenuDrawer(R.layout.tips,"Tips",R.color.transparent);
		
		mViewPager = (ViewPager)findViewById(R.id.pager);
		TipsViewPagerAdapter mTipsViewPagerAdapter = new TipsViewPagerAdapter(this);
		
		mViewPager.setAdapter(mTipsViewPagerAdapter);
	}

}
