package com.checkinlibrary.ws.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.checkinlibrary.helpers.LogUtils;
import com.checkinlibrary.models.CauseProfileResult;
import com.google.myjson.Gson;

public class CauseProfileWebService implements WebServiceIface {
    public static CauseProfileResult getCauseProfile(Context context, String[] allparams) {
        CauseProfileResult result = null;
        LogUtils.LOGE("CHECKINFORGOOD", "Network available: " + Boolean.toString(isNetworkAvailable(context)));
        WebService webService = new WebService(BASE_URL + "/mobile_user/cause_profile.json");

        List<NameValuePair> params = new ArrayList<NameValuePair>(2);

        params.add(new BasicNameValuePair("api_key", allparams[0]));
        params.add(new BasicNameValuePair("organization_id", allparams[1]));

        String response = webService.webPost("", params);
        if ( response != null ) {
        	LogUtils.LOGE("CHECKINFORGOOD", response);
            try {
                result = new Gson().fromJson(response, CauseProfileResult.class);
                LogUtils.LOGE("CHECKINFORGOOD", result.toString());
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	LogUtils.LOGE("CHECKINFORGOOD", "DoCheckinResult Error: " + e.getMessage());
            }
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