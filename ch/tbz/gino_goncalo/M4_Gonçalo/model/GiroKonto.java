package ch.tbz.gino_goncalo.M4_Gonçalo.model;

import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InsufficientFundsException;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InvalidAmountException;

/**
 * Girokonto - fuer taegliche Transaktionen
 * Hat Ueberziehungslimit
 * Keine Zinsen
 */
public class GiroKonto extends Account {

    // Clean Code: Konstante statt Hard-Coding
    private static final double OVERDRAFT_LIMIT = 1000.0;
    private static final String ACCOUNT_TYPE = "Girokonto";

    public GiroKonto(String ownerName) {
        super(ownerName, ACCOUNT_TYPE);
    }

    /**
     * Ueberschreibt withdraw() von Account
     * Erlaubt Ueberziehung bis zum Limit
     * Polymorphismus: Eigenes Verhalten fuer GiroKonto
     */
    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        // Input validierung
        if(amount <= 0) {
            throw new InvalidAmountException(amount);
        }

        // Berechne verfuegbaren Betrag inkl. Ueberziehung
        double availableAmount = balance + OVERDRAFT_LIMIT;

        if(amount > availableAmount) {
            throw new InsufficientFundsException(amount, balance);
        }

        balance -= amount;
    }

    /**
     * Girokonto hat keine Zinsen
     */
    @Override
    public double calculateInterest() {
        return 0.0;
    }

    public double getOverdraftLimit() {
        return OVERDRAFT_LIMIT;
    }
}
