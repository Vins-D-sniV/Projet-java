package payment;

public class BankTransferPaymentStrategy implements PaymentStrategy{
    private String iban;
    private String bic;

    public BankTransferPaymentStrategy(String iban, String bic) {
        this.iban = iban;
        this.bic = bic;
    }

    @Override
    public boolean pay(PaymentTransaction transaction) {
        System.out.println("\n🏦 Traitement paiement VIREMENT BANCAIRE");
        System.out.println("   IBAN: " + maskIban(iban));
        System.out.println("   BIC: " + bic);
        System.out.println("   Montant: " + transaction.getAmount() + " " + transaction.getCurrency());

        System.out.println("   ⏳ Initiation du virement SEPA...");
        simulateDelay(1500);
        System.out.println("   ⚠️  Délai de traitement: 1-2 jours ouvrables");

        double fee = getTransactionFee(transaction.getAmount());
        System.out.println("   💰 Frais de virement: " + fee + " " + transaction.getCurrency());

        System.out.println("   ✅ Virement programmé!");
        return true;
    }

    @Override
    public String getMethodName() {
        return "BANK_TRANSFER";
    }

    @Override
    public boolean supports(String currency) {
        return currency.equals("EUR"); // SEPA uniquement pour EUR
    }

    @Override
    public double getTransactionFee(double amount) {
        return 0.0; // Souvent gratuit pour les virements SEPA
    }

    private String maskIban(String iban) {
        return iban.substring(0, 4) + " **** **** " + iban.substring(iban.length() - 4);
    }

    private void simulateDelay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
