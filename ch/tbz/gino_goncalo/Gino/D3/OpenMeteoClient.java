package ch.tbz.gino_goncalo.Gino.D3;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;

public class OpenMeteoClient implements WeatherClient {
  private final HttpClient http = HttpClient.newBuilder()
      .connectTimeout(Duration.ofSeconds(10))
      .build();

  @Override
  public WeatherSummary summary(String city, LocalDate date) {
    try {
      double[] latlon = geocodeSwiss(city);
      double lat = latlon[0];
      double lon = latlon[1];

      String url = "https://api.open-meteo.com/v1/forecast"
          + "?latitude=" + lat
          + "&longitude=" + lon
          + "&daily=temperature_2m_max,temperature_2m_min,precipitation_sum"
          + "&timezone=Europe%2FZurich"
          + "&start_date=" + date
          + "&end_date=" + date;

      HttpRequest req = HttpRequest.newBuilder(URI.create(url))
          .timeout(Duration.ofSeconds(15))
          .GET()
          .build();

      HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
      if (resp.statusCode() != 200) {
        throw new ExternalServiceException("HTTP " + resp.statusCode() + " beim Forecast");
      }

      String body = resp.body();

      double tMax = extractFirstArrayNumber(body, "temperature_2m_max");
      double tMin = extractFirstArrayNumber(body, "temperature_2m_min");
      double avg = (tMax + tMin) / 2.0;
      double precip = extractFirstArrayNumber(body, "precipitation_sum");

      return new WeatherSummary(city, date, avg, precip);
    } catch (ExternalServiceException e) {
      throw e;
    } catch (Exception e) {
      throw new ExternalServiceException("Fehler beim Abruf der Wetterdaten", e);
    }
  }

  private double[] geocodeSwiss(String city) throws Exception {
    String q = URLEncoder.encode(city, StandardCharsets.UTF_8);
    String url = "https://geocoding-api.open-meteo.com/v1/search?count=1&language=de&country=CH&name=" + q;

    HttpRequest req = HttpRequest.newBuilder(URI.create(url))
        .timeout(Duration.ofSeconds(15))
        .GET()
        .build();

    HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
    if (resp.statusCode() != 200) {
      throw new ExternalServiceException("HTTP " + resp.statusCode() + " bei Geocoding");
    }
    String body = resp.body();

    Double lat = extractFirstNumber(body, "\"latitude\"");
    Double lon = extractFirstNumber(body, "\"longitude\"");
    if (lat == null || lon == null) {
      throw new ExternalServiceException("Ort nicht in der Schweiz gefunden: " + city);
    }
    return new double[] { lat, lon };
  }

  private Double extractFirstNumber(String json, String key) {
    int k = json.indexOf(key);
    if (k < 0) return null;
    int colon = json.indexOf(":", k);
    if (colon < 0) return null;
    int i = colon + 1;
    while (i < json.length() && Character.isWhitespace(json.charAt(i))) i++;
    int j = i;
    while (j < json.length()) {
      char c = json.charAt(j);
      if ((c >= '0' && c <= '9') || c == '.' || c == '-') j++;
      else break;
    }
    String num = json.substring(i, j);
    try {
      return Double.parseDouble(num);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private double extractFirstArrayNumber(String json, String key) {
    int k = json.indexOf("\"" + key + "\"");
    if (k < 0) throw new ExternalServiceException("Key fehlt: " + key);
    int lb = json.indexOf("[", k);
    int rb = json.indexOf("]", lb);
    if (lb < 0 || rb < 0) throw new ExternalServiceException("Array fehlt: " + key);
    String arr = json.substring(lb + 1, rb).trim();
    int comma = arr.indexOf(",");
    String first = (comma >= 0) ? arr.substring(0, comma) : arr;
    try {
      return Double.parseDouble(first.trim());
    } catch (NumberFormatException e) {
      throw new ExternalServiceException("Zahl ungueltig bei: " + key);
    }
  }
}
