package ch.tbz.gino_goncalo.Q3.airports;

import ch.tbz.gino_goncalo.Q3.airports.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        AirportManager manager = new AirportManager();
        manager.loadFromCSV("airports.csv");

        UserInterface ui = new UserInterface(manager);
        ui.start();
    }
}
