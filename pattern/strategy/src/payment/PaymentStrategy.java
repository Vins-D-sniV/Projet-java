package payment;

public interface PaymentStrategy {
    boolean pay(PaymentTransaction transaction);
    String getMethodName();
    boolean supports(String currency);
    double getTransactionFee(double amount);

}
