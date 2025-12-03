package payment;

import java.util.Arrays;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    private final String cardNumber;
    private final String cvv;
    private final  String expiryDate;

    public CreditCardPaymentStrategy(String cardNumber, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean pay(PaymentTransaction transaction) {
        System.out.println("\n Traitement paiement CARTE DE CRÉDIT");
        System.out.println("   Carte: " + cardNumber);
        System.out.println("   Montant: " + transaction.getAmount() + " " + transaction.getCurrency());

        // Simulation validation
        if (!validateCard()) {
            System.out.println("   Carte invalide ou expirée");
            return false;
        }

        // Simulation du traitement
        System.out.println("   Connexion au réseau bancaire...");
        simulateDelay(1000);
        System.out.println("   Vérification des fonds...");
        simulateDelay(800);

        double fee = getTransactionFee(transaction.getAmount());
        System.out.println("   Frais de transaction: " + fee + " " + transaction.getCurrency());

        System.out.println("   Paiement autorisé!");
        return true;
    }

    @Override
    public String getMethodName() {
        return "CREDIT_CARD";
    }

    @Override
    public boolean supports(String currency) {
        return Arrays.asList("EUR", "USD", "GBP").contains(currency);
    }

    @Override
    public double getTransactionFee(double amount) {
        return amount * 0.029 + 0.30; // 2.9% + 0.30€
    }

    private boolean validateCard() {
        // Simulation validation
        return !expiryDate.equals("12/23"); // Date expirée pour l'exemple
    }

    private String maskCardNumber(String cardNumber) {
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    private void simulateDelay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
