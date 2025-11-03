package ch.tbz.gino_goncalo.M4_Gonçalo.service;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.User;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.UserRepository;

/**
 * Service fuer User-Operationen
 * Delegation: Delegiert an UserRepository
 * Clean Code: SRP - nur User-Business-Logik
 */
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registriert neuen User
     */
    public User register(String username, String password, String firstName, String lastName) throws Exception {
        // Validierung: Username muss eindeutig sein
        if(userRepository.existsByUsername(username)) {
            throw new Exception("Username existiert bereits!");
        }

        // Validierung: Passwort-Laenge
        if(password.length() < 6) {
            throw new Exception("Passwort muss mind. 6 Zeichen haben!");
        }

        User user = new User(username, password, firstName, lastName);
        userRepository.save(user);
        return user;
    }

    /**
     * Login-Funktion
     */
    public User login(String username, String password) throws Exception {
        User user = userRepository.authenticate(username, password);
        if(user == null) {
            throw new Exception("Falscher Username oder Passwort!");
        }
        return user;
    }

    /**
     * Findet User nach Username
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
