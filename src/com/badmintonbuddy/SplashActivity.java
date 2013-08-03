package com.badmintonbuddy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.Toast;

import com.badmintonbuddy.helpers.AppStatus;
import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.location.LocationActivity;
import com.weboapps.badmintonbuddy.R;

public class SplashActivity extends Activity {
	
	final static String TAG = "SplashActivity";
	private ProgressDialog loading;
    Handler mhandler;
    AppStatus appStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        LogUtils.LOGV(TAG, "here in splash");
        
        appStatus = AppStatus.getInstance(this);
        mhandler = new Handler();
        loading = new ProgressDialog(this);
        loading.setMessage("Loading...");
        loading.setCancelable(true);
        
        startApp();
    }
    
    void startApp() {
        // showLoading(true, "Loading", "In Process please wait...");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if ( appStatus.isOnline() ) {
                	if ( appStatus.isRegistered() ) {

                		if(!appStatus.getSharedBoolValue(appStatus.IS_FIRST_TIME)){
                			Intent i = new Intent(SplashActivity.this, LocationActivity.class);
                			startActivity(i);
                		}else{
                			Intent i = new Intent(SplashActivity.this, BadmintonBuddyNativeActivity.class);
                			startActivity(i);
                		}
                        finish();
                    } else {
                    	Intent intent_login = new Intent(SplashActivity.this, LoginActivity.class);
                    	startActivity(intent_login);
                    	 finish();
                    }
                } else {
                     message("Please check you internet connection!!");
                    finish();
                }
                // showLoading(false, "", "");
            }
        });
        t.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.badminton_buddy_native, menu);
        return true;
    }
    
    void message(String msg) {
        final String mesage = msg;
        mhandler.post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(SplashActivity.this, mesage, 8000);
                toast.show();
            }
        });
    }
    
}
