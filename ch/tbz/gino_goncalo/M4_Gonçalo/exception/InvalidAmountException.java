package ch.tbz.gino_goncalo.M4_Gonçalo.exception;

/**
 * Exception fuer ungueltige Betraege
 * z.B. negative Werte oder 0 bei Einzahlung/Abhebung
 */
public class InvalidAmountException extends Exception {

    private double invalidAmount;

    public InvalidAmountException(double invalidAmount) {
        super("Ungueltiger Betrag: CHF " + invalidAmount + ". Betrag muss positiv sein!");
        this.invalidAmount = invalidAmount;
    }

    public InvalidAmountException(String message) {
        super(message);
    }

    public double getInvalidAmount() {
        return invalidAmount;
    }
}
