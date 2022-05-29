package com.example.weatherappttiktok.ApiService;

import com.example.weatherappttiktok.Model.Forecast.MyForecast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-DD HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://api.data.gov.sg/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    @GET("v1/environment/2-hour-weather-forecast")
    Call<MyForecast> getForecast(@Query("date_time") String date_time);


}
