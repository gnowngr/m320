package ch.tbz.gino_goncalo.D3.wetterreiseplaner.ui;

import ch.tbz.gino_goncalo.D3.wetterreiseplaner.exceptions.InvalidCityNameException;
import ch.tbz.gino_goncalo.D3.wetterreiseplaner.exceptions.InvalidTemperatureRangeException;
import ch.tbz.gino_goncalo.D3.wetterreiseplaner.logic.TravelPlanner;
import ch.tbz.gino_goncalo.D3.wetterreiseplaner.validation.InputValidator;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private InputValidator validator;
    private TravelPlanner travelPlanner;

    public UserInterface(InputValidator validator, TravelPlanner travelPlanner) {
        this.scanner = new Scanner(System.in);
        this.validator = validator;
        this.travelPlanner = travelPlanner;
    }

    public void start() {
        printWelcome();
        boolean continueProgram = true;

        while (continueProgram) {
            try {
                String cityName = getCityFromUser();
                double[] tempRange = getTemperatureRangeFromUser();
                String recommendation = travelPlanner.planTravel(cityName, tempRange[0], tempRange[1]);
                System.out.println("\n" + recommendation);
            } catch (InvalidCityNameException | InvalidTemperatureRangeException e) {
                System.out.println("\nVALIDIERUNGSFEHLER: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\nFEHLER: " + e.getMessage());
            }
            continueProgram = askContinue();
        }

        System.out.println("\nDanke für die Nutzung des Wetter-Reise-Planers!");
        scanner.close();
    }

    private String getCityFromUser() throws InvalidCityNameException {
        System.out.print("\nBitte geben Sie den Stadtnamen ein: ");
        String cityName = scanner.nextLine().trim();
        validator.validateCityName(cityName);
        return cityName;
    }

    private double[] getTemperatureRangeFromUser() throws InvalidTemperatureRangeException {
        double minTemp = getTemperatureFromUser("Minimale gewünschte Temperatur (°C): ");
        double maxTemp = getTemperatureFromUser("Maximale gewünschte Temperatur (°C): ");
        validator.validateTemperatureRange(minTemp, maxTemp);
        return new double[]{minTemp, maxTemp};
    }

    private double getTemperatureFromUser(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Bitte geben Sie eine gültige Zahl ein!");
            }
        }
    }

    private boolean askContinue() {
        System.out.print("\nMöchten Sie eine weitere Stadt prüfen? (j/n): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("j") || answer.equals("ja") || answer.equals("y") || answer.equals("yes");
    }

    private void printWelcome() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("      WETTER-REISE-PLANER");
        System.out.println("=".repeat(60));
        System.out.println("Finden Sie heraus, ob Ihr Reiseziel das perfekte Wetter hat!");
        System.out.println("=".repeat(60));
    }
}
