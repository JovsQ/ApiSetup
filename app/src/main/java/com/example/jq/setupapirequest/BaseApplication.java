package com.example.jq.setupapirequest;

import android.app.Application;

import com.example.jq.setupapirequest.helpers.AppConstants;
import com.example.jq.setupapirequest.interfaces.ApiInterface;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ctmanalo on 9/14/16.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if  (AppConstants.RETROFIT == null && AppConstants.API_INTERFACE == null) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir == null) {
                cacheDir = getCacheDir();
            }
            final Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            return chain.proceed(request);
                        }
                    })
                    .cache(cache)
                    .build();

            /** initialize retrofit */
            AppConstants.RETROFIT = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            /** initialize retrofit interface */
            AppConstants.API_INTERFACE = AppConstants.RETROFIT.create(ApiInterface.class);
        }
    }
}
