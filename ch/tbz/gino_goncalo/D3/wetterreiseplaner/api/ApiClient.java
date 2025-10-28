package ch.tbz.gino_goncalo.D3.wetterreiseplaner.api;

import ch.tbz.gino_goncalo.D3.wetterreiseplaner.model.WeatherData;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {
    private static final String API_BASE_URL = "https://wttr.in";

    public ApiClient() {
    }

    public WeatherData fetchWeatherData(String cityName) throws Exception {
        String urlString = String.format(
            "%s/%s?format=j1",
            API_BASE_URL,
            cityName.replace(" ", "%20")
        );

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "WeatherTravelPlanner/1.0");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("API-Fehler: Stadt '" + cityName + "' nicht gefunden (HTTP " + responseCode + ")");
        }

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream())
        );
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return parseWeatherData(response.toString(), cityName);
    }

    private WeatherData parseWeatherData(String json, String cityName) {
        int currentCondStart = json.indexOf("\"current_condition\"");
        if (currentCondStart == -1) {
            return new WeatherData(cityName, 0, 0, "No data", 0, 0);
        }

        String currentSection = json.substring(currentCondStart);

        double temperature = extractDoubleFromSection(currentSection, "\"temp_C\"", ":");
        double feelsLike = extractDoubleFromSection(currentSection, "\"FeelsLikeC\"", ":");
        int humidity = (int) extractDoubleFromSection(currentSection, "\"humidity\"", ":");
        double windSpeedKmh = extractDoubleFromSection(currentSection, "\"windspeedKmph\"", ":");
        double windSpeed = windSpeedKmh / 3.6;

        String description = extractDescriptionFromJson(json);

        String actualCityName = cityName;
        String areaName = extractAreaName(json);
        if (areaName != null && !areaName.isEmpty()) {
            actualCityName = areaName;
        }

        return new WeatherData(actualCityName, temperature, feelsLike, description, humidity, windSpeed);
    }

    private double extractDoubleFromSection(String json, String key, String separator) {
        int keyStart = json.indexOf(key);
        if (keyStart == -1) return 0.0;

        int colonPos = json.indexOf(separator, keyStart);
        if (colonPos == -1) return 0.0;

        int valueStart = colonPos + separator.length();
        while (valueStart < json.length() && (json.charAt(valueStart) == ' ' || json.charAt(valueStart) == '"')) {
            valueStart++;
        }

        int valueEnd = valueStart;
        while (valueEnd < json.length() && Character.isDigit(json.charAt(valueEnd)) ||
               (valueEnd < json.length() && json.charAt(valueEnd) == '.')) {
            valueEnd++;
        }

        if (valueStart >= valueEnd) return 0.0;

        String value = json.substring(valueStart, valueEnd);
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String extractDescriptionFromJson(String json) {
        int weatherDescStart = json.indexOf("\"weatherDesc\"");
        if (weatherDescStart == -1) return "Unknown";

        String section = json.substring(weatherDescStart);
        int valueStart = section.indexOf("\"value\"");
        if (valueStart == -1) return "Unknown";

        int quoteStart = section.indexOf("\"", valueStart + 7);
        if (quoteStart == -1) return "Unknown";

        int quoteEnd = section.indexOf("\"", quoteStart + 1);
        if (quoteEnd == -1) return "Unknown";

        return section.substring(quoteStart + 1, quoteEnd);
    }

    private String extractAreaName(String json) {
        int areaStart = json.indexOf("\"nearest_area\"");
        if (areaStart == -1) return "";

        String section = json.substring(areaStart);
        int areaNameStart = section.indexOf("\"areaName\"");
        if (areaNameStart == -1) return "";

        section = section.substring(areaNameStart);
        int valueStart = section.indexOf("\"value\"");
        if (valueStart == -1) return "";

        int quoteStart = section.indexOf("\"", valueStart + 7);
        if (quoteStart == -1) return "";

        int quoteEnd = section.indexOf("\"", quoteStart + 1);
        if (quoteEnd == -1) return "";

        return section.substring(quoteStart + 1, quoteEnd);
    }
}
