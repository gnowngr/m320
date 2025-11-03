package ch.tbz.gino_goncalo.M4_Gonçalo.exception;

/**
 * Exception wenn Username bereits existiert
 */
public class UserAlreadyExistsException extends Exception {

    private String username;

    public UserAlreadyExistsException(String username) {
        super("Benutzer mit Username '" + username + "' existiert bereits!");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
