package com.example.weatherappttiktok.Model.Forecast;

public class LabelLocation {

    private Double latitude;
    private Double longitude;

    public LabelLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
