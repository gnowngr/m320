package ch.tbz.gino_goncalo.D1.garagesimulator;

public class GarageSimulator {
    public static void main(String[] args) {
        Garage g = new Garage("M320 Garage");

        Vehicle a = new Vehicle("ZH-12345", "VW", "Golf", "blau", 85);
        Vehicle b = new Vehicle("AG-98765", "Toyota", "Corolla", "rot", 60);
        Vehicle c = new Vehicle("BE-22222", "BMW", "320i", "schwarz"); // Zustand default 100

        g.registerVehicle(a);
        g.registerVehicle(b);
        g.registerVehicle(c);

        System.out.println("Initial:");
        g.printOverview();

        g.damageVehicle("BE-22222", 35);
        g.damageVehicle("AG-98765", 15);

        System.out.println("\nNach Schäden:");
        g.printOverview();

        g.repairVehicle("AG-98765", 720.50);
        g.repairVehicle("ZH-12345", 480.00);

        System.out.println("\nNach Reparaturen:");
        g.printOverview();

        System.out.println("\nReparierte Fahrzeuge:");
        for (var v : g.repairedVehicles()) System.out.println(v);

        System.out.println("\nOffene Fahrzeuge:");
        for (var v : g.pendingVehicles()) System.out.println(v);
    }
}
