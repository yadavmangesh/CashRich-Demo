package com.example.cashrichdemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit=null;

    static Retrofit getClient(){

        retrofit=new Retrofit.Builder()
                .baseUrl("http://demo0312305.mockable.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit;
    }
}
