import payment.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║      STRATEGY PATTERN - DÉMONSTRATION         ║");
        System.out.println("╚════════════════════════════════════════════════╝");

        PaymentProcessor processor = new PaymentProcessor();

        // ==================== Exemple 1 : Carte de crédit ====================
        System.out.println("\n📋 SCÉNARIO 1 : Paiement par carte");
        System.out.println("═════════════════════════════════════════════════");

        PaymentTransaction tx1 = new PaymentTransaction(99.99, "EUR");
        processor.setPaymentStrategy(
                new CreditCardPaymentStrategy("1234567812345678", "123", "12/25")
        );
        processor.processPayment(tx1);
        System.out.println("\n" + tx1);

        // ==================== Exemple 2 : PayPal ====================
        System.out.println("\n\n📋 SCÉNARIO 2 : Paiement PayPal");
        System.out.println("═════════════════════════════════════════════════");

        PaymentTransaction tx2 = new PaymentTransaction(149.50, "USD");
        processor.setPaymentStrategy(
                new PayPalPaymentStrategy("user@example.com", "securepass123")
        );
        processor.processPayment(tx2);
        System.out.println("\n" + tx2);

        // ==================== Exemple 3 : Crypto ====================
        System.out.println("\n\n📋 SCÉNARIO 3 : Paiement Crypto");
        System.out.println("═════════════════════════════════════════════════");

        PaymentTransaction tx3 = new PaymentTransaction(500.00, "USD");
        processor.setPaymentStrategy(
                new CryptoPaymentStrategy("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "BTC")
        );
        processor.processPayment(tx3);
        System.out.println("\n" + tx3);

        // ==================== Exemple 4 : Virement bancaire ====================
        System.out.println("\n\n📋 SCÉNARIO 4 : Virement bancaire");
        System.out.println("═════════════════════════════════════════════════");

        PaymentTransaction tx4 = new PaymentTransaction(1200.00, "EUR");
        processor.setPaymentStrategy(
                new BankTransferPaymentStrategy("FR7612345678901234567890123", "BNPAFRPP")
        );
        processor.processPayment(tx4);
        System.out.println("\n" + tx4);

        // ==================== Exemple 5 : Devise non supportée ====================
        System.out.println("\n\n📋 SCÉNARIO 5 : Devise non supportée");
        System.out.println("═════════════════════════════════════════════════");

        PaymentTransaction tx5 = new PaymentTransaction(75.00, "JPY");
        processor.setPaymentStrategy(
                new BankTransferPaymentStrategy("FR7612345678901234567890123", "BNPAFRPP")
        );
        processor.processPayment(tx5);
        System.out.println("\n" + tx5);

        // ==================== RÉSUMÉ ====================
        System.out.println("\n\n✅ AVANTAGES DU STRATEGY PATTERN :");
        System.out.println("   • Élimination des if/else complexes");
        System.out.println("   • Facile d'ajouter de nouvelles méthodes de paiement");
        System.out.println("   • Chaque stratégie est testable indépendamment");
        System.out.println("   • Code plus maintenable et extensible");
        System.out.println("   • Respecte le principe Open/Closed");
    }
}