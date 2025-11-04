package ch.tbz.gino_goncalo.M4_Gonçalo.service;

/**
 * Interface fuer Stock API Integration
 * Demonstriert: Interface-Verwendung und API-Delegation
 *
 * Clean Code: Interface definiert Vertrag, Implementierung kann ausgetauscht werden
 * (z.B. MockAPI fuer Tests, RealAPI fuer Production)
 */
public interface StockAPI {

    /**
     * Holt aktuellen Aktienkurs
     * @param symbol Aktien-Symbol (z.B. "AAPL", "GOOGL")
     * @return Aktueller Preis oder -1 bei Fehler
     */
    double getCurrentPrice(String symbol);

    /**
     * Prueft ob Aktie existiert
     * @param symbol Aktien-Symbol
     * @return true wenn gefunden
     */
    boolean isValidSymbol(String symbol);

    /**
     * Holt Firmenname zu Symbol
     * @param symbol Aktien-Symbol
     * @return Firmenname oder null wenn nicht gefunden
     */
    String getCompanyName(String symbol);

    /**
     * Aktualisiert Kurse (simuliert API refresh)
     */
    void refreshPrices();
}
