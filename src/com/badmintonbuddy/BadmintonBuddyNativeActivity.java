package com.badmintonbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.badmintonbuddy.helpers.AppStatus;
import com.badmintonbuddy.slidingmenu.MenuDrawer;
import com.weboapps.badmintonbuddy.R;

public class BadmintonBuddyNativeActivity extends Activity {

	private static final String STATE_ACTIVE_POSITION = "com.badmintonbuddy.activePosition";
	final static String TAG = "BadmintonBuddyNativeActivity";
	public static AppStatus appStatus;
	public MenuDrawer mMenuDrawer;
	
	Bundle bundle;
	private int mActivePosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        bundle = savedInstanceState;

		if (savedInstanceState != null) {
			mActivePosition = savedInstanceState.getInt(STATE_ACTIVE_POSITION);
		}
		appStatus = AppStatus.getInstance(this);
		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_CONTENT);
		mMenuDrawer.setContentView(R.layout.main);
		mMenuDrawer.setMenuView(R.layout.menu_drawer);
        //setContentView(R.layout.main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.badminton_buddy_native, menu);
        return true;
    }
    
}
