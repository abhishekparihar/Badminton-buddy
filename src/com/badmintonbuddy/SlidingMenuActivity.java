package com.badmintonbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.badmintonbuddy.helpers.AppStatus;
import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.slidingmenu.MenuDrawer;
import com.weboapps.badmintonbuddy.R;

public class SlidingMenuActivity extends Activity implements OnClickListener {

	private static final String STATE_ACTIVE_POSITION = "com.badmintonbuddy.activePosition";

	final static String TAG = "SlidingMenuActivity";
	public static AppStatus appStatus;
	public MenuDrawer mMenuDrawer;

	Bundle bundle;
	private int mActivePosition = -1;

	public Button buttonHome, buttonMyBuddies, buttonFavLocation, buttonSchedule, buttonTournaments, buttonRules, buttonTips, 
	buttonAbout, buttonAccount, buttonLogout;

	private Intent i;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.blank);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.header);
		bundle = savedInstanceState;

		if (savedInstanceState != null) {
			mActivePosition = savedInstanceState.getInt(STATE_ACTIVE_POSITION);
		}
		appStatus = AppStatus.getInstance(this);

		

	}


	private void initializeWidgets() {
		buttonHome = (Button)findViewById(R.id.buttonHome);
		buttonMyBuddies = (Button)findViewById(R.id.buttonMyBuddies);
		buttonFavLocation = (Button)findViewById(R.id.buttonFavLocation);
		buttonSchedule = (Button)findViewById(R.id.buttonSchedule);
		buttonTournaments = (Button)findViewById(R.id.buttonTournaments);
		buttonRules = (Button)findViewById(R.id.buttonRules);
		buttonTips = (Button)findViewById(R.id.buttonTips);
		buttonAbout = (Button)findViewById(R.id.buttonAbout);
		buttonAccount = (Button)findViewById(R.id.buttonAccount);
		buttonLogout = (Button)findViewById(R.id.buttonLogout);

		buttonAbout.setOnClickListener(buttonLister);
		/*buttonHome.setOnClickListener(SlidingMenuActivity.this);
		buttonMyBuddies.setOnClickListener(SlidingMenuActivity.this);
		buttonFavLocation.setOnClickListener(SlidingMenuActivity.this);
		buttonSchedule.setOnClickListener(SlidingMenuActivity.this);
		buttonTournaments.setOnClickListener(SlidingMenuActivity.this);
		buttonRules.setOnClickListener(this);
		buttonTips.setOnClickListener(this); 
		buttonAccount.setOnClickListener(this);
		buttonLogout.setOnClickListener(this);*/

	}


	@Override
	public void onClick(View v) {
		LogUtils.LOGV(TAG, "on click called");
		switch (v.getId()) {
		case R.id.buttonAbout :
			i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
			break;
		case R.id.buttonHome :
			i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
			break;
		case R.id.buttonMyBuddies :
			i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
			break;
		case R.id.buttonFavLocation :
			i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
			break;
		case R.id.buttonSchedule :
			i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
			break;
		case R.id.buttonTournaments :
			i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
			break;
		case R.id.buttonRules :
			i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
			break;
		case R.id.buttonTips :
			i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
			break;
		case R.id.buttonAccount :
			i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
			break;
		case R.id.buttonLogout :
			i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
			break;

		default:
			break;
		}
	}

	public void setMenuDrawer(int layoutId){
		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_WINDOW);
		mMenuDrawer.setContentView(layoutId);
		mMenuDrawer.setMenuView(R.layout.menu_drawer);
		
		initializeWidgets();
		
		
	}
	
	OnClickListener buttonLister=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			LogUtils.LOGV(TAG, "on click called");
			switch (v.getId()) {
			case R.id.buttonAbout :
				i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				break;
			case R.id.buttonHome :
				i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				break;
			case R.id.buttonMyBuddies :
				i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				break;
			case R.id.buttonFavLocation :
				i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				break;
			case R.id.buttonSchedule :
				i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				break;
			case R.id.buttonTournaments :
				i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				break;
			case R.id.buttonRules :
				i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				break;
			case R.id.buttonTips :
				i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				break;
			case R.id.buttonAccount :
				i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				break;
			case R.id.buttonLogout :
				i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				break;

			default:
				break;
			}
			
			startActivity(i);
			
		}
	};
}