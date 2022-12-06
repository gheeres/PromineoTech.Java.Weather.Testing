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
    //TODO
    fail("Not implemented");
  }
  
  @Test
  void testConstructorWithTemperatureLessThan459_67ThrowException() {
    //TODO
    fail("Not implemented");
  }
  
  @Test
  void testSetFahrenheitWithTemperatureLessThan459_67ThrowException() {
    //TODO
    fail("Not implemented");
  }

  @Test
  void testGetCelsiusWith32FahrenheitReturns0Celsius() 
  {
    //TODO
    fail("Not implemented");
  }

  @Test
  void testGetCelsiusWith212FahrenheitReturns100Celsius() 
  {
    //TODO
    fail("Not implemented");
  }
  
  @Test
  void testGetCelsiusWith60FahrenheitReturns15_5556Celsius() 
  {
    //TODO
    fail("Not implemented");
  }

  @Test
  void testToStringReturnsExpectedValue() 
  {
    //TODO
    fail("Not implemented");
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
    //TODO
    fail("Not implemented");
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
    //TODO
    fail("Not implemented");
  }
}
