package ch.tbz.gino_goncalo.D1.pflanzenmanager;

import java.util.List;

public class Garten {
    private final List<Pflanze> pflanzen;

    public Garten(List<Pflanze> pflanzen) {
        this.pflanzen = pflanzen;
    }

    public void pflanzenHinzufuegen(Pflanze pflanze) {
        pflanzen.add(pflanze);
        System.out.println(pflanze.getName() + " wurde zum Garten hinzugefügt.");
    }

    public void pflegeAllePflanzen() {
        System.out.println("\n--- Alle Pflanzen werden gepflegt ---");
        for (Pflanze pflanze : pflanzen) {
            pflanze.giessen();
            pflanze.duengen();
        }
    }

    public void zeigeGesundheitszustand() {
        System.out.println("\n--- Gesundheitszustand der Pflanzen ---");
        for (Pflanze pflanze : pflanzen) {
            System.out.println(pflanze.getName() + " ist " + pflanze.getGesundheitszustand() + ".");
        }
    }
}