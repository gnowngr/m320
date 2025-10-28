package ch.tbz.gino_goncalo.D3.wetterreiseplaner.validation;

import ch.tbz.gino_goncalo.D3.wetterreiseplaner.exceptions.InvalidCityNameException;
import ch.tbz.gino_goncalo.D3.wetterreiseplaner.exceptions.InvalidTemperatureRangeException;

public class InputValidator {

    public void validateCityName(String cityName) throws InvalidCityNameException {
        if (cityName == null || cityName.trim().isEmpty()) {
            throw new InvalidCityNameException("Stadtname darf nicht leer sein!");
        }

        if (!cityName.matches(".*[a-zA-ZäöüÄÖÜß].*")) {
            throw new InvalidCityNameException("Stadtname muss mindestens einen Buchstaben enthalten!");
        }

        System.out.println("InputValidator: Stadtname '" + cityName + "' ist gültig");
    }

    public void validateTemperatureRange(double minTemp, double maxTemp) throws InvalidTemperatureRangeException {
        if (minTemp > maxTemp) {
            throw new InvalidTemperatureRangeException(
                String.format("Ungültige Temperatur-Range: Min (%.1f°C) darf nicht größer als Max (%.1f°C) sein!",
                    minTemp, maxTemp)
            );
        }

        if (minTemp < -50 || maxTemp > 60) {
            throw new InvalidTemperatureRangeException(
                "Temperaturwerte außerhalb des realistischen Bereichs (-50°C bis +60°C)!"
            );
        }

        System.out.println("InputValidator: Temperatur-Range (" + minTemp + "°C bis " + maxTemp + "°C) ist gültig");
    }

    public boolean isValidTemperature(double temperature) {
        return temperature >= -50 && temperature <= 60;
    }
}
