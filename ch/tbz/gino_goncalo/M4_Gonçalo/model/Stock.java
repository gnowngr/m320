package ch.tbz.gino_goncalo.M4_Gonçalo.model;

/**
 * Repraesentiert eine Aktie im Portfolio
 * Speichert Symbol, Anzahl, Kaufpreis
 */
public class Stock {

    private String symbol;
    private String companyName;
    private int quantity;
    private double purchasePrice;
    private double currentPrice;

    public Stock(String symbol, String companyName, int quantity, double purchasePrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.currentPrice = purchasePrice;
    }

    /**
     * Berechnet Gesamtwert der Position
     */
    public double getTotalValue() {
        return quantity * currentPrice;
    }

    /**
     * Berechnet Gewinn/Verlust
     */
    public double getProfitLoss() {
        double purchaseValue = quantity * purchasePrice;
        double currentValue = getTotalValue();
        return currentValue - purchaseValue;
    }

    /**
     * Berechnet Gewinn/Verlust in Prozent
     */
    public double getProfitLossPercent() {
        double profit = getProfitLoss();
        double purchaseValue = quantity * purchasePrice;
        if(purchaseValue == 0) {
            return 0;
        }
        return (profit / purchaseValue) * 100;
    }

    // Getter
    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    // Setter
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return symbol + " (" + companyName + ") | " +
               "Anzahl: " + quantity +
               " | Kaufpreis: CHF " + String.format("%.2f", purchasePrice) +
               " | Aktuell: CHF " + String.format("%.2f", currentPrice) +
               " | Wert: CHF " + String.format("%.2f", getTotalValue());
    }
}
