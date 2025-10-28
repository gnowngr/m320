package ch.tbz.gino_goncalo.D1.pflanzenmanager;

/**
 * Eine spezielle {@link Pflanze}, die Sukkulent ist
 * und zusätzlich die Information speichert, ob sie Stacheln hat.
 */
public class Sukkulente extends Pflanze {

    /** Gibt an, ob die Sukkulente Stacheln hat. */
    private boolean hatStacheln;

    /**
     * Erstellt eine neue Sukkulente.
     *
     * @param name Name der Pflanze
     * @param wasserbedarf Wasserbedarf
     * @param lichtbedarf Lichtbedarf
     * @param gesundheitszustand Gesundheitszustand
     * @param hatStacheln {@code true} falls die Pflanze Stacheln hat
     */
    public Sukkulente(String name, String wasserbedarf, String lichtbedarf,
                      String gesundheitszustand, boolean hatStacheln) {
        super(name, "Sukkulente", wasserbedarf, lichtbedarf, gesundheitszustand);
        this.hatStacheln = hatStacheln;
    }

    /**
     * Prüft, ob diese Sukkulente Stacheln hat.
     *
     * @return {@code true} wenn die Pflanze Stacheln hat, sonst {@code false}
     */
    public boolean hatStacheln() {
        return hatStacheln;
    }
}