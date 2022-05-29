package com.example.weatherappttiktok.Utils;

import com.example.weatherappttiktok.R;

public class MyUtils {

    public static int getImg(String forecast){
        int imgRes = R.drawable.ic_partly_cloudy_night;
        switch (forecast) {
            case "Partly Cloudy (Night)":
                imgRes = R.drawable.ic_partly_cloudy_night;
                break;
            case "Partly Cloudy (Day)":
                imgRes = R.drawable.ic_partly_cloudy_day;
                break;
            case "Cloudy":
                imgRes = R.drawable.ic_cloudy;
                break;
            case "Showers":
                imgRes = R.drawable.ic_shower;
                break;
            case "Light Showers":
            case "Light Rain":
                imgRes = R.drawable.ic_light_rain;
                break;
            case "Moderate Rain":
                imgRes = R.drawable.ic_rainy;
                break;
            case "Thundery Showers":
                imgRes = R.drawable.ic_thundery_shower;
                break;
            case "Heavy Thundery Showers":
            case "Heavy Thundery Showers with Gusty Winds":
                imgRes = R.drawable.ic_thunderstorm;
                break;
            case "Fair (Night)":
                imgRes = R.drawable.ic_night;
                break;
            case "Fair (Day)":
                imgRes = R.drawable.ic_sunny;
                break;
            case "Fair & Warm":
                imgRes = R.drawable.ic_fair_warm;
        }

        return imgRes;
    }


}
