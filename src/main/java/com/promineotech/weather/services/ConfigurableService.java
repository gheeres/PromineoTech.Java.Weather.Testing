package com.promineotech.weather.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ConfigurableService {
  protected static Properties properties;
  
  static {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    properties = new Properties();
    try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
      properties.load(resourceStream);
    } catch (IOException e) {
    }
  }
  
  public Properties getProperties() {
    return properties;
  }
}