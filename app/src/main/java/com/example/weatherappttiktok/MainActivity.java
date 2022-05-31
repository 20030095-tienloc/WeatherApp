package com.example.weatherappttiktok;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.weatherappttiktok.Adapter.ForecastAdapter;
import com.example.weatherappttiktok.ApiService.ApiService;
import com.example.weatherappttiktok.Model.Forecast.AreaMetadata;
import com.example.weatherappttiktok.Model.Forecast.Forecast;
import com.example.weatherappttiktok.Model.Forecast.MyForecast;
import com.example.weatherappttiktok.Utils.MyUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LocationListener{

    RecyclerView recyclerView;
    ForecastAdapter forecastAdapter;
    TextView mainArea;
    TextView mainWeather;
    ImageView mainIcon;
    LocationManager locationManager;
    SwipeRefreshLayout swipeRefreshLayout;
    SearchView searchView;

    double myLat = 0;
    double myLong = 0;

    List<Forecast> forecasts;
    List<AreaMetadata> areaMetadata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        mainArea = findViewById(R.id.mainArea);
        mainWeather = findViewById(R.id.mainWeather);
        mainIcon = findViewById(R.id.mainIcon);
        recyclerView = findViewById(R.id.recView);

        swipeRefreshLayout = findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            getLocation();
            callApi();
            swipeRefreshLayout.setRefreshing(false);
        });

        searchView = findViewById(R.id.edtSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                forecastAdapter.getFilter().filter(text);
                return false;
            }

        });

        getLocation();
        callApi();

    }

    private void loadData(List<Forecast> apiForecasts, List<AreaMetadata> apiAreaMetadata){
        forecasts = apiForecasts;
        areaMetadata = apiAreaMetadata;
    }

    public void callApi() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date today = Calendar.getInstance().getTime();
        String currentTime = dateFormat.format(today);
        Log.d("today", currentTime + "");
        ApiService.apiService.getForecast(currentTime).enqueue(new Callback<MyForecast>() {
            @Override
            public void onResponse(@NonNull Call<MyForecast> call, @NonNull Response<MyForecast> response) {
                List<Forecast> forecasts = null;
                if (response.body() != null) {
                    forecasts = response.body().getItems().get(0).getForecasts();
                }
                List<AreaMetadata> areaMetadata = null;
                if (response.body() != null) {
                    areaMetadata = response.body().getArea_metadata();
                }
                loadData(forecasts, areaMetadata);
                setUpRecView(forecasts);
                setMainWeatherInfo();
                Log.d("apiCall", "Success");
            }

            @Override
            public void onFailure(@NonNull Call<MyForecast> call, @NonNull Throwable t) {
                Log.d("apiCall", "Fail");
            }
        });
    }

    private void setUpRecView(List<Forecast> forecasts){
        forecastAdapter = new ForecastAdapter(this, (pos, forecastList) -> {
            String mainAreaName;
            String mainAreaForecast;

            mainAreaName = forecasts.get(pos).getArea();
            mainAreaForecast = forecasts.get(pos).getForecast();

            mainArea.setText(mainAreaName);
            mainWeather.setText(mainAreaForecast);
            mainIcon.setImageResource(MyUtils.getImg(mainAreaForecast));

        }, forecasts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(forecastAdapter);
    }

    public void setMainWeatherInfo(){
        double nearest = calDistance(myLat, myLong,
                areaMetadata.get(0).getLabel_location().getLatitude(),
                areaMetadata.get(0).getLabel_location().getLongitude());
        int nearestIndex = 0;

        for (int i = 0; i < areaMetadata.size(); i++){

            if(calDistance(myLat, myLong,
                    areaMetadata.get(i).getLabel_location().getLatitude(),
                    areaMetadata.get(i).getLabel_location().getLongitude()) < nearest){

                nearest = calDistance(myLat, myLong,
                        areaMetadata.get(i).getLabel_location().getLatitude(),
                        areaMetadata.get(i).getLabel_location().getLongitude());
                nearestIndex = i;
            }

        }

        String mainAreaName = forecasts.get(nearestIndex).getArea();
        String mainAreaForecast = forecasts.get(nearestIndex).getForecast();

        mainArea.setText(mainAreaName);
        mainWeather.setText(mainAreaForecast);
        mainIcon.setImageResource(MyUtils.getImg(mainAreaForecast));
    }


    public double calDistance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 6371;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return earthRadius * c;
    }

    @SuppressLint("MissingPermission")
    private void getLocation(){
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, MainActivity.this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, MainActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        myLat = location.getLatitude();
        myLong = location.getLongitude();
    }


}