package ch.tbz.gino_goncalo.Q1.arbeitszeiterfassung;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Projekt {
    long startZeit = 0;
    long gesamtdauer = 0;
}

public class Arbeitszeiterfassung {

    private Map<String, Projekt> projekte = new HashMap<>();

    public void start(String projektName) {
        Projekt projekt = projekte.computeIfAbsent(projektName, k -> new Projekt());
        projekt.startZeit = System.currentTimeMillis();
        System.out.println("Zeiterfassung für Projekt '" + projektName + "' gestartet.");
    }

    public void stopp(String projektName) {
        Projekt projekt = projekte.get(projektName);
        if (projekt != null && projekt.startZeit != 0) {
            long endZeit = System.currentTimeMillis();
            long dauer = endZeit - projekt.startZeit;
            projekt.gesamtdauer += dauer;
            projekt.startZeit = 0;
            System.out.println("Zeiterfassung für Projekt '" + projektName + "' gestoppt.");
        } else {
            System.out.println("Projekt '" + projektName + "' wurde nicht gefunden oder die Zeit wurde bereits gestoppt.");
        }
    }

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
        System.out.println("----------------------------\n");
    }

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