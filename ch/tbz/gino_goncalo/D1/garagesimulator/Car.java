package ch.tbz.gino_goncalo.D1.garagesimulator;


public class Car extends Vehicle {
    private final int doorCount;

    public Car(String licensePlate, String brand, String model, String color, int condition, int doorCount) {
        super(licensePlate, brand, model, color, condition);
        this.doorCount = Math.max(2, doorCount);
    }

    public Car(String licensePlate, String brand, String model, String color, int doorCount) {
        super(licensePlate, brand, model, color);
        this.doorCount = Math.max(2, doorCount);
    }

    public int getDoorCount() { return doorCount; }

    @Override
    public double calculateRepairCost() {
        return super.calculateRepairCost() + doorCount * 20.0;
    }
}


