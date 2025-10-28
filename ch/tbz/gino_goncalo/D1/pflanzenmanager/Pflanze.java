package ch.tbz.gino_goncalo.D1.pflanzenmanager;

/**
 * Repräsentiert eine Pflanze mit Eigenschaften wie Name, Art,
 * Wasserbedarf, Lichtbedarf und Gesundheitszustand.
 */
public class Pflanze {

    /** Name der Pflanze. */
    protected String name;

    /** Art der Pflanze. */
    protected String art;

    /** Wasserbedarf der Pflanze. */
    protected String wasserbedarf;

    /** Lichtbedarf der Pflanze. */
    protected String lichtbedarf;

    /** Aktueller Gesundheitszustand der Pflanze. */
    protected String gesundheitszustand;

    /**
     * Erstellt eine neue Pflanze.
     *
     * @param name Name der Pflanze
     * @param art Art der Pflanze
     * @param wasserbedarf erforderlicher Wasserbedarf
     * @param lichtbedarf erforderlicher Lichtbedarf
     * @param gesundheitszustand aktueller Gesundheitszustand
     */
    public Pflanze(String name, String art, String wasserbedarf, String lichtbedarf, String gesundheitszustand) {
        this.name = name;
        this.art = art;
        this.wasserbedarf = wasserbedarf;
        this.lichtbedarf = lichtbedarf;
        this.gesundheitszustand = gesundheitszustand;
    }

    /**
     * Liefert den Namen der Pflanze.
     *
     * @return Name der Pflanze
     */
    public String getName() { return name; }

    /**
     * Setzt den Namen der Pflanze.
     *
     * @param name neuer Name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Liefert die Art der Pflanze.
     *
     * @return Art der Pflanze
     */
    public String getArt() { return art; }

    /**
     * Setzt die Art der Pflanze.
     *
     * @param art neue Art
     */
    public void setArt(String art) { this.art = art; }

    /**
     * Liefert den Wasserbedarf der Pflanze.
     *
     * @return Wasserbedarf als String
     */
    public String getWasserbedarf() { return wasserbedarf; }

    /**
     * Setzt den Wasserbedarf der Pflanze.
     *
     * @param wasserbedarf neuer Wasserbedarf
     */
    public void setWasserbedarf(String wasserbedarf) { this.wasserbedarf = wasserbedarf; }

    /**
     * Liefert den Lichtbedarf der Pflanze.
     *
     * @return Lichtbedarf als String
     */
    public String getLichtbedarf() { return lichtbedarf; }

    /**
     * Setzt den Lichtbedarf der Pflanze.
     *
     * @param lichtbedarf neuer Lichtbedarf
     */
    public void setLichtbedarf(String lichtbedarf) { this.lichtbedarf = lichtbedarf; }

    /**
     * Liefert den aktuellen Gesundheitszustand der Pflanze.
     *
     * @return aktueller Gesundheitszustand
     */
    public String getGesundheitszustand() { return gesundheitszustand; }

    /**
     * Setzt den Gesundheitszustand der Pflanze.
     *
     * @param gesundheitszustand neuer Gesundheitszustand
     */
    public void setGesundheitszustand(String gesundheitszustand) { this.gesundheitszustand = gesundheitszustand; }

    /**
     * Gießt die Pflanze mit Standardmenge.
     */
    public void giessen() {
        System.out.println(name + " wurde gegossen.");
    }

    /**
     * Gießt die Pflanze mit spezifischer Wassermenge.
     *
     * @param mengeInMl Wassermenge in Millilitern
     */
    public void giessen(int mengeInMl) {
        System.out.println(name + " wurde mit " + mengeInMl + "ml gegossen.");
    }

    /**
     * Düngt die Pflanze.
     */
    public void duengen() {
        System.out.println(name + " wurde gedüngt.");
    }

    /**
     * Prüft und gibt den Gesundheitszustand der Pflanze aus.
     */
    public void prüfenGesundheit() {
        System.out.println(name + " ist aktuell: " + gesundheitszustand);
    }
}