package ch.tbz.gino_goncalo.D1.PflanzenManager;

public class Pflanze {
    private String name;
    private String art;
    private String wasserbedarf;
    private String lichtbedarf;
    private String gesundheitszustand;

    public Pflanze(String name, String art, String wasserbedarf, String lichtbedarf, String gesundheitszustand) {
        this.name = name;
        this.art = art;
        this.wasserbedarf = wasserbedarf;
        this.lichtbedarf = lichtbedarf;
        this.gesundheitszustand = gesundheitszustand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getWasserbedarf() {
        return wasserbedarf;
    }

    public void setWasserbedarf(String wasserbedarf) {
        this.wasserbedarf = wasserbedarf;
    }

    public String getLichtbedarf() {
        return lichtbedarf;
    }

    public void setLichtbedarf(String lichtbedarf) {
        this.lichtbedarf = lichtbedarf;
    }

    public String getGesundheitszustand() {
        return gesundheitszustand;
    }

    public void setGesundheitszustand(String gesundheitszustand) {
        this.gesundheitszustand = gesundheitszustand;
    }

    public void giessen() {
        System.out.println(name + " wurde gegossen.");
    }

    public void duengen() {
        System.out.println(name + " wurde gedüngt.");
    }

    public void prüfenGesundheit() {
        if (gesundheitszustand.equals("gesund")) {
            System.out.println(name + " ist gesund.");
        } else if (gesundheitszustand.equals("krank")) {
            System.out.println(name + " ist krank. Sofortige Pflege erforderlich!");
        } else {
            System.out.println(name + " hat einen unbekannten Gesundheitszustand: " + gesundheitszustand);
        }
    }

}
