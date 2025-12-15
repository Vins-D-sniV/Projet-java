package trade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// ==================== MODÈLE DE DONNÉES ====================
class StockPrice {
    private final String symbol;
    private final double price;
    private final double previousPrice;
    private final LocalDateTime timestamp;
    private final double changePercent;

    public StockPrice(String symbol, double price, double previousPrice) {
        this.symbol = symbol;
        this.price = price;
        this.previousPrice = previousPrice;
        this.timestamp = LocalDateTime.now();
        this.changePercent = ((price - previousPrice) / previousPrice) * 100;
    }

    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }
    public double getPreviousPrice() { return previousPrice; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public double getChangePercent() { return changePercent; }

    public boolean isSignificantChange() {
        return Math.abs(changePercent) >= 2.0; // Changement >= 2%
    }

    public String getTrend() {
        if (changePercent > 0) return " HAUSSE";
        if (changePercent < 0) return " BAISSE";
        return "STABLE";
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f$ (%+.2f%%) [%s]",
                symbol, price, changePercent,
                timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}