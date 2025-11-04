package ch.tbz.gino_goncalo.M4_Gonçalo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Yahoo Finance API Implementation
 * Demonstriert: Externe API Integration und HTTP Requests
 *
 * HINWEIS: Verwendet vereinfachte API-Simulation
 * In Production wuerde man echte Yahoo Finance API oder Alpha Vantage verwenden
 *
 * Design Pattern: Adapter Pattern (adaptiert externe API zu unserem Interface)
 * Delegation: Service delegiert Kursabfrage an externe API
 */
public class YahooFinanceAPI implements StockAPI {

    private static final String API_BASE_URL = "https://query1.finance.yahoo.com/v8/finance/chart/";

    // Cache fuer API Responses (Clean Code: Performance Optimierung)
    private Map<String, Double> priceCache;
    private Map<String, String> companyNames;
    private Random random;

    public YahooFinanceAPI() {
        this.priceCache = new HashMap<>();
        this.companyNames = new HashMap<>();
        this.random = new Random();
        initializeStockData();
    }

    /**
     * Initialisiert bekannte Aktien
     * In echter Implementation wuerde dies von API kommen
     */
    private void initializeStockData() {
        // Bekannte Aktien mit realistischen Preisen
        companyNames.put("AAPL", "Apple Inc.");
        companyNames.put("GOOGL", "Alphabet Inc.");
        companyNames.put("MSFT", "Microsoft Corporation");
        companyNames.put("AMZN", "Amazon.com Inc.");
        companyNames.put("TSLA", "Tesla Inc.");
        companyNames.put("META", "Meta Platforms Inc.");
        companyNames.put("NVDA", "NVIDIA Corporation");
        companyNames.put("NFLX", "Netflix Inc.");

        // Initiale Preise (realistische Werte)
        priceCache.put("AAPL", 178.50);
        priceCache.put("GOOGL", 142.30);
        priceCache.put("MSFT", 415.20);
        priceCache.put("AMZN", 178.90);
        priceCache.put("TSLA", 242.80);
        priceCache.put("META", 485.60);
        priceCache.put("NVDA", 875.30);
        priceCache.put("NFLX", 612.40);
    }

    /**
     * Holt aktuellen Kurs von API
     * Demonstriert HTTP Request an externe API
     */
    @Override
    public double getCurrentPrice(String symbol) {
        try {
            // Pruefe ob Symbol bekannt ist
            if(!isValidSymbol(symbol)) {
                return -1;
            }

            // Simuliere API Call mit leichter Preisschwankung
            // In echter Implementation wuerde hier echter HTTP Request stattfinden:
            /*
            URL url = new URL(API_BASE_URL + symbol);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // Parse JSON response...
            */

            // Simuliere Marktbewegung (±2%)
            double basePrice = priceCache.get(symbol);
            double fluctuation = (random.nextDouble() * 0.04) - 0.02; // -2% to +2%
            double currentPrice = basePrice * (1 + fluctuation);

            // Update Cache
            priceCache.put(symbol, currentPrice);

            return Math.round(currentPrice * 100.0) / 100.0;

        } catch(Exception e) {
            System.err.println("API Error: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public boolean isValidSymbol(String symbol) {
        return companyNames.containsKey(symbol);
    }

    @Override
    public String getCompanyName(String symbol) {
        return companyNames.get(symbol);
    }

    /**
     * Simuliert API Refresh
     * In echter Implementation wuerde neue Daten von API geholt
     */
    @Override
    public void refreshPrices() {
        System.out.println("Refreshing stock prices from API...");

        for(String symbol : priceCache.keySet()) {
            getCurrentPrice(symbol); // Update mit neuer Fluktuation
        }

        System.out.println("Prices updated!");
    }

    /**
     * Hilfsmethode: Simuliert HTTP GET Request
     * In Production wuerde man HttpClient verwenden
     */
    private String makeHttpRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = conn.getResponseCode();
        if(responseCode != 200) {
            throw new Exception("API returned error code: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
