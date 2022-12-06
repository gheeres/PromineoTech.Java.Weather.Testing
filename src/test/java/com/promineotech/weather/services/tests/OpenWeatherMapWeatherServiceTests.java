package com.promineotech.weather.services.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.Instant;
import java.util.Date;
import com.promineotech.weather.models.WeatherModel;
import com.promineotech.weather.services.OpenWeatherMapWeatherService;

public class OpenWeatherMapWeatherServiceTests extends WeatherServiceTests {
  private OpenWeatherMapWeatherService service;
  
  @BeforeEach
  public void setUp() throws Exception {
    service = new OpenWeatherMapWeatherService();
  }

  @Test
  public void testToWeatherModelReturnsWeatherModel() {
    String json = "{\r\n"
        + "  \"coord\": {\r\n"
        + "    \"lon\": 10.99,\r\n"
        + "    \"lat\": 44.34\r\n"
        + "  },\r\n"
        + "  \"weather\": [\r\n"
        + "    {\r\n"
        + "      \"description\": \"moderate rain\"\r\n"
        + "    }\r\n"
        + "  ],\r\n"
        + "  \"main\": {\r\n"
        + "    \"temp\": 298.48,\r\n"
        + "    \"feels_like\": 298.74\r\n"
        + "  },\r\n"
        + "  \"wind\": {\r\n"
        + "    \"speed\": 0.62,\r\n"
        + "    \"deg\": 349\r\n"
        + "  },\r\n"
        + "  \"dt\": 1661870592\r\n"
        + "}";
    
    //TODO
    fail("Not implemented");
  }
  
  @Test
  public void testGetWithStevensPointReturnsWeather() {
    float latitude = 44.5236f;
    float longitude = -89.5746f;
    Date now = Date.from(Instant.now());
    double expectedTemperature = 271.47;
    
    //TODO
    fail("Not implemented");
  }
  
  @Test
  public void testGetReturnsCorrectWeather() {
    String json = "{\"coord\":{\"lon\":-89.5746,\"lat\":44.5236},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":292.68,\"feels_like\":292.8,\"temp_min\":290.81,\"temp_max\":293.98,\"pressure\":1019,\"humidity\":81},\"visibility\":10000,\"wind\":{\"speed\":2.68,\"deg\":175,\"gust\":5.36},\"clouds\":{\"all\":0},\"dt\":1663293758,\"sys\":{\"type\":1,\"id\":5927,\"country\":\"US\",\"sunrise\":1663241838,\"sunset\":1663286995},\"timezone\":-18000,\"id\":5274644,\"name\":\"Stevens Point\",\"cod\":200}"; 
    float latitude = 44.5236f;
    float longitude = -89.5746f;
    Date date = Date.from(Instant.ofEpochSecond(1663293758));

    //TODO
    fail("Not implemented");
  }
}
