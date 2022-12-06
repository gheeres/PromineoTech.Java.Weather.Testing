package com.promineotech.weather.models.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.promineotech.weather.exceptions.MinimumTemperatureExceededException;
import com.promineotech.weather.models.TemperatureModel;

public class TemperatureModelTests {
  @Test
  void testConstructorTakesTemperatureAsFahrenheit() {
    // Arrange - Setup our test environment
    double fahrenheit = 45;
    
    // Act
    TemperatureModel temperature = new TemperatureModel(fahrenheit);
    
    // Assert
    assertThat(temperature.getFahrenheit())
              .isCloseTo(fahrenheit, offset(0.01));
  }
  
  @Test
  void testConstructorWithTemperatureLessThan459_67ThrowException() {
    // Arrange
    double fahrenheit = -459.68;

    // Act/Asset
    try {
      TemperatureModel temperature = new TemperatureModel(fahrenheit);
      // should not get here
      fail("Excepted minimum temperature exception was not thrown");
    }catch(MinimumTemperatureExceededException e) {
      // success
      assertThat(e.getTemperature()).isCloseTo(fahrenheit, offset(0.01));
    }
  }
  
  @Test
  void testSetFahrenheitWithTemperatureLessThan459_67ThrowException() {
    // Arrange
    double fahrenheit = -459.68;
    String expectedMessage = String.format(
          "Temperature (%3.2f) exceeds absolute zero. "
        + "Value must be greater than %3.2f",
        fahrenheit, TemperatureModel.ABSOLUTE_ZERO_FAHRENHEIT);
    
    // Assert
    assertThatExceptionOfType(MinimumTemperatureExceededException.class)
    .isThrownBy(() -> {
      // Act
      TemperatureModel temperature = new TemperatureModel(0);
      temperature.setFahrenheit(fahrenheit);
    })
    .withMessage(expectedMessage); 
  }

  @Test
  void testGetCelsiusWith32FahrenheitReturns0Celsius() 
  {
    // Arrange
    double fahrenheit = 32;
    double expected = 0;
    TemperatureModel temperature = new TemperatureModel(fahrenheit);
    
    // Act
    double actual = temperature.getCelsius();
    
    // Assert
    assertThat(actual).isCloseTo(expected, offset(0.01));
  }

  @Test
  void testGetCelsiusWith212FahrenheitReturns100Celsius() 
  {
    // Arrange
    double fahrenheit = 212;
    double expected = 100;
    TemperatureModel temperature = new TemperatureModel(fahrenheit);
    
    // Act
    double actual = temperature.getCelsius();
    
    // Assert
    assertThat(actual).isCloseTo(expected, offset(0.01));
  }
  
  @Test
  void testGetCelsiusWith60FahrenheitReturns15_5556Celsius() 
  {
    // Arrange
    double fahrenheit = 60;
    double expected = 15.5556;
    TemperatureModel temperature = new TemperatureModel(fahrenheit);
    
    // Act
    double actual = temperature.getCelsius();
    
    // Assert
    assertThat(actual).isCloseTo(expected, offset(0.01));
  }

  @Test
  void testToStringReturnsExpectedValue() 
  {
    // Arrange
    double fahrenheit = 32;
    TemperatureModel temperature = new TemperatureModel(fahrenheit);
    String expected = "32.00°F (0.00°C)";
    
    // Act
    String actual = temperature.toString();
    
    // Assert
    assertThat(actual).isEqualTo(expected);
  }
  
  private static Stream<Arguments> getKelvinToFahrenheitParameters() {
    return Stream.of(
      Arguments.of(373.15, 212),
      Arguments.of(273.15, 32),
      Arguments.of(400, 260.33),
      Arguments.of(248.7, -12.01)
    );
  }
  
  @ParameterizedTest
  @MethodSource("getKelvinToFahrenheitParameters")
  void testFromKelvinReturnsCorrectInstance(double kelvin, 
                                            double expectedFahrenheit) 
  {
    // Arrange
    
    // Act
    TemperatureModel temperature = TemperatureModel.fromKelvin(kelvin);
    
    // Assert
    assertThat(temperature.getFahrenheit())
              .isCloseTo(expectedFahrenheit, offset(0.01));
  }

  private static Stream<Arguments> getCelsiusToFahrenheitParameters() {
    return Stream.of(
      Arguments.of(0, 32),
      Arguments.of(100, 212),
      Arguments.of(-60, -76),
      Arguments.of(-273.15, -459.67)
    );
  }
  
  @ParameterizedTest
  @MethodSource("getCelsiusToFahrenheitParameters")
  void testFromCelsiusReturnsCorrectInstance(double celsius, 
                                             double expectedFahrenheit) 
  {
    // Arrange
    
    // Act
    TemperatureModel temperature = TemperatureModel.fromCelsius(celsius);
    
    // Assert
    assertThat(temperature.getFahrenheit())
              .isCloseTo(expectedFahrenheit, offset(0.01));
  }
}
