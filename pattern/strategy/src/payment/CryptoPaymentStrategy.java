package payment;

import java.util.Arrays;

public class CryptoPaymentStrategy implements PaymentStrategy{
    private String walletAddress;
    private String cryptoCurrency;

    public CryptoPaymentStrategy(String walletAddress, String cryptoCurrency) {
        this.walletAddress = walletAddress;
        this.cryptoCurrency = cryptoCurrency;
    }

    @Override
    public boolean pay(PaymentTransaction transaction) {
        System.out.println("\n₿ Traitement paiement CRYPTO (" + cryptoCurrency + ")");
        System.out.println("   Wallet: " + walletAddress);
        System.out.println("   Montant: " + transaction.getAmount() + " " + transaction.getCurrency());

        // Conversion en crypto
        double cryptoAmount = convertToCrypto(transaction.getAmount());
        System.out.println("   Équivalent: " + cryptoAmount + " " + cryptoCurrency);

        System.out.println("   ⏳ Création de la transaction blockchain...");
        simulateDelay(2000);
        System.out.println("   ⏳ Attente de confirmation (1/3)...");
        simulateDelay(1500);

        double fee = getTransactionFee(transaction.getAmount());
        System.out.println("   💰 Frais réseau: " + fee + " " + cryptoCurrency);

        System.out.println("   ✅ Transaction confirmée sur la blockchain!");
        return true;
    }

    @Override
    public String getMethodName() {
        return "CRYPTO_" + cryptoCurrency;
    }

    @Override
    public boolean supports(String currency) {
        return Arrays.asList("USD", "EUR").contains(currency);
    }

    @Override
    public double getTransactionFee(double amount) {
        return 0.0001; // Frais fixes en crypto
    }

    private double convertToCrypto(double fiatAmount) {
        // Simulation taux de change (exemple avec BTC)
        return cryptoCurrency.equals("BTC") ? fiatAmount / 45000.0 : fiatAmount / 2500.0;
    }

    private void simulateDelay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
