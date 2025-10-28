package ch.tbz.gino_goncalo.Q3.wetterreiseplaner.logic;

import ch.tbz.gino_goncalo.Q3.wetterreiseplaner.model.WeatherData;
import ch.tbz.gino_goncalo.Q3.wetterreiseplaner.service.WeatherService;

public class TravelPlanner {
    private WeatherService weatherService;

    public TravelPlanner(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public String planTravel(String cityName, double minTemp, double maxTemp) {
        try {
            System.out.println("\nTravelPlanner: Starte Reiseplanung für " + cityName + "...");
            WeatherData weather = weatherService.getWeatherForCity(cityName);
            System.out.println("TravelPlanner: Bewerte Wetterdaten...\n");
            return evaluateWeather(weather, minTemp, maxTemp);
        } catch (Exception e) {
            return "FEHLER: " + e.getMessage();
        }
    }

    private String evaluateWeather(WeatherData weather, double minTemp, double maxTemp) {
        StringBuilder recommendation = new StringBuilder();
        recommendation.append("=== WETTER-REISE-BEWERTUNG ===\n\n");
        recommendation.append(weather.toString()).append("\n\n");

        // Temperatur-Bewertung
        double currentTemp = weather.getTemperature();
        boolean tempInRange = currentTemp >= minTemp && currentTemp <= maxTemp;

        recommendation.append("--- BEWERTUNG ---\n");
        recommendation.append(String.format("Gewünschte Temperatur: %.1f°C - %.1f°C\n", minTemp, maxTemp));
        recommendation.append(String.format("Aktuelle Temperatur: %.1f°C\n\n", currentTemp));

        if (tempInRange) {
            recommendation.append("✓ EMPFEHLUNG: PERFEKT FÜR IHRE REISE!\n");
            recommendation.append("Die Temperatur liegt genau in Ihrem gewünschten Bereich.\n");
        } else if (currentTemp < minTemp) {
            double difference = minTemp - currentTemp;
            recommendation.append("✗ EMPFEHLUNG: ZU KALT\n");
            recommendation.append(String.format("Es ist %.1f°C kälter als gewünscht.\n", difference));
            recommendation.append("Tipp: Packen Sie warme Kleidung ein oder wählen Sie eine andere Reisezeit.\n");
        } else {
            double difference = currentTemp - maxTemp;
            recommendation.append("✗ EMPFEHLUNG: ZU WARM\n");
            recommendation.append(String.format("Es ist %.1f°C wärmer als gewünscht.\n", difference));
            recommendation.append("Tipp: Denken Sie an Sonnenschutz und leichte Kleidung.\n");
        }

        // Zusätzliche Faktoren
        recommendation.append("\n--- ZUSÄTZLICHE FAKTOREN ---\n");

        // Wind-Bewertung
        if (weather.getWindSpeed() > 10) {
            recommendation.append("⚠ Starker Wind (").append(weather.getWindSpeed()).append(" m/s)\n");
        } else if (weather.getWindSpeed() > 5) {
            recommendation.append("~ Mäßiger Wind (").append(weather.getWindSpeed()).append(" m/s)\n");
        } else {
            recommendation.append("✓ Schwacher Wind (").append(weather.getWindSpeed()).append(" m/s)\n");
        }

        // Luftfeuchtigkeit-Bewertung
        if (weather.getHumidity() > 80) {
            recommendation.append("Hohe Luftfeuchtigkeit (").append(weather.getHumidity()).append("%)\n");
        } else if (weather.getHumidity() < 30) {
            recommendation.append("Niedrige Luftfeuchtigkeit (").append(weather.getHumidity()).append("%)\n");
        } else {
            recommendation.append("Angenehme Luftfeuchtigkeit (").append(weather.getHumidity()).append("%)\n");
        }

        return recommendation.toString();
    }
}
