package ch.tbz.gino_goncalo.M4_Gonçalo.service;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.User;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.UserRepository;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.UserAlreadyExistsException;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InvalidCredentialsException;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InvalidAmountException;

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
     * Verwendet Custom Exceptions fuer besseres Error Handling
     */
    public User register(String username, String password, String firstName, String lastName)
        throws UserAlreadyExistsException, InvalidAmountException {

        // Validierung: Username muss eindeutig sein
        if(userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException(username);
        }

        // Validierung: Passwort-Laenge
        if(password.length() < 6) {
            throw new InvalidAmountException("Passwort muss mind. 6 Zeichen haben!");
        }

        User user = new User(username, password, firstName, lastName);
        userRepository.save(user);
        return user;
    }

    /**
     * Login-Funktion
     * Verwendet Custom Exception
     */
    public User login(String username, String password) throws InvalidCredentialsException {
        User user = userRepository.authenticate(username, password);
        if(user == null) {
            throw new InvalidCredentialsException();
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
