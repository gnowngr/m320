public class GarageSimulator {
    public static void main(String[] args) {
        Garage g = new Garage("M320 Garage");

        Vehicle a = new Car("ZH-12345", "VW", "Golf", "blau", 85, 5);
        Vehicle b = new Motorcycle("AG-98765", "Toyota", "Corolla", "rot", 60, true);
        Vehicle c = new Truck("BE-22222", "BMW", "320i", "schwarz", 8.5);
        Vehicle d = new ElectricCar("ZH-99999", "Tesla", "Model 3", "weiss", 4, 75);

        g.registerVehicle(a);
        g.registerVehicle(b);
        g.registerVehicle(c);
        g.registerVehicle(d);

        System.out.println("Initial:");
        g.printOverview();

        g.damageVehicle("BE-22222", 35);
        g.damageVehicle("AG-98765", 15);

        System.out.println("\nNach Schäden:");
        g.printOverview();

        // Polymorphe Reparaturkosten
        g.repairVehicle("AG-98765");
        g.repairVehicle("ZH-12345");

        System.out.println("\nNach Reparaturen:");
        g.printOverview();

        System.out.println("\nReparierte Fahrzeuge:");
        for (var v : g.repairedVehicles()) System.out.println(v);

        System.out.println("\nOffene Fahrzeuge:");
        for (var v : g.pendingVehicles()) System.out.println(v);

        System.out.println("\nAlle reparieren:");
        double sum = g.repairAll();
        System.out.println("Summe weitere Reparaturen: " + String.format("%.2f", sum) + " CHF");
        g.printOverview();
    }
}
