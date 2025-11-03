package ch.tbz.gino_goncalo.M4_Gonçalo.util;

/**
 * Utility-Klasse fuer Geld-Formatierung
 * Clean Code: SRP - nur fuer Money-Formatting zustaendig
 */
public class MoneyFormatter {

    // Clean Code: Konstanten
    private static final String CURRENCY = "CHF";

    /**
     * Formatiert Betrag mit 2 Nachkommastellen
     */
    public static String format(double amount) {
        return String.format("%.2f", amount);
    }

    /**
     * Formatiert Betrag mit Waehrung
     */
    public static String formatWithCurrency(double amount) {
        return CURRENCY + " " + format(amount);
    }

    /**
     * Formatiert grossen Betrag mit Tausender-Trennzeichen
     */
    public static String formatLarge(double amount) {
        return String.format("%,.2f", amount);
    }

    /**
     * Formatiert mit Waehrung und Tausender-Trennzeichen
     */
    public static String formatLargeWithCurrency(double amount) {
        return CURRENCY + " " + formatLarge(amount);
    }

    /**
     * Formatiert Prozentsatz
     */
    public static String formatPercent(double percent) {
        return String.format("%.2f%%", percent);
    }

    /**
     * Formatiert positive/negative Betraege mit +/-
     */
    public static String formatWithSign(double amount) {
        String sign = amount >= 0 ? "+" : "";
        return sign + formatWithCurrency(amount);
    }
}
