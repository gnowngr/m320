public class Truck extends Vehicle {
    private final double payloadTons;

    public Truck(String licensePlate, String brand, String model, String color, int condition, double payloadTons) {
        super(licensePlate, brand, model, color, condition);
        this.payloadTons = Math.max(0, payloadTons);
    }

    public Truck(String licensePlate, String brand, String model, String color, double payloadTons) {
        super(licensePlate, brand, model, color);
        this.payloadTons = Math.max(0, payloadTons);
    }

    public double getPayloadTons() { return payloadTons; }

    @Override
    public double calculateRepairCost() {
        return super.calculateRepairCost() + payloadTons * 80.0 + 200.0;
    }
}


