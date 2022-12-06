package com.promineotech.weather.models;

/**
 * Represents the temperature and provides built-in
 * conversion methods for converting to/from Celsius
 * and Kelvin.
 */
public class TemperatureModel {
  public static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67; 
  public static final double KELVIN_TO_CELSIUS_CONVERSION = 273.15; 
  private double fahrenheit;
  
  /**
   * Creates an instance of the TemperatureModel class.
   * @param fahrenheit The temperature in Fahrenheit.
   */
  public TemperatureModel(double fahrenheit) {
    //TODO
    throw new UnsupportedOperationException();
  }
  
  /**
   * Retrieves the temperature value in Fahrenheit.
   * @return The temperature
   */  
  public double getFahrenheit() {
    //TODO
    throw new UnsupportedOperationException();
  }
}
