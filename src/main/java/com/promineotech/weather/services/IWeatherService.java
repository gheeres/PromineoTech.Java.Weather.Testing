package com.promineotech.weather.services;

import com.promineotech.weather.models.WeatherModel;

/**
 * Interface for getting current weather.
 */
public interface IWeatherService {
  /**
   * Retrieves the current weather for the specified location.
   * @param latitude The geographic latitude
   * @param longitude The geographic longitude
   * @return The instance of WeatherModel if successful, otherwise null.
   */
  WeatherModel get(float latitude, float longitude);
}
