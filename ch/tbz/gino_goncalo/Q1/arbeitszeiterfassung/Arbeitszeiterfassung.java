package ch.tbz.gino_goncalo.Q1.arbeitszeiterfassung;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Represents a project for time tracking purposes.
 * Stores the start time of the current tracking session and the total accumulated duration.
 */
class Projekt {
    /** The timestamp when the project's time tracking was last started (in milliseconds). */
    long startZeit = 0;
    /** The total accumulated duration for this project (in milliseconds). */
    long gesamtdauer = 0;
}

/**
 * Manages the time tracking for various projects.
 * Allows starting, stopping, and displaying the recorded time for each project.
 */
public class Arbeitszeiterfassung {

    /** A map to store {@link Projekt} objects, keyed by their project names. */
    private Map<String, Projekt> projekte = new HashMap<>();

    /**
     * Creates a new time tracking system.
     */
    public Arbeitszeiterfassung() {
        // Default constructor
    }

    /**
     * Starts or resumes time tracking for a specified project.
     * If the project does not exist, it will be created.
     * If tracking is already active for the project, its start time will be reset.
     *
     * @param projektName the name of the project to start time tracking for
     */
    public void start(String projektName) {
        Projekt projekt = projekte.computeIfAbsent(projektName, k -> new Projekt());
        projekt.startZeit = System.currentTimeMillis();
        System.out.println("Zeiterfassung für Projekt '" + projektName + "' gestartet.");
    }

    /**
     * Stops the active time tracking for a specified project and adds the duration
     * to its total accumulated time.
     * If the project is not found or tracking is not active, an appropriate message is displayed.
     *
     * @param projektName the name of the project to stop time tracking for
     */
    public void stopp(String projektName) {
        Projekt projekt = projekte.get(projektName);
        if (projekt != null && projekt.startZeit != 0) {
            long endZeit = System.currentTimeMillis();
            long dauer = endZeit - projekt.startZeit;
            projekt.gesamtdauer += dauer;
            projekt.startZeit = 0; // Reset start time to indicate tracking is stopped
            System.out.println("Zeiterfassung für Projekt '" + projektName + "' gestoppt.");
        } else {
            System.out.println("Projekt '" + projektName + "' wurde nicht gefunden oder die Zeit wurde bereits gestoppt.");
        }
    }

    /**
     * Displays the current accumulated working times for all tracked projects.
     * If a project is currently running, its ongoing duration is included in the display.
     * Times are formatted in hours, minutes, and seconds.
     */
    public void zeitenAnzeigen() {
        if (projekte.isEmpty()) {
            System.out.println("Noch keine Zeiten erfasst.");
            return;
        }

        System.out.println("\n--- Erfasste Arbeitszeiten ---");
        for (Map.Entry<String, Projekt> eintrag : projekte.entrySet()) {
            String projektName = eintrag.getKey();
            Projekt projekt = eintrag.getValue();
            long dauerInMillis = projekt.gesamtdauer;

            // If the project is currently running, add the ongoing duration
            if (projekt.startZeit != 0) {
                long laufendeDauer = System.currentTimeMillis() - projekt.startZeit;
                dauerInMillis += laufendeDauer;
            }

            long stunden = TimeUnit.MILLISECONDS.toHours(dauerInMillis);
            long minuten = TimeUnit.MILLISECONDS.toMinutes(dauerInMillis) % 60;
            long sekunden = TimeUnit.MILLISECONDS.toSeconds(dauerInMillis) % 60;

            System.out.printf("Projekt: %s - Dauer: %dh %dm %ds%s\n",
                    projektName, stunden, minuten, sekunden, (projekt.startZeit != 0 ? " (läuft)" : ""));
        }
        System.out.println("----\n");
    }

    /**
     * Main method to run the interactive time tracking application.
     * Users can start, stop, and view project times via console commands.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Arbeitszeiterfassung zeiterfassung = new Arbeitszeiterfassung();
        Scanner scanner = new Scanner(System.in);
        boolean laeuft = true;

        System.out.println("Willkommen zur Arbeitszeiterfassung!");

        while (laeuft) {
            System.out.println("Was möchten Sie tun? (start, stopp, anzeigen, exit)");
            System.out.print("> ");
            String aktion = scanner.nextLine();

            switch (aktion) {
                case "start":
                    System.out.print("Für welches Projekt möchten Sie die Zeit starten? ");
                    String startProjekt = scanner.nextLine();
                    zeiterfassung.start(startProjekt);
                    break;
                case "stopp":
                    System.out.print("Für welches Projekt möchten Sie die Zeit stoppen? ");
                    String stoppProjekt = scanner.nextLine();
                    zeiterfassung.stopp(stoppProjekt);
                    break;
                case "anzeigen":
                    zeiterfassung.zeitenAnzeigen();
                    break;
                case "exit":
                    System.out.println("Programm wird beendet.");
                    laeuft = false;
                    break;
                default:
                    System.out.println("Unbekannte Aktion. Bitte versuchen Sie es erneut.");
                    break;
            }
        }
        scanner.close();
    }
}