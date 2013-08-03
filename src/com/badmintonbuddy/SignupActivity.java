package com.badmintonbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.badmintonbuddy.models.LoginResult;
import com.weboapps.badmintonbuddy.R;

public class SignupActivity extends Activity {
	EditText editTextSignUpEmail, editTextSignUpPassword, editTextPhone, editTextSignUpName;
	Button buttonSubmit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		initializeWidgets();
	}


	private void initializeWidgets() {
		editTextSignUpEmail = (EditText)findViewById(R.id.editTextSignUpEmail);
		editTextSignUpPassword = (EditText)findViewById(R.id.editTextSignUpPassword);
		editTextPhone = (EditText)findViewById(R.id.editTextPhone);
		editTextSignUpName = (EditText)findViewById(R.id.editTextSignUpName);
		buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
		
	}

	public void onSignUpClick(View v){
		
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
