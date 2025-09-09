package ch.tbz.gino_goncalo.V1.fahrzeug;

public class Main {
    public static void main(String[] args) {
        Fahrzeug meinAuto = new Auto("Volkswagen", 2021, 5);
        Fahrzeug meinFahrrad = new Fahrrad("Canyon", 2022, true);

        meinAuto.bewegeDich();
        meinFahrrad.bewegeDich();

        System.out.println("Mein Auto ist von: " + meinAuto.getHersteller());
        System.out.println("Mein Fahrrad ist aus dem Jahr: " + meinFahrrad.getBaujahr());
    }
}