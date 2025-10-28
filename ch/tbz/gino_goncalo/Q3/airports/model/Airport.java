package ch.tbz.gino_goncalo.Q3.airports.model;

public class Airport {
    private String code;
    private String name;

    public Airport(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", code, name);
    }
}
