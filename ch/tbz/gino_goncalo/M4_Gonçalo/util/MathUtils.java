package ch.tbz.gino_goncalo.M4_Gonçalo.util;

/**
 * Mathematische Hilfsfunktionen
 * Clean Code: SRP - nur fuer Math-Operationen
 */
public class MathUtils {

    /**
     * Rundet auf 2 Dezimalstellen
     */
    public static double roundTo2Decimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Berechnet Prozentsatz
     */
    public static double calculatePercentage(double part, double whole) {
        if(whole == 0) {
            return 0;
        }
        return (part / whole) * 100.0;
    }

    /**
     * Berechnet Zinsen
     */
    public static double calculateCompoundInterest(double principal, double rate, int years) {
        return principal * Math.pow(1 + rate, years);
    }

    /**
     * Prueft ob Wert in Bereich ist
     */
    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    /**
     * Gibt groesseren Wert zurueck
     */
    public static double max(double a, double b) {
        return a > b ? a : b;
    }

    /**
     * Gibt kleineren Wert zurueck
     */
    public static double min(double a, double b) {
        return a < b ? a : b;
    }
}
