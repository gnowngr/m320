package ch.tbz.gino_goncalo.M4_Gonçalo;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.*;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.*;
import ch.tbz.gino_goncalo.M4_Gonçalo.service.*;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.*;

import java.util.Scanner;

/**
 * Main-Klasse fuer M4 Banking System - Interaktives Menü
 *
 * Demonstriert alle OOP-Konzepte:
 * - Polymorphismus: Account-Hierarchie mit ueberschriebenen Methoden
 * - Delegation: UI -> Service -> Repository -> API
 * - Design Patterns: Factory Pattern, Repository Pattern
 * - API Integration: RealYahooFinanceAPI fuer echte Aktienkurse
 * - Exception Handling: Custom Exceptions fuer Fehlerbehandlung
 * - Clean Code: SRP, Konstanten, sprechende Namen
 *
 * @author Gino Gonçalo
 * @version 1.0
 * @since 2025
 */
public class BankingApp {

    private static AccountRepository accountRepo;
    private static TransactionRepository transactionRepo;
    private static AccountService accountService;
    private static StockService stockService;
    private static TransactionService transactionService;
    private static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   M4 BANKING SYSTEM - INTERAKTIV      ║");
        System.out.println("║   Mit ECHTER Yahoo Finance API!        ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // System initialisieren
        initializeSystem();

        // Hauptmenü starten
        boolean running = true;
        while(running) {
            showMainMenu();
            int choice = getUserChoice();

            switch(choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> transfer();
                case 5 -> showAllAccounts();
                case 6 -> showAccountBalance();
                case 7 -> buyStock();
                case 8 -> showPortfolio();
                case 9 -> refreshStockPrices();
                case 10 -> showTransactionHistory();
                case 11 -> showAvailableStocks();
                case 0 -> {
                    System.out.println("\nVielen Dank! Auf Wiedersehen.");
                    running = false;
                }
                default -> System.out.println("Ungültige Eingabe! Bitte wählen Sie 0-11.");
            }

            if(running) {
                System.out.println("\nDrücken Sie Enter um fortzufahren...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void initializeSystem() {
        accountRepo = new AccountRepository();
        transactionRepo = new TransactionRepository();
        accountService = new AccountService(accountRepo, transactionRepo);

        // ECHTE API Integration!
        StockAPI stockAPI = new RealYahooFinanceAPI();
        stockService = new StockService(accountRepo, transactionRepo, stockAPI);

        transactionService = new TransactionService(transactionRepo);
        scanner = new Scanner(System.in);
    }

    private static void showMainMenu() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("                HAUPTMENÜ");
        System.out.println("=".repeat(45));
        System.out.println("  [1]  Konto erstellen");
        System.out.println("  [2]  Geld einzahlen");
        System.out.println("  [3]  Geld abheben");
        System.out.println("  [4]  Überweisung durchführen");
        System.out.println("  [5]  Alle Konten anzeigen");
        System.out.println("  [6]  Kontostand prüfen");
        System.out.println("  ---");
        System.out.println("  [7]  Aktie kaufen (mit ECHTER API!)");
        System.out.println("  [8]  Portfolio anzeigen");
        System.out.println("  [9]  Aktienkurse aktualisieren");
        System.out.println("  [10] Transaktionshistorie");
        System.out.println("  [11] Verfügbare Aktien anzeigen");
        System.out.println("  ---");
        System.out.println("  [0]  Beenden");
        System.out.println("=".repeat(45));
        System.out.print("Ihre Wahl: ");
    }

    private static int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch(NumberFormatException e) {
            return -1;
        }
    }

    private static void createAccount() {
        System.out.println("\n--- Konto erstellen ---");
        System.out.print("Name des Kontoinhabers: ");
        String owner = scanner.nextLine().trim();

        System.out.println("\nKontotyp wählen:");
        System.out.println("  [1] Girokonto (mit Überziehungslimit)");
        System.out.println("  [2] Sparkonto (mit Zinsen)");
        System.out.println("  [3] Depot (für Aktienhandel)");
        System.out.print("Wahl: ");

        try {
            int type = Integer.parseInt(scanner.nextLine().trim());
            String accountType = switch(type) {
                case 1 -> "GIRO";
                case 2 -> "SPAR";
                case 3 -> "DEPOT";
                default -> throw new IllegalArgumentException("Ungültiger Typ");
            };

            Account account = accountService.createAccount(accountType, owner);
            System.out.println("\nKonto erfolgreich erstellt!");
            System.out.println("IBAN: " + account.getIban());
            System.out.println("Typ: " + accountType);
            System.out.println("Inhaber: " + owner);

        } catch(Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }

    private static void deposit() {
        System.out.println("\n--- Geld einzahlen ---");
        System.out.print("IBAN: ");
        String iban = scanner.nextLine().trim();
        System.out.print("Betrag (CHF): ");

        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            accountService.deposit(iban, amount);
            Account account = accountRepo.findByIban(iban);
            System.out.println("\nEinzahlung erfolgreich!");
            System.out.println("Neuer Saldo: CHF " + String.format("%.2f", account.getBalance()));
        } catch(Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }

    private static void withdraw() {
        System.out.println("\n--- Geld abheben ---");
        System.out.print("IBAN: ");
        String iban = scanner.nextLine().trim();
        System.out.print("Betrag (CHF): ");

        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            accountService.withdraw(iban, amount);
            Account account = accountRepo.findByIban(iban);
            System.out.println("\nAbhebung erfolgreich!");
            System.out.println("Neuer Saldo: CHF " + String.format("%.2f", account.getBalance()));
        } catch(Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }

    private static void transfer() {
        System.out.println("\n--- Überweisung ---");
        System.out.print("Von IBAN: ");
        String fromIban = scanner.nextLine().trim();
        System.out.print("Nach IBAN: ");
        String toIban = scanner.nextLine().trim();
        System.out.print("Betrag (CHF): ");

        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            accountService.transfer(fromIban, toIban, amount);
            System.out.println("\nÜberweisung erfolgreich!");

            Account from = accountRepo.findByIban(fromIban);
            Account to = accountRepo.findByIban(toIban);
            System.out.println("Saldo " + fromIban + ": CHF " + String.format("%.2f", from.getBalance()));
            System.out.println("Saldo " + toIban + ": CHF " + String.format("%.2f", to.getBalance()));
        } catch(Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }

    private static void showAllAccounts() {
        System.out.println("\n--- Alle Konten ---");
        var accounts = accountRepo.findAll();

        if(accounts.isEmpty()) {
            System.out.println("Keine Konten vorhanden.");
            return;
        }

        System.out.println(String.format("%-20s %-25s %-15s %15s", "Typ", "IBAN", "Inhaber", "Saldo (CHF)"));
        System.out.println("-".repeat(80));

        for(Account account : accounts) {
            String type = account.getClass().getSimpleName();
            System.out.println(String.format("%-20s %-25s %-15s %15.2f",
                type, account.getIban(), account.getOwnerName(), account.getBalance()));
        }
    }

    private static void showAccountBalance() {
        System.out.println("\n--- Kontostand prüfen ---");
        System.out.print("IBAN: ");
        String iban = scanner.nextLine().trim();

        try {
            Account account = accountRepo.findByIban(iban);
            System.out.println("\n" + account.getClass().getSimpleName());
            System.out.println("IBAN: " + account.getIban());
            System.out.println("Inhaber: " + account.getOwnerName());
            System.out.println("Saldo: CHF " + String.format("%.2f", account.getBalance()));

            // Zeige Zinsen (Polymorphismus!)
            double interest = account.calculateInterest();
            if(interest > 0) {
                System.out.println("Zinsen: CHF " + String.format("%.2f", interest));
            }

        } catch(AccountNotFoundException e) {
            System.err.println("Fehler: Konto nicht gefunden!");
        }
    }

    private static void buyStock() {
        System.out.println("\n--- Aktie kaufen (ECHTE API!) ---");
        System.out.print("Depot-IBAN: ");
        String iban = scanner.nextLine().trim();
        System.out.print("Aktiensymbol (z.B. AAPL): ");
        String symbol = scanner.nextLine().trim().toUpperCase();
        System.out.print("Anzahl: ");

        try {
            int quantity = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("\nHole aktuellen Kurs von Yahoo Finance API...");
            stockService.buyStockWithAPI(iban, symbol, quantity);

            Account account = accountRepo.findByIban(iban);
            System.out.println("\nAktie erfolgreich gekauft!");
            System.out.println("Neuer Depot-Saldo: CHF " + String.format("%.2f", account.getBalance()));

        } catch(Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }

    private static void showPortfolio() {
        System.out.println("\n--- Portfolio anzeigen ---");
        System.out.print("Depot-IBAN: ");
        String iban = scanner.nextLine().trim();

        try {
            Account account = accountRepo.findByIban(iban);

            if(!(account instanceof Depot)) {
                System.err.println("Fehler: Das ist kein Depot-Konto!");
                return;
            }

            Depot depot = (Depot) account;
            System.out.println("\nPortfolio für " + depot.getIban() + ":");
            System.out.println("Verfügbares Kapital: CHF " + String.format("%.2f", depot.getBalance()));
            System.out.println("\nAktien:");

            if(depot.getStocks().isEmpty()) {
                System.out.println("  Keine Aktien im Portfolio.");
            } else {
                depot.printPortfolio();
            }

        } catch(AccountNotFoundException e) {
            System.err.println("Fehler: Konto nicht gefunden!");
        }
    }

    private static void refreshStockPrices() {
        System.out.println("\n--- Aktienkurse aktualisieren ---");
        System.out.print("Depot-IBAN: ");
        String iban = scanner.nextLine().trim();

        try {
            System.out.println("\nAktualisiere Kurse von Yahoo Finance API...");
            stockService.refreshAllStockPrices(iban);
            System.out.println("Kurse erfolgreich aktualisiert!");

        } catch(Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }

    private static void showTransactionHistory() {
        System.out.println("\n--- Transaktionshistorie ---");
        System.out.print("IBAN: ");
        String iban = scanner.nextLine().trim();

        try {
            Account account = accountRepo.findByIban(iban);
            System.out.println("\nTransaktionen für " + account.getIban() + ":");
            transactionService.printTransactionHistory(iban);

        } catch(AccountNotFoundException e) {
            System.err.println("Fehler: Konto nicht gefunden!");
        }
    }

    private static void showAvailableStocks() {
        System.out.println("\n--- Verfügbare Aktien (von ECHTER API!) ---");
        stockService.showAvailableStocks();
    }
}
