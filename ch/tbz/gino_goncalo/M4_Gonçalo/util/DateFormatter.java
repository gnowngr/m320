package ch.tbz.gino_goncalo.M4_Gonçalo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility-Klasse fuer Datum-Formatierung
 * Clean Code: SRP - nur fuer Date-Formatting zustaendig
 */
public class DateFormatter {

    // Clean Code: Konstanten definieren
    private static final String DEFAULT_PATTERN = "dd.MM.yyyy HH:mm:ss";
    private static final String SHORT_PATTERN = "dd.MM.yyyy";
    private static final String TIME_PATTERN = "HH:mm:ss";

    /**
     * Formatiert DateTime mit Standard-Pattern
     */
    public static String format(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        return dateTime.format(formatter);
    }

    /**
     * Formatiert DateTime mit kurzem Pattern (nur Datum)
     */
    public static String formatShort(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SHORT_PATTERN);
        return dateTime.format(formatter);
    }

    /**
     * Formatiert DateTime nur Zeit
     */
    public static String formatTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        return dateTime.format(formatter);
    }

    /**
     * Formatiert mit custom Pattern
     */
    public static String formatCustom(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * Gibt aktuelles Datum formatiert zurueck
     */
    public static String getCurrentDateTime() {
        return format(LocalDateTime.now());
    }
}
