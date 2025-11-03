package ch.tbz.gino_goncalo.M4_Gonçalo.service;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.*;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.AccountRepository;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.TransactionRepository;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.*;

/**
 * Service fuer Aktienhandel
 * Delegation: Delegiert an Repository
 * Clean Code: SRP - nur Stock-Business-Logik
 */
public class StockService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public StockService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Kauft Aktien
     * Komplexe Business-Logik: Pruefung Depot, Guthaben, Gebuehren
     */
    public void buyStock(String depotIban, String symbol, String companyName, int quantity, double pricePerShare)
        throws AccountNotFoundException, InsufficientFundsException, InvalidAmountException {

        // Validierung
        if(quantity <= 0) {
            throw new InvalidAmountException("Anzahl muss positiv sein!");
        }
        if(pricePerShare <= 0) {
            throw new InvalidAmountException("Preis muss positiv sein!");
        }

        // Finde Depot
        Account account = accountRepository.findByIban(depotIban);
        if(!(account instanceof Depot)) {
            throw new InvalidAmountException("Konto ist kein Depot!");
        }
        Depot depot = (Depot) account;

        // Berechne Gesamtkosten
        double totalCost = (quantity * pricePerShare) + depot.getTransactionFee();

        // Pruefe Guthaben
        if(depot.getBalance() < totalCost) {
            throw new InsufficientFundsException(totalCost, depot.getBalance());
        }

        // Erstelle Stock
        Stock stock = new Stock(symbol, companyName, quantity, pricePerShare);

        // Fuehre Kauf durch
        depot.setBalance(depot.getBalance() - totalCost);
        depot.addStock(stock);

        // Speichere Transaction
        Transaction transaction = new Transaction(
            Transaction.TransactionType.STOCK_PURCHASE,
            totalCost,
            depotIban,
            null,
            "Kauf: " + quantity + "x " + symbol
        );
        transactionRepository.save(transaction);
    }

    /**
     * Verkauft Aktien
     */
    public void sellStock(String depotIban, String symbol, int quantity)
        throws AccountNotFoundException, InvalidAmountException {

        if(quantity <= 0) {
            throw new InvalidAmountException("Anzahl muss positiv sein!");
        }

        // Finde Depot
        Account account = accountRepository.findByIban(depotIban);
        if(!(account instanceof Depot)) {
            throw new InvalidAmountException("Konto ist kein Depot!");
        }
        Depot depot = (Depot) account;

        // Finde Stock im Portfolio
        Stock stockToSell = null;
        for(Stock s : depot.getStocks()) {
            if(s.getSymbol().equals(symbol)) {
                stockToSell = s;
                break;
            }
        }

        if(stockToSell == null) {
            throw new InvalidAmountException("Aktie nicht im Portfolio!");
        }

        if(stockToSell.getQuantity() < quantity) {
            throw new InvalidAmountException("Nicht genug Aktien vorhanden!");
        }

        // Berechne Verkaufspreis
        double salePrice = quantity * stockToSell.getCurrentPrice();
        double netAmount = salePrice - depot.getTransactionFee();

        // Fuehre Verkauf durch
        if(stockToSell.getQuantity() == quantity) {
            depot.removeStock(stockToSell);
        } else {
            stockToSell.setQuantity(stockToSell.getQuantity() - quantity);
        }

        depot.setBalance(depot.getBalance() + netAmount);

        // Speichere Transaction
        Transaction transaction = new Transaction(
            Transaction.TransactionType.STOCK_SALE,
            netAmount,
            null,
            depotIban,
            "Verkauf: " + quantity + "x " + symbol
        );
        transactionRepository.save(transaction);
    }

    /**
     * Aktualisiert Aktienkurs (simuliert)
     */
    public void updateStockPrice(String depotIban, String symbol, double newPrice)
        throws AccountNotFoundException, InvalidAmountException {

        Account account = accountRepository.findByIban(depotIban);
        if(!(account instanceof Depot)) {
            throw new InvalidAmountException("Konto ist kein Depot!");
        }
        Depot depot = (Depot) account;

        // Finde Stock und update Preis
        for(Stock s : depot.getStocks()) {
            if(s.getSymbol().equals(symbol)) {
                s.setCurrentPrice(newPrice);
                return;
            }
        }
    }
}
