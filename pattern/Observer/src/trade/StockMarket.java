package trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockMarket implements StockSubject {
    private List<StockObserver> observers = new ArrayList<>();
    private Map<String, StockPrice> currentPrices = new HashMap<>();
    private int updateCount = 0;

    @Override
    public void attach(StockObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("✅ " + observer.getObserverName() + " s'est abonné au marché");
        }
    }

    @Override
    public void detach(StockObserver observer) {
        if (observers.remove(observer)) {
            System.out.println(" " + observer.getObserverName() + " s'est désabonné du marché");
        }
    }

    @Override
    public void notifyObservers() {
        for (StockPrice price : currentPrices.values()) {
            for (StockObserver observer : observers) {
                observer.update(price);
            }
        }
    }

    public void updateStockPrice(String symbol, double newPrice) {
        double previousPrice = currentPrices.containsKey(symbol)
                ? currentPrices.get(symbol).getPrice()
                : newPrice;

        StockPrice stockPrice = new StockPrice(symbol, newPrice, previousPrice);
        currentPrices.put(symbol, stockPrice);
        updateCount++;

        System.out.println("\n" + "═".repeat(60));
        System.out.println("📊 MISE À JOUR #" + updateCount + " : " + stockPrice);
        System.out.println("═".repeat(60));

        // Notifier tous les observateurs
        for (StockObserver observer : observers) {
            observer.update(stockPrice);
        }
    }

    public StockPrice getStockPrice(String symbol) {
        return currentPrices.get(symbol);
    }
}