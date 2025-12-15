package trade;

import java.util.HashMap;
import java.util.Map;

// Observer 2 : Service d'alertes SMS/Email
public class AlertService implements StockObserver {
    private Map<String, Double> alertThresholds = new HashMap<>();
    private int alertsSent = 0;

    public void setAlert(String symbol, double threshold) {
        alertThresholds.put(symbol, threshold);
        System.out.println("🔔 Alerte configurée pour " + symbol + " à " + threshold + "$");
    }

    @Override
    public void update(StockPrice stockPrice) {
        String symbol = stockPrice.getSymbol();

        if (alertThresholds.containsKey(symbol)) {
            double threshold = alertThresholds.get(symbol);

            if (stockPrice.getPrice() >= threshold) {
                sendAlert(stockPrice, "OBJECTIF ATTEINT");
            } else if (stockPrice.isSignificantChange()) {
                sendAlert(stockPrice, "CHANGEMENT SIGNIFICATIF");
            }
        }
    }

    private void sendAlert(StockPrice stockPrice, String reason) {
        alertsSent++;
        System.out.println("\n🚨 ALERTE #" + alertsSent + " - " + reason);
        System.out.println("   📱 SMS envoyé: " + stockPrice);
        System.out.println("   📧 Email envoyé: Détails de la transaction");
    }

    @Override
    public String getObserverName() {
        return "AlertService";
    }
}
