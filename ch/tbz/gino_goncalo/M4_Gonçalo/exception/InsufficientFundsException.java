package ch.tbz.gino_goncalo.M4_Gonçalo.exception;

/**
 * Exception wird geworfen wenn nicht genug Guthaben vorhanden ist
 * Beispiel: Abhebung von 500 CHF aber nur 300 CHF auf Konto
 */
public class InsufficientFundsException extends Exception {

    private double requestedAmount;
    private double availableBalance;

    public InsufficientFundsException(double requestedAmount, double availableBalance) {
        super("Nicht genug Guthaben! Gewuenscht: CHF " + requestedAmount +
              ", verfuegbar: CHF " + availableBalance);
        this.requestedAmount = requestedAmount;
        this.availableBalance = availableBalance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }
}
