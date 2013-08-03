package com.badmintonbuddy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.badmintonbuddy.helpers.AppStatus;
import com.weboapps.badmintonbuddy.R;

public class BadmintonBuddyNativeActivity extends SlidingMenuActivity  implements OnClickListener{

	final static String TAG = "BadmintonBuddyNativeActivity";
	public static AppStatus appStatus;
	
	Bundle bundle;
	private int mActivePosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        bundle = savedInstanceState;
        setContentView(R.layout.main);
        setMenuDrawer(R.layout.main,"Home",R.color.green_light);
		appStatus = AppStatus.getInstance(this);
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.badminton_buddy_native, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
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
