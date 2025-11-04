package ch.tbz.gino_goncalo.M4_Gonçalo.service;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.*;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.AccountRepository;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.TransactionRepository;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.*;

/**
 * Service fuer Aktienhandel
 * Delegation: Delegiert an Repository UND externe API
 * Clean Code: SRP - nur Stock-Business-Logik
 *
 * API Integration: Verwendet StockAPI Interface fuer externe Kursdaten
 */
public class StockService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private StockAPI stockAPI;

    public StockService(AccountRepository accountRepository, TransactionRepository transactionRepository, StockAPI stockAPI) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.stockAPI = stockAPI;
    }

    // Legacy Konstruktor ohne API (fuer Tests)
    public StockService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this(accountRepository, transactionRepository, new YahooFinanceAPI());
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

    /**
     * Kauft Aktie mit aktuellem Preis von API
     * Delegation: Holt Preis von externer API
     * API Integration demonstriert hier!
     */
    public void buyStockWithAPI(String depotIban, String symbol, int quantity)
        throws AccountNotFoundException, InsufficientFundsException, InvalidAmountException {

        // Validiere Symbol mit API
        if(!stockAPI.isValidSymbol(symbol)) {
            throw new InvalidAmountException("Ungültiges Aktien-Symbol: " + symbol);
        }

        // Hole aktuellen Preis von API
        double currentPrice = stockAPI.getCurrentPrice(symbol);
        if(currentPrice <= 0) {
            throw new InvalidAmountException("Konnte Preis nicht von API laden!");
        }

        // Hole Company Name von API
        String companyName = stockAPI.getCompanyName(symbol);

        System.out.println("API: Aktueller Preis fuer " + symbol + ": CHF " + currentPrice);

        // Verwende normale buyStock Methode mit API-Preis
        buyStock(depotIban, symbol, companyName, quantity, currentPrice);
    }

    /**
     * Aktualisiert alle Aktienkurse von API
     */
    public void refreshAllStockPrices(String depotIban)
        throws AccountNotFoundException, InvalidAmountException {

        Account account = accountRepository.findByIban(depotIban);
        if(!(account instanceof Depot)) {
            throw new InvalidAmountException("Konto ist kein Depot!");
        }
        Depot depot = (Depot) account;

        System.out.println("\nAktualisiere Kurse von API...");

        for(Stock stock : depot.getStocks()) {
            double newPrice = stockAPI.getCurrentPrice(stock.getSymbol());
            if(newPrice > 0) {
                stock.setCurrentPrice(newPrice);
                System.out.println(stock.getSymbol() + ": CHF " + newPrice);
            }
        }

        System.out.println("Kurse aktualisiert!\n");
    }

    /**
     * Zeigt verfuegbare Aktien von API
     */
    public void showAvailableStocks() {
        System.out.println("\n=== Verfuegbare Aktien (von API) ===");
        String[] symbols = {"AAPL", "GOOGL", "MSFT", "AMZN", "TSLA", "META", "NVDA", "NFLX"};

        for(String symbol : symbols) {
            String company = stockAPI.getCompanyName(symbol);
            double price = stockAPI.getCurrentPrice(symbol);
            System.out.println(symbol + " - " + company + " | CHF " + String.format("%.2f", price));
        }
        System.out.println("====================================\n");
    }
}
