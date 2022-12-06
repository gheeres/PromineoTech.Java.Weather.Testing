package com.promineotech.weather.services.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.fail;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.promineotech.weather.models.TemperatureModel;
import com.promineotech.weather.models.WeatherModel;
import com.promineotech.weather.services.VisualCrossingWeatherService;

public class VisualCrossingWeatherServiceTests extends WeatherServiceTests {
  private VisualCrossingWeatherService service;
  
  @BeforeEach
  public void setUp() throws Exception {
    service = new VisualCrossingWeatherService();
  }
  
  @Test
  public void testToWeatherModelReturnsWeatherModel() {
    String json = "{\r\n"
        + " \"latitude\": 44.5236,\r\n"
        + " \"longitude\": -89.5746,\r\n"
        + " \"currentConditions\": {\r\n"
        + "  \"datetimeEpoch\": 1663345769,\r\n"
        + "  \"temp\": 17,\r\n"
        + "  \"feelslike\": 18,\r\n"
        + "  \"windspeed\": 11.3,\r\n"
        + "  \"winddir\": 180,\r\n"
        + "  \"conditions\": \"Clear\",\r\n"
        + " }\r\n"
        + "}";
    
    WeatherModel weather = VisualCrossingWeatherService.toWeatherModel(json);

    assertThat(weather).isNotNull();
    //TODO
    fail("Not implemented");
  }
  
  @Test
  public void testGetWithStevensPointReturnsWeather() {
    float latitude = 44.5236f;
    float longitude = -89.5746f;
    Date now = Date.from(Instant.now());
    
    WeatherModel weather = service.get(latitude, longitude);

    assertThat(weather).isNotNull();
    //TODO
    fail("Not implemented");
  }
}
