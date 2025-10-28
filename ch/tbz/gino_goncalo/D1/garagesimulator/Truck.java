package ch.tbz.gino_goncalo.D1.garagesimulator;

/**
 * Represents a truck, a type of {@link Vehicle}.
 */
public class Truck extends Vehicle {

    /** Maximum payload capacity in tons. */
    private double payloadTons;

    /**
     * Creates a new truck with all properties including condition.
     *
     * @param licensePlate the license plate
     * @param brand the brand
     * @param model the model
     * @param color the color
     * @param condition the condition percentage (0-100)
     * @param payloadTons the payload capacity in tons
     */
    public Truck(String licensePlate, String brand, String model, String color, int condition, double payloadTons) {
        super(licensePlate, brand, model, color, condition);
        this.payloadTons = payloadTons;
    }

    /**
     * Creates a new truck with perfect condition (100%).
     *
     * @param licensePlate the license plate
     * @param brand the brand
     * @param model the model
     * @param color the color
     * @param payloadTons the payload capacity in tons
     */
    public Truck(String licensePlate, String brand, String model, String color, double payloadTons) {
        this(licensePlate, brand, model, color, 100, payloadTons);
    }

    /**
     * Returns the payload capacity of this truck.
     *
     * @return payload capacity in tons
     */
    public double getPayloadTons() { return payloadTons; }

    @Override
    public double calculateRepairCost() {
        return (100 - condition) * 100; // trucks are more expensive
    }
}