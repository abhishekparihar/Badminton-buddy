package com.weboapps.badmintonbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	
	EditText editTextUser, editTextPassword;
	Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        initializeWidgets();
    }


    private void initializeWidgets() {
    	editTextUser = (EditText)findViewById(R.id.editTextUser);
    	editTextPassword = (EditText)findViewById(R.id.editTextPassword);
    	buttonLogin = (Button)findViewById(R.id.buttonLogin);
	}
    
    
    public void onLoginClick(View v) {
    	
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.badminton_buddy_native, menu);
        return true;
    }
    
}
