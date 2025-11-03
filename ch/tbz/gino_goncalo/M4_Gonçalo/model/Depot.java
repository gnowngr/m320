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
}
