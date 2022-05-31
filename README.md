# WeatherApp
RETRIEVE UP TO DATE WEATHER FORECASTS OF DIFFERENT AREAS OF SINGAPORE

FRAMEWORKS & LIBRARIES:
  - ANDROID SDK VER 31 (MINIMALLY RUNABLE ON ANDROID PHONES RUNNING ANDROID SDK VER 26)
  - GRADLE VER 7.0.2

EXTERNAL FRAMEWORKS & LIBRARIES:
  - RETROFIT VER 2.9.0
  - GOOGLE GSON VER 2.9.0 
  - ANDROID SWIPEREFRESHLAYOUT VER 1.1.0

LANGUAGE
  - JAVA

ANDROID PERMISSIONS CONFIGURED
  - USER-PERMISSION android.permission.ACCESS_COARSE_LOCATION
  - USER-PERMISSION android.permission.INTERNET

API:
https://api.data.gov.sg/
ENDPOINT:
v1/environment/2-hour-weather-forecast/

Highlight

Search Function using SearchView to filter the RecylcerView + RecyclerView OnClickListener
  The search function allows the user to filter out the desired location and display that location and its current weather forecast.
  The RecyclerView will be dynamically updated according to the search keyword.

Automatically Update Main Area and Forecast by Statically Listening to the GPS Location Changes (Triggered By OnSwipe Event)
		Getting approximate location by calculating the nearest distance from the latitude and longitude retrieved from GPS with the latitude and longitude retrieved from    the API call.  The main area and forecast will then be updated based on the area with the nearest distance.

