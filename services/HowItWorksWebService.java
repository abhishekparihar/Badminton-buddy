package com.checkinlibrary.ws.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.checkinlibrary.helpers.LogUtils;
import com.checkinlibrary.models.VideoResult;
import com.google.myjson.Gson;

public class HowItWorksWebService implements WebServiceIface {

    public static VideoResult getVideoLinks(String[] allparams) {
        WebService webService = new WebService(BASE_URL + "/mobile_user/video_links.json");

        List<NameValuePair> params = new ArrayList<NameValuePair>(1);

        params.add(new BasicNameValuePair("api_key", allparams[0]));

        String response = webService.webPost("", params);
        VideoResult result = null;
        if ( response != null ) {
        	LogUtils.LOGE("CHECKINFORGOOD", response);
            try {
                result = new Gson().fromJson(response, VideoResult.class);
                LogUtils.LOGE("CHECKINFORGOOD", result.toString());
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	LogUtils.LOGE("CHECKINFORGOOD", "DoCheckinResult Error: " + e.getMessage());
            }
        }
        return result;
    }
}
