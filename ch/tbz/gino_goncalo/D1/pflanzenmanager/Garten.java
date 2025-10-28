package ch.tbz.gino_goncalo.D1.pflanzenmanager;

import java.util.List;

/**
 * Repräsentiert einen Garten, in dem mehrere {@link Pflanze} verwaltet werden.
 */
public class Garten {

    /** Liste aller Pflanzen im Garten. */
    private List<Pflanze> pflanzen;

    /**
     * Erstellt einen neuen Garten mit einer Liste von Pflanzen.
     *
     * @param pflanzen die Anfangsliste der Pflanzen
     */
    public Garten(List<Pflanze> pflanzen) {
        this.pflanzen = pflanzen;
    }

    /**
     * Fügt eine neue Pflanze zum Garten hinzu.
     *
     * @param pflanze die hinzuzufügende Pflanze
     */
    public void pflanzenHinzufuegen(Pflanze pflanze) {
        pflanzen.add(pflanze);
    }

    /** Pflegt alle Pflanzen im Garten durch Gießen und Düngen. */
    public void pflegeAllePflanzen() {
        for (Pflanze p : pflanzen) {
            p.giessen();
            p.duengen();
        }
    }

    /** Zeigt für alle Pflanzen den aktuellen Gesundheitszustand. */
    public void zeigeGesundheitszustand() {
        for (Pflanze p : pflanzen) {
            p.prüfenGesundheit();
        }
    }
}