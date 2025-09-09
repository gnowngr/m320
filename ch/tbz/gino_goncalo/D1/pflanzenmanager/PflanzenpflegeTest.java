package ch.tbz.gino_goncalo.D1.pflanzenmanager;

import java.util.ArrayList;
import java.util.List;

public class PflanzenpflegeTest {
    public static void main(String[] args) {
        List<Pflanze> pflanzenListe = new ArrayList<>();

        Blume rose = new Blume("Rose", "mittel", "viel", "gesund", "rot");
        Sukkulente kaktus = new Sukkulente("Kaktus", "wenig", "sehr viel", "gesund", true);
        Blume tulpe = new Blume("Tulpe", "hoch", "mittel", "krank", "gelb");

        pflanzenListe.add(rose);
        pflanzenListe.add(kaktus);
        pflanzenListe.add(tulpe);

        Garten garten = new Garten(pflanzenListe);

        garten.zeigeGesundheitszustand();
        garten.pflegeAllePflanzen();

        System.out.println("\n--- Test von Überladung ---");
        rose.giessen();
        rose.giessen(150);
    }
}