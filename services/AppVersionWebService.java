package com.checkinlibrary.ws.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.checkinlibrary.CheckinLibraryActivity;
import com.checkinlibrary.helpers.LogUtils;
import com.checkinlibrary.models.AppVersionResult;
import com.google.myjson.Gson;

public class AppVersionWebService implements WebServiceIface {

    public static AppVersionResult setAppVersion(CheckinLibraryActivity context, String[] appVersion) {

        AppVersionResult result = null;
        WebService webService = new WebService(BASE_URL + "/mobile_user/app_versions.json?");
        List<NameValuePair> params = new ArrayList<NameValuePair>(1);
        params.add(new BasicNameValuePair("app_id", appVersion[0]));
        params.add(new BasicNameValuePair("api_key", CheckinLibraryActivity.mAuthToken));
        String response = webService.webPost("", params);
        if ( response != null || response == "{}" ) {
        	LogUtils.LOGE("CHECKINFORGOOD", response.toString());

            try {
                result = new Gson().fromJson(response, AppVersionResult.class);
                LogUtils.LOGE("CHECKINFORGOOD", result.toString());
            } catch (Exception e) {
            	LogUtils.LOGE("CHECKINFORGOOD", "LoginResult Error: " + e.getMessage());
            }
        }
        return result;

    }

}
