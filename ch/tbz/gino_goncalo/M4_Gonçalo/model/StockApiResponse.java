package ch.tbz.gino_goncalo.M4_Gonçalo.model;

/**
 * Repraesentiert API Response fuer Aktiendaten
 * Clean Code: Kapselt API Response in eigenem Objekt
 */
public class StockApiResponse {

    private String symbol;
    private String companyName;
    private double currentPrice;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private long volume;
    private boolean success;
    private String errorMessage;

    public StockApiResponse(String symbol, String companyName, double currentPrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.openPrice = currentPrice;
        this.highPrice = currentPrice;
        this.lowPrice = currentPrice;
        this.volume = 0;
        this.success = true;
        this.errorMessage = null;
    }

    public StockApiResponse(String symbol, String companyName, double currentPrice,
                           double openPrice, double highPrice, double lowPrice, long volume) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
        this.success = true;
        this.errorMessage = null;
    }

    // Error Response
    public static StockApiResponse error(String symbol, String errorMessage) {
        StockApiResponse response = new StockApiResponse(symbol, null, -1);
        response.success = false;
        response.errorMessage = errorMessage;
        return response;
    }

    // Getter
    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public long getVolume() {
        return volume;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        if(!success) {
            return "API Error: " + errorMessage;
        }
        return symbol + " (" + companyName + ") - CHF " + String.format("%.2f", currentPrice);
    }
}
