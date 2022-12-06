package com.promineotech.weather.services.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
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
    
    WeatherModel weather = OpenWeatherMapWeatherService.toWeatherModel(json);
    assertThat(weather).isNotNull();

    Date expectedDate = Date.from(Instant.ofEpochSecond(1661870592));
    assertThat(weather.getDate().getTime()).isEqualTo(expectedDate.getTime());
    assertThat(weather.getLongitude()).isCloseTo(10.99, offset(0.01));
    assertThat(weather.getLatitude()).isCloseTo(44.34, offset(0.01));
    assertThat(weather.getTemperature()).isNotNull();
    assertThat(weather.getTemperature().getKelvin())
              .isCloseTo(298.48, offset(0.01));
    assertThat(weather.getFeelsLike()).isNotNull();
    assertThat(weather.getFeelsLike().getKelvin())
              .isCloseTo(298.74, offset(0.01));
    assertThat(weather.getWindSpeed()).isCloseTo(0.62, offset(0.01));
    assertThat(weather.getWindDirection()).isCloseTo(349, offset(0.01));
    assertThat(weather.getDescription()).isEqualTo("moderate rain");
  }
  
  @Test
  public void testGetWithStevensPointReturnsWeather() {
    float latitude = 44.5236f;
    float longitude = -89.5746f;
    Date now = Date.from(Instant.now());
    double expectedTemperature = 271.47;
    
    WeatherModel weather = service.get(latitude, longitude);

    assertThat(weather).isNotNull();
    assertThat(weather.getLatitude()).isCloseTo(latitude, offset(0.9));
    assertThat(weather.getLongitude()).isCloseTo(longitude, offset(0.9));
    assertThat((weather.getDate().getTime() - now.getTime()))
              .isLessThan(10000);
    assertThat(weather.getTemperature()).isNotNull();
    assertThat(weather.getTemperature().getKelvin())
              .isCloseTo(expectedTemperature, offset(100.0)); // Hack
  }
  
  @Test
  public void testGetReturnsCorrectWeather() {
    String json = "{\"coord\":{\"lon\":-89.5746,\"lat\":44.5236},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":292.68,\"feels_like\":292.8,\"temp_min\":290.81,\"temp_max\":293.98,\"pressure\":1019,\"humidity\":81},\"visibility\":10000,\"wind\":{\"speed\":2.68,\"deg\":175,\"gust\":5.36},\"clouds\":{\"all\":0},\"dt\":1663293758,\"sys\":{\"type\":1,\"id\":5927,\"country\":\"US\",\"sunrise\":1663241838,\"sunset\":1663286995},\"timezone\":-18000,\"id\":5274644,\"name\":\"Stevens Point\",\"cod\":200}"; 
    float latitude = 44.5236f;
    float longitude = -89.5746f;
    Date date = Date.from(Instant.ofEpochSecond(1663293758));

    OpenWeatherMapWeatherService mockService = Mockito.spy(service);
    Mockito.doReturn(getSampleResponse(200,json))
           .when(mockService)
           .getHTTPResponse(anyString());
    
    WeatherModel weather = mockService.get(latitude, longitude);

    assertThat(weather).isNotNull();
    assertThat(weather.getLatitude()).isCloseTo(latitude, offset(0.9));
    assertThat(weather.getLongitude()).isCloseTo(longitude, offset(0.9));
    assertThat(weather.getDate().getTime()).isEqualTo(date.getTime());
    assertThat(weather.getTemperature()).isNotNull();
    assertThat(weather.getTemperature().getKelvin())
              .isCloseTo(292.68, offset(0.01));
    assertThat(weather.getFeelsLike()).isNotNull();
    assertThat(weather.getFeelsLike().getKelvin())
              .isCloseTo(292.8, offset(0.01));
    assertThat(weather.getWindSpeed()).isCloseTo(2.68, offset(0.01));
    assertThat(weather.getWindDirection()).isCloseTo(175, offset(0.01));
    assertThat(weather.getDescription()).isEqualTo("clear sky");
  }
}
