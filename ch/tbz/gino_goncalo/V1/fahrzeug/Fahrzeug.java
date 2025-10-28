package ch.tbz.gino_goncalo.V1.fahrzeug;

/**
 * Abstract base class representing a vehicle with basic properties.
 * All vehicles have a manufacturer and year of manufacture.
 */
abstract class Fahrzeug {

    /** The manufacturer of the vehicle. */
    protected String hersteller;

    /** The year the vehicle was manufactured. */
    protected int baujahr;

    /**
     * Constructs a new {@code Fahrzeug} with the specified manufacturer and year.
     *
     * @param hersteller the manufacturer of the vehicle
     * @param baujahr    the year of manufacture
     */
    public Fahrzeug(String hersteller, int baujahr) {
        this.hersteller = hersteller;
        this.baujahr = baujahr;
    }

    /**
     * Abstract method that defines how the vehicle moves.
     * Must be implemented by all subclasses.
     */
    public abstract void bewegeDich();

    /**
     * Returns the manufacturer of the vehicle.
     *
     * @return the manufacturer name
     */
    public String getHersteller() {
        return hersteller;
    }

    /**
     * Returns the year the vehicle was manufactured.
     *
     * @return the year of manufacture
     */
    public int getBaujahr() {
        return baujahr;
    }
}