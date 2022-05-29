package com.example.weatherappttiktok.Model.Forecast;

import java.util.List;

public class Item {

    private List<Forecast> forecasts;
    private String timestamp;
    private String update_timestamp;
    private ValidPeriod valid_period;

    public Item(List<Forecast> forecasts, String timestamp, String update_timestamp, ValidPeriod valid_period) {
        this.forecasts = forecasts;
        this.timestamp = timestamp;
        this.update_timestamp = update_timestamp;
        this.valid_period = valid_period;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUpdate_timestamp() {
        return update_timestamp;
    }

    public ValidPeriod getValid_period() {
        return valid_period;
    }
}
