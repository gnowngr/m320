package ch.tbz.gino_goncalo.D2;

import java.util.Scanner;

/**
 * Console application for managing flights and passengers.
 * Provides an interactive menu to create flights, add/passengers, and view flights.
 */
public class FlightApp {

    /** The schedule containing all flights. */
    private Schedule schedule;

    /**
     * Constructs a new application with an empty schedule.
     */
    public FlightApp() {
        schedule = new Schedule();
    }

    /**
     * Starts the application and displays the interactive menu.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Willkommen zur Flugverwaltung!");

        while (running) {
            System.out.println("\nWas möchten Sie tun?");
            System.out.println("1. Flug hinzufügen");
            System.out.println("2. Passagier hinzufügen");
            System.out.println("3. Passagier entfernen");
            System.out.println("4. Flüge anzeigen");
            System.out.println("5. Beenden");
            System.out.print("> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Flugnummer: ");
                    String fn = scanner.nextLine();
                    System.out.print("Ziel: ");
                    String dest = scanner.nextLine();
                    schedule.addFlight(new Flight(fn, dest));
                    System.out.println("Flug hinzugefügt.");
                    break;
                case "2":
                    System.out.print("Flugnummer: ");
                    fn = scanner.nextLine();
                    Flight flight = schedule.findFlight(fn);
                    if (flight != null) {
                        System.out.print("Passagiername: ");
                        String name = scanner.nextLine();
                        flight.addPassenger(new Passenger(name));
                        System.out.println("Passagier hinzugefügt.");
                    } else {
                        System.out.println("Flug nicht gefunden.");
                    }
                    break;
                case "3":
                    System.out.print("Flugnummer: ");
                    fn = scanner.nextLine();
                    flight = schedule.findFlight(fn);
                    if (flight != null) {
                        System.out.print("Passagiername: ");
                        String name = scanner.nextLine();
                        if (flight.removePassenger(name)) {
                            System.out.println("Passagier entfernt.");
                        } else {
                            System.out.println("Passagier nicht gefunden.");
                        }
                    } else {
                        System.out.println("Flug nicht gefunden.");
                    }
                    break;
                case "4":
                    System.out.println("\n--- Alle Flüge ---");
                    for (Flight f : schedule.getAllFlights()) {
                        System.out.println("Flug: " + f.getFlightNumber() + " -> " + f.getDestination()
                                + " | Passagiere: " + f.getPassengers().size());
                    }
                    break;
                case "5":
                    System.out.println("Programm beendet.");
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Eingabe. Bitte erneut versuchen.");
                    break;
            }
        }

        scanner.close();
    }

    /**
     * The main method that starts the flight management app.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new FlightApp().run();
    }
}