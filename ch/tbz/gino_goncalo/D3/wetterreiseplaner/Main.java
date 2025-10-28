package ch.tbz.gino_goncalo.D3.wetterreiseplaner;

import ch.tbz.gino_goncalo.D3.wetterreiseplaner.api.ApiClient;
import ch.tbz.gino_goncalo.D3.wetterreiseplaner.logic.TravelPlanner;
import ch.tbz.gino_goncalo.D3.wetterreiseplaner.service.WeatherService;
import ch.tbz.gino_goncalo.D3.wetterreiseplaner.ui.UserInterface;
import ch.tbz.gino_goncalo.D3.wetterreiseplaner.validation.InputValidator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Initialisiere Wetter-Reise-Planer...\n");

        try {
            System.out.println("Erstelle Komponenten...");
            ApiClient apiClient = new ApiClient();
            System.out.println("ApiClient erstellt");
            WeatherService weatherService = new WeatherService(apiClient);
            System.out.println("WeatherService erstellt");
            InputValidator validator = new InputValidator();
            System.out.println("InputValidator erstellt");
            TravelPlanner travelPlanner = new TravelPlanner(weatherService);
            System.out.println("TravelPlanner erstellt");
            UserInterface ui = new UserInterface(validator, travelPlanner);
            System.out.println("UserInterface erstellt");
            System.out.println("\nAlle Komponenten bereit!\n");
            ui.start();
        } catch (Exception e) {
            System.err.println("\nKritischer Fehler: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
