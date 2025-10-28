package ch.tbz.gino_goncalo.Gino.D3;

import java.time.LocalDate;

public class PlannerService {
  private final WeatherClient weather;

  public PlannerService(WeatherClient weather) {
    this.weather = weather;
  }

  public Recommendation recommend(String city, LocalDate date) {
    if (city == null || city.isBlank()) throw new InputValidationException("Ort fehlt");
    if (date == null) throw new InputValidationException("Datum fehlt");
    WeatherSummary w = weather.summary(city.trim(), date);
    return rules(w);
  }

  private Recommendation rules(WeatherSummary w) {
    boolean warm = w.avgTempC() >= 20.0;
    boolean cold = w.avgTempC() < 10.0;
    boolean rain = w.rainMm() >= 1.0;

    if (warm && !rain) return new Recommendation("Outdoor ok", "T-Shirt und Sonnencreme, Wasser mitnehmen.");
    if (rain) return new Recommendation("Nass", "Regenschirm, lieber Indoor-Aktivität.");
    if (cold) return new Recommendation("Kuehl", "Zwiebellook und Jacke.");
    return new Recommendation("Gemischt", "Leichte Jacke, flexibel bleiben.");
  }
}
