package trade;

import java.util.HashMap;
import java.util.Map;

// Observer 1 : Trader qui achète/vend automatiquement
public class AutoTrader implements StockObserver {
    private String name;
    private Map<String, Integer> portfolio = new HashMap<>();
    private double cashBalance = 50000.0;
    private double buyThreshold = -1.5; // Acheter si baisse de 1.5%
    private double sellThreshold = 2.0;  // Vendre si hausse de 2%

    public AutoTrader(String name) {
        this.name = name;
    }

    @Override
    public void update(StockPrice stockPrice) {
        String symbol = stockPrice.getSymbol();
        double changePercent = stockPrice.getChangePercent();

        System.out.println("\n🤖 " + name + " analyse " + symbol + "...");

        // Logique d'achat
        if (changePercent <= buyThreshold && cashBalance >= stockPrice.getPrice()) {
            int quantity = (int)(cashBalance * 0.1 / stockPrice.getPrice()); // 10% du cash
            if (quantity > 0) {
                portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
                cashBalance -= quantity * stockPrice.getPrice();
                System.out.println("   💰 ACHAT: " + quantity + " actions de " + symbol +
                        " à " + stockPrice.getPrice() + "$");
                System.out.println("   Solde restant: " + String.format("%.2f$", cashBalance));
            }
        }

        // Logique de vente
        else if (changePercent >= sellThreshold && portfolio.containsKey(symbol)) {
            int quantity = portfolio.get(symbol);
            double profit = quantity * (stockPrice.getPrice() - stockPrice.getPreviousPrice());
            portfolio.remove(symbol);
            cashBalance += quantity * stockPrice.getPrice();
            System.out.println("   💸 VENTE: " + quantity + " actions de " + symbol +
                    " à " + stockPrice.getPrice() + "$");
            System.out.println("   Profit: " + String.format("%+.2f$", profit));
            System.out.println("   Nouveau solde: " + String.format("%.2f$", cashBalance));
        }

        else {
            System.out.println("   ⏸️  ATTENTE - Pas d'action pour le moment");
        }
    }

    @Override
    public String getObserverName() {
        return "AutoTrader[" + name + "]";
    }

    public void displayPortfolio() {
        System.out.println("\n📊 Portfolio de " + name + ":");
        System.out.println("   Cash: " + String.format("%.2f$", cashBalance));
        portfolio.forEach((symbol, qty) ->
                System.out.println("   " + symbol + ": " + qty + " actions"));
    }
}
