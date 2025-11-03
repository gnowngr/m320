package ch.tbz.gino_goncalo.M4_Gonçalo.util;

import ch.tbz.gino_goncalo.M4_Gonçalo.model.Account;
import ch.tbz.gino_goncalo.M4_Gonçalo.model.GiroKonto;
import ch.tbz.gino_goncalo.M4_Gonçalo.model.SparKonto;
import ch.tbz.gino_goncalo.M4_Gonçalo.model.Depot;

/**
 * Factory Pattern fuer Account-Erstellung
 *
 * Design Pattern: Factory Pattern
 * Begründung: Kapselt die Erstellungslogik verschiedener Kontotypen.
 * Ermoeglicht einfache Erweiterung um neue Kontotypen ohne bestehenden Code zu aendern.
 * Clean Code: Single Responsibility - nur fuer Konto-Erstellung zustaendig
 */
public class AccountFactory {

    // Enum fuer Account Typen (Clean Code: keine String-Literale)
    public enum AccountType {
        GIRO,
        SPAR,
        DEPOT
    }

    /**
     * Erstellt Account basierend auf Typ
     * Demonstriert Factory Pattern
     */
    public static Account createAccount(AccountType type, String ownerName) {
        // Clean Code: switch statement mit return (kurze Methode)
        switch(type) {
            case GIRO:
                return new GiroKonto(ownerName);
            case SPAR:
                return new SparKonto(ownerName);
            case DEPOT:
                return new Depot(ownerName);
            default:
                return new GiroKonto(ownerName);
        }
    }

    /**
     * Erstellt Account basierend auf String (fuer User Input)
     */
    public static Account createAccount(String typeString, String ownerName) {
        AccountType type;

        // Input validierung und Konvertierung
        switch(typeString.toUpperCase()) {
            case "GIRO":
            case "GIROKONTO":
                type = AccountType.GIRO;
                break;
            case "SPAR":
            case "SPARKONTO":
                type = AccountType.SPAR;
                break;
            case "DEPOT":
                type = AccountType.DEPOT;
                break;
            default:
                type = AccountType.GIRO;
        }

        return createAccount(type, ownerName);
    }
}
