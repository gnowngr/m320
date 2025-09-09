package ch.tbz.gino_goncalo.D1.pflanzenmanager;

public class Sukkulente extends Pflanze {

    private final boolean hatStacheln;

    public Sukkulente(String name, String wasserbedarf, String lichtbedarf, String gesundheitszustand, boolean hatStacheln) {
        super(name, "Sukkulente", wasserbedarf, lichtbedarf, gesundheitszustand);
        this.hatStacheln = hatStacheln;
    }

    public boolean hatStacheln() {
        return hatStacheln;
    }

    @Override
    public void giessen() {
        System.out.println("Die Sukkulente '" + name + "' wird nur sehr sparsam gegossen (Wasserbedarf: " + wasserbedarf + ").");
    }

    @Override
    public void duengen() {
        System.out.println("Die Sukkulente '" + name + "' wird mit speziellem Kakteendünger gedüngt.");
    }
}