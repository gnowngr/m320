package ch.tbz.gino_goncalo.D1.garagesimulator;

/**
 * Represents a motorcycle, a type of {@link Vehicle}.
 */
public class Motorcycle extends Vehicle {

    /** Whether the motorcycle has ABS (Anti-lock Braking System). */
    private boolean abs;

    /**
     * Creates a new motorcycle with all properties including condition.
     *
     * @param licensePlate the license plate
     * @param brand the brand
     * @param model the model
     * @param color the color
     * @param condition the condition percentage (0-100)
     * @param abs whether the motorcycle has ABS
     */
    public Motorcycle(String licensePlate, String brand, String model, String color, int condition, boolean abs) {
        super(licensePlate, brand, model, color, condition);
        this.abs = abs;
    }

    /**
     * Creates a new motorcycle with perfect condition (100%).
     *
     * @param licensePlate the license plate
     * @param brand the brand
     * @param model the model
     * @param color the color
     * @param abs whether the motorcycle has ABS
     */
    public Motorcycle(String licensePlate, String brand, String model, String color, boolean abs) {
        this(licensePlate, brand, model, color, 100, abs);
    }

    /**
     * Checks if this motorcycle has ABS.
     *
     * @return {@code true} if the motorcycle has ABS, otherwise {@code false}
     */
    public boolean hasAbs() { return abs; }

    @Override
    public double calculateRepairCost() {
        return (100 - condition) * 30; // motorcycles are cheaper to repair
    }
}