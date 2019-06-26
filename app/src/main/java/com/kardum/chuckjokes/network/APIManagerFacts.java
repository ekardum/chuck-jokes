package com.kardum.chuckjokes.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManagerFacts {
    public static final String kAPIBaseURL = "http://randomuselessfact.appspot.com/";

    private static APIManagerFacts _APIManager;
    private Retrofit _Retrofit;
    private APIRequestHelperFacts _APIHelper;

    private APIManagerFacts(){}

    public static synchronized APIManagerFacts APIManagerFacts(){
        if(_APIManager == null){
            _APIManager = new APIManagerFacts();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .addInterceptor(interceptor)
                    .build();
            _APIManager._Retrofit = new Retrofit.Builder()
                    .baseUrl(kAPIBaseURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            _APIManager._APIHelper = _APIManager._Retrofit.create(APIRequestHelperFacts.class);
        }
        return _APIManager;
    }

    public APIRequestHelperFacts getRequestHelper(){
        return _APIHelper;
    }

}
