package com.badmintonbuddy;

import android.os.Bundle;
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
        setMenuDrawer(R.layout.main);
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
    
}
