package ch.tbz.gino_goncalo.Q3.wetterreiseplaner.service;

import ch.tbz.gino_goncalo.Q3.wetterreiseplaner.api.ApiClient;
import ch.tbz.gino_goncalo.Q3.wetterreiseplaner.model.WeatherData;

public class WeatherService {
    private ApiClient apiClient;

    public WeatherService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public WeatherData getWeatherForCity(String cityName) throws Exception {
        System.out.println("WeatherService: Rufe Wetterdaten für '" + cityName + "' ab...");
        WeatherData weatherData = apiClient.fetchWeatherData(cityName);
        System.out.println("WeatherService: Wetterdaten erfolgreich empfangen");
        return weatherData;
    }
}
