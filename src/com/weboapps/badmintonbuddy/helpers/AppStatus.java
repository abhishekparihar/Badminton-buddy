package com.weboapps.badmintonbuddy.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;

public class AppStatus {

	final static String TAG = "AppStatus";
	private static AppStatus instance = new AppStatus();
	static Context context;
	boolean connected = false;
	ConnectivityManager connectivityManager;

	public static final String FILE_NAME = "appdata";
	public final String LOGIN_STATUS = "login_status";
	

	public final String IS_USER_API_CALLED = "is_user_api_called";

	public static AppStatus getInstance(Context ctx) {
		context = ctx;
		return instance;
	}

	public Boolean isOnline() {

		try {
			connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo networkInfo = connectivityManager
					.getActiveNetworkInfo();
			connected = networkInfo != null && networkInfo.isAvailable()
					&& networkInfo.isConnected();
			return connected;

		} catch (Exception e) {
			LogUtils.LOGE(TAG, e.toString());
		}
		return connected;
	}

	public Boolean isRegistered() {
		try {
			if (!getSharedBoolValue(LOGIN_STATUS))
				return false;
			else
				return true;
		} catch (Exception e) {
			LogUtils.LOGE(TAG, e.toString());
		}

		return false;
	}
	
	public Boolean validateEditText(EditText field) {
		String strField = field.getText().toString();
		if (strField.trim().equals("")) {
			return false;
		}
		return true;
	}

	public String getSharedStringValue(String key) {
		SharedPreferences sp = context.getSharedPreferences("FILE_NAME", 0);
		String value = sp.getString(key, null);
		return value;
	}

	public boolean saveSharedStringValue(String key, String value) {
		SharedPreferences sp = context.getSharedPreferences("FILE_NAME", 0);
		Editor edit = sp.edit();
		edit.putString(key, value);
		return edit.commit();
	}

	public boolean clearSharedData() {
		SharedPreferences sp = context.getSharedPreferences("FILE_NAME", 0);
		Editor edit = sp.edit();
		edit.clear();
		return edit.commit();
	}

	public boolean clearSharedDataWithKey(String key) {
		SharedPreferences sp = context.getSharedPreferences("FILE_NAME", 0);
		Editor edit = sp.edit();
		edit.remove(key);
		return edit.commit();
	}

	public Boolean getSharedBoolValue(String key) {
		SharedPreferences sp = context.getSharedPreferences("FILE_NAME", 0);
		Boolean value = sp.getBoolean(key, false);
		return value;
	}

	public boolean saveSharedBoolValue(String key, Boolean value) {
		SharedPreferences sp = context.getSharedPreferences("FILE_NAME", 0);
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		return edit.commit();
	}

	public int getSharedIntValue(String key) {
		SharedPreferences sp = context.getSharedPreferences("FILE_NAME", 0);
		int value = sp.getInt(key, 0);
		return value;
	}

	public boolean saveSharedIntValue(String key, int value) {
		SharedPreferences sp = context.getSharedPreferences("FILE_NAME", 0);
		Editor edit = sp.edit();
		edit.putInt(key, value);
		return edit.commit();
	}
}
