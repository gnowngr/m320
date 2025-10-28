package ch.tbz.gino_goncalo.D1.garagesimulator;

/**
 * Abstract base class representing a vehicle in the garage.
 * Vehicles have a license plate, brand, model, color, and condition.
 */
public abstract class Vehicle {

    /** License plate of the vehicle. */
    protected String licensePlate;

    /** Brand of the vehicle. */
    protected String brand;

    /** Model of the vehicle. */
    protected String model;

    /** Color of the vehicle. */
    protected String color;

    /** Condition percentage (0–100). */
    protected int condition;

    /**
     * Constructs a new Vehicle.
     *
     * @param licensePlate the license plate
     * @param brand the brand
     * @param model the model
     * @param color the color
     * @param condition the condition percentage (0–100)
     */
    public Vehicle(String licensePlate, String brand, String model, String color, int condition) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.condition = condition;
    }

    /**
     * Calculates the repair cost for this vehicle.
     *
     * @return the calculated repair cost
     */
    public abstract double calculateRepairCost();

    /**
     * Returns the license plate of this vehicle.
     *
     * @return the license plate string
     */
    public String getLicensePlate() { return licensePlate; }

    /**
     * Returns the brand of this vehicle.
     *
     * @return the brand of the vehicle
     */
    public String getBrand() { return brand; }

    /**
     * Returns the model of this vehicle.
     *
     * @return the model name
     */
    public String getModel() { return model; }

    /**
     * Returns the color of this vehicle.
     *
     * @return the color of the vehicle
     */
    public String getColor() { return color; }

    /**
     * Returns the current condition of this vehicle.
     *
     * @return the condition percentage
     */
    public int getCondition() { return condition; }

    /**
     * Sets the condition of this vehicle after damage or repair.
     *
     * @param condition new condition percentage (0-100)
     */
    public void setCondition(int condition) { this.condition = condition; }
}