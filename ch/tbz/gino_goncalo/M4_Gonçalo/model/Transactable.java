package ch.tbz.gino_goncalo.M4_Gonçalo.model;

import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InsufficientFundsException;
import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InvalidAmountException;

/**
 * Interface fuer alle Objekte die Transaktionen durchfuehren koennen
 * Definiert Vertrag fuer Einzahlung und Abhebung
 */
public interface Transactable {

    /**
     * Zahlt Betrag ein
     */
    void deposit(double amount) throws InvalidAmountException;

    /**
     * Hebt Betrag ab
     */
    void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException;

    /**
     * Gibt aktuellen Saldo zurueck
     */
    double getBalance();
}
