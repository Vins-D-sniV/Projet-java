package trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Observer 4 : Analyseur de tendances
public class TrendAnalyzer implements StockObserver {
    private Map<String, List<Double>> priceHistory = new HashMap<>();

    @Override
    public void update(StockPrice stockPrice) {
        String symbol = stockPrice.getSymbol();
        priceHistory.putIfAbsent(symbol, new ArrayList<>());
        priceHistory.get(symbol).add(stockPrice.getPrice());

        if (priceHistory.get(symbol).size() >= 3) {
            analyzeTrend(symbol);
        }
    }

    private void analyzeTrend(String symbol) {
        List<Double> prices = priceHistory.get(symbol);
        int size = prices.size();

        double recentAvg = prices.subList(Math.max(0, size - 3), size)
                .stream().mapToDouble(Double::doubleValue).average().orElse(0);

        double overallAvg = prices.stream().mapToDouble(Double::doubleValue).average().orElse(0);

        System.out.println("\n📊 Analyse tendance " + symbol + ":");
        System.out.println("   Moyenne récente (3 derniers): " + String.format("%.2f$", recentAvg));
        System.out.println("   Moyenne globale: " + String.format("%.2f$", overallAvg));

        if (recentAvg > overallAvg * 1.02) {
            System.out.println("   ⬆️  Tendance HAUSSIÈRE détectée");
        } else if (recentAvg < overallAvg * 0.98) {
            System.out.println("   ⬇️  Tendance BAISSIÈRE détectée");
        } else {
            System.out.println("   ➡️  Marché LATÉRAL");
        }
    }

    @Override
    public String getObserverName() {
        return "TrendAnalyzer";
    }
}