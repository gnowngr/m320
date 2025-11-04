# M4 Banking System - OOP Project

Ein objektorientiertes Bankensystem mit Yahoo Finance API Integration.

---

## Features

- Kontoverwaltung (Girokonto, Sparkonto, Depot)
- Geldtransaktionen (Einzahlen, Abheben, Überweisen)
- Aktienhandel mit **ECHTER** Yahoo Finance API Integration
- Transaktionshistorie
- Portfolio-Management
- Exception Handling

---

## Technische Anforderungen Erfüllt

| Anforderung | Status | Details |
|-------------|--------|---------|
| Klassen | OK | 25+ (gefordert: min. 8) |
| Zeilen Code | OK | 1200+ (gefordert: min. 800) |
| Design Patterns | OK | 2 (Factory Pattern, Repository Pattern) |
| Interfaces | OK | StockAPI, Transactable |
| Vererbung | OK | Account → GiroKonto/SparKonto/Depot |
| Polymorphismus | OK | Überschriebene Methoden (withdraw, calculateInterest) |
| Delegation | OK | UI → Service → Repository → API |
| Exception Handling | OK | 7 Custom Exceptions |
| Clean Code | OK | SRP, DRY, Constants |
| **API Integration** | OK | **Yahoo Finance (echte HTTP Requests!)** |

---

## Klassendiagramm

```
┌─────────────────────────────────────────────────────────────────────┐
│                         BANKING SYSTEM                               │
└─────────────────────────────────────────────────────────────────────┘

┌──────────────────┐
│   <<abstract>>   │
│     Account      │◄────────────────────────┐
├──────────────────┤                         │
│ - accountNumber  │                         │
│ - owner          │                         │
│ - balance        │                         │
├──────────────────┤                         │
│ + deposit()      │                         │
│ + withdraw()     │ (überschrieben)         │
│ + getBalance()   │                         │
└────────┬─────────┘                         │
         │                                   │
         │ (Vererbung)                       │
         │                                   │
    ┌────┴────┬──────────┬───────────┐      │
    │         │          │           │      │
┌───▼────┐ ┌─▼──────┐ ┌─▼───────┐ ┌─▼──────────┐

│ Giro   │ │ Spar   │ │  Depot  │ │ Customer   │
│ Konto  │ │ Konto  │ │         │ ├────────────┤
├────────┤ ├────────┤ ├─────────┤ │ - name     │
│-limit  │ │-zinsen │ │-stocks  │ │ - accounts │◄─┐
├────────┤ ├────────┤ ├─────────┤ └────────────┘  │
│withdraw│ │withdraw│ │buyStock │                 │
└────────┘ └────────┘ └─────────┘                 │
                          │                        │
                          │ verwendet              │
                          ▼                        │
                  ┌──────────────┐                 │
                  │ StockService │                 │
                  ├──────────────┤                 │
                  │ + buyStock() │─────────┐       │
                  │ + sellStock()│         │       │
                  └──────┬───────┘         │       │
                         │                 │       │
                         │ delegiert       │       │
                         ▼                 ▼       │
              ┌──────────────────┐   ┌─────────────────┐
              │  <<interface>>   │   │ AccountRepo     │
              │    StockAPI      │   ├─────────────────┤
              ├──────────────────┤   │ + findById()    │
              │ + getPrice()     │   │ + save()        │───┘
              │ + isValid()      │   │ + findAll()     │
              └────────┬─────────┘   └─────────────────┘
                       │
                       │ implementiert
                       ▼
            ┌──────────────────────┐
            │ RealYahooFinanceAPI  │
            ├──────────────────────┤
            │ - BASE_URL           │
            │ - priceCache         │
            ├──────────────────────┤
            │ + getPrice()         │◄──── HTTP Request!
            │ - parsePrice()       │      (echte API)
            └──────────────────────┘


┌──────────────────────────────────────────────────────────────┐
│  DESIGN PATTERNS:                                             │
│  • Factory Pattern: AccountFactory erstellt Account-Objekte  │
│  • Repository Pattern: AccountRepo, TransactionRepo           │
│  • Delegation: UI → Service → Repository → API               │
└──────────────────────────────────────────────────────────────┘
```

---

## Sequenzdiagramm - Use Case: "Aktie Kaufen"

Dieser Use Case zeigt das **Delegation Pattern** in Aktion:

```
User          BankingUI      StockService    AccountRepo    RealYahooFinanceAPI     Yahoo Server
 │                │               │                │                │                    │
 │  "Buy AAPL"   │               │                │                │                    │
 ├───────────────►│               │                │                │                    │
 │                │  buyStock()   │                │                │                    │
 │                ├───────────────►│                │                │                    │
 │                │               │  findById()    │                │                    │
 │                │               ├────────────────►│                │                    │
 │                │               │   [Depot]      │                │                    │
 │                │               │◄────────────────┤                │                    │
 │                │               │                │  getPrice()    │                    │
 │                │               ├────────────────────────────────►│                    │
 │                │               │                │                │  HTTP GET          │
 │                │               │                │                ├───────────────────►│
 │                │               │                │                │   JSON Response    │
 │                │               │                │                │◄───────────────────┤
 │                │               │                │   parsePrice() │                    │
 │                │               │                │                ├─┐                  │
 │                │               │                │                │ │ Extract value    │
 │                │               │                │                │◄┘                  │
 │                │               │    $269.05     │                │                    │
 │                │               │◄────────────────────────────────┤                    │
 │                │               │                │                │                    │
 │                │               ├─┐ Berechne     │                │                    │
 │                │               │ │ Gesamtpreis  │                │                    │
 │                │               │◄┘              │                │                    │
 │                │               │                │                │                    │
 │                │               ├─┐ Prüfe Depot  │                │                    │
 │                │               │ │ Balance      │                │                    │
 │                │               │◄┘              │                │                    │
 │                │               │                │                │                    │
 │                │               │  depot.buyStock()               │                    │
 │                │               ├─┐              │                │                    │
 │                │               │ │ Update Balance + Stocks        │                    │
 │                │               │◄┘              │                │                    │
 │                │               │                │                │                    │
 │                │               │   save(depot)  │                │                    │
 │                │               ├────────────────►│                │                    │
 │                │               │                │                │                    │
 │                │               │  createTransaction()            │                    │
 │                │               ├─┐              │                │                    │
 │                │               │ │ Log Transaction                │                    │
 │                │               │◄┘              │                │                    │
 │                │               │                │                │                    │
 │                │  "Success!"   │                │                │                    │
 │                │◄───────────────┤                │                │                    │
 │  "Gekauft!"   │               │                │                │                    │
 │◄───────────────┤               │                │                │                    │
 │                │               │                │                │                    │


POLYMORPHISMUS BEISPIEL:
━━━━━━━━━━━━━━━━━━━━━━━
depot.withdraw(1000)  ──► Ruft Depot.withdraw() (überschrieben)
giro.withdraw(1000)   ──► Ruft GiroKonto.withdraw() (mit Limit-Check)
spar.withdraw(1000)   ──► Ruft SparKonto.withdraw() (mit Gebühr)

Alle drei Konten sind vom Typ "Account", aber jede withdraw()-Methode
verhält sich anders (= Polymorphismus durch Method Overriding!)
```

---

## Projektstruktur

```
ch.tbz.gino_goncalo.M4_Gonçalo
│
├── model/              # Domain-Klassen
│   ├── Account.java         (abstract)
│   ├── GiroKonto.java       (extends Account)
│   ├── SparKonto.java       (extends Account)
│   ├── Depot.java           (extends Account)
│   ├── Customer.java
│   ├── Transaction.java
│   └── Stock.java
│
├── service/            # Business-Logik
│   ├── AccountService.java
│   ├── StockService.java
│   ├── TransactionService.java
│   ├── StockAPI.java        (interface)
│   ├── RealYahooFinanceAPI.java   (echte HTTP Requests!)
│   └── YahooFinanceAPI.java       (alte Simulation)
│
├── repository/         # Datenpersistenz (Repository Pattern)
│   ├── AccountRepository.java
│   └── TransactionRepository.java
│
├── ui/                 # User Interface
│   ├── BankingUI.java
│   └── MenuUI.java
│
├── exception/          # Custom Exceptions
│   ├── InsufficientFundsException.java
│   ├── AccountNotFoundException.java
│   ├── InvalidAmountException.java
│   ├── OverdraftException.java
│   ├── InvalidStockSymbolException.java
│   ├── AccountAlreadyExistsException.java
│   └── TransactionFailedException.java
│
├── util/               # Hilfsfunktionen
│   ├── AccountFactory.java  (Factory Pattern)
│   └── Validator.java
│
└── BankingApp.java     # Main Entry Point
```

---

## Starten des Projekts

```bash
# Kompilieren (falls nötig)
javac ch/tbz/gino_goncalo/M4_Gonçalo/BankingApp.java

# Ausführen
java ch.tbz.gino_goncalo.M4_Gonçalo.BankingApp
```

---

## API Integration - Yahoo Finance

Die **RealYahooFinanceAPI** macht echte HTTP-Requests an Yahoo Finance:

```java
// URL Format:
https://query1.finance.yahoo.com/v8/finance/chart/AAPL?interval=1d&range=1d

// JSON Response wird geparst (ohne externe Libraries!)
"regularMarketPrice": 269.05
```

**Features:**
- Echte HTTP GET Requests mit HttpURLConnection
- JSON Parsing ohne externe Libraries (String-Manipulation)
- Caching (1 Minute) um API nicht zu überlasten
- Error Handling (Try-Catch, Timeout)
- User-Agent Header für Kompatibilität

**Beispiel-Ausgabe:**
```
[REAL API] Fetched AAPL: $269.05
[REAL API] Fetched GOOGL: $283.72
[REAL API] Fetched TSLA: $468.37
```

---

## OOP Prinzipien Demonstriert

### 1. Polymorphismus
```java
Account konto = new GiroKonto(...);  // Polymorphe Variable
konto.withdraw(100);                  // Ruft GiroKonto.withdraw() auf
```

### 2. Vererbung
```java
GiroKonto extends Account
SparKonto extends Account
Depot extends Account
```

### 3. Delegation
```java
BankingUI → AccountService → AccountRepository → Database
          → StockService → RealYahooFinanceAPI → Yahoo Server
```

### 4. Design Patterns

**Factory Pattern:**
```java
Account konto = AccountFactory.createAccount("GIRO", owner, number);
```

**Repository Pattern:**
```java
accountRepo.save(konto);
konto = accountRepo.findById(id);
```

### 5. Clean Code
- Single Responsibility Principle (jede Klasse hat eine Aufgabe)
- Don't Repeat Yourself (Helper-Methoden)
- Meaningful Names (deposit, withdraw, buyStock)
- Constants statt Magic Numbers

---

## Exception Handling

Das System verwendet 7 Custom Exceptions:

1. **InsufficientFundsException** - Nicht genug Geld
2. **AccountNotFoundException** - Konto existiert nicht
3. **InvalidAmountException** - Ungültiger Betrag
4. **OverdraftException** - Überziehungslimit überschritten
5. **InvalidStockSymbolException** - Unbekanntes Aktiensymbol
6. **AccountAlreadyExistsException** - Konto existiert bereits
7. **TransactionFailedException** - Transaktion fehlgeschlagen

```java
try {
    depot.buyStock("AAPL", 10, stockAPI.getCurrentPrice("AAPL"));
} catch(InsufficientFundsException e) {
    System.err.println("Fehler: " + e.getMessage());
}
```

---

## Testing

**API Test:**
```bash
java ch.tbz.gino_goncalo.M4_Gonçalo.service.RealYahooFinanceAPI
```

**Full Application:**
```bash
java ch.tbz.gino_goncalo.M4_Gonçalo.BankingApp
```

---

## Autor

**Gino Gonçalo**
Modul 320 - Objektorientiert programmieren
2025

---

## Fazit / Reflection

Dieses Projekt demonstriert alle wichtigen OOP-Konzepte:

- **Vererbung** wird durch die Account-Hierarchie gezeigt
- **Polymorphismus** ermöglicht verschiedene withdraw()-Implementierungen
- **Delegation** trennt UI, Business-Logik und Datenzugriff sauber
- **Design Patterns** machen den Code wartbar und erweiterbar
- **API Integration** zeigt Real-World-Anwendung (Yahoo Finance)

Die größte Herausforderung war die **echte API-Integration ohne externe Libraries**. Das JSON-Parsing musste manuell mit String-Manipulation implementiert werden, was fehleranfällig war aber das Verständnis von HTTP und JSON vertieft hat.

Das **Repository Pattern** und **Factory Pattern** machen das System leicht erweiterbar - neue Account-Typen oder APIs können einfach hinzugefügt werden ohne bestehenden Code zu ändern.

---

**END OF README**
