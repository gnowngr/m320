package ch.tbz.gino_goncalo.Q3.airports;

import ch.tbz.gino_goncalo.Q3.airports.model.Airport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AirportManager {
    private HashMap<String, Airport> airports;
    private Stack<String> searchHistory;

    public AirportManager() {
        this.airports = new HashMap<>();
        this.searchHistory = new Stack<>();
    }

    public void loadFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length >= 2) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();

                    Airport airport = new Airport(code, name);
                    airports.put(code, airport);
                }
            }
            System.out.println(airports.size() + " Flughäfen erfolgreich geladen.");
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der CSV-Datei: " + e.getMessage());
        }
    }

    public void addAirport(String code, String name) {
        Airport airport = new Airport(code, name);
        airports.put(code, airport);
        System.out.println("Flughafen " + code + " hinzugefügt.");
    }

    public Airport search(String code) {
        code = code.toUpperCase();
        Airport airport = airports.get(code);

        if (airport != null) {
            searchHistory.push(code);
            return airport;
        }
        return null;
    }

    public boolean remove(String code) {
        code = code.toUpperCase();
        if (airports.containsKey(code)) {
            airports.remove(code);
            System.out.println("Flughafen " + code + " entfernt.");
            return true;
        }
        System.out.println("Flughafen " + code + " nicht gefunden.");
        return false;
    }

    public void showAll() {
        if (airports.isEmpty()) {
            System.out.println("Keine Flughäfen vorhanden.");
            return;
        }

        System.out.println("\n=== ALLE FLUGHÄFEN ===");
        airports.forEach((k, v) -> System.out.println(v));
    }

    public void showAllSorted() {
        if (airports.isEmpty()) {
            System.out.println("Keine Flughäfen vorhanden.");
            return;
        }

        List<Airport> sortedList = new ArrayList<>(airports.values());
        sortedList.sort(Comparator.comparing(Airport::getCode));

        System.out.println("\n=== FLUGHÄFEN (ALPHABETISCH) ===");
        sortedList.forEach(System.out::println);
    }

    public void showSearchHistory() {
        if (searchHistory.isEmpty()) {
            System.out.println("Keine Suchhistorie vorhanden.");
            return;
        }

        System.out.println("\n=== SUCHHISTORIE (NEUESTE ZUERST) ===");
        Stack<String> temp = (Stack<String>) searchHistory.clone();

        while (!temp.isEmpty()) {
            String code = temp.pop();
            Airport airport = airports.get(code);
            if (airport != null) {
                System.out.println(airport);
            }
        }
    }

    public int getCount() {
        return airports.size();
    }
}
