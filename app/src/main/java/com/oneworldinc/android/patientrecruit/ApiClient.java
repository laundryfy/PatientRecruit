package com.oneworldinc.android.patientrecruit;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by sabari on 4/15/2015.
 */
public class ApiClient {
    private static final String BASE_API_URL = "http://oneworldapp.elasticbeanstalk.com/api/";
    //private static final String BASE_API_URL = "http://192.168.1.16:59260/api/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_API_URL + relativeUrl;
    }
}
