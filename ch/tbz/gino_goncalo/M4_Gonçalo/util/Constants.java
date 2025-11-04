package ch.tbz.gino_goncalo.M4_Gonçalo.util;

/**
 * Zentrale Konstanten fuer das Banking System
 * Clean Code: Keine Magic Numbers oder Strings im Code
 */
public class Constants {

    // Waehrung
    public static final String CURRENCY = "CHF";

    // Account Limits
    public static final double GIROKONTO_OVERDRAFT_LIMIT = 1000.0;
    public static final double MAX_TRANSACTION_AMOUNT = 1000000.0;
    public static final double MIN_TRANSACTION_AMOUNT = 0.01;

    // Interest Rates
    public static final double SPARKONTO_INTEREST_RATE = 0.015; // 1.5%
    public static final double GIROKONTO_INTEREST_RATE = 0.0;   // 0%

    // Fees
    public static final double DEPOT_TRANSACTION_FEE = 5.0;

    // Validation
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 50;
    public static final int MIN_USERNAME_LENGTH = 3;
    public static final int IBAN_LENGTH = 21;
    public static final String IBAN_PREFIX = "CH";

    // Date Formats
    public static final String DATE_FORMAT_FULL = "dd.MM.yyyy HH:mm:ss";
    public static final String DATE_FORMAT_SHORT = "dd.MM.yyyy";
    public static final String DATE_FORMAT_TIME = "HH:mm:ss";

    // Application
    public static final String APP_NAME = "Banking System";
    public static final String APP_VERSION = "1.0.0";

    // Private constructor to prevent instantiation
    private Constants() {
        throw new UnsupportedOperationException("Utility class");
    }
}
