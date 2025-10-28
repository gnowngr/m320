package ch.tbz.gino_goncalo.D1.garagesimulator;

/**
 * Represents a car, which is a type of {@link Vehicle}.
 */
public class Car extends Vehicle {

    /** Number of doors the car has. */
    private int doorCount;

    /**
     * Creates a new car with all properties including condition.
     *
     * @param licensePlate the license plate
     * @param brand the brand
     * @param model the model
     * @param color the color
     * @param condition the condition percentage (0-100)
     * @param doorCount the number of doors
     */
    public Car(String licensePlate, String brand, String model, String color, int condition, int doorCount) {
        super(licensePlate, brand, model, color, condition);
        this.doorCount = doorCount;
    }

    /**
     * Creates a new car with perfect condition (100%).
     *
     * @param licensePlate the license plate
     * @param brand the brand
     * @param model the model
     * @param color the color
     * @param doorCount the number of doors
     */
    public Car(String licensePlate, String brand, String model, String color, int doorCount) {
        this(licensePlate, brand, model, color, 100, doorCount);
    }

    /**
     * Returns the number of doors this car has.
     *
     * @return number of doors
     */
    public int getDoorCount() { return doorCount; }

    @Override
    public double calculateRepairCost() {
        return (100 - condition) * 50; // €50 per damage point
    }
}