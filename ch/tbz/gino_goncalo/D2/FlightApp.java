package ch.tbz.gino_goncalo.D2;

import java.util.List;
import java.util.Scanner;

public class FlightApp {
    private Schedule schedule;
    private Scanner scanner;

    public static void main(String[] args) {
        FlightApp app = new FlightApp();
        app.run();
    }

    public FlightApp() {
        this.schedule = new Schedule();
        this.scanner = new Scanner(System.in);
        generateInitialData();
    }

    private void generateInitialData() {
        Flight flight1 = new Flight("LX1830", "Berlin");
        flight1.addPassenger(new Passenger("Anna Müller"));
        flight1.addPassenger(new Passenger("Peter Weber"));

        Flight flight2 = new Flight("WK288", "Mallorca");
        flight2.addPassenger(new Passenger("Maria Schmid"));

        schedule.addFlight(flight1);
        schedule.addFlight(flight2);
    }

    public void run() {
        System.out.println("--- Flug-Verwaltungssystem ---");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addPassengerToFlight();
                    break;
                case "2":
                    removePassengerFromFlight();
                    break;
                case "3":
                    showPassengersForFlight();
                    break;
                case "4":
                    searchForPassenger();
                    break;
                case "5":
                    listAllFlights();
                    break;
                case "6":
                    running = false;
                    System.out.println("Anwendung wird beendet.");
                    break;
                default:
                    System.out.println("Ungültige Eingabe, bitte erneut versuchen.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nMenü:");
        System.out.println("1. Passagier zu einem Flug hinzufügen");
        System.out.println("2. Passagier von einem Flug entfernen");
        System.out.println("3. Passagiere für einen Flug anzeigen");
        System.out.println("4. Nach einem Passagier suchen");
        System.out.println("5. Alle Flüge anzeigen");
        System.out.println("6. Beenden");
        System.out.print("Bitte wählen Sie eine Option: ");
    }

    private void addPassengerToFlight() {
        System.out.print("Geben Sie die Flugnummer ein: ");
        String flightNumber = scanner.nextLine();
        Flight flight = schedule.findFlight(flightNumber);

        if (flight != null) {
            System.out.print("Geben Sie den Namen des neuen Passagiers ein: ");
            String passengerName = scanner.nextLine();
            flight.addPassenger(new Passenger(passengerName));
            System.out.println("Passagier '" + passengerName + "' wurde zu Flug " + flightNumber + " hinzugefügt.");
        } else {
            System.out.println("Fehler: Flug nicht gefunden.");
        }
    }

    private void removePassengerFromFlight() {
        System.out.print("Geben Sie die Flugnummer ein: ");
        String flightNumber = scanner.nextLine();
        Flight flight = schedule.findFlight(flightNumber);

        if (flight != null) {
            System.out.print("Geben Sie den Namen des zu entfernenden Passagiers ein: ");
            String passengerName = scanner.nextLine();
            if (flight.removePassenger(passengerName)) {
                System.out.println("Passagier '" + passengerName + "' wurde von Flug " + flightNumber + " entfernt.");
            } else {
                System.out.println("Fehler: Passagier auf diesem Flug nicht gefunden.");
            }
        } else {
            System.out.println("Fehler: Flug nicht gefunden.");
        }
    }

    private void showPassengersForFlight() {
        System.out.print("Geben Sie die Flugnummer ein, um die Passagiere anzuzeigen: ");
        String flightNumber = scanner.nextLine();
        Flight flight = schedule.findFlight(flightNumber);

        if (flight != null) {
            System.out.println("Passagiere auf Flug " + flight.getFlightNumber() + " nach " + flight.getDestination() + ":");
            List<Passenger> passengers = flight.getPassengers();
            if (passengers.isEmpty()) {
                System.out.println("- Keine Passagiere registriert.");
            } else {
                for (Passenger p : passengers) {
                    System.out.println("- " + p.getName());
                }
            }
        } else {
            System.out.println("Fehler: Flug nicht gefunden.");
        }
    }

    private void searchForPassenger() {
        System.out.print("Geben Sie den Namen des zu suchenden Passagiers ein: ");
        String passengerName = scanner.nextLine();
        boolean found = false;

        for(Flight flight : schedule.getAllFlights()){
            for(Passenger passenger : flight.getPassengers()){
                if(passenger.getName().equalsIgnoreCase(passengerName)){
                    System.out.println("- " + passengerName + " ist auf Flug " + flight.getFlightNumber() + " nach " + flight.getDestination() + " gebucht.");
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Kein Passagier mit dem Namen '" + passengerName + "' gefunden.");
        }
    }

    private void listAllFlights() {
        System.out.println("Verfügbare Flüge:");
        if (schedule.getAllFlights().isEmpty()){
            System.out.println("- Keine Flüge im System.");
        } else {
            for (Flight flight : schedule.getAllFlights()) {
                System.out.println("- " + flight);
            }
        }
    }
}