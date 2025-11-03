package ch.tbz.gino_goncalo.M4_Gonçalo.model;

/**
 * Enum fuer Kontotypen
 * Clean Code: Enums statt String-Literale verwenden
 */
public enum AccountType {
    GIROKONTO("Girokonto"),
    SPARKONTO("Sparkonto"),
    DEPOT("Depot");

    private String displayName;

    AccountType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Konvertiert String zu AccountType
     */
    public static AccountType fromString(String str) {
        switch(str.toUpperCase()) {
            case "GIRO":
            case "GIROKONTO":
                return GIROKONTO;
            case "SPAR":
            case "SPARKONTO":
                return SPARKONTO;
            case "DEPOT":
                return DEPOT;
            default:
                return GIROKONTO;
        }
    }
}
