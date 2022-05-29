package com.example.weatherappttiktok.Adapter;

import com.example.weatherappttiktok.Model.Forecast.Forecast;

import java.util.List;

public interface MyClickListener {
    void onClickGetViewPos(int pos, List<Forecast> filteredForecast);
}
