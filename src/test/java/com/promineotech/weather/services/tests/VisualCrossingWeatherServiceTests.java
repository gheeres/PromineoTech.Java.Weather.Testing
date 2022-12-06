package com.promineotech.weather.services.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
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

    Date expectedDate = Date.from(Instant.ofEpochSecond(1663345769));
    assertThat(weather.getDate().getTime()).isEqualTo(expectedDate.getTime());
    assertThat(weather.getLongitude()).isCloseTo(-89.5746, offset(0.01));
    assertThat(weather.getLatitude()).isCloseTo(44.5236, offset(0.01));
    assertThat(weather.getTemperature()).isNotNull();
    assertThat(weather.getTemperature().getKelvin())
              .isCloseTo(17 + TemperatureModel.KELVIN_TO_CELSIUS_CONVERSION, offset(0.01));
    assertThat(weather.getFeelsLike()).isNotNull();
    assertThat(weather.getFeelsLike().getKelvin())
              .isCloseTo(18 + TemperatureModel.KELVIN_TO_CELSIUS_CONVERSION, offset(0.01));
    assertThat(weather.getWindSpeed()).isCloseTo(11.3, offset(0.01));
    assertThat(weather.getWindDirection()).isEqualTo(180, offset(0.01));
    assertThat(weather.getDescription()).isEqualTo("Clear");
  }
  
  @Test
  public void testGetWithStevensPointReturnsWeather() {
    float latitude = 44.5236f;
    float longitude = -89.5746f;
    Date now = Date.from(Instant.now());
    
    WeatherModel weather = service.get(latitude, longitude);

    assertThat(weather).isNotNull();
    assertThat(weather.getLatitude()).isCloseTo(latitude, offset(0.9));
    assertThat(weather.getLongitude()).isCloseTo(longitude, offset(0.9));
    assertThat((weather.getDate().getTime() - now.getTime()))
              .isLessThan(1000);
  }
}
