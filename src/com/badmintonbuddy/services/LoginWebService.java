package com.badmintonbuddy.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.badmintonbuddy.MyMessageActivity;
import com.badmintonbuddy.SendMessageActivity;
import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.models.AreaResult;
import com.badmintonbuddy.models.BuddyResult;
import com.badmintonbuddy.models.CourtResult;
import com.badmintonbuddy.models.LoginResult;
import com.google.myjson.Gson;

public class LoginWebService implements WebServiceIface {

	final static String TAG = "LoginWebService";

	public static LoginResult login(Context context, String... login_params) {
		LoginResult result = null;

		WebService webService = new WebService(BASE_URL + "log_in.json");

		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("email", login_params[0]));
		params.add(new BasicNameValuePair("password", login_params[1]));

		String response = webService.webPost("", params);

		if ( response != null ) {
			LogUtils.LOGE("LoginWebService", response.toString());
		}

		try {
			result = new Gson().fromJson(response, LoginResult.class);
			LogUtils.LOGE("LoginWebService", result.toString());
		} catch (Exception e) {
			LogUtils.LOGE("LoginWebService", "LoginResult Error: " + e.getMessage());
		}

		return result;
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();

		if ( networkInfo != null && networkInfo.isConnected() ) {
			return true;
		}

		return false;
	}

	public static LoginResult create(Context context, String[] create_params) {
		LoginResult result = null;
		LogUtils.LOGE(TAG, "Network available: " + Boolean.toString(isNetworkAvailable(context)));
		WebService webService = new WebService(BASE_URL + "sign_up.json");

		List<NameValuePair> params = new ArrayList<NameValuePair>(5);
		params.add(new BasicNameValuePair("name", create_params[0]));
		params.add(new BasicNameValuePair("email", create_params[1]));
		params.add(new BasicNameValuePair("phone_number", create_params[2]));
		params.add(new BasicNameValuePair("password", create_params[3]));

		String response = webService.webPost("", params);
		if ( response != null )
			LogUtils.LOGE(TAG, "CREATE ACCOUNT RESULT: " + response.toString());

		try {
			result = new Gson().fromJson(response, LoginResult.class);

			LogUtils.LOGE(TAG, result.toString());
		} catch (Exception e) {
			LogUtils.LOGE(TAG, "LoginResult Error: " + e.getMessage());
		}
		return result;
	}

	public static AreaResult getAreas(Context context, String... areaParams) {
		AreaResult result = null;

		WebService webService = new WebService(BASE_URL + "all_areas.json");

		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("auth_key", areaParams[0]));

		String response = webService.webPost("", params);

		if ( response != null ) {
			LogUtils.LOGE("LoginWebService", response.toString());
		}

		try {
			result = new Gson().fromJson(response, AreaResult.class);
			LogUtils.LOGE("LoginWebService", result.toString());
		} catch (Exception e) {
			LogUtils.LOGE("LoginWebService", "LoginResult Error: " + e.getMessage());
		}

		return result;
	}

	public static BuddyResult getBuddies(Context context, String... areaParams) {
		BuddyResult result = null;

		WebService webService = new WebService(BASE_URL + "get_buddies.json");

		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("auth_key", areaParams[0]));
		params.add(new BasicNameValuePair("id", areaParams[1]));


		String response = webService.webPost("", params);

		if ( response != null ) {
			LogUtils.LOGE("LoginWebService", response.toString());
		}

		try {
			result = new Gson().fromJson(response, BuddyResult.class);
			LogUtils.LOGE("LoginWebService", result.toString());
		} catch (Exception e) {
			LogUtils.LOGE("LoginWebService", "LoginResult Error: " + e.getMessage());
		}

		return result;
	}

	public static String sendMessage(SendMessageActivity context, String... areaParams) {

		WebService webService = new WebService(BASE_URL + "send_message.json");

		List<NameValuePair> params = new ArrayList<NameValuePair>(3);
		params.add(new BasicNameValuePair("auth_key", areaParams[0]));
		params.add(new BasicNameValuePair("message", areaParams[1]));
		params.add(new BasicNameValuePair("users", areaParams[2]));

		String response = webService.webPost("", params);
		
		return response;
	}

	public static String myMessages(MyMessageActivity context, String... areaParams) {

		WebService webService = new WebService(BASE_URL + "my_messages.json");

		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("auth_key", "rrzncptmpgdxbnw"));//areaParams[0]));

		String response = webService.webPost("", params);

		/*if ( response != null ) {
			LogUtils.LOGE("LoginWebService", response.toString());
		}*/

		return response;
	}
	
	public static CourtResult getCourts(Context context, String... areaParams) {
		CourtResult result = null;

		WebService webService = new WebService(BASE_URL + "get_courts.json");

		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("auth_key", areaParams[0]));


		String response = webService.webPost("", params);

		if ( response != null ) {
			LogUtils.LOGE("LoginWebService", response.toString());
		}

		try {
			result = new Gson().fromJson(response, CourtResult.class);
			LogUtils.LOGE("LoginWebService", result.toString());
		} catch (Exception e) {
			LogUtils.LOGE("LoginWebService", "LoginResult Error: " + e.getMessage());
		}

		return result;
	}

}