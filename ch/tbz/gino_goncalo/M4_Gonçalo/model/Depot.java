package ch.tbz.gino_goncalo.M4_Gonçalo.model;

import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InsufficientFundsException;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InvalidAmountException;
import java.util.ArrayList;
import java.util.List;

/**
 * Depot - fuer Aktienhandel
 * Speichert gekaufte Aktien
 * Hat Transaktionsgebuehren
 */
public class Depot extends Account {

    private static final double TRANSACTION_FEE = 5.0; // CHF 5 pro Transaktion
    private static final String ACCOUNT_TYPE = "Depot";

    // Delegation: Depot HAT eine Liste von Stocks
    private List<Stock> stocks;

    public Depot(String ownerName) {
        super(ownerName, ACCOUNT_TYPE);
        // Objekt wird im Konstruktor instanziiert (Komposition)
        this.stocks = new ArrayList<>();
    }

    /**
     * Abhebung mit Gebuehren
     * Polymorphismus: Eigene Implementierung fuer Depot
     */
    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if(amount <= 0) {
            throw new InvalidAmountException(amount);
        }

        double totalAmount = amount + TRANSACTION_FEE;

        if(totalAmount > balance) {
            throw new InsufficientFundsException(totalAmount, balance);
        }

        balance = balance - totalAmount;
    }

    /**
     * Depot hat keine Zinsen auf Cash
     */
    @Override
    public double calculateInterest() {
        return 0.0;
    }

    /**
     * Fuegt Aktie zum Portfolio hinzu
     */
    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    /**
     * Entfernt Aktie aus Portfolio
     */
    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public double getTransactionFee() {
        return TRANSACTION_FEE;
    }

    /**
     * Berechnet Gesamtwert des Portfolios
     */
    public double getPortfolioValue() {
        double totalValue = balance;
        for(Stock stock : stocks) {
            totalValue = totalValue + stock.getTotalValue();
        }
        return totalValue;
    }

    /**
     * Berechnet Gesamt-Gewinn/Verlust des Portfolios
     */
    public double getTotalProfitLoss() {
        double total = 0;
        for(Stock stock : stocks) {
            total += stock.getProfitLoss();
        }
        return total;
    }

    /**
     * Berechnet durchschnittliche Portfolio-Performance in Prozent
     */
    public double getAveragePerformance() {
        if(stocks.isEmpty()) {
            return 0;
        }
        double totalPerf = 0;
        for(Stock stock : stocks) {
            totalPerf += stock.getProfitLossPercent();
        }
        return totalPerf / stocks.size();
    }

    /**
     * Findet Stock nach Symbol
     */
    public Stock findStockBySymbol(String symbol) {
        for(Stock stock : stocks) {
            if(stock.getSymbol().equals(symbol)) {
                return stock;
            }
        }
        return null;
    }

    /**
     * Gibt Portfolio-Uebersicht aus
     */
    public void printPortfolio() {
        System.out.println("\n=== Portfolio-Uebersicht ===");
        System.out.println("Cash: CHF " + String.format("%.2f", balance));

        if(stocks.isEmpty()) {
            System.out.println("Keine Aktien vorhanden.");
        } else {
            System.out.println("\nAktien:");
            for(Stock stock : stocks) {
                System.out.println("  " + stock);
            }
            System.out.println("\nPortfolio-Wert: CHF " + String.format("%.2f", getPortfolioValue()));
            System.out.println("Gesamt G/V: CHF " + String.format("%.2f", getTotalProfitLoss()));
            System.out.println("Durchschn. Performance: " + String.format("%.2f%%", getAveragePerformance()));
        }
        System.out.println("===========================\n");
    }
}
