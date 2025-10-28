package ch.tbz.gino_goncalo.D1.pflanzenmanager;

/**
 * Eine spezielle {@link Pflanze}, die zusätzlich eine Blütenfarbe besitzt.
 */
public class Blume extends Pflanze {

    /** Die Blütenfarbe der Blume. */
    private String bluetenfarbe;

    /**
     * Erstellt eine neue Blume.
     *
     * @param name Name der Blume
     * @param wasserbedarf Wasserbedarf
     * @param lichtbedarf Lichtbedarf
     * @param gesundheitszustand Gesundheitszustand
     * @param bluetenfarbe Blütenfarbe
     */
    public Blume(String name, String wasserbedarf, String lichtbedarf,
                 String gesundheitszustand, String bluetenfarbe) {
        super(name, "Blume", wasserbedarf, lichtbedarf, gesundheitszustand);
        this.bluetenfarbe = bluetenfarbe;
    }

    /**
     * Liefert die Blütenfarbe dieser Blume.
     *
     * @return die Blütenfarbe
     */
    public String getBluetenfarbe() {
        return bluetenfarbe;
    }
}