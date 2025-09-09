public class Motorcycle extends Vehicle {
    private final boolean abs;

    public Motorcycle(String licensePlate, String brand, String model, String color, int condition, boolean abs) {
        super(licensePlate, brand, model, color, condition);
        this.abs = abs;
    }

    public Motorcycle(String licensePlate, String brand, String model, String color, boolean abs) {
        super(licensePlate, brand, model, color);
        this.abs = abs;
    }

    public boolean hasAbs() { return abs; }

    @Override
    public double calculateRepairCost() {
        return super.calculateRepairCost() - 50.0 + (abs ? 50.0 : 0.0);
    }
}


