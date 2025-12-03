package payment;

public class PaymentProcessor {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean processPayment(PaymentTransaction transaction) {
        if (strategy == null) {
            throw new IllegalStateException("Aucune stratégie de paiement définie");
        }

        // Vérifier si la stratégie supporte la devise
        if (!strategy.supports(transaction.getCurrency())) {
            System.out.println(" La méthode " + strategy.getMethodName() +
                    " ne supporte pas la devise " + transaction.getCurrency());
            transaction.setStatus("REJECTED");
            return false;
        }

        transaction.setMethod(strategy.getMethodName());

        boolean success = strategy.pay(transaction);
        transaction.setStatus(success ? "COMPLETED" : "FAILED");

        return success;
    }
}
