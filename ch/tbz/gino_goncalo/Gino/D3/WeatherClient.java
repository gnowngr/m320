import java.time.LocalDate;

public interface WeatherClient {
  WeatherSummary summary(String city, LocalDate date);
}
