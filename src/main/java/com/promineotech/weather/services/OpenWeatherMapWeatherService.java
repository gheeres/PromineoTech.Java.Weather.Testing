package com.promineotech.weather.services;

import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import com.promineotech.weather.models.TemperatureModel;
import com.promineotech.weather.models.WeatherModel;

public class OpenWeatherMapWeatherService extends HttpWeatherService
                                          implements IWeatherService {
  private final String defaultBaseUrl = "https://api.openweathermap.org/data/2.5/weather";
  private final String apiKey;
  private final String baseUrl;
  
  public OpenWeatherMapWeatherService() {
    this(null);
  }
  
  public OpenWeatherMapWeatherService(String apiKey) {
    baseUrl = getProperties().getProperty("openweathermap.api.url", defaultBaseUrl);
    this.apiKey = (apiKey == null) 
      ? getProperties().getProperty("openweathermap.api.key")
      : apiKey;
  }

  /**
   * Creates an instance of the WeatherModel class from the specified JSON value.
   * @param json The JSON to parse.
   * @return The WeatherModel instance.
   */
  public static WeatherModel toWeatherModel(String json) {
    if ((json == null) || (json.isEmpty())) {
      return null;
    }
    
    // Sample data
    /*
      {
        "coord": {
          "lon": 10.99,
          "lat": 44.34
        },
        "weather": [
          {
            "id": 501,
            "main": "Rain",
            "description": "moderate rain",
            "icon": "10d"
          }
        ],
        "base": "stations",
        "main": {
          "temp": 298.48,
          "feels_like": 298.74,
          "temp_min": 297.56,
          "temp_max": 300.05,
          "pressure": 1015,
          "humidity": 64,
          "sea_level": 1015,
          "grnd_level": 933
        },
        "visibility": 10000,
        "wind": {
          "speed": 0.62,
          "deg": 349,
          "gust": 1.18
        },
        "rain": {
          "1h": 3.16
        },
        "clouds": {
          "all": 100
        },
        "dt": 1661870592,
        "sys": {
          "type": 2,
          "id": 2075663,
          "country": "IT",
          "sunrise": 1661834187,
          "sunset": 1661882248
        },
        "timezone": 7200,
        "id": 3163858,
        "name": "Zocca",
        "cod": 200
      }   
     */
    JSONObject obj = new org.json.JSONObject(json);
    JSONObject coord = obj.getJSONObject("coord");
    JSONArray weather = obj.getJSONArray("weather");
    JSONObject wind = obj.getJSONObject("wind");
    JSONObject main = obj.getJSONObject("main");
    return new WeatherModel()
        .setLatitude(coord.getFloat("lat"))
        .setLongitude(coord.getFloat("lon"))
        .setDate(Date.from(Instant.ofEpochSecond(obj.getInt("dt"))))
        .setDescription(weather.getJSONObject(0).getString("description"))
        .setTemperature(TemperatureModel.fromKelvin(main.getFloat("temp")))
        .setFeelsLike(TemperatureModel.fromKelvin(main.getFloat("feels_like")))
        .setWindSpeed(wind.getFloat("speed"))
        .setWindDirection(wind.getFloat("deg"));
  }

  public WeatherModel get(float latitude, float longitude) {
    String url = String.format("%s?lat=%f&lon=%f&appid=%s", 
                               baseUrl, latitude, longitude, apiKey);
    HttpResponse<String> response = getHTTPResponse(url);
    if (response.statusCode() == 200) {
      return toWeatherModel(response.body());
    }
    return null;
  }
}
