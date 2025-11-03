package ch.tbz.gino_goncalo.M4_Gonçalo.exception;

/**
 * Exception fuer falsche Login-Daten
 */
public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException() {
        super("Falscher Benutzername oder Passwort!");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
