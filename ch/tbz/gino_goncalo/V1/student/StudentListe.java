package ch.tbz.gino_goncalo.V1.student;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A specialized list for managing {@link Student} objects.
 * Extends {@link ArrayList} to provide additional functionality for student management.
 */
class StudentenListe extends ArrayList<Student> {

    /**
     * Prints all students in the list to the console.
     * Each student is printed on a separate line using their {@code toString()} method.
     */
    public void gibStudentenAus() {
        Iterator<Student> iterator = this.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}