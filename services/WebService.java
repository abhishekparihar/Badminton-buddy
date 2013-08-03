package com.checkinlibrary.ws.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.checkinlibrary.helpers.LogUtils;
import com.google.myjson.Gson;

public class WebService {
    DefaultHttpClient httpClient;
    HttpContext localContext;
    private String ret;

    HttpResponse response = null;
    HttpPost httpPost = null;
    HttpGet httpGet = null;
    String webServiceUrl;

    public WebService(String serviceName) {
        HttpParams myParams = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(myParams, 20000);
        HttpConnectionParams.setSoTimeout(myParams, 20000);
        httpClient = new DefaultHttpClient(myParams);
        localContext = new BasicHttpContext();
        webServiceUrl = serviceName;
    }

    public String webInvoke(String methodName, Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();

        for ( Map.Entry<String, Object> param : params.entrySet() ) {
            try {
                jsonObject.put(param.getKey(), param.getValue());
            } catch (JSONException e) {
            	LogUtils.LOGE("CHECKINFORGOOD", "JSONException : " + e);
            }
        }

        return webInvoke(methodName, jsonObject.toString(), "application/json");
    }

    private String webInvoke(String methodName, String data, String contentType) {
        ret = null;

        httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);
        httpPost = new HttpPost(webServiceUrl + methodName);
        response = null;
        StringEntity tmp = null;

        httpPost.setHeader("Accept", "text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");

        if ( contentType != null ) {
            httpPost.setHeader("Content-Type", contentType);
        } else {
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        }

        try {
            tmp = new StringEntity(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        	LogUtils.LOGE("CHECKINFORGOOD", "HttpUtils : UnsupportedEncodingException : " + e);
        }

        httpPost.setEntity(tmp);

        LogUtils.LOGD("CHECKINFORGOOD", webServiceUrl + "?" + data);

        try {
            response = httpClient.execute(httpPost, localContext);

            if ( response != null ) {
                ret = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
        	LogUtils.LOGE("CHECKINFORGOOD", "HttpUtils: " + e);
        }

        return ret;
    }

    public String webPost(String methodName, List<NameValuePair> params) {
        String postUrl = webServiceUrl + methodName;

        httpPost = new HttpPost(postUrl);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException uee) {
        	LogUtils.LOGE("CHECKINFORGOOD", uee.getMessage());
        }
        LogUtils.LOGE("WebGetURL: ", postUrl);

        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            if ( e.getMessage() != null ) {
            	LogUtils.LOGE("CHECKINFORGOOD", "httpClient.execute(httpPost) Exception: " + e.getMessage());
            } else {
            	LogUtils.LOGE("CHECKINFORGOOD", "httpClient.execute(httpPost) Exception: " + e.getClass().toString());
            }
        }

        try {
            ret = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            if ( e.getMessage() != null ) {
            	LogUtils.LOGE("CHECKINFORGOOD", e.getMessage());
            } else {
            	LogUtils.LOGE("CHECKINFORGOOD", e.getClass().toString());
            }
        }

        return ret;
    }

    public static JSONObject Object(Object o) {
        try {
            return new JSONObject(new Gson().toJson(o));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InputStream postHttpStream(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if ( !(conn instanceof HttpURLConnection) )
            throw new IOException("Not an HTTP connection");

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("POST");
            httpConn.connect();

            response = httpConn.getResponseCode();

            if ( response == HttpURLConnection.HTTP_OK ) {
                in = httpConn.getInputStream();
            }
        } catch (Exception e) {
            throw new IOException("Error connecting");
        }

        return in;
    }

    public void clearCookies() {
        httpClient.getCookieStore().clear();
    }

    public void abort() {
        try {
            if ( httpClient != null ) {
            	LogUtils.LOGE("CHECKINFORGOOD", "No http client.");
                httpPost.abort();
            }
        } catch (Exception e) {
        	LogUtils.LOGE("CHECKINFORGOOD", "CheckinForGood: " + e);
        }
    }

    public String webGET(String methodName, List<NameValuePair> nameValuePairs) {
        String result = null;
        if ( nameValuePairs != null && !nameValuePairs.isEmpty() ) {

            methodName += "?";

            Iterator<NameValuePair> iter = nameValuePairs.iterator();
            while ( iter.hasNext() ) {

                NameValuePair nvp = iter.next();
                methodName += nvp.getName();
                methodName += "=";
                methodName += nvp.getValue();
                if ( iter.hasNext() )
                    methodName += "&";

            }
        }

        LogUtils.LOGV("REstClient", "####url GET: " + methodName);
        HttpGet httpGet = new HttpGet(methodName);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        httpGet.setHeader("accept", "text/mobile");
        HttpClient httpclient = new DefaultHttpClient();
        try {
            result = httpclient.execute(httpGet, responseHandler);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public HttpResponse webGetHeader(String methodName, List<NameValuePair> params) {
        String postUrl = webServiceUrl + methodName;

        httpPost = new HttpPost(postUrl);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException uee) {
        	LogUtils.LOGE("CHECKINFORGOOD", uee.getMessage());
        }
        LogUtils.LOGE("WebGetURL: ", postUrl);

        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            if ( e.getMessage() != null ) {
            	LogUtils.LOGE("CHECKINFORGOOD", "httpClient.execute(httpPost) Exception: " + e.getMessage());
            } else {
            	LogUtils.LOGE("CHECKINFORGOOD", "httpClient.execute(httpPost) Exception: " + e.getClass().toString());
            }
        }
        return response;
    }

    public String getResponseString(HttpResponse response) {
        try {
            ret = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            if ( e.getMessage() != null ) {
            	LogUtils.LOGE("CHECKINFORGOOD", e.getMessage());
            } else {
            	LogUtils.LOGE("CHECKINFORGOOD", e.getClass().toString());
            }
        }
        return ret;
    }
}
