package ch.tbz.gino_goncalo.M4_Gonçalo.repository;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.Account;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository Pattern fuer Account-Persistenz
 *
 * Design Pattern: Repository Pattern
 * Begründung: Trennt Datenzugriff von Business-Logik.
 * Ermoeglicht Wechsel der Speicher-Methode (z.B. File zu DB)
 * ohne Aenderung der Business-Logik.
 * Clean Code: SRP - nur fuer Datenpersistenz zustaendig
 *
 * Delegation: Service-Klassen delegieren Datenzugriff an Repository
 */
public class AccountRepository {

    // In-Memory Storage (koennte spaeter durch File/DB ersetzt werden)
    private List<Account> accounts;

    public AccountRepository() {
        this.accounts = new ArrayList<>();
    }

    /**
     * Speichert Account
     */
    public void save(Account account) {
        accounts.add(account);
    }

    /**
     * Findet Account nach IBAN
     */
    public Account findByIban(String iban) throws AccountNotFoundException {
        for(Account acc : accounts) {
            if(acc.getIban().equals(iban)) {
                return acc;
            }
        }
        throw new AccountNotFoundException(iban);
    }

    /**
     * Gibt alle Accounts zurueck
     */
    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }

    /**
     * Loescht Account
     */
    public void delete(Account account) {
        accounts.remove(account);
    }

    /**
     * Findet alle Accounts eines Besitzers
     */
    public List<Account> findByOwner(String ownerName) {
        List<Account> result = new ArrayList<>();
        for(Account acc : accounts) {
            if(acc.getOwnerName().equals(ownerName)) {
                result.add(acc);
            }
        }
        return result;
    }

    /**
     * Prueft ob IBAN existiert
     */
    public boolean existsByIban(String iban) {
        try {
            findByIban(iban);
            return true;
        } catch(AccountNotFoundException e) {
            return false;
        }
    }

    /**
     * Anzahl gespeicherter Accounts
     */
    public int count() {
        return accounts.size();
    }
}
