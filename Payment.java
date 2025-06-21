
public class Payment {

    //Attributes
    private double totalAmount;
    public String paymentStatus;
    private String cardNumber;
    private Student student; // Association
    private Delivery delivery; // Association

    // Default constructor
    public Payment() {
        this.totalAmount = 0.0;
        this.paymentStatus = "Pending";
    }

    //Parameterized constructor
    public Payment(double amount) {
        this.totalAmount = amount;
        this.paymentStatus = "Pending";
    }

    //Parameterized constructor
    public Payment(double amount, Student student, Delivery delivery) {
        this.totalAmount = amount;
        this.paymentStatus = "Pending";
        this.cardNumber = "";
        this.student = student;
        this.delivery = delivery;
    }

    // Accessors (Getter methods)
    public double getTotalAmount() { return totalAmount; }
    public String getCardNumber() { return cardNumber; }
    public String getPaymentStatus(){return paymentStatus; }
    public Student getStudent() { return student; }
    public Delivery getDelivery() { return delivery; }

    // Mutators (Setter methods)
    public void setTotalAmount(double amount) { this.totalAmount = amount; }
    public void setPaymentStatus(String status) { this.paymentStatus = status; }
    public void setCardNumber(String card) { this.cardNumber = card; }
    public void setStudent(Student student) { this.student = student; }
    public void setDelivery(Delivery delivery) { this.delivery = delivery; }

    // Calculate final amount to be paid if discount is applicable
    public double calculateFinalAmount(double amount) {
        double discount = 0.0;

        if (student != null) {
            int meritPoints = student.getMeritPoints();
            if (meritPoints >= 10 && meritPoints <= 30) {
                discount = amount * 0.1;
                System.out.println("------------------------------------------");
                System.out.println("------------YOU HAVE DISCOUNT-------------");
                System.out.printf("Subtotal Amount: RM %.2f%n", amount);
                System.out.printf("Discount Applied (10%%): RM %.2f%n", discount);
            } else if (meritPoints > 30) {
                discount = amount * 0.2;
                System.out.println("-------------------------------------------");
                System.out.println("----------YOU HAVE DISCOUNT----------------");
                System.out.printf("Subtotal Amount: RM %.2f%n", amount);
                System.out.printf("Discount Applied (20%%): RM %.2f%n", discount);
            }
        }

        //deduct discount from final amount
        double finalAmount = amount - discount;

        if (discount > 0.0) {
            System.out.printf("Final Amount after discount: RM %.2f%n", finalAmount);
        } else {
            System.out.println("-------------------------------------------");
            System.out.println("------- Not applicable for discount. ------");
        }

        if (delivery != null) {
            System.out.println("Delivery Fee: RM " + delivery.getDeliveryFee());
            finalAmount += delivery.getDeliveryFee();
        } else {
            System.out.println("No delivery fee added. Proceed to pickup.");
        }

        totalAmount = finalAmount;
        System.out.println("Final Total Amount: RM " + finalAmount);
        return finalAmount;
    }
    
    //Display all payment details
    public void displayPaymentDetails() {
        System.out.println("\n--- Payment Details ---");
        System.out.println("Total Amount: RM " + totalAmount);
        System.out.println("Payment Status: " + paymentStatus);
        System.out.println();
    }
}
