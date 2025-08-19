package ch.tbz.gino_goncalo.D1.pflanzenmanager;

import java.util.ArrayList;
import java.util.List;

public class PflanzenpflegeTest {
    public static void main(String[] args) {
        List<Pflanze> pflanzenListe = new ArrayList<>();

        Pflanze rose = new Pflanze("Rose", "Blume", "mittel", "viel", "gesund");
        Pflanze kaktus = new Pflanze("Kaktus", "Sukkulente", "wenig", "wenig", "gesund");
        Pflanze tulpe = new Pflanze("Tulpe", "Blume", "hoch", "mittel", "krank");

        pflanzenListe.add(rose);
        pflanzenListe.add(kaktus);
        pflanzenListe.add(tulpe);

        Garten garten = new Garten(pflanzenListe);

        garten.zeigeGesundheitszustand();

        garten.pflegeAllePflanzen();

        for (Pflanze pflanze : pflanzenListe) {
            pflanze.prüfenGesundheit();
        }
    }
}
