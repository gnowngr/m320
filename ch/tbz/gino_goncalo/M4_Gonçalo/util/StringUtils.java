package ch.tbz.gino_goncalo.M4_Gonçalo.util;

/**
 * String Utility Klasse
 * Clean Code: Hilfsmethoden an zentralem Ort
 */
public class StringUtils {

    /**
     * Maskiert IBAN (zeigt nur letzte 4 Ziffern)
     * Fuer Sicherheit bei Ausgaben
     */
    public static String maskIban(String iban) {
        if(iban == null || iban.length() < 4) {
            return "****";
        }
        String lastFour = iban.substring(iban.length() - 4);
        return "***************" + lastFour;
    }

    /**
     * Formatiert Namen (Capitalize first letter)
     */
    public static String capitalizeName(String name) {
        if(name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    /**
     * Entfernt Leerzeichen
     */
    public static String removeWhitespace(String str) {
        if(str == null) {
            return null;
        }
        return str.replaceAll("\\s+", "");
    }

    /**
     * Prueft ob String nur Zahlen enthaelt
     */
    public static boolean isNumeric(String str) {
        if(str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("\\d+");
    }

    /**
     * Truncate String auf maximale Laenge
     */
    public static String truncate(String str, int maxLength) {
        if(str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength) + "...";
    }
}
