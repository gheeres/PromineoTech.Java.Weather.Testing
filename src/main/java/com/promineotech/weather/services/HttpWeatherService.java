package com.promineotech.weather.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public abstract class HttpWeatherService extends ConfigurableService {
  private final HttpClient client;
  
  protected HttpWeatherService() {
    client = HttpClient.newBuilder()
                       .version(Version.HTTP_2)
                       .followRedirects(Redirect.NORMAL)
                       .build();
  }

  /**
   * Retrieves the HTTP response from the url.
   * @param url The url to get.
   * @return The response if valid, otherwise null.
   */
  public HttpResponse<String> getHTTPResponse(String url) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
                                       .uri(URI.create(url))
                                       .header("Content-Type", "application/json")
                                       .GET()
                                       .build();
      return client.send(request, BodyHandlers.ofString());
    } catch (IOException e) {
    } catch (InterruptedException e) {
    }
    return null;
  }
  
  /**
   * Retrieves the base or root url for the service.
   * @param name The name of the property to read.
   * @return The value.
   */
  public String getBaseUrl(String name) {
    return getProperties().get(name)
                          .toString();
  }
}
