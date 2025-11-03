package ch.tbz.gino_goncalo.M4_Gonçalo.service;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.Account;
import ch.tbz.gino_goncalo.M4_Gonçalo.model.Transaction;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.AccountRepository;
import ch.tbz.gino_goncalo.M4_Gonçalo.repository.TransactionRepository;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.*;
import ch.tbz.gino_goncalo.M4_Gonçalo.util.AccountFactory;

/**
 * Service-Schicht fuer Account-Operationen
 *
 * Delegation: Service delegiert Datenzugriff an Repository
 * Clean Code: SRP - nur Business-Logik fuer Accounts
 * Trennung UI -> Service -> Repository
 */
public class AccountService {

    // Delegation: Service HAT Repository (Objektregistrierung via Konstruktor)
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Erstellt neues Konto
     * Delegiert an Factory und Repository
     */
    public Account createAccount(String type, String ownerName) {
        Account account = AccountFactory.createAccount(type, ownerName);
        accountRepository.save(account);
        return account;
    }

    /**
     * Einzahlung auf Konto
     */
    public void deposit(String iban, double amount) throws AccountNotFoundException, InvalidAmountException {
        Account account = accountRepository.findByIban(iban);
        account.deposit(amount);

        // Transaction speichern
        Transaction transaction = new Transaction(
            Transaction.TransactionType.DEPOSIT,
            amount,
            null,
            iban,
            "Einzahlung"
        );
        transactionRepository.save(transaction);
    }

    /**
     * Abhebung von Konto
     */
    public void withdraw(String iban, double amount) throws AccountNotFoundException, InsufficientFundsException, InvalidAmountException {
        Account account = accountRepository.findByIban(iban);
        account.withdraw(amount);

        // Transaction speichern
        Transaction transaction = new Transaction(
            Transaction.TransactionType.WITHDRAWAL,
            amount,
            iban,
            null,
            "Abhebung"
        );
        transactionRepository.save(transaction);
    }

    /**
     * Ueberweisung zwischen Konten
     * Komplexe Business-Logik hier in Service
     */
    public void transfer(String fromIban, String toIban, double amount)
        throws AccountNotFoundException, InsufficientFundsException, InvalidAmountException {

        Account fromAccount = accountRepository.findByIban(fromIban);
        Account toAccount = accountRepository.findByIban(toIban);

        // Abheben von Quellkonto
        fromAccount.withdraw(amount);

        // Einzahlen auf Zielkonto
        toAccount.deposit(amount);

        // Transaction speichern
        Transaction transaction = new Transaction(
            Transaction.TransactionType.TRANSFER,
            amount,
            fromIban,
            toIban,
            "Ueberweisung"
        );
        transactionRepository.save(transaction);
    }

    /**
     * Findet Account nach IBAN
     * Delegation an Repository
     */
    public Account findByIban(String iban) throws AccountNotFoundException {
        return accountRepository.findByIban(iban);
    }

    /**
     * Loescht Account
     */
    public void deleteAccount(String iban) throws AccountNotFoundException {
        Account account = accountRepository.findByIban(iban);
        accountRepository.delete(account);
    }
}
