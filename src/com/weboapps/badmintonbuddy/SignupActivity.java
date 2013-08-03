package com.weboapps.badmintonbuddy;

import com.weboapps.badmintonbuddy.models.LoginResult;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SignupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.badminton_buddy_native, menu);
        return true;
    }


	public void onAuthenticationResult(LoginResult result) {
		
	}
    
}
