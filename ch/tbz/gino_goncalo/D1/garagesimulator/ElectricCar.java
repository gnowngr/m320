package ch.tbz.gino_goncalo.D1.garagesimulator;

/**
 * Represents an electric car, which is a type of {@link Car}.
 * In addition to standard car properties, it has a battery capacity.
 */
public class ElectricCar extends Car {

    /** Battery capacity in kWh. */
    private int batteryKwh;

    /**
     * Creates a new electric car with all properties including condition.
     *
     * @param licensePlate the license plate
     * @param brand the brand
     * @param model the model
     * @param color the color
     * @param condition the condition percentage (0-100)
     * @param doorCount the number of doors
     * @param batteryKwh the battery capacity in kWh
     */
    public ElectricCar(String licensePlate, String brand, String model, String color, int condition, int doorCount, int batteryKwh) {
        super(licensePlate, brand, model, color, condition, doorCount);
        this.batteryKwh = batteryKwh;
    }

    /**
     * Creates a new electric car with perfect condition (100%).
     *
     * @param licensePlate the license plate
     * @param brand the brand
     * @param model the model
     * @param color the color
     * @param doorCount the number of doors
     * @param batteryKwh the battery capacity in kWh
     */
    public ElectricCar(String licensePlate, String brand, String model, String color, int doorCount, int batteryKwh) {
        this(licensePlate, brand, model, color, 100, doorCount, batteryKwh);
    }

    /**
     * Returns the battery capacity of this electric car.
     *
     * @return battery capacity in kilowatt-hours
     */
    public int getBatteryKwh() { return batteryKwh; }

    @Override
    public double calculateRepairCost() {
        return (100 - getCondition()) * 70; // electric cars medium expensive
    }
}