package ch.tbz.gino_goncalo.V1.student;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        StudentenListe liste = new StudentenListe();
        liste.add(new Student("Max Müller", 12345));
        liste.add(new Student("Erika Mustermann", 12340));
        liste.add(new Student("Peter Pan", 12350));

        System.out.println("Unsortierte Liste:");
        liste.gibStudentenAus();

        Collections.sort(liste);

        System.out.println("\nSortierte Liste (nach Matrikelnummer):");
        liste.gibStudentenAus();
    }
}