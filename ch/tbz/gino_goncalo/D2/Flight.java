package ch.tbz.gino_goncalo.D2;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a flight with a flight number, destination, and passenger list.
 */
public class Flight {

    /** The unique flight number. */
    private String flightNumber;

    /** The destination of the flight. */
    private String destination;

    /** The list of passengers booked for this flight. */
    private List<Passenger> passengers;

    /**
     * Constructs a new {@code Flight} with the specified flight number and destination.
     *
     * @param flightNumber the flight number
     * @param destination  the destination of the flight
     */
    public Flight(String flightNumber, String destination) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.passengers = new ArrayList<>();
    }

    /**
     * Returns the flight number.
     *
     * @return the flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Returns the destination of the flight.
     *
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Returns a list of all passengers on this flight.
     *
     * @return a list of passengers
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Adds a passenger to the flight.
     *
     * @param passenger the passenger to add
     */
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    /**
     * Removes a passenger with the given name from the flight.
     *
     * @param passengerName the name of the passenger to remove
     * @return {@code true} if the passenger was removed, {@code false} otherwise
     */
    public boolean removePassenger(String passengerName) {
        return passengers.removeIf(p -> p.getName().equalsIgnoreCase(passengerName));
    }
}