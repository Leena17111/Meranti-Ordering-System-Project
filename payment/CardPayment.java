package payment;

//  import statements
import java.util.Scanner;

public class CardPayment extends Payment implements Payable {
    // Attributes
    private Scanner scanner; //to store passed scanner for reuse
    private String cardNumber;

    // Constructor
    public CardPayment(Payment payment, Scanner scanner) {
        super(payment.getTotalAmount(), payment.getStudent(), payment.getDelivery());
        this.scanner = scanner; // store passed-in Scanner for reuse
    }

    // Overriding processPayment method from Payable interface
    @Override
    public void processPayment() {
        System.out.print("Enter your card number: ");
        cardNumber = scanner.nextLine();

        // setter to update private parent attribute
        setPaymentStatus("Successful");

        // Mask full card number for privacy, display last 4 digits only
        String last4 = cardNumber.length() >= 4 ? cardNumber.substring(cardNumber.length() - 4) : "XXXX";
        System.out.println("Card payment successful. Ending in **** **** **** " + last4);
    }
}