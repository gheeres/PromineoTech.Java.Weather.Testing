package com.promineotech.weather.services.tests;

import java.net.http.HttpResponse;
import org.mockito.Mockito;

public abstract class WeatherServiceTests {
  @SuppressWarnings("unchecked")
  protected HttpResponse<String> getSampleResponse(int statusCode, String content) {
    HttpResponse<String> response = (HttpResponse<String>) Mockito.mock(HttpResponse.class);
    Mockito.when(response.statusCode()).thenReturn(statusCode);
    Mockito.when(response.body()).thenReturn(content);
    return response;
  }
}
