package com.promineotech.weather.models;

import com.promineotech.weather.exceptions.MinimumTemperatureExceededException;

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
    setFahrenheit(fahrenheit);
  }

  /**
   * Retrieves the temperature value in Fahrenheit.
   * @return The temperature
   */
  public double getFahrenheit() {
    return fahrenheit;
  }
  
  /**
   * Retrieves the temperature value in Fahrenheit.
   * @return The temperature
   */
  public TemperatureModel setFahrenheit(double fahrenheit) {
    // prevent a value less than 
    if (fahrenheit < ABSOLUTE_ZERO_FAHRENHEIT) {
      throw new MinimumTemperatureExceededException(fahrenheit,
           String.format("Temperature (%3.2f) exceeds absolute zero. "
                       + "Value must be greater than %3.2f",
                         fahrenheit, ABSOLUTE_ZERO_FAHRENHEIT));
    }
    this.fahrenheit = fahrenheit;
    return this;
  }  

  /**
   * Converts the specified temperature value to Celsius.
   * @return The value in Celsius.
   */
  public double getCelsius() {
    return toCelsius(getFahrenheit());
  }

  /**
   * Converts the specified temperature value to Celsius.
   * @return The value in Celsius.
   */
  public static double toCelsius(double fahrenheit) {
    return (fahrenheit - 32.0) * (5.0/9.0);
  }
  
  /**
   * Converts the specified temperature value to Kelvin.
   * @return The value in Kelvin.
   */
  public double getKelvin() {
    return toKelvin(getFahrenheit());
  }
  
  /**
   * Converts the specified temperature value to Celsius.
   * @return The value in Celsius.
   */
  public static double toKelvin(double fahrenheit) {
    return toCelsius(fahrenheit) + KELVIN_TO_CELSIUS_CONVERSION;
  }

  /**
   * Creates an instance of TemperatureModel from a Kelvin value.
   * @param kelvin The temperature in Kelvin
   * @return The instance.
   */
  public static TemperatureModel fromKelvin(double kelvin) {
    return fromCelsius(kelvin - KELVIN_TO_CELSIUS_CONVERSION);
  }

  /**
   * Creates an instance of TemperatureModel from a Celsius value.
   * @param celsius The temperature in Celsius
   * @return The instance.
   */
  public static TemperatureModel fromCelsius(double celsius) {
    return new TemperatureModel(celsius * (9.0/5.0) + 32);
  }
  
  /**
   * Returns the string representation of the instance.
   */
  @Override 
  public String toString() {
    return String.format("%3.2f°F (%3.2f°C)",
                         getFahrenheit(), getCelsius());
  }
}
