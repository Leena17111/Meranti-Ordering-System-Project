public class Payment {
    private double totalAmount;
    private String paymentStatus;
    private String cardNumber; // ⚠️ Note: In real apps, avoid storing raw card numbers unencrypted
    private Student student;
    private Delivery delivery;

    public Payment() {
        this.totalAmount = 0.0;
        this.paymentStatus = "Pending";
    }

    public Payment(double amount) {
        this.totalAmount = amount;
        this.paymentStatus = "Pending";
    }

    public Payment(double amount, Student student, Delivery delivery) {
        this.totalAmount = amount;
        this.paymentStatus = "Pending";
        this.student = student;
        this.delivery = delivery;
        this.cardNumber = ""; // Optional privacy: don't persist full number
    }

    public double getTotalAmount() { return totalAmount; }
    public String getCardNumber() { return cardNumber; }
    public String getPaymentStatus() { return paymentStatus; }
    public Student getStudent() { return student; }
    public Delivery getDelivery() { return delivery; }

    public void setTotalAmount(double amount) { this.totalAmount = amount; }
    public void setPaymentStatus(String status) { this.paymentStatus = status; }
    public void setCardNumber(String card) { this.cardNumber = card; }
    public void setStudent(Student student) { this.student = student; }
    public void setDelivery(Delivery delivery) { this.delivery = delivery; }

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

    public void displayPaymentDetails() {
        System.out.println("\n--- Payment Details ---");
        System.out.println("Total Amount   : RM " + totalAmount);
        System.out.println("Payment Status : " + paymentStatus);
        System.out.println();
    }
}