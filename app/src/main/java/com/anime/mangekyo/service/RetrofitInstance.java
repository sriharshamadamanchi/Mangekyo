package com.anime.mangekyo.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private final static String BASE_URL = "https://api.consumet.org/meta/";
    private static Retrofit retrofitInstance = null;

    public static ApiInterface getRetrofitInstance(){
        if(retrofitInstance == null){
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitInstance.create(ApiInterface.class);
    }
}
