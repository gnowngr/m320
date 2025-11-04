package ch.tbz.gino_goncalo.M4_Gonçalo.util;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.*;
import java.util.List;

/**
 * Utility-Klasse fuer Account-Zusammenfassungen
 * Clean Code: SRP - nur fuer Account-Reporting zustaendig
 */
public class AccountSummary {

    /**
     * Gibt formatierte Kontozusammenfassung aus
     */
    public static void printAccountSummary(Account account) {
        System.out.println("\n=== Kontozusammenfassung ===");
        System.out.println("Typ: " + account.getAccountType());
        System.out.println("IBAN: " + account.getIban());
        System.out.println("Besitzer: " + account.getOwnerName());
        System.out.println("Saldo: " + MoneyFormatter.formatWithCurrency(account.getBalance()));

        // Typ-spezifische Informationen
        if(account instanceof GiroKonto) {
            GiroKonto giro = (GiroKonto) account;
            System.out.println("Ueberziehungslimit: " + MoneyFormatter.formatWithCurrency(giro.getOverdraftLimit()));
        } else if(account instanceof SparKonto) {
            SparKonto spar = (SparKonto) account;
            double interest = spar.calculateInterest();
            System.out.println("Zinssatz: " + MoneyFormatter.formatPercent(spar.getInterestRate() * 100));
            System.out.println("Jahreszinsen: " + MoneyFormatter.formatWithCurrency(interest));
        } else if(account instanceof Depot) {
            Depot depot = (Depot) account;
            System.out.println("Transaktionsgebuehr: " + MoneyFormatter.formatWithCurrency(depot.getTransactionFee()));
            System.out.println("Portfolio-Wert: " + MoneyFormatter.formatWithCurrency(depot.getPortfolioValue()));
            System.out.println("Anzahl Aktien: " + depot.getStocks().size());
        }
        System.out.println("========================\n");
    }

    /**
     * Berechnet Gesamtsaldo aller Accounts
     */
    public static double calculateTotalBalance(List<Account> accounts) {
        double total = 0;
        for(Account acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }

    /**
     * Gibt Uebersicht ueber alle Accounts aus
     */
    public static void printAllAccounts(List<Account> accounts) {
        System.out.println("\n=== Alle Konten ===");
        if(accounts.isEmpty()) {
            System.out.println("Keine Konten vorhanden.");
            return;
        }

        for(Account acc : accounts) {
            System.out.println(acc);
        }

        double total = calculateTotalBalance(accounts);
        System.out.println("\nGesamtsaldo: " + MoneyFormatter.formatWithCurrency(total));
        System.out.println("Anzahl Konten: " + accounts.size());
        System.out.println("===================\n");
    }

    /**
     * Zaehlt Konten nach Typ
     */
    public static void printAccountStatistics(List<Account> accounts) {
        int giroCount = 0;
        int sparCount = 0;
        int depotCount = 0;

        for(Account acc : accounts) {
            if(acc instanceof GiroKonto) giroCount++;
            else if(acc instanceof SparKonto) sparCount++;
            else if(acc instanceof Depot) depotCount++;
        }

        System.out.println("\n=== Konto-Statistik ===");
        System.out.println("Girokonten: " + giroCount);
        System.out.println("Sparkonten: " + sparCount);
        System.out.println("Depots: " + depotCount);
        System.out.println("Total: " + accounts.size());
        System.out.println("=======================\n");
    }
}
