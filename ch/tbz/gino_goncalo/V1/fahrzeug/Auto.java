package ch.tbz.gino_goncalo.V1.fahrzeug;

/**
 * Represents a car as a specific type of {@link Fahrzeug}.
 * A car has a specific number of doors.
 */
class Auto extends Fahrzeug {

    /** The number of doors this car has. */
    private int anzahlTueren;

    /**
     * Constructs a new {@code Auto} with the given manufacturer, year, and door count.
     *
     * @param hersteller    the manufacturer of the car
     * @param baujahr       the year of manufacture
     * @param anzahlTueren  the number of doors
     */
    public Auto(String hersteller, int baujahr, int anzahlTueren) {
        super(hersteller, baujahr);
        this.anzahlTueren = anzahlTueren;
    }

    /**
     * Prints a message to simulate the car being driven.
     */
    @Override
    public void bewegeDich() {
        System.out.println("Das Auto fährt auf der Strasse.");
    }

    /**
     * Returns the number of doors this car has.
     *
     * @return the number of doors
     */
    public int getAnzahlTueren() {
        return anzahlTueren;
    }
}