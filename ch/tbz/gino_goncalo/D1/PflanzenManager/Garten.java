package ch.tbz.gino_goncalo.D1.PflanzenManager;

import java.util.List;

public class Garten {
    private List<Pflanze> pflanzen;

    public Garten(List<Pflanze> pflanzen) {
        this.pflanzen = pflanzen;
    }

    public void pflanzenHinzufuegen(Pflanze pflanze) {
        pflanzen.add(pflanze);
        System.out.println(pflanze.getName() + " wurde zum Garten hinzugefügt.");
    }

    public void pflegeAllePflanzen() {
        for (Pflanze pflanze : pflanzen) {
            pflanze.giessen();
        }
    }

    public void zeigeGesundheitszustand() {
        for (Pflanze pflanze : pflanzen) {
            System.out.println(pflanze.getName() + " ist " + pflanze.getGesundheitszustand() + ".");
        }
    }
}
