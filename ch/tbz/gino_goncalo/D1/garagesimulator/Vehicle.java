package ch.tbz.gino_goncalo.D1.garagesimulator;


public abstract class Vehicle {
    private final String licensePlate;
    private final String brand;
    private final String model;
    private final String color;
    private int condition;       // 0..100
    private boolean repaired;
    private double repairCosts;

    public Vehicle(String licensePlate, String brand, String model, String color, int condition) {
        if (licensePlate == null || licensePlate.isBlank()) throw new IllegalArgumentException("licensePlate");
        if (brand == null || brand.isBlank()) throw new IllegalArgumentException("brand");
        if (model == null || model.isBlank()) throw new IllegalArgumentException("model");
        if (color == null || color.isBlank()) throw new IllegalArgumentException("color");
        if (condition < 0) condition = 0;
        if (condition > 100) condition = 100;
        this.licensePlate = licensePlate.trim();
        this.brand = brand.trim();
        this.model = model.trim();
        this.color = color.trim();
        this.condition = condition;
        this.repaired = false;
        this.repairCosts = 0.0;
    }

    public Vehicle(String licensePlate, String brand, String model, String color) {
        this(licensePlate, brand, model, color, 100);
    }

    public String getLicensePlate() { return licensePlate; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getColor() { return color; }
    public int getCondition() { return condition; }
    public boolean isRepaired() { return repaired; }
    public double getRepairCosts() { return repairCosts; }

    public void damage(int amount) {
        if (amount < 0) return;
        condition -= amount;
        if (condition < 0) condition = 0;
        repaired = false;
    }

    public void repair(double costs) {
        if (costs < 0) throw new IllegalArgumentException("costs >= 0 required");
        repaired = true;
        repairCosts = costs;
        if (condition < 100) condition = Math.min(100, condition + 20); // kleine Verbesserung
    }

    // Überladen: Standard-Berechnung über Zustand
    public double calculateRepairCost() {
        // Basis abhängig vom Schaden (100 - condition) und einfacher Faktor
        double damageFactor = Math.max(0, 100 - condition);
        return 100 + damageFactor * 5; // Default-Formel, kann überschrieben werden
    }

    @Override
    public String toString() {
        return brand + " " + model + " [" + licensePlate + "], " + color +
                " | Zustand: " + condition +
                " | Repariert: " + (repaired ? "ja" : "nein") +
                " | Kosten: " + String.format("%.2f", repairCosts) + " CHF";
    }
}
