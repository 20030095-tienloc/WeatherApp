package com.example.weatherappttiktok;

import org.junit.Test;

import static org.junit.Assert.*;

import android.os.Bundle;

import com.example.weatherappttiktok.Model.Forecast.Forecast;
import com.example.weatherappttiktok.Utils.MyUtils;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void imgResIs700066WhenForecastIsNotMatched() {

        //Arrange
       int imgRes = R.drawable.ic_partly_cloudy_night;
       String forecast = "Severe Wind";

        //Act
       int actualImgRes = MyUtils.getImg(forecast);

        //Assert
       assertTrue(!(actualImgRes != imgRes));
    }
}