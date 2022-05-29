package com.example.weatherappttiktok.Model.Forecast;

import java.util.List;

public class MyForecast {


    private ApiInfo api_info;
    private List<AreaMetadata> area_metadata;
    private List<Item> items;

    public ApiInfo getApi_info() {
        return api_info;
    }

    public List<AreaMetadata> getArea_metadata() {
        return area_metadata;
    }

    public List<Item> getItems() {
        return items;
    }

    public MyForecast(ApiInfo api_info, List<AreaMetadata> area_metadata, List<Item> items) {
        this.api_info = api_info;
        this.area_metadata = area_metadata;
        this.items = items;
    }
}
