package payment;

public class PayPalPaymentStrategy implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPaymentStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean pay(PaymentTransaction transaction) {
        System.out.println("\n🅿 Traitement paiement PAYPAL");
        System.out.println("   Compte: " + email);
        System.out.println("   Montant: " + transaction.getAmount() + " " + transaction.getCurrency());

        // Simulation authentification
        System.out.println("    Authentification PayPal...");
        simulateDelay(1200);

        if (!authenticate()) {
            System.out.println("    Échec d'authentification");
            return false;
        }

        System.out.println("    Vérification du solde...");
        simulateDelay(800);

        double fee = getTransactionFee(transaction.getAmount());
        System.out.println("    Frais de transaction: " + fee + " " + transaction.getCurrency());

        System.out.println("    Paiement effectué!");
        return true;
    }

    @Override
    public String getMethodName() {
        return "PAYPAL";
    }

    @Override
    public boolean supports(String currency) {
        return true; // PayPal supporte presque toutes les devises
    }

    @Override
    public double getTransactionFee(double amount) {
        return amount * 0.034 + 0.35; // 3.4% + 0.35€
    }

    private boolean authenticate() {
        return password.length() >= 6; // Simulation simple
    }

    private void simulateDelay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
