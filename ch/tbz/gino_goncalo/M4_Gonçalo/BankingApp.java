package ch.tbz.gino_goncalo.M4_Gonçalo;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.*;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.*;
import ch.tbz.gino_goncalo.M4_Gonçalo.service.*;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.*;

/**
 * Main-Klasse fuer Banking System
 * Demonstriert Funktionalitaet des Systems
 */
public class BankingApp {

    public static void main(String[] args) {
        System.out.println("=== Banking System gestartet ===\n");

        // Repositories erstellen
        AccountRepository accountRepo = new AccountRepository();
        UserRepository userRepo = new UserRepository();
        TransactionRepository transactionRepo = new TransactionRepository();

        // Services erstellen (Delegation)
        AccountService accountService = new AccountService(accountRepo, transactionRepo);
        StockService stockService = new StockService(accountRepo, transactionRepo);
        TransactionService transactionService = new TransactionService(transactionRepo);

        try {
            // Demo: Konten erstellen
            System.out.println("1. Erstelle Konten...");
            Account giro = accountService.createAccount("GIRO", "Max Mustermann");
            Account spar = accountService.createAccount("SPAR", "Max Mustermann");
            Account depot = accountService.createAccount("DEPOT", "Max Mustermann");

            System.out.println("Girokonto: " + giro.getIban());
            System.out.println("Sparkonto: " + spar.getIban());
            System.out.println("Depot: " + depot.getIban());

            // Demo: Einzahlungen
            System.out.println("\n2. Einzahlungen...");
            accountService.deposit(giro.getIban(), 5000.0);
            accountService.deposit(spar.getIban(), 10000.0);
            accountService.deposit(depot.getIban(), 20000.0);

            System.out.println("Girokonto Saldo: CHF " + giro.getBalance());
            System.out.println("Sparkonto Saldo: CHF " + spar.getBalance());
            System.out.println("Depot Saldo: CHF " + depot.getBalance());

            // Demo: Abhebung
            System.out.println("\n3. Abhebung vom Girokonto...");
            accountService.withdraw(giro.getIban(), 200.0);
            System.out.println("Girokonto Saldo: CHF " + giro.getBalance());

            // Demo: Ueberweisung
            System.out.println("\n4. Ueberweisung Giro -> Spar...");
            accountService.transfer(giro.getIban(), spar.getIban(), 1000.0);
            System.out.println("Girokonto Saldo: CHF " + giro.getBalance());
            System.out.println("Sparkonto Saldo: CHF " + spar.getBalance());

            // Demo: Zinsen berechnen (Polymorphismus!)
            System.out.println("\n5. Zinsen berechnen (Polymorphismus)...");
            System.out.println("Girokonto Zinsen: CHF " + giro.calculateInterest());
            System.out.println("Sparkonto Zinsen: CHF " + spar.calculateInterest());
            System.out.println("Depot Zinsen: CHF " + depot.calculateInterest());

            // Demo: Aktienhandel
            System.out.println("\n6. Aktienhandel...");
            stockService.buyStock(depot.getIban(), "AAPL", "Apple Inc.", 10, 150.0);
            stockService.buyStock(depot.getIban(), "GOOGL", "Alphabet Inc.", 5, 2800.0);
            System.out.println("Depot Saldo nach Kauf: CHF " + depot.getBalance());

            // Zeige Portfolio
            Depot depotAccount = (Depot) depot;
            System.out.println("\nPortfolio:");
            for(Stock stock : depotAccount.getStocks()) {
                System.out.println("  " + stock);
            }

            // Demo: Transaktionshistorie
            System.out.println("\n7. Transaktionshistorie Depot:");
            transactionService.printTransactionHistory(depot.getIban());

            System.out.println("\n=== Demo erfolgreich abgeschlossen ===");

        } catch(Exception e) {
            System.err.println("Fehler: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
