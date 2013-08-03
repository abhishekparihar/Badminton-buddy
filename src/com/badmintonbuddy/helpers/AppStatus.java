package com.badmintonbuddy.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppStatus {
    private static AppStatus instance = new AppStatus();
    ConnectivityManager connectivityManager;
    static Context context;
    boolean connected = false;
    public static final String FILE_NAME = "appdata";
    public final String AUTH_KEY = "auth_key";
    
    public final String NAME = "name";
    public final String EMAIL = "email";
    public final String PHONE_NO = "phone_no";
    
    public final String IS_FIRST_TIME = "first_time";
 

    public static AppStatus getInstance(Context ctx) {
        context = ctx;
        return instance;
    }

    public Boolean isOnline() {

        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
            return connected;

        } catch (Exception e) {
            LogUtils.LOGE("AppStatus", e.toString());
        }
        return connected;
    }

    public Boolean isRegistered() {
        try {
            String auth_key = getSharedStringValue(AUTH_KEY);
            if ( auth_key == null )
                return false;
            else
                return true;
        } catch (Exception e) {
        	LogUtils.LOGD("AppStatus", e.toString());
        }

        return false;
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

    public Long getSharedLongValue(String key) {
        SharedPreferences sp = context.getSharedPreferences("FILE_NAME", 0);
        Long value = sp.getLong(key, 0);
        return value;
    }

    public boolean saveSharedLongValue(String key, Long value) {
        SharedPreferences sp = context.getSharedPreferences("FILE_NAME", 0);
        Editor edit = sp.edit();
        edit.putLong(key, value);
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
