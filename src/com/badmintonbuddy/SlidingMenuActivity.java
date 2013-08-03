package com.badmintonbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.badmintonbuddy.helpers.AppStatus;
import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.slidingmenu.MenuDrawer;
import com.weboapps.badmintonbuddy.R;

public class SlidingMenuActivity extends Activity {

	private static final String STATE_ACTIVE_POSITION = "com.badmintonbuddy.activePosition";

	final static String TAG = "SlidingMenuActivity";
	public static AppStatus appStatus;
	public MenuDrawer mMenuDrawer;

	Bundle bundle;
	private int mActivePosition = -1;

	public Button buttonHome, buttonMyBuddies, buttonCourts, buttonMsgs, buttonTournaments, buttonRules, buttonTips, 
	buttonAbout, buttonAccount, buttonLogout;
	
	TextView textViewHeaderTitle;
	ImageView imageViewTitleButton;

	private Intent i;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.blank);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.header);
		textViewHeaderTitle = (TextView)findViewById(R.id.textViewHeaderTitle);
		imageViewTitleButton = (ImageView)findViewById(R.id.imageViewTitleButton);
		bundle = savedInstanceState;

		if (savedInstanceState != null) {
			mActivePosition = savedInstanceState.getInt(STATE_ACTIVE_POSITION);
		}
		appStatus = AppStatus.getInstance(this);
		
		imageViewTitleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				opencloseMenu();
				
			}
		});

	}
	
	private void opencloseMenu() {
		final int drawerState = mMenuDrawer.getDrawerState();
		if(drawerState == MenuDrawer.STATE_CLOSED || drawerState == MenuDrawer.STATE_CLOSING){
			mMenuDrawer.openMenu();
		}else {
			mMenuDrawer.closeMenu();
		}
		
	}
	
	public void setMenuDrawer(int layoutId,String header,int background){
		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_WINDOW);
		mMenuDrawer.setContentView(layoutId);
		mMenuDrawer.setMenuView(R.layout.menu_drawer);
		textViewHeaderTitle.setText(header);
		textViewHeaderTitle.setBackgroundResource(background);
		initializeWidgets();
		
		
	}


	private void initializeWidgets() {
		buttonHome = (Button)findViewById(R.id.buttonHome);
		buttonMyBuddies = (Button)findViewById(R.id.buttonMyBuddies);
		buttonCourts = (Button)findViewById(R.id.buttonCourts);
		buttonMsgs = (Button)findViewById(R.id.buttonMsg);
		buttonTournaments = (Button)findViewById(R.id.buttonTournaments);
		buttonRules = (Button)findViewById(R.id.buttonRules);
		buttonTips = (Button)findViewById(R.id.buttonTips);
		buttonAbout = (Button)findViewById(R.id.buttonAbout);
		buttonAccount = (Button)findViewById(R.id.buttonAccount);
		buttonLogout = (Button)findViewById(R.id.buttonLogout);

		buttonAbout.setOnClickListener(buttonLister);
		buttonHome.setOnClickListener(buttonLister);
		buttonMyBuddies.setOnClickListener(buttonLister);
		buttonCourts.setOnClickListener(buttonLister);
		buttonMsgs.setOnClickListener(buttonLister);
		buttonTournaments.setOnClickListener(buttonLister);
		buttonRules.setOnClickListener(buttonLister);
		buttonTips.setOnClickListener(buttonLister); 
		buttonAccount.setOnClickListener(buttonLister);
		buttonLogout.setOnClickListener(buttonLister);

	}


	OnClickListener buttonLister=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			LogUtils.LOGV(TAG, "on click called");
			switch (v.getId()) {
			case R.id.buttonAbout :
				//i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				showMessage();
				break;
			case R.id.buttonHome :
				i = new Intent(SlidingMenuActivity.this, BadmintonBuddyNativeActivity.class);
				startActivity(i);
				break;
			case R.id.buttonMyBuddies :
				//i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				showMessage();
				break;
			case R.id.buttonCourts :
				i = new Intent(SlidingMenuActivity.this, CourtsActivity.class);
				startActivity(i);
				break;
			case R.id.buttonMsg :
				i = new Intent(SlidingMenuActivity.this, MyMessageActivity.class);
				startActivity(i);
				break;
			case R.id.buttonTournaments :
				//i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				showMessage();
				break;
			case R.id.buttonRules :
				//i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				showMessage();
				break;
			case R.id.buttonTips :
				i = new Intent(SlidingMenuActivity.this, TipsActivity.class);
				startActivity(i);
				break;
			case R.id.buttonAccount :
				//i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				showMessage();
				break;
			case R.id.buttonLogout :
				//i = new Intent(SlidingMenuActivity.this, SplashActivity.class);
				showMessage();
				break;

			default:
				break;
			}
			
			
			
		}
	};
	
	public void showMessage(){
		Toast toast = Toast.makeText(SlidingMenuActivity.this, "Coming soon...", 8000);
		toast.show();
	}
}