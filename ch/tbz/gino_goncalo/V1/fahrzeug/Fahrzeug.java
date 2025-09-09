package ch.tbz.gino_goncalo.V1.fahrzeug;

abstract class Fahrzeug {
    protected String hersteller;
    protected int baujahr;

    public Fahrzeug(String hersteller, int baujahr) {
        this.hersteller = hersteller;
        this.baujahr = baujahr;
    }

    public abstract void bewegeDich();

    public String getHersteller() {
        return hersteller;
    }

    public int getBaujahr() {
        return baujahr;
    }
}