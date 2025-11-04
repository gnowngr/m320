package ch.tbz.gino_goncalo.M4_Gonçalo.util;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.Account;
import ch.tbz.gino_goncalo.M4_Gonçalo.model.Transaction;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Utility-Klasse fuer Kontoauszug-Erstellung
 * Clean Code: SRP - nur fuer Statement-Generierung zustaendig
 */
public class BankStatement {

    /**
     * Erstellt und gibt Kontoauszug aus
     */
    public static void generateStatement(Account account, List<Transaction> transactions) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║              KONTOAUSZUG                               ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Kontoinhaber: " + account.getOwnerName());
        System.out.println("Kontotyp:     " + account.getAccountType());
        System.out.println("IBAN:         " + account.getIban());
        System.out.println("Datum:        " + DateFormatter.getCurrentDateTime());
        System.out.println();
        System.out.println("Aktueller Saldo: " + MoneyFormatter.formatWithCurrency(account.getBalance()));
        System.out.println();
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("Transaktionshistorie:");
        System.out.println("─────────────────────────────────────────────────────────");

        if(transactions.isEmpty()) {
            System.out.println("Keine Transaktionen vorhanden.");
        } else {
            for(Transaction t : transactions) {
                System.out.println(t);
            }
        }

        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("Anzahl Transaktionen: " + transactions.size());
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════");
        System.out.println();
    }

    /**
     * Generiert kurzen Auszug mit nur den letzten N Transaktionen
     */
    public static void generateShortStatement(Account account, List<Transaction> transactions, int limit) {
        List<Transaction> limitedTransactions;

        // Nehme nur letzte N Transaktionen
        int startIndex = Math.max(0, transactions.size() - limit);
        limitedTransactions = transactions.subList(startIndex, transactions.size());

        System.out.println("\n=== Kontoauszug (Kurz) ===");
        System.out.println("IBAN: " + account.getIban());
        System.out.println("Saldo: " + MoneyFormatter.formatWithCurrency(account.getBalance()));
        System.out.println("\nLetzte " + limit + " Transaktionen:");

        if(limitedTransactions.isEmpty()) {
            System.out.println("Keine Transaktionen.");
        } else {
            for(Transaction t : limitedTransactions) {
                System.out.println("  " + t);
            }
        }
        System.out.println("==========================\n");
    }

    /**
     * Berechnet Statistiken fuer Statement
     */
    public static void printTransactionStatistics(List<Transaction> transactions) {
        double totalDeposits = 0;
        double totalWithdrawals = 0;
        double totalTransfers = 0;
        int depositCount = 0;
        int withdrawalCount = 0;
        int transferCount = 0;

        for(Transaction t : transactions) {
            switch(t.getType()) {
                case DEPOSIT:
                    totalDeposits += t.getAmount();
                    depositCount++;
                    break;
                case WITHDRAWAL:
                    totalWithdrawals += t.getAmount();
                    withdrawalCount++;
                    break;
                case TRANSFER:
                    totalTransfers += t.getAmount();
                    transferCount++;
                    break;
            }
        }

        System.out.println("\n=== Transaktions-Statistik ===");
        System.out.println("Einzahlungen:  " + depositCount + " (" + MoneyFormatter.formatWithCurrency(totalDeposits) + ")");
        System.out.println("Abhebungen:    " + withdrawalCount + " (" + MoneyFormatter.formatWithCurrency(totalWithdrawals) + ")");
        System.out.println("Ueberweisungen: " + transferCount + " (" + MoneyFormatter.formatWithCurrency(totalTransfers) + ")");
        System.out.println("Total:         " + transactions.size());
        System.out.println("==============================\n");
    }
}
