package com.promineotech.weather.exceptions;

/**
 * Base exception used for weather related errors.
 * All application generated exceptions should 
 * inherit from this base class.
 */
public class WeatherException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public WeatherException() {
  }

  public WeatherException(String message) {
    super(message);
  }

  public WeatherException(Throwable cause) {
    super(cause);
  }

  public WeatherException(String message, Throwable cause) {
    super(message, cause);
  }

  public WeatherException(String message, Throwable cause, boolean enableSuppression,
                          boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
