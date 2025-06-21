///  import packages
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Order {
    //Attributes
    private int orderID = ThreadLocalRandom.current().nextInt(1000, 10000);
    private Vendor vendor; // Association
    private Menu orderMenu; // Association
    private ArrayList<String> orderItems = new ArrayList<>();
    private ArrayList<Integer> quantities = new ArrayList<>();
    private ArrayList<Double> itemTotals = new ArrayList<>();
    private double totalAmount = 0.0;

    private Payment payment;      // Composition
    private OrderTracking orderTracking; // Composition
    private Delivery delivery;    // Aggregation

    //Accessors (getter methods)
    public int getOrderID() {
        return orderID; }

    public double getTotalAmount() {
        return totalAmount; }

    public Payment getPayment() {
        return payment; }

    public Delivery getDelivery() {
        return delivery; }
    
    //Mutators (setter methods)
    public void setOrderTracking(OrderTracking tracking) {
    this.orderTracking = tracking; }

    public void setMenu(Menu menu) {
        this.orderMenu = menu;
    }

    public void setVendor(Vendor vendor) {
        this.vendor= vendor;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    // function to add item to order
    public void addItem(int itemChoice, int quantity) {
        try {
            if (orderMenu == null)
                throw new NullPointerException("Menu not assigned to order.");

            int menuSize = orderMenu.getSize();

            if (itemChoice < 1 || itemChoice > menuSize)
                throw new IllegalArgumentException("Invalid item selection.");

            int index = itemChoice - 1;
            String itemName = orderMenu.getItemName(index);
            double itemPrice = orderMenu.getItemPrice(index);
            double itemTotal = itemPrice * quantity;

            orderItems.add(itemName);
            quantities.add(quantity);
            itemTotals.add(itemTotal);
            totalAmount += itemTotal;

            System.out.printf("Added: %s x%d (RM %.2f)\n", itemName, quantity, itemTotal);

        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    // display order info
    public void displayOrder() {
        System.out.println("\n========== ORDER SUMMARY ==========");
        System.out.println("Order ID: " + orderID);
        System.out.println("Vendor: " + vendor.getVendorName());
        System.out.println("-----------------------------------");

        if (orderItems.isEmpty()) {
            System.out.println("No items in the order yet.");
        } else {
            for (int i = 0; i < orderItems.size(); i++) {
                System.out.printf("%d. %s x%d - RM %.2f\n",
                        i + 1, orderItems.get(i), quantities.get(i), itemTotals.get(i));
            }
            System.out.println("-----------------------------------");
            System.out.printf("Total Amount: RM %.2f\n", totalAmount);
        }

        if (payment != null) {
            System.out.println("Payment Status: " + payment.getPaymentStatus());
        } else {
            System.out.println("Payment not set.");
        }

        if (delivery != null) {
            delivery.displayDeliveryInfo();
        } else {
            System.out.println("Delivery not set.");
        }

        System.out.println("===================================");
        orderTracking.displayTrackingInfo();
    }

    // write order info to file to be saved
    public void writeOrderToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("========== ORDER SUMMARY ==========");
            writer.println("Order ID: " + orderID);
            writer.println("Vendor: " + vendor.getVendorName());
            writer.println("-----------------------------------");

            if (orderItems.isEmpty()) {
                writer.println("No items in the order.");
            } else {
                for (int i = 0; i < orderItems.size(); i++) {
                    writer.printf("%d. %s x%d - RM %.2f\n",
                            i + 1, orderItems.get(i), quantities.get(i), itemTotals.get(i));
                }
                writer.println("-----------------------------------");
                writer.printf("Total Amount: RM %.2f\n", totalAmount);
            }

            if (payment != null) {
                writer.println("Payment Status: " + payment.getPaymentStatus());
            } else {
                writer.println("Payment not set.");
            }

            if (delivery != null) {
                writer.println("--- Delivery Details ---");
                writer.println("Address: " + delivery.getDeliveryAddress());
                writer.println("Estimated Time: " + delivery.getEstimatedTime() + " minutes");
                writer.println("Delivery Fee: RM " + delivery.getDeliveryFee());
                writer.println("Status: " + delivery.getDeliveryStatus());
            } else {
                writer.println("Delivery not set.");
            }

            writer.println("===================================");
            System.out.println("Order successfully written to file: " + filename);
        } catch (IOException e) {
            System.out.println("Failed to write order to file: " + e.getMessage());
        }
    }

}
