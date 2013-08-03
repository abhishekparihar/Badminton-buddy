package com.weboapps.badmintonbuddy.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.myjson.Gson;
import com.weboapps.badmintonbuddy.helpers.LogUtils;
import com.weboapps.badmintonbuddy.models.LoginResult;

public class LoginWebService implements WebServiceIface {

    public static LoginResult login(Context context, String... login_params) {
        LoginResult result = null;

        LogUtils.LOGE("LoginWebService", "Network available: " + Boolean.toString(isNetworkAvailable(context)));
        WebService webService = new WebService(BASE_URL + "/mobile_user/sign_in.json");

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
}