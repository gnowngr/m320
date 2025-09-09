package ch.tbz.gino_goncalo.V1.fahrzeug;

class Fahrrad extends Fahrzeug {
    private boolean hatKlingel;

    public Fahrrad(String hersteller, int baujahr, boolean hatKlingel) {
        super(hersteller, baujahr);
        this.hatKlingel = hatKlingel;
    }

    @Override
    public void bewegeDich() {
        System.out.println("Das Fahrrad fährt auf dem Radweg.");
    }

    public boolean hatKlingel() {
        return hatKlingel;
    }
}