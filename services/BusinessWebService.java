package com.checkinlibrary.ws.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.content.Context;
import android.location.Location;
import com.checkinlibrary.CheckinLibraryActivity;
import com.checkinlibrary.helpers.LogUtils;
import com.checkinlibrary.models.BusinessResult;
import com.google.myjson.Gson;
import com.google.myjson.reflect.TypeToken;

public class BusinessWebService implements WebServiceIface {

    public static List<BusinessResult> getNearby(Context context, Double[] location_params, Boolean isSupported, int page) {

        List<BusinessResult> res = null;
        WebService webService = new WebService(BASE_URL + "/mobile_user/v2/businesses/get_businesses.json");

        List<NameValuePair> params = new ArrayList<NameValuePair>(6);
        params.add(new BasicNameValuePair("latitude", Double.toString(location_params[0])));
        params.add(new BasicNameValuePair("longitude", Double.toString(location_params[1])));
        params.add(new BasicNameValuePair("api_key", CheckinLibraryActivity.mAuthToken));
        params.add(new BasicNameValuePair("is_supported", isSupported.toString()));
        params.add(new BasicNameValuePair("page", String.valueOf(page)));
        params.add(new BasicNameValuePair("app_id", CheckinLibraryActivity.APP_NAME));
        LogUtils.LOGE("CHECKINFORGOOD -- Base url", BASE_URL + "app -" + CheckinLibraryActivity.APP_NAME + "auth -" + CheckinLibraryActivity.mAuthToken);
        String response = webService.webPost("", params);
        if ( response != null )
        	//LogUtils.LOGE("CHECKINFORGOOD", "Business Result for lat " + location_params[0] + " lon " + location_params[1] + ": " + response.toString());

        try {
            java.lang.reflect.Type typeOfListOfBusiness = (new TypeToken<List<BusinessResult>>() {
            }).getType();

            res = new Gson().fromJson(response, (java.lang.reflect.Type) typeOfListOfBusiness);

           // LogUtils.LOGE("CHECKINFORGOOD", res.toString());
        } catch (Exception e) {
        	LogUtils.LOGE("CHECKINFORGOOD", "BusinessResult Error: " + e.getMessage());
        }

        return res;
    }

    public static List<BusinessResult> search(CharSequence searchText, Location location,int page) {
        Double[] latLon;

        if ( location == null ) {
            // Default to center of US
            latLon = new Double[] { 39.833, -98.583 };
        } else {
            latLon = new Double[] { location.getLatitude(), location.getLongitude() };
        }

        List<BusinessResult> res = null;
        WebService webService = new WebService(BASE_URL + "/mobile_user/v2/businesses/search_bus.json");

        List<NameValuePair> params = new ArrayList<NameValuePair>(5);
        params.add(new BasicNameValuePair("api_key", CheckinLibraryActivity.mAuthToken));
        params.add(new BasicNameValuePair("search_text", searchText.toString()));
        params.add(new BasicNameValuePair("latitude", Double.toString(latLon[0])));
        params.add(new BasicNameValuePair("longitude", Double.toString(latLon[1])));
        params.add(new BasicNameValuePair("page", String.valueOf(page)));
        params.add(new BasicNameValuePair("app_id", CheckinLibraryActivity.APP_NAME));

        String response = webService.webPost("", params);

        if ( response == null ) {
        	LogUtils.LOGV("CHECKINFORGOOD", "Null response from business search web service");
        } else {
        	LogUtils.LOGV("CHECKINFORGOOD", "Business search response: " + response.toString());
        }

        try {
            java.lang.reflect.Type typeOfListOfBusinessResult = (new TypeToken<List<BusinessResult>>() {
            }).getType();
            res = new Gson().fromJson(response, (java.lang.reflect.Type) typeOfListOfBusinessResult);
        } catch (Exception e) {
        	LogUtils.LOGV("CHECKINFORGOOD", "Business search error: " + e.getMessage());
        }

        return res;
    }
}