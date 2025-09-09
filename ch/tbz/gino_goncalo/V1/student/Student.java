package ch.tbz.gino_goncalo.V1.student;

class Student implements Comparable<Student> {
    private String name;
    private int matrikelnummer;

    public Student(String name, int matrikelnummer) {
        this.name = name;
        this.matrikelnummer = matrikelnummer;
    }

    public String getName() {
        return name;
    }

    public int getMatrikelnummer() {
        return matrikelnummer;
    }

    @Override
    public String toString() {
        return "Student [Name=" + name + ", Matrikelnummer=" + matrikelnummer + "]";
    }

    @Override
    public int compareTo(Student anderer) {
        return Integer.compare(this.matrikelnummer, anderer.matrikelnummer);
    }
}