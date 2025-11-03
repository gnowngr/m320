package ch.tbz.gino_goncalo.M4_Gonçalo.repository;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository Pattern fuer User-Persistenz
 * Clean Code: SRP - verantwortlich nur fuer User-Datenzugriff
 */
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    /**
     * Speichert User
     */
    public void save(User user) {
        users.add(user);
    }

    /**
     * Findet User nach Username
     */
    public User findByUsername(String username) {
        for(User u : users) {
            if(u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Authentifiziert User
     * Prueft Username und Passwort
     */
    public User authenticate(String username, String password) {
        User user = findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    /**
     * Gibt alle Users zurueck
     */
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    /**
     * Loescht User
     */
    public void delete(User user) {
        users.remove(user);
    }

    /**
     * Prueft ob Username existiert
     */
    public boolean existsByUsername(String username) {
        return findByUsername(username) != null;
    }

    /**
     * Anzahl User
     */
    public int count() {
        return users.size();
    }
}
