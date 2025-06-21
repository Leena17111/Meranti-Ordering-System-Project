package payment;


public class CashPayment extends Payment implements Payable {
    // Constructor
    public CashPayment(Payment payment) {
        super(payment.getTotalAmount(), payment.getStudent(), payment.getDelivery());
    }

    // Overriding processPayment method from Payable class
    @Override
    public void processPayment() {
        // ✅ Fix: Use setter instead of direct access
        setPaymentStatus("Pending");
        System.out.println("Cash payment is pending. Please pay at counter or delivery.");
    }
}
