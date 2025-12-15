package trade;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Observer 3 : Logger pour l'historique
public class MarketLogger implements StockObserver {
    private List<String> history = new ArrayList<>();

    @Override
    public void update(StockPrice stockPrice) {
        String logEntry = String.format("[%s] %s: %.2f$ (%s)",
                stockPrice.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                stockPrice.getSymbol(),
                stockPrice.getPrice(),
                stockPrice.getTrend());

        history.add(logEntry);
        System.out.println("\n📝 Log enregistré: " + logEntry);
    }

    @Override
    public String getObserverName() {
        return "MarketLogger";
    }

    public void printHistory() {
        System.out.println("\n📜 HISTORIQUE DU MARCHÉ:");
        System.out.println("═".repeat(60));
        history.forEach(System.out::println);
        System.out.println("═".repeat(60));
    }
}
