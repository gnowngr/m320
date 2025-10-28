package ch.tbz.gino_goncalo.V1.student;

/**
 * Represents a student with a name and a unique matriculation number.
 * Implements {@link Comparable} to allow comparison by matriculation number.
 */
class Student implements Comparable<Student> {

    /** The name of the student. */
    private String name;

    /** The student's unique matriculation number. */
    private int matrikelnummer;

    /**
     * Creates a new {@code Student} with the given name and matriculation number.
     *
     * @param name the full name of the student
     * @param matrikelnummer the matriculation number (unique ID)
     */
    public Student(String name, int matrikelnummer) {
        this.name = name;
        this.matrikelnummer = matrikelnummer;
    }

    /**
     * Returns the student's name.
     *
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the student's matriculation number.
     *
     * @return the matriculation number
     */
    public int getMatrikelnummer() {
        return matrikelnummer;
    }

    /**
     * Provides a string representation of the student in the format
     * {@code Student [Name=..., Matrikelnummer=...]}.
     *
     * @return a string representation of this student
     */
    @Override
    public String toString() {
        return "Student [Name=" + name + ", Matrikelnummer=" + matrikelnummer + "]";
    }

    /**
     * Compares this student with another student by matriculation number.
     *
     * @param anderer the other student to compare with
     * @return a negative integer, zero, or a positive integer as this student's
     *         matriculation number is less than, equal to, or greater than the other student's
     */
    @Override
    public int compareTo(Student anderer) {
        return Integer.compare(this.matrikelnummer, anderer.matrikelnummer);
    }
}