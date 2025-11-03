package ch.tbz.gino_goncalo.M4_Gonçalo.model;

import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InsufficientFundsException;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InvalidAmountException;
import java.util.Random;

/**
 * Abstrakte Basisklasse fuer alle Kontoarten
 * Implementiert gemeinsame Funktionalitaet
 * Subklassen muessen withdraw() und calculateInterest() ueberschreiben (Polymorphismus)
 */
public abstract class Account implements Transactable {

    // Clean Code: sprechende Variablennamen
    protected String iban;
    protected double balance;
    protected String ownerName;
    protected String accountType;

    // Konstruktor
    public Account(String ownerName, String accountType) {
        this.iban = generateIBAN();
        this.balance = 0.0;
        this.ownerName = ownerName;
        this.accountType = accountType;
    }

    /**
     * Generiert zufaellige IBAN
     * Format: CH + 2 Pruefziffern + 17 Ziffern
     * AI-assisted: IBAN generation logic
     */
    private String generateIBAN() {
        Random rand = new Random();
        StringBuilder ibanBuilder = new StringBuilder("CH");

        // Pruefziffer zwischen 10-99
        int checkDigit = rand.nextInt(90) + 10;
        ibanBuilder.append(checkDigit);

        // 17 zufaellige Ziffern anhaengen
        for(int i = 0; i < 17; i++) {
            ibanBuilder.append(rand.nextInt(10));
        }

        return ibanBuilder.toString();
    }

    /**
     * Einzahlung auf Konto
     * Validiert ob Betrag positiv ist
     */
    @Override
    public void deposit(double amount) throws InvalidAmountException {
        // Clean Code: Input Validierung mit aussagekraeftiger Exception
        if(amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        balance += amount;
    }

    /**
     * Abhebung - muss von Subklassen ueberschrieben werden
     * Jeder Kontotyp hat eigene Regeln (z.B. Ueberziehungslimit)
     * Polymorphismus: Dynamic Binding zur Laufzeit
     */
    @Override
    public abstract void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException;

    /**
     * Zinsberechnung - muss von Subklassen implementiert werden
     * Jeder Kontotyp hat eigene Zinssaetze
     * Polymorphismus: Unterschiedliches Verhalten je nach Kontotyp
     */
    public abstract double calculateInterest();

    // Getter (keine Kommentare noetig laut Clean Code)
    @Override
    public double getBalance() {
        return balance;
    }

    public String getIban() {
        return iban;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getAccountType() {
        return accountType;
    }

    // Setter
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return accountType + " | IBAN: " + iban +
               " | Saldo: CHF " + String.format("%.2f", balance) +
               " | Besitzer: " + ownerName;
    }
}
