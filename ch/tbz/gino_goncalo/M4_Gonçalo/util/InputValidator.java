package ch.tbz.gino_goncalo.M4_Gonçalo.util;

import ch.tbz.gino_goncalo.M4_Gonçalo.exception.InvalidAmountException;

/**
 * Utility-Klasse fuer Input-Validierung
 * Clean Code: SRP - nur fuer Validierung zustaendig
 * Hilft bei Exception Handling
 */
public class InputValidator {

    // Clean Code: Konstanten statt Hard-Coding
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 50;
    private static final int IBAN_LENGTH = 21;

    /**
     * Validiert Betrag
     */
    public static void validateAmount(double amount) throws InvalidAmountException {
        if(amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        if(amount > 1000000) {
            throw new InvalidAmountException("Betrag zu gross (max. 1'000'000)");
        }
    }

    /**
     * Validiert IBAN Format
     */
    public static boolean isValidIban(String iban) {
        if(iban == null || iban.length() != IBAN_LENGTH) {
            return false;
        }
        // Pruefe ob mit CH startet
        if(!iban.startsWith("CH")) {
            return false;
        }
        return true;
    }

    /**
     * Validiert Passwort
     */
    public static boolean isValidPassword(String password) {
        if(password == null) {
            return false;
        }
        int length = password.length();
        return length >= MIN_PASSWORD_LENGTH && length <= MAX_PASSWORD_LENGTH;
    }

    /**
     * Validiert Username
     */
    public static boolean isValidUsername(String username) {
        if(username == null || username.trim().isEmpty()) {
            return false;
        }
        // Username muss mind. 3 Zeichen haben
        return username.length() >= 3;
    }

    /**
     * Validiert ob String nicht leer ist
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * Validiert Email Format
     */
    public static boolean isValidEmail(String email) {
        if(email == null || email.trim().isEmpty()) {
            return false;
        }
        // Einfache Email-Validierung
        return email.contains("@") && email.contains(".");
    }

    /**
     * Validiert Aktien-Symbol
     * Symbole sind 1-5 Grossbuchstaben
     */
    public static boolean isValidStockSymbol(String symbol) {
        if(symbol == null || symbol.isEmpty()) {
            return false;
        }
        // Symbol muss 1-5 Zeichen haben und nur Buchstaben enthalten
        if(symbol.length() < 1 || symbol.length() > 5) {
            return false;
        }
        return symbol.matches("[A-Z]+");
    }

    /**
     * Validiert ob Wert positiv ist
     */
    public static boolean isPositive(double value) {
        return value > 0;
    }

    /**
     * Validiert ob Wert nicht negativ ist
     */
    public static boolean isNonNegative(double value) {
        return value >= 0;
    }

    /**
     * Validiert Ganzzahl-Bereich
     */
    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
