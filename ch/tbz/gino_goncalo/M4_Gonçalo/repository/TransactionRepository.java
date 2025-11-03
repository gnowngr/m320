package ch.tbz.gino_goncalo.M4_Gonçalo.repository;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository Pattern fuer Transaction-Persistenz
 * Speichert Transaktionshistorie
 */
public class TransactionRepository {

    private List<Transaction> transactions;

    public TransactionRepository() {
        this.transactions = new ArrayList<>();
    }

    /**
     * Speichert Transaction
     */
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Gibt alle Transactions zurueck
     */
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }

    /**
     * Findet Transactions nach IBAN
     */
    public List<Transaction> findByIban(String iban) {
        List<Transaction> result = new ArrayList<>();
        for(Transaction t : transactions) {
            // Suche in fromIban oder toIban
            if(iban.equals(t.getFromIban()) || iban.equals(t.getToIban())) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Findet Transactions nach Typ
     */
    public List<Transaction> findByType(Transaction.TransactionType type) {
        List<Transaction> result = new ArrayList<>();
        for(Transaction t : transactions) {
            if(t.getType() == type) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Anzahl Transactions
     */
    public int count() {
        return transactions.size();
    }
}
