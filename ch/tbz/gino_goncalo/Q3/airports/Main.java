package ch.tbz.gino_goncalo.Q3.airports;

import ch.tbz.gino_goncalo.Q3.airports.model.Airport;
import java.util.Scanner;

public class Main {
    private static AirportManager manager;
    private static Scanner scanner;

    public static void main(String[] args) {
        manager = new AirportManager();
        scanner = new Scanner(System.in);

        manager.loadFromCSV("airports.csv");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    showAllAirports();
                    break;
                case 2:
                    showAllAirportsSorted();
                    break;
                case 3:
                    searchAirport();
                    break;
                case 4:
                    addAirport();
                    break;
                case 5:
                    removeAirport();
                    break;
                case 6:
                    showHistory();
                    break;
                case 0:
                    running = false;
                    System.out.println("Auf Wiedersehen!");
                    break;
                default:
                    System.out.println("Ungültige Auswahl.");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     US-FLUGHÄFEN VERWALTUNG");
        System.out.println("=".repeat(50));
        System.out.println("1. Alle Flughäfen anzeigen");
        System.out.println("2. Flughäfen alphabetisch sortiert anzeigen");
        System.out.println("3. Flughafen suchen");
        System.out.println("4. Neuen Flughafen hinzufügen");
        System.out.println("5. Flughafen entfernen");
        System.out.println("6. Suchhistorie anzeigen");
        System.out.println("0. Beenden");
        System.out.println("=".repeat(50));
    }

    private static int getChoice() {
        System.out.print("Ihre Wahl: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void showAllAirports() {
        manager.showAll();
    }

    private static void showAllAirportsSorted() {
        manager.showAllSorted();
    }

    private static void searchAirport() {
        System.out.print("\nFlughafen-Code eingeben (z.B. LAX): ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("Kein Code eingegeben.");
            return;
        }

        Airport airport = manager.search(code);
        if (airport != null) {
            System.out.println("\nGefunden:");
            System.out.println(airport);
        } else {
            System.out.println("Flughafen mit Code '" + code + "' nicht gefunden.");
        }
    }

    private static void addAirport() {
        System.out.println("\n=== NEUEN FLUGHAFEN HINZUFÜGEN ===");

        System.out.print("Code (z.B. LAX): ");
        String code = scanner.nextLine().trim().toUpperCase();

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        if (code.isEmpty() || name.isEmpty()) {
            System.out.println("Code und Name müssen ausgefüllt sein.");
            return;
        }

        manager.addAirport(code, name);
    }

    private static void removeAirport() {
        System.out.print("\nFlughafen-Code zum Entfernen: ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("Kein Code eingegeben.");
            return;
        }

        manager.remove(code);
    }

    private static void showHistory() {
        manager.showSearchHistory();
    }
}
