public class ElectricCar extends Car {
    private final int batteryKwh;

    public ElectricCar(String licensePlate, String brand, String model, String color, int condition, int doorCount, int batteryKwh) {
        super(licensePlate, brand, model, color, condition, doorCount);
        this.batteryKwh = Math.max(0, batteryKwh);
    }

    public ElectricCar(String licensePlate, String brand, String model, String color, int doorCount, int batteryKwh) {
        super(licensePlate, brand, model, color, doorCount);
        this.batteryKwh = Math.max(0, batteryKwh);
    }

    public int getBatteryKwh() { return batteryKwh; }

    @Override
    public double calculateRepairCost() {
        return super.calculateRepairCost() + 150.0;
    }
}


