package com.example.jq.setupapirequest.helpers;

import com.example.jq.setupapirequest.interfaces.ApiInterface;

import retrofit2.Retrofit;

/**
 * Created by ctmanalo on 9/14/16.
 */
public class AppConstants {

    public static final String BASE_URL = "http://192.168.0.187:8080";

    public static Retrofit RETROFIT = null;
    public static ApiInterface API_INTERFACE = null;
}
