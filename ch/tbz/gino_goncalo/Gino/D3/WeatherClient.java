package ch.tbz.gino_goncalo.Gino.D3;

import java.time.LocalDate;

public interface WeatherClient {
  WeatherSummary summary(String city, LocalDate date);
}
