package com.example.weatherappttiktok.Model.Forecast;

public class Forecast {

    private String area;
    private String forecast;

    public Forecast(String area, String forecast) {
        this.area = area;
        this.forecast = forecast;
    }

    public String getArea() {
        return area;
    }

    public String getForecast() {
        return forecast;
    }
}
