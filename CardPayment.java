import java.util.Scanner;

public class CardPayment extends Payment implements Payable {
    //Attributes
    private String cardNumber;

    //Constructor
    public CardPayment(Payment payment, Scanner scanner) {
        super(payment.getTotalAmount(), payment.getStudent(), payment.getDelivery());
        
    }
    //Overriding processPayment method from payable class
    @Override
    public void processPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your card number: ");
        cardNumber = scanner.nextLine();
        paymentStatus = "Successful";
        
        //hide the full card number for privacy, display last 4 digits only
        String last4 = cardNumber.length() >= 4 ? cardNumber.substring(cardNumber.length() - 4) : "XXXX";
        System.out.println("Card payment successful. Ending in **** **** **** " + last4);
        scanner.close();
    }
}
