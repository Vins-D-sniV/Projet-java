package trade;

interface StockObserver {
    void update(StockPrice stockPrice);
    String getObserverName();
}
