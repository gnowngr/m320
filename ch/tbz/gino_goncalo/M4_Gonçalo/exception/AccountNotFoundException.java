package ch.tbz.gino_goncalo.M4_Gonçalo.exception;

/**
 * Exception wenn Konto nicht gefunden wird
 * Tritt auf bei Suche nach IBAN die nicht existiert
 */
public class AccountNotFoundException extends Exception {

    private String searchedIban;

    public AccountNotFoundException(String iban) {
        super("Konto mit IBAN " + iban + " wurde nicht gefunden!");
        this.searchedIban = iban;
    }

    public String getSearchedIban() {
        return searchedIban;
    }
}
