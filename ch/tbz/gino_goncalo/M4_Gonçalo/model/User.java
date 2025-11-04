package ch.tbz.gino_goncalo.M4_Gonçalo.model;

import ch.tbz.gino_goncalo.M4_Gonçalo.exception.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repraesentiert einen Benutzer im Bankensystem
 * Demonstriert Delegation: User HAT mehrere Accounts
 * Clean Code: SRP - User verwaltet nur Benutzer-spezifische Daten
 */
public class User {

    private String userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    // Delegation: User HAT eine Liste von Accounts
    // Komposition: Accounts werden im Konstruktor initialisiert
    private List<Account> accounts;

    public User(String username, String password, String firstName, String lastName) {
        this.userId = generateUserId();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = "";
        this.accounts = new ArrayList<>();
    }

    public User(String username, String password, String firstName, String lastName, String email) {
        this.userId = generateUserId();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    /**
     * Generiert eindeutige User ID
     */
    private String generateUserId() {
        return "USR" + System.currentTimeMillis();
    }

    /**
     * Fuegt Account hinzu
     * Delegation: User delegiert Konten-Verwaltung
     */
    public void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     * Entfernt Account
     */
    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    /**
     * Sucht Account nach IBAN
     */
    public Account findAccountByIban(String iban) throws AccountNotFoundException {
        for(Account acc : accounts) {
            if(acc.getIban().equals(iban)) {
                return acc;
            }
        }
        throw new AccountNotFoundException(iban);
    }

    /**
     * Berechnet Gesamtvermoegen des Users
     * Delegation: Verwendet balance von jedem Account
     */
    public double getTotalBalance() {
        double total = 0;
        for(Account acc : accounts) {
            total = total + acc.getBalance();
        }
        return total;
    }

    // Getter
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User: " + getFullName() +
               " | Username: " + username +
               " | Anzahl Konten: " + accounts.size();
    }
}
