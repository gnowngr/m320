package ch.tbz.gino_goncalo.D1.garagesimulator;

/**
 * Console entry point to demonstrate the usage of the {@link Garage}.
 */
public class GarageSimulator {

    /**
     * Creates a new garage simulator instance.
     */
    public GarageSimulator() {
        // Default constructor
    }

    /**
     * Main method for testing and demonstrating functionality
     * of the garage simulator.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Garage g = new Garage("TBZ Garage");

        g.registerVehicle(new Car("ZH12345", "VW", "Golf", "Blau", 60, 5));
        g.registerVehicle(new Truck("ZH98765", "Mercedes", "Actros", "Weiß", 80, 10));
        g.registerVehicle(new Motorcycle("ZH67890", "Yamaha", "R1", "Rot", true));
        g.registerVehicle(new ElectricCar("ZH54321", "Tesla", "Model 3", "Schwarz", 4, 75));

        g.printOverview();

        System.out.println("\nGesamtkosten Reparatur: " + g.totalRepairCosts());
        g.repairAll();
        System.out.println("\nNach der Reparatur:");
        g.printOverview();
    }
}