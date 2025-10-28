package ch.tbz.gino_goncalo.D2;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a flight schedule that manages multiple {@link Flight} objects.
 */
public class Schedule {

    /** The list of flights in the schedule. */
    private List<Flight> flights;

    /**
     * Creates an empty flight schedule.
     */
    public Schedule() {
        flights = new ArrayList<>();
    }

    /**
     * Adds a flight to the schedule.
     *
     * @param flight the flight to add
     */
    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    /**
     * Finds a flight by its flight number.
     *
     * @param flightNumber the flight number to search for
     * @return the {@link Flight} if found, otherwise {@code null}
     */
    public Flight findFlight(String flightNumber) {
        for (Flight f : flights) {
            if (f.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Returns all flights in the schedule.
     *
     * @return a list of flights
     */
    public List<Flight> getAllFlights() {
        return flights;
    }
}