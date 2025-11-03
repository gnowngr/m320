package ch.tbz.gino_goncalo.M4_Gonçalo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Repraesentiert eine Transaktion
 * Wird fuer Transaktionshistorie verwendet
 */
public class Transaction {

    // Enum fuer Transaktionstypen (Clean Code: statt String Literale)
    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL,
        TRANSFER,
        STOCK_PURCHASE,
        STOCK_SALE
    }

    private String transactionId;
    private TransactionType type;
    private double amount;
    private String fromIban;
    private String toIban;
    private LocalDateTime timestamp;
    private String description;

    public Transaction(TransactionType type, double amount, String fromIban, String toIban, String description) {
        this.transactionId = generateTransactionId();
        this.type = type;
        this.amount = amount;
        this.fromIban = fromIban;
        this.toIban = toIban;
        this.timestamp = LocalDateTime.now();
        this.description = description;
    }

    /**
     * Generiert eindeutige Transaction ID
     * AI-assisted: ID generation
     */
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }

    // Getter
    public String getTransactionId() {
        return transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getFromIban() {
        return fromIban;
    }

    public String getToIban() {
        return toIban;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return timestamp.format(formatter) + " | " +
               type + " | CHF " + String.format("%.2f", amount) +
               " | " + description;
    }
}
