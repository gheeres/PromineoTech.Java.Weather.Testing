package com.promineotech.weather.models;

import java.util.Date;

/**
 * The weather information for a point in time 
 * at a specified location (latitude/longitude).
 */
public class WeatherModel {
  private Date date;
  private double latitude;
  private double longitude;
  private TemperatureModel temperature;
  private TemperatureModel feelsLike;
  private double windSpeed;
  private double windDirection;
  private String description;

  /**
   * Creates an instance of the WeatherModel class.
   */
  public WeatherModel() {
    date = new Date();
  }
  
  public double getLatitude() {
    return latitude;
  }
  public WeatherModel setLatitude(float latitude) {
    this.latitude = latitude;
    return this;
  }
  
  public double getLongitude() {
    return longitude;
  }
  public WeatherModel setLongitude(double longitude) {
    this.longitude = longitude;
    return this;
  }
  
  public Date getDate() {
    return date;
  }
  public WeatherModel setDate(Date date) {
    this.date = date;
    return this;
  }
  
  public TemperatureModel getTemperature() {
    return temperature;
  }
  public WeatherModel setTemperature(TemperatureModel temperature) {
    this.temperature = temperature;
    return this;
  }
  public WeatherModel setTemperature(double fahrenheit) {
    this.temperature = new TemperatureModel(fahrenheit);
    return this;
  }
  
  public TemperatureModel getFeelsLike() {
    return feelsLike;
  }
  public WeatherModel setFeelsLike(TemperatureModel feelsLike) {
    this.feelsLike = feelsLike;
    return this;
  }
  public WeatherModel setFeelsLike(double feelsLike) {
    this.feelsLike = new TemperatureModel(feelsLike);
    return this;
  }
  
  public double getWindSpeed() {
    return windSpeed;
  }
  public WeatherModel setWindSpeed(double windSpeed) {
    this.windSpeed = windSpeed;
    return this;
  }

  public double getWindDirection() {
    return windDirection;
  }
  public WeatherModel setWindDirection(double windDirection) {
    this.windDirection = windDirection;
    return this;
  }

  public String getDescription() {
    return description;
  }
  public WeatherModel setDescription(String description) {
    this.description = description;
    return this;
  }
}
