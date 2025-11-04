package ch.tbz.gino_goncalo.M4_Gonçalo.exception;

/**
 * Exception wenn nicht genug Aktien zum Verkauf vorhanden sind
 */
public class InsufficientStockException extends Exception {

    private String symbol;
    private int requested;
    private int available;

    public InsufficientStockException(String symbol, int requested, int available) {
        super("Nicht genug Aktien! Symbol: " + symbol +
              ", gewuenscht: " + requested +
              ", verfuegbar: " + available);
        this.symbol = symbol;
        this.requested = requested;
        this.available = available;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getRequested() {
        return requested;
    }

    public int getAvailable() {
        return available;
    }
}
