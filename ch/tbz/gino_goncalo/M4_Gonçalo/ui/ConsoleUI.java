package ch.tbz.gino_goncalo.M4_Gonçalo.ui;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.User;
import ch.tbz.gino_goncalo.M4_Gonçalo.service.*;
import java.util.Scanner;

/**
 * Console User Interface
 * Delegation: UI delegiert alle Logik an Service-Schicht
 * Clean Code: SRP - nur fuer User-Interaktion zustaendig
 */
public class ConsoleUI {

    // Delegation: UI HAT Services
    private AccountService accountService;
    private UserService userService;
    private TransactionService transactionService;
    private StockService stockService;

    private Scanner scanner;
    private User currentUser;

    public ConsoleUI(AccountService accountService, UserService userService,
                     TransactionService transactionService, StockService stockService) {
        this.accountService = accountService;
        this.userService = userService;
        this.transactionService = transactionService;
        this.stockService = stockService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Startet die UI
     */
    public void start() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     Banking System - Willkommen!      ║");
        System.out.println("╚════════════════════════════════════════╝");

        boolean running = true;
        while(running) {
            if(currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    /**
     * Login-Menu
     */
    private void showLoginMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Registrieren");
        System.out.println("3. Beenden");
        System.out.print("\nWahl: ");

        String choice = scanner.nextLine();

        switch(choice) {
            case "1":
                login();
                break;
            case "2":
                register();
                break;
            case "3":
                System.out.println("Auf Wiedersehen!");
                System.exit(0);
                break;
            default:
                System.out.println("Ungueltige Eingabe!");
        }
    }

    /**
     * Login-Funktion
     */
    private void login() {
        System.out.print("\nUsername: ");
        String username = scanner.nextLine();

        System.out.print("Passwort: ");
        String password = scanner.nextLine();

        try {
            currentUser = userService.login(username, password);
            System.out.println("\nWillkommen " + currentUser.getFullName() + "!");
        } catch(Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }

    /**
     * Registrierungs-Funktion
     */
    private void register() {
        System.out.print("\nUsername: ");
        String username = scanner.nextLine();

        System.out.print("Passwort: ");
        String password = scanner.nextLine();

        System.out.print("Vorname: ");
        String firstName = scanner.nextLine();

        System.out.print("Nachname: ");
        String lastName = scanner.nextLine();

        try {
            currentUser = userService.register(username, password, firstName, lastName);
            System.out.println("\nRegistrierung erfolgreich! Willkommen " + currentUser.getFullName() + "!");
        } catch(Exception e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }

    /**
     * Hauptmenu nach Login
     */
    private void showMainMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║            Hauptmenu                  ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Konto erstellen");
        System.out.println("2. Kontostand anzeigen");
        System.out.println("3. Geld einzahlen");
        System.out.println("4. Geld abheben");
        System.out.println("5. Ueberweisung");
        System.out.println("6. Transaktionshistorie");
        System.out.println("7. Logout");
        System.out.print("\nWahl: ");

        String choice = scanner.nextLine();
        handleMainMenuChoice(choice);
    }

    /**
     * Behandelt Hauptmenu-Auswahl
     */
    private void handleMainMenuChoice(String choice) {
        // Implementierung in Teil 2
        System.out.println("Funktion in Entwicklung...");
    }
}
