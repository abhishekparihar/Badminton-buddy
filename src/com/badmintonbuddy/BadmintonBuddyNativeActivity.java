package com.badmintonbuddy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
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
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ShowMessageBox();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void ShowMessageBox() {
		AlertDialog exitAlert = new AlertDialog.Builder(this).create();

		exitAlert.setTitle("Exit Application");

		exitAlert.setMessage("Are you sure you want to exit?");

		exitAlert.setButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				finish();
			}
		});
		exitAlert.setButton2("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		exitAlert.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if ( keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_MENU ) {
					return true;
				}
				return false;
			}
		});
		exitAlert.show();
	}
}
