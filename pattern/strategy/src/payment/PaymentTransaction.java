package payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PaymentTransaction {
    private final String id;
    private final double amount;
    private final String currency;
    private final LocalDateTime timestamp;
    private String status;
    private String method;

    public PaymentTransaction(double amount, String currency) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.amount = amount;
        this.currency = currency;
        this.timestamp = LocalDateTime.now();
        this.status = "PENDING";
    }

    public void setStatus(String status) { this.status = status; }
    public void setMethod(String method) { this.method = method; }

    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getId() { return id; }

    @Override
    public String toString() {
        return String.format("[Transaction %s] %.2f %s - %s via %s (%s)",
                id, amount, currency, status, method,
                timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}