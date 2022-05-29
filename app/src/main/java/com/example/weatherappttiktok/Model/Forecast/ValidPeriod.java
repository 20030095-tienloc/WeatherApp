package com.example.weatherappttiktok.Model.Forecast;

public class ValidPeriod {

    private String end;
    private String start;

    public ValidPeriod(String end, String start) {
        this.end = end;
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }
}
