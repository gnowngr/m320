package ch.tbz.gino_goncalo.D1.garagesimulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a garage where vehicles can be registered, damaged, and repaired.
 */
public class Garage {

    /** Name of the garage. */
    private String name;

    /** List of all vehicles inside the garage. */
    private List<Vehicle> vehicles = new ArrayList<>();

    /**
     * Creates a new garage with the given name.
     *
     * @param name the name of the garage
     */
    public Garage(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this garage.
     *
     * @return the name of the garage
     */
    public String getName() { return name; }

    /**
     * Registers a new vehicle in the garage.
     *
     * @param v the vehicle to register
     * @return {@code true} if successfully added
     */
    public boolean registerVehicle(Vehicle v) {
        return vehicles.add(v);
    }

    /**
     * Finds a vehicle by its license plate.
     *
     * @param licensePlate the license plate to search for
     * @return the vehicle with the specified license plate or {@code null} if not found
     */
    public Vehicle get(String licensePlate) {
        return vehicles.stream()
                .filter(v -> v.getLicensePlate().equalsIgnoreCase(licensePlate))
                .findFirst().orElse(null);
    }

    /**
     * Repairs a vehicle with custom repair costs.
     *
     * @param licensePlate the license plate of the vehicle
     * @param costs the repair costs to charge
     * @return {@code true} if the vehicle was found and repaired
     */
    public boolean repairVehicle(String licensePlate, double costs) {
        Vehicle v = get(licensePlate);
        if (v != null) {
            v.setCondition(100);
            return true;
        }
        return false;
    }

    /**
     * Repairs a vehicle using its calculated repair cost.
     *
     * @param licensePlate the license plate of the vehicle
     * @return {@code true} if the vehicle was found and repaired
     */
    public boolean repairVehicle(String licensePlate) {
        Vehicle v = get(licensePlate);
        if (v != null) {
            v.calculateRepairCost();
            v.setCondition(100);
            return true;
        }
        return false;
    }

    /**
     * Repairs all vehicles in the garage and calculates total cost.
     *
     * @return total repair costs for all vehicles
     */
    public double repairAll() {
        double total = 0;
        for (Vehicle v : vehicles) {
            total += v.calculateRepairCost();
            v.setCondition(100);
        }
        return total;
    }

    /**
     * Damages a vehicle by reducing its condition.
     *
     * @param licensePlate the license plate of the vehicle
     * @param amount damage amount (decrease of condition)
     * @return {@code true} if the vehicle was found and damaged
     */
    public boolean damageVehicle(String licensePlate, int amount) {
        Vehicle v = get(licensePlate);
        if (v != null) {
            v.setCondition(Math.max(0, v.getCondition() - amount));
            return true;
        }
        return false;
    }

    /**
     * Returns a list of all vehicles in this garage.
     *
     * @return list of all vehicles
     */
    public List<Vehicle> allVehicles() { return vehicles; }

    /**
     * Returns a list of all vehicles that need repair.
     *
     * @return list of vehicles with condition less than 100%
     */
    public List<Vehicle> pendingVehicles() {
        return vehicles.stream().filter(v -> v.getCondition() < 100).toList();
    }

    /**
     * Returns a list of all vehicles that are fully repaired.
     *
     * @return list of vehicles with condition equal to 100%
     */
    public List<Vehicle> repairedVehicles() {
        return vehicles.stream().filter(v -> v.getCondition() == 100).toList();
    }

    /**
     * Calculates total repair costs for all vehicles without actually repairing them.
     *
     * @return total cost of all repairs
     */
    public double totalRepairCosts() {
        return vehicles.stream().mapToDouble(Vehicle::calculateRepairCost).sum();
    }

    /**
     * Prints a summary of all vehicles in the garage.
     */
    public void printOverview() {
        System.out.println("Garage " + name + " Übersicht:");
        for (Vehicle v : vehicles) {
            System.out.println(v.getLicensePlate() + " (" + v.getBrand() + " " + v.getModel() + ") - Zustand: " + v.getCondition() + "%");
        }
    }
}