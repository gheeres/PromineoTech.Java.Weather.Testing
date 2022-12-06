package com.promineotech.weather.services;

import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.Date;
import org.json.JSONObject;
import com.promineotech.weather.models.TemperatureModel;
import com.promineotech.weather.models.WeatherModel;

/**
 * Implementation of WeatherService using VisualCrossing weather services.
 * https://www.visualcrossing.com/weather/weather-data-services
 */
public class VisualCrossingWeatherService extends HttpWeatherService 
                                          implements IWeatherService {
  private final String defaultBaseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
  private final String apiKey;
  private final String baseUrl;
  
  public VisualCrossingWeatherService() {
    this(null);
  }
  
  public VisualCrossingWeatherService(String apiKey) {
    baseUrl = getProperties().getProperty("visualcrossing.api.url", defaultBaseUrl);
    this.apiKey = (apiKey == null) 
      ? getProperties().getProperty("visualcrossing.api.key")
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
       "queryCost": 1,
       "latitude": 44.5236,
       "longitude": -89.5746,
       "resolvedAddress": "44.5236,-89.5746",
       "address": "44.5236,-89.5746",
       "timezone": "America/Chicago",
       "tzoffset": -5,
       "days": [
        {
         "datetime": "2022-09-16",
         "datetimeEpoch": 1663304400,
         "tempmax": 25.4,
         "tempmin": 15.8,
         "temp": 20.7,
         "feelslikemax": 25.4,
         "feelslikemin": 15.8,
         "feelslike": 20.7,
         "dew": 15.7,
         "humidity": 74.6,
         "precip": 0,
         "precipprob": 28.6,
         "precipcover": 0,
         "preciptype": [
          "rain"
         ],
         "snow": 0,
         "snowdepth": 0,
         "windgust": 31,
         "windspeed": 16.9,
         "winddir": 173.2,
         "pressure": 1016.8,
         "cloudcover": 50.1,
         "visibility": 20.5,
         "solarradiation": 155.8,
         "solarenergy": 13.5,
         "uvindex": 6,
         "severerisk": 10,
         "sunrise": "06:38:25",
         "sunriseEpoch": 1663328305,
         "sunset": "19:07:02",
         "sunsetEpoch": 1663373222,
         "moonphase": 0.69,
         "conditions": "Partially cloudy",
         "description": "Becoming cloudy in the afternoon.",
         "icon": "partly-cloudy-day",
         "stations": [
          "KISW",
          "KCWA",
          "MNEW3",
          "KMFI",
          "KSTE",
          "KY50",
          "KAUW",
          "KPCZ"
         ],
         "source": "comb"
        }
       ],
       "stations": {
        "KISW": {
         "distance": 27868,
         "latitude": 44.36,
         "longitude": -89.84,
         "useCount": 0,
         "id": "KISW",
         "name": "KISW",
         "quality": 100,
         "contribution": 0
        },
        "KCWA": {
         "distance": 29525,
         "latitude": 44.78,
         "longitude": -89.67,
         "useCount": 0,
         "id": "KCWA",
         "name": "KCWA",
         "quality": 73,
         "contribution": 0
        },
        "MNEW3": {
         "distance": 30100,
         "latitude": 44.697,
         "longitude": -89.866,
         "useCount": 0,
         "id": "MNEW3",
         "name": "MEAD WI US",
         "quality": 0,
         "contribution": 0
        },
        "AU530": {
         "distance": 3224,
         "latitude": 44.496,
         "longitude": -89.562,
         "useCount": 0,
         "id": "AU530",
         "name": "KB9TTQ-14 Whiting WI US",
         "quality": 0,
         "contribution": 0
        },
        "KMFI": {
         "distance": 49444,
         "latitude": 44.63,
         "longitude": -90.18,
         "useCount": 0,
         "id": "KMFI",
         "name": "KMFI",
         "quality": 99,
         "contribution": 0
        },
        "KSTE": {
         "distance": 4600,
         "latitude": 44.55,
         "longitude": -89.53,
         "useCount": 0,
         "id": "KSTE",
         "name": "KSTE",
         "quality": 99,
         "contribution": 0
        },
        "KY50": {
         "distance": 58112,
         "latitude": 44.04,
         "longitude": -89.3,
         "useCount": 0,
         "id": "KY50",
         "name": "WAUTOMA/WAUTOMA MUN, WI",
         "quality": 100,
         "contribution": 0
        },
        "KAUW": {
         "distance": 45452,
         "latitude": 44.93,
         "longitude": -89.63,
         "useCount": 0,
         "id": "KAUW",
         "name": "KAUW",
         "quality": 99,
         "contribution": 0
        },
        "KPCZ": {
         "distance": 49075,
         "latitude": 44.33,
         "longitude": -89.02,
         "useCount": 0,
         "id": "KPCZ",
         "name": "KPCZ",
         "quality": 98,
         "contribution": 0
        }
       },
       "currentConditions": {
        "datetime": "11:29:29",
        "datetimeEpoch": 1663345769,
        "temp": 17,
        "feelslike": 17,
        "humidity": 18.9,
        "dew": -6.9,
        "precip": 0,
        "precipprob": null,
        "snow": 0,
        "snowdepth": 0,
        "preciptype": null,
        "windgust": null,
        "windspeed": 11.3,
        "winddir": 180,
        "pressure": 1018,
        "visibility": 16,
        "cloudcover": 0,
        "solarradiation": 573,
        "solarenergy": 2.1,
        "uvindex": 6,
        "conditions": "Clear",
        "icon": "clear-day",
        "stations": [
         "KISW",
         "AU530",
         "KSTE"
        ],
        "sunrise": "06:38:25",
        "sunriseEpoch": 1663328305,
        "sunset": "19:07:02",
        "sunsetEpoch": 1663373222,
        "moonphase": 0.69
       }
      }  
     */
    JSONObject obj = new org.json.JSONObject(json);
    JSONObject currentConditions = obj.getJSONObject("currentConditions");
    return new WeatherModel()
        .setLatitude(obj.getFloat("latitude"))
        .setLongitude(obj.getFloat("longitude"))
        .setDate(Date.from(Instant.ofEpochSecond(currentConditions.getLong("datetimeEpoch"))))
        .setDescription(currentConditions.getString("conditions"))
        .setTemperature(TemperatureModel.fromCelsius(currentConditions.getFloat("temp")))
        .setFeelsLike(TemperatureModel.fromCelsius(currentConditions.getFloat("feelslike")))
        .setWindSpeed(currentConditions.getFloat("windspeed"))
        .setWindDirection(currentConditions.getFloat("winddir"));
  }
  
  @Override
  public WeatherModel get(float latitude, float longitude) {
    String url = String.format("%s%f%%2C%f?contentType=json&include=current&unitGroup=metric&key=%s", 
                               baseUrl, latitude, longitude, apiKey);
    HttpResponse<String> response = getHTTPResponse(url);
    if (response.statusCode() == 200) {
      return toWeatherModel(response.body());
    }
    return null;
  }
}
