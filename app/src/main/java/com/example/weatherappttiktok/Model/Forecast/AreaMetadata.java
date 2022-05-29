package com.example.weatherappttiktok.Model.Forecast;

public class AreaMetadata {

    private LabelLocation label_location;
    private String name;

    public AreaMetadata(LabelLocation label_location, String name) {
        this.label_location = label_location;
        this.name = name;
    }

    public LabelLocation getLabel_location() {
        return label_location;
    }

    public String getName() {
        return name;
    }
}
