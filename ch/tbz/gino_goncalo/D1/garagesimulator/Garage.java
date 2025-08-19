package ch.tbz.gino_goncalo.D1.garagesimulator;

import java.util.*;

public class Garage {
    private final String name;
    private final Map<String, Vehicle> byPlate = new HashMap<>();
    private final List<Vehicle> vehicles = new ArrayList<>();

    public Garage(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name");
        this.name = name.trim();
    }

    public String getName() { return name; }

    public boolean registerVehicle(Vehicle v) {
        String key = v.getLicensePlate().toUpperCase(Locale.ROOT);
        if (byPlate.containsKey(key)) return false;
        byPlate.put(key, v);
        vehicles.add(v);
        return true;
    }

    public Vehicle get(String licensePlate) {
        if (licensePlate == null) return null;
        return byPlate.get(licensePlate.toUpperCase(Locale.ROOT));
    }

    public boolean repairVehicle(String licensePlate, double costs) {
        Vehicle v = get(licensePlate);
        if (v == null) return false;
        v.repair(costs);
        return true;
    }

    public boolean damageVehicle(String licensePlate, int amount) {
        Vehicle v = get(licensePlate);
        if (v == null) return false;
        v.damage(amount);
        return true;
    }

    public List<Vehicle> allVehicles() {
        return Collections.unmodifiableList(vehicles);
    }

    public List<Vehicle> repairedVehicles() {
        List<Vehicle> out = new ArrayList<>();
        for (Vehicle v : vehicles) if (v.isRepaired()) out.add(v);
        return out;
    }

    public List<Vehicle> pendingVehicles() {
        List<Vehicle> out = new ArrayList<>();
        for (Vehicle v : vehicles) if (!v.isRepaired()) out.add(v);
        return out;
    }

    public double totalRepairCosts() {
        double sum = 0.0;
        for (Vehicle v : vehicles) sum += v.getRepairCosts();
        return sum;
    }

    public void printOverview() {
        System.out.println("--- Fahrzeuge in " + name + " ---");
        for (Vehicle v : vehicles) System.out.println(v);
        System.out.println("Gesamtkosten: " + String.format("%.2f", totalRepairCosts()) + " CHF");
    }
}
