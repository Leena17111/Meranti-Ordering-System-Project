import java.util.Scanner;

public class CardPayment extends Payment implements Payable {
    // Attribute to store passed scanner for reuse
    private Scanner scanner;
    private String cardNumber;

    // Constructor now stores scanner
    public CardPayment(Payment payment, Scanner scanner) {
        super(payment.getTotalAmount(), payment.getStudent(), payment.getDelivery());
        this.scanner = scanner; // ✅ Fixed: store passed-in Scanner for reuse
    }

    // Overriding processPayment method from Payable interface
    @Override
    public void processPayment() {
        // ✅ Fixed: use existing scanner, avoid creating new one
        System.out.print("Enter your card number: ");
        cardNumber = scanner.nextLine();

        // ✅ Fixed: Use setter to update private parent attribute
        setPaymentStatus("Successful");

        // Mask full card number for privacy, display last 4 digits only
        String last4 = cardNumber.length() >= 4 ? cardNumber.substring(cardNumber.length() - 4) : "XXXX";
        System.out.println("Card payment successful. Ending in **** **** **** " + last4);
    }
}