package com.promineotech.weather.exceptions;

/**
 * Occurs when an temperature value exceeds the minimum
 * value based on physics (i.e. Absolute Zero)
 */
public class MinimumTemperatureExceededException extends WeatherException {
  private static final long serialVersionUID = 1L;

  private double temperature;
  
  public MinimumTemperatureExceededException(double temperature) {
    this(temperature, 
         String.format("The specified temperature is invalid. Temperature: %3.2f", temperature));
  }

  public MinimumTemperatureExceededException(double temperature, String message) {
    super(message);
    this.temperature = temperature;
  }

  public MinimumTemperatureExceededException(double temperature, Throwable cause) {
    this(temperature, 
         String.format("The specified temperature is invalid. Temperature: %3.2f", temperature), 
         cause);
  }

  public MinimumTemperatureExceededException(double temperature, String message, Throwable cause) {
    super(message, cause);
    this.temperature = temperature;
  }

  public double getTemperature() {
    return temperature;
  }
}
