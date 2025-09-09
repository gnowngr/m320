package ch.tbz.gino_goncalo.D1.pflanzenmanager;

public class Blume extends Pflanze {

    private final String bluetenfarbe;

    public Blume(String name, String wasserbedarf, String lichtbedarf, String gesundheitszustand, String bluetenfarbe) {
        super(name, "Blume", wasserbedarf, lichtbedarf, gesundheitszustand);
        this.bluetenfarbe = bluetenfarbe;
    }

    public String getBluetenfarbe() {
        return bluetenfarbe;
    }

    @Override
    public void giessen() {
        System.out.println("Die Blume '" + this.name + "' wird vorsichtig am Stiel gegossen, damit die Blüten (" + this.bluetenfarbe + ") nicht beschädigt werden.");
    }

    @Override
    public void duengen() {
        System.out.println("Die Blume '" + this.name + "' erhält speziellen Blumendünger.");
    }
}