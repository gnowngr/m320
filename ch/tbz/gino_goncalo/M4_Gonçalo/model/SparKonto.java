package ch.tbz.gino_goncalo.M4_Gonçalo.model;

import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InsufficientFundsException;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InvalidAmountException;

/**
 * Sparkonto - fuer langfristiges Sparen
 * Hat Zinsen aber keine Ueberziehung
 * Saldo darf nicht negativ werden
 */
public class SparKonto extends Account {

    // Clean Code: Konstanten definieren statt Hard-Coding
    private static final double INTEREST_RATE = 0.015; // 1.5% Jahreszins
    private static final String ACCOUNT_TYPE = "Sparkonto";

    public SparKonto(String ownerName) {
        super(ownerName, ACCOUNT_TYPE);
    }

    /**
     * Ueberschreibt withdraw() - keine Ueberziehung erlaubt
     * Polymorphismus: Unterschiedliches Verhalten als GiroKonto
     */
    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if(amount <= 0) {
            throw new InvalidAmountException(amount);
        }

        // Sparkonto erlaubt keine Ueberziehung
        if(amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }

        balance = balance - amount;
    }

    /**
     * Berechnet Jahreszinsen
     * Polymorphismus: Eigene Implementierung fuer SparKonto
     */
    @Override
    public double calculateInterest() {
        double zinsen = balance * INTEREST_RATE;
        return zinsen;
    }

    /**
     * Schreibt berechnete Zinsen gut
     */
    public void applyInterest() {
        double interest = calculateInterest();
        balance += interest;
    }

    public double getInterestRate() {
        return INTEREST_RATE;
    }
}
