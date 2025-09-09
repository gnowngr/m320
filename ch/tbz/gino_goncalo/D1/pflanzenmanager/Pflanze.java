package ch.tbz.gino_goncalo.D1.pflanzenmanager;

public class Pflanze {
    protected String name;
    protected String art;
    protected String wasserbedarf;
    protected String lichtbedarf;
    protected String gesundheitszustand;

    public Pflanze(String name, String art, String wasserbedarf, String lichtbedarf, String gesundheitszustand) {
        this.name = name;
        this.art = art;
        this.wasserbedarf = wasserbedarf;
        this.lichtbedarf = lichtbedarf;
        this.gesundheitszustand = gesundheitszustand;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getArt() { return art; }
    public void setArt(String art) { this.art = art; }
    public String getWasserbedarf() { return wasserbedarf; }
    public void setWasserbedarf(String wasserbedarf) { this.wasserbedarf = wasserbedarf; }
    public String getLichtbedarf() { return lichtbedarf; }
    public void setLichtbedarf(String lichtbedarf) { this.lichtbedarf = lichtbedarf; }
    public String getGesundheitszustand() { return gesundheitszustand; }
    public void setGesundheitszustand(String gesundheitszustand) { this.gesundheitszustand = gesundheitszustand; }

    public void giessen() {
        System.out.println(this.name + " (Pflanze) wurde generell gegossen.");
    }

    public void giessen(int mengeInMl) {
        System.out.println(this.name + " (Pflanze) wurde mit " + mengeInMl + "ml Wasser gegossen.");
    }

    public void duengen() {
        System.out.println(this.name + " (Pflanze) wurde gedüngt.");
    }

    public void prüfenGesundheit() {
        if ("gesund".equals(this.gesundheitszustand)) {
            System.out.println(this.name + " ist gesund.");
        } else if ("krank".equals(this.gesundheitszustand)) {
            System.out.println(this.name + " ist krank. Pflege erforderlich!");
        } else {
            System.out.println(this.name + " hat einen unbekannten Gesundheitszustand: " + this.gesundheitszustand);
        }
    }
}