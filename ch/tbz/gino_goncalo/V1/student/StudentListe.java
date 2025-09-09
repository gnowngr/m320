package ch.tbz.gino_goncalo.V1.student;

import java.util.ArrayList;
import java.util.Iterator;

class StudentenListe extends ArrayList<Student> {

    public void gibStudentenAus() {
        Iterator<Student> iterator = this.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}