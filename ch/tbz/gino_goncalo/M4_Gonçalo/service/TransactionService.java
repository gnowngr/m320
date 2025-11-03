package ch.tbz.gino_goncalo.M4_Gonçalo.service;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.Transaction;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.TransactionRepository;
import java.util.List;

/**
 * Service fuer Transaction-Operationen
 * Delegation: Delegiert an TransactionRepository
 * Clean Code: SRP - nur Transaction-Business-Logik
 */
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Gibt alle Transaktionen zurueck
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Gibt Transaktionen fuer ein Konto zurueck
     */
    public List<Transaction> getTransactionsByIban(String iban) {
        return transactionRepository.findByIban(iban);
    }

    /**
     * Gibt Transaktionen nach Typ zurueck
     */
    public List<Transaction> getTransactionsByType(Transaction.TransactionType type) {
        return transactionRepository.findByType(type);
    }

    /**
     * Zaehlt Transaktionen
     */
    public int countTransactions() {
        return transactionRepository.count();
    }

    /**
     * Berechnet Gesamtumsatz fuer ein Konto
     * Komplexe Business-Logik
     */
    public double calculateTotalVolume(String iban) {
        List<Transaction> transactions = transactionRepository.findByIban(iban);
        double total = 0;
        for(Transaction t : transactions) {
            total = total + t.getAmount();
        }
        return total;
    }

    /**
     * Zeigt Transaktionshistorie an
     */
    public void printTransactionHistory(String iban) {
        List<Transaction> transactions = getTransactionsByIban(iban);

        if(transactions.isEmpty()) {
            System.out.println("Keine Transaktionen vorhanden.");
            return;
        }

        System.out.println("\n=== Transaktionshistorie ===");
        for(Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println("Anzahl Transaktionen: " + transactions.size());
    }
}
