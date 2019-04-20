package com.example.cashrichdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiInterface {

     @GET("/testCashRich")
    Call<List<SipData>> getSipData();
}
