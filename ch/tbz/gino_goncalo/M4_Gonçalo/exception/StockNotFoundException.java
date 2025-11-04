package ch.tbz.gino_goncalo.M4_Gonçalo.exception;

/**
 * Exception wenn Aktie nicht gefunden wird
 */
public class StockNotFoundException extends Exception {

    private String symbol;

    public StockNotFoundException(String symbol) {
        super("Aktie mit Symbol '" + symbol + "' nicht gefunden!");
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
