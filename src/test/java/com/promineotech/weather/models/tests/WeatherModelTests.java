package com.promineotech.weather.models.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.promineotech.weather.models.WeatherModel;

public class WeatherModelTests {

  @BeforeEach
  public void setUp() throws Exception {
  }
  
  @Test
  public void testDateInitializedToCurrentDateTime() {
    Date now = new Date();
    WeatherModel model = new WeatherModel();

    assertThat((model.getDate().getTime() - now.getTime()))
               .isLessThan(1000);
  }
  
  @Test
  public void testSetTemperatureWithFloatValueCreatesTemperatureModel() {
    double fahrenheit = 34.7;
    WeatherModel model = new WeatherModel()
                             .setTemperature(fahrenheit);
    
    assertThat(model.getTemperature()).isNotNull();
    assertThat(model.getTemperature().getFahrenheit())
              .isCloseTo(fahrenheit, offset(0.0001));
  }

  @Test
  public void testSetFeelsLikeWithFloatValueCreatesTemperatureModel() {
    double fahrenheit = 94.2;
    WeatherModel model = new WeatherModel()
                             .setFeelsLike(fahrenheit);
    
    assertThat(model.getFeelsLike()).isNotNull();
    assertThat(model.getFeelsLike().getFahrenheit())
              .isCloseTo(fahrenheit, offset(0.0001));
  }
}
