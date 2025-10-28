package ch.tbz.gino_goncalo.D1.pflanzenmanager;

import java.util.ArrayList;

/**
 * Testklasse zum Ausprobieren von {@link Pflanze}-Objekten
 * in einem {@link Garten}.
 */
public class PflanzenpflegeTest {

    /**
     * Creates a new plant care test instance.
     */
    public PflanzenpflegeTest() {
        // Default constructor
    }

    /**
     * Startpunkt des Testprogramms.
     * Erstellt einige Beispielpflanzen und zeigt deren Pflegeablauf.
     *
     * @param args nicht verwendet
     */
    public static void main(String[] args) {
        ArrayList<Pflanze> pflanzenListe = new ArrayList<>();
        pflanzenListe.add(new Blume("Rose", "Mittel", "Sonne", "Gesund", "Rot"));
        pflanzenListe.add(new Sukkulente("Aloe Vera", "Gering", "Sonne", "Sehr gesund", true));

        Garten garten = new Garten(pflanzenListe);

        garten.pflegeAllePflanzen();
        garten.zeigeGesundheitszustand();
    }
}