package ch.tbz.gino_goncalo.D2;

/**
 * Represents a passenger on a flight.
 */
public class Passenger {

    /** The name of the passenger. */
    private String name;

    /**
     * Creates a new passenger with the given name.
     *
     * @param name the name of the passenger
     */
    public Passenger(String name) {
        this.name = name;
    }

    /**
     * Returns the passenger's name.
     *
     * @return the name of the passenger
     */
    public String getName() {
        return name;
    }
}