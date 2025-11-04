package ch.tbz.gino_goncalo.M4_Gonçalo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * ECHTE Yahoo Finance API Implementation mit HTTP Requests
 * Holt echte Aktienkurse von Yahoo Finance
 *
 * WICHTIG: Dies ist eine ECHTE API Integration!
 * - Macht echte HTTP Requests
 * - Holt echte Daten von Yahoo Finance
 * - Parst JSON Response
 *
 * Delegation: Service delegiert an externe Yahoo Finance API
 * Clean Code: Error Handling, Caching fuer Performance
 */
public class RealYahooFinanceAPI implements StockAPI {

    private static final String BASE_URL = "https://query1.finance.yahoo.com/v8/finance/chart/";
    private Map<String, String> companyNames;
    private Map<String, Double> priceCache;
    private long lastCacheUpdate;
    private static final long CACHE_DURATION = 60000; // 1 Minute

    public RealYahooFinanceAPI() {
        this.companyNames = new HashMap<>();
        this.priceCache = new HashMap<>();
        this.lastCacheUpdate = 0;
        initializeCompanyNames();
    }

    private void initializeCompanyNames() {
        companyNames.put("AAPL", "Apple Inc.");
        companyNames.put("GOOGL", "Alphabet Inc.");
        companyNames.put("MSFT", "Microsoft Corporation");
        companyNames.put("AMZN", "Amazon.com Inc.");
        companyNames.put("TSLA", "Tesla Inc.");
        companyNames.put("META", "Meta Platforms Inc.");
        companyNames.put("NVDA", "NVIDIA Corporation");
        companyNames.put("NFLX", "Netflix Inc.");
    }

    /**
     * Holt ECHTEN Aktienkurs von Yahoo Finance API
     * Macht echten HTTP Request!
     */
    @Override
    public double getCurrentPrice(String symbol) {
        try {
            // Check Cache
            if(priceCache.containsKey(symbol) && !isCacheExpired()) {
                return priceCache.get(symbol);
            }

            // Mache ECHTEN HTTP Request
            String urlString = BASE_URL + symbol + "?interval=1d&range=1d";
            System.out.println("[DEBUG] Requesting: " + urlString);

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            int responseCode = conn.getResponseCode();
            System.out.println("[DEBUG] Response Code: " + responseCode);

            if(responseCode != 200) {
                System.err.println("API Error: HTTP " + responseCode);
                // Lese Error Response
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String errorLine;
                while((errorLine = errorReader.readLine()) != null) {
                    System.err.println("[ERROR] " + errorLine);
                }
                errorReader.close();
                return -1;
            }

            // Lese Response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON (simple parsing ohne externe Library)
            String jsonResponse = response.toString();

            // Debug: Zeige ersten Teil von Response
            System.out.println("[DEBUG] Response length: " + jsonResponse.length());
            System.out.println("[DEBUG] Response preview: " + jsonResponse.substring(0, Math.min(200, jsonResponse.length())));

            double price = parsePrice(jsonResponse);

            if(price > 0) {
                priceCache.put(symbol, price);
                lastCacheUpdate = System.currentTimeMillis();
                System.out.println("[REAL API] Fetched " + symbol + ": $" + price);
            } else {
                System.err.println("[ERROR] Failed to parse price from response");
            }

            return price;

        } catch(Exception e) {
            System.err.println("API Request failed: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Parst Preis aus Yahoo Finance JSON Response
     * Findet "regularMarketPrice" Wert
     */
    private double parsePrice(String json) {
        try {
            // DEBUG: Print full JSON for analysis
            System.out.println("[DEBUG] Full JSON Response:");
            System.out.println(json);
            System.out.println("[DEBUG] End of JSON\n");

            // Suche nach "regularMarketPrice":{"raw":150.25
            int priceIndex = json.indexOf("\"regularMarketPrice\"");
            System.out.println("[DEBUG] regularMarketPrice index: " + priceIndex);

            if(priceIndex == -1) {
                System.err.println("[ERROR] Could not find 'regularMarketPrice' in JSON");
                return -1;
            }

            // Finde "raw": Wert
            int rawIndex = json.indexOf("\"raw\":", priceIndex);
            System.out.println("[DEBUG] raw index: " + rawIndex);

            if(rawIndex == -1) {
                System.err.println("[ERROR] Could not find 'raw' after regularMarketPrice");
                // Print context around priceIndex
                int contextStart = Math.max(0, priceIndex - 50);
                int contextEnd = Math.min(json.length(), priceIndex + 200);
                System.err.println("[DEBUG] Context: " + json.substring(contextStart, contextEnd));
                return -1;
            }

            // Extrahiere Zahl
            int start = rawIndex + 6; // Skip "raw":
            int end = start;
            while(end < json.length() && (Character.isDigit(json.charAt(end)) || json.charAt(end) == '.')) {
                end++;
            }

            String priceStr = json.substring(start, end);
            System.out.println("[DEBUG] Extracted price string: '" + priceStr + "'");

            double price = Double.parseDouble(priceStr);
            System.out.println("[DEBUG] Parsed price: " + price);
            return price;

        } catch(Exception e) {
            System.err.println("JSON Parse Error: " + e.getMessage());
            e.printStackTrace();
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
     * Aktualisiert alle Kurse von API
     */
    @Override
    public void refreshPrices() {
        System.out.println("\n[REAL API] Refreshing prices from Yahoo Finance...");
        priceCache.clear();
        lastCacheUpdate = 0;

        for(String symbol : companyNames.keySet()) {
            double price = getCurrentPrice(symbol);
            if(price > 0) {
                System.out.println("  " + symbol + ": $" + String.format("%.2f", price));
            }
        }
        System.out.println("Prices updated!\n");
    }

    private boolean isCacheExpired() {
        return (System.currentTimeMillis() - lastCacheUpdate) > CACHE_DURATION;
    }

    /**
     * Test Methode - zeigt dass API funktioniert
     */
    public static void main(String[] args) {
        System.out.println("=== Testing REAL Yahoo Finance API ===\n");
        RealYahooFinanceAPI api = new RealYahooFinanceAPI();

        String[] testSymbols = {"AAPL", "GOOGL", "TSLA"};

        for(String symbol : testSymbols) {
            double price = api.getCurrentPrice(symbol);
            if(price > 0) {
                System.out.println(symbol + " (" + api.getCompanyName(symbol) + "): $" + price);
            } else {
                System.out.println(symbol + ": Failed to fetch");
            }
        }

        System.out.println("\n=== API Test Complete ===");
    }
}
