package ch.tbz.gino_goncalo.Gino.D3;

import java.time.LocalDate;

public class WeatherSummary {
  private final String city;
  private final LocalDate date;
  private final double avgTempC;
  private final double rainMm;

  public WeatherSummary(String city, LocalDate date, double avgTempC, double rainMm) {
    this.city = city;
    this.date = date;
    this.avgTempC = avgTempC;
    this.rainMm = rainMm;
  }

  public String city() { return city; }
  public LocalDate date() { return date; }
  public double avgTempC() { return avgTempC; }
  public double rainMm() { return rainMm; }
}
