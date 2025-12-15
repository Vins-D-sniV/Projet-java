import trade.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║      OBSERVER PATTERN - DÉMONSTRATION         ║");
        System.out.println("║         Système de Trading en Temps Réel      ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");

        // Créer le sujet (marché boursier)
        StockMarket market = new StockMarket();

        // Créer les observateurs
        AutoTrader trader1 = new AutoTrader("AlgoBot-3000");
        AutoTrader trader2 = new AutoTrader("SmartTrader-AI");
        AlertService alertService = new AlertService();
        MarketLogger logger = new MarketLogger();
        TrendAnalyzer analyzer = new TrendAnalyzer();

        System.out.println("🔧 CONFIGURATION DES OBSERVATEURS\n");

        // Abonner les observateurs
        market.attach(trader1);
        market.attach(alertService);
        market.attach(logger);
        market.attach(analyzer);

        // Configurer des alertes
        alertService.setAlert("AAPL", 180.00);
        alertService.setAlert("GOOGL", 145.00);

        System.out.println("\n🚀 DÉBUT DE LA SIMULATION DE MARCHÉ\n");
        Thread.sleep(1000);

        // Simulation de mises à jour du marché
        market.updateStockPrice("AAPL", 175.50);
        Thread.sleep(800);

        market.updateStockPrice("GOOGL", 140.20);
        Thread.sleep(800);

        // Ajouter un deuxième trader en cours de route
        System.out.println("\n");
        market.attach(trader2);
        Thread.sleep(500);

        // Baisse significative (déclenchera des achats)
        market.updateStockPrice("AAPL", 172.30); // -1.82%
        Thread.sleep(800);

        // Hausse significative (déclenchera des ventes)
        market.updateStockPrice("GOOGL", 145.50); // +3.78%
        Thread.sleep(800);

        market.updateStockPrice("AAPL", 180.00); // +4.47% (alerte!)
        Thread.sleep(800);

        // Détacher un observateur
        System.out.println("\n");
        market.detach(trader2);
        Thread.sleep(500);

        market.updateStockPrice("GOOGL", 142.80);
        Thread.sleep(800);

        // ==================== RÉSULTATS ====================
        System.out.println("\n\n" + "═".repeat(60));
        System.out.println("📈 RÉSULTATS DE LA SESSION");
        System.out.println("═".repeat(60));

        trader1.displayPortfolio();
        logger.printHistory();

        System.out.println("\n✅ AVANTAGES DU OBSERVER PATTERN :");
        System.out.println("   • Découplage total entre Subject et Observers");
        System.out.println("   • Ajout/suppression dynamique d'observateurs");
        System.out.println("   • Notification automatique de tous les abonnés");
        System.out.println("   • Chaque observer réagit indépendamment");
        System.out.println("   • Facilite l'extension sans modifier le code existant");
    }
}