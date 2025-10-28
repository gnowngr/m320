package ch.tbz.gino_goncalo.V1.fahrzeug;

/**
 * Represents a bicycle as a specific type of {@link Fahrzeug}.
 * A bicycle can optionally have a bike bell.
 */
class Fahrrad extends Fahrzeug {

    /** Indicates whether this bicycle has a bell. */
    private boolean hatKlingel;

    /**
     * Constructs a new {@code Fahrrad} with the given manufacturer, year, and bell option.
     *
     * @param hersteller the manufacturer of the bicycle
     * @param baujahr    the year of manufacture
     * @param hatKlingel {@code true} if the bicycle has a bell, {@code false} otherwise
     */
    public Fahrrad(String hersteller, int baujahr, boolean hatKlingel) {
        super(hersteller, baujahr);
        this.hatKlingel = hatKlingel;
    }

    /**
     * Prints a message to simulate the bicycle being ridden.
     */
    @Override
    public void bewegeDich() {
        System.out.println("Das Fahrrad fährt auf dem Radweg.");
    }

    /**
     * Indicates whether this bicycle has a bell.
     *
     * @return {@code true} if the bicycle has a bell, {@code false} otherwise
     */
    public boolean hatKlingel() {
        return hatKlingel;
    }
}