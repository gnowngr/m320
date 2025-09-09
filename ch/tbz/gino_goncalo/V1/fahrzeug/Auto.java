package ch.tbz.gino_goncalo.V1.fahrzeug;

class Auto extends Fahrzeug {
    private int anzahlTueren;

    public Auto(String hersteller, int baujahr, int anzahlTueren) {
        super(hersteller, baujahr);
        this.anzahlTueren = anzahlTueren;
    }

    @Override
    public void bewegeDich() {
        System.out.println("Das Auto fährt auf der Strasse.");
    }

    public int getAnzahlTueren() {
        return anzahlTueren;
    }
}
