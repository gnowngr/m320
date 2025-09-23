package ch.tbz.gino_goncalo.V3.figuren;

import ch.tbz.gino_goncalo.V3.waffen.WaffenVerhalten;

public abstract class Figur {
    private String name;
    private WaffenVerhalten waffenVerhalten;

    protected Figur(String name, WaffenVerhalten waffenVerhalten) {
        this.name = name;
        this.waffenVerhalten = waffenVerhalten;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WaffenVerhalten getWaffenVerhalten() {
        return waffenVerhalten;
    }

    public void setWaffenVerhalten(WaffenVerhalten waffenVerhalten) {
        this.waffenVerhalten = waffenVerhalten;
    }

    public String laufen() {
        return name + " läuft.";
    }

    public String kaempfen() {
        return name + " kämpft mit " + waffenVerhalten.verwendeteWaffe() + ".";
    }
}


