package ch.tbz.gino_goncalo.D2;

import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String flightNumber;
    private String destination;
    private List<Passenger> passengers;

    public Flight(String flightNumber, String destination) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.passengers = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public boolean removePassenger(String passengerName) {
        return this.passengers.removeIf(p -> p.getName().equalsIgnoreCase(passengerName));
    }

    @Override
    public String toString() {
        return "Flug " + flightNumber + " nach " + destination;
    }
}