package ch.tbz.gino_goncalo.D3.wetterreiseplaner.model;

public class WeatherData {
    private String cityName;
    private double temperature;
    private double feelsLike;
    private String description;
    private int humidity;
    private double windSpeed;

    public WeatherData(String cityName, double temperature, double feelsLike,
                      String description, int humidity, double windSpeed) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.description = description;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public String getDescription() {
        return description;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String toString() {
        return String.format(
            "Stadt: %s\n" +
            "Temperatur: %.1f°C (gefühlt: %.1f°C)\n" +
            "Wetter: %s\n" +
            "Luftfeuchtigkeit: %d%%\n" +
            "Windgeschwindigkeit: %.1f m/s",
            cityName, temperature, feelsLike, description, humidity, windSpeed
        );
    }
}
