import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Main {
    public static void main(String[] args) {
        try {
            // Gather user info using JOptionPane first
            String userName = JOptionPane.showInputDialog("Enter your name:");
            JOptionPane.showMessageDialog(null, "Welcome " + userName + ", let's get you registered first.");
            String userEmail = JOptionPane.showInputDialog("Enter your email:");
            String userPhone = JOptionPane.showInputDialog("Enter your phone number:");

            // Masked password input
            JPasswordField pwdField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(null, pwdField, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
            String password = "";
            if (option == JOptionPane.OK_OPTION) {
                password = new String(pwdField.getPassword());
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("\n--- Welcome to Meranti Online Ordering System ---");
            System.out.println("Please select your user type:");
            System.out.println("1. Student\n2. Staff\n3. Visitor");
            int userType = sc.nextInt();
            sc.nextLine(); // Clear buffer

            // Generate random ID and prepare user
            User currentUser = null;
            String userID = "U" + ThreadLocalRandom.current().nextInt(100, 1000);

            // Based on user type, create corresponding object (polymorphism concept)
            if (userType == 1) {
                try {
                    String meritInput = JOptionPane.showInputDialog("Enter your merit points:");
                    int meritPoints = Integer.parseInt(meritInput);
                    currentUser = new Student(userID, userName, userEmail, userPhone, password, meritPoints);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid merit points. Set to 0.");
                    currentUser = new Student(userID, userName, userEmail, userPhone, password, 0);
                }
            } else if (userType == 2) {
                String position = JOptionPane.showInputDialog("Enter your staff position:");
                currentUser = new Staff(userID, userName, userEmail, userPhone, password, position);
            } else {
                System.out.println("Proceeding as a visitor.");
            }

            // Display user info using overridden method
            if (currentUser != null) {
                System.out.println("\n--- User Details ---");
                currentUser.displayUserDetails();
            }

            // Array of menu
            Menu richiMenu = new Menu(new String[]{"Americano", "Espresso", "Cappuccino"}, new double[]{5.99, 8.99, 6.99});
            Menu tealiveMenu = new Menu(new String[]{"Americano", "Espresso", "Cappuccino"}, new double[]{12.99, 9.99, 11.99});
            Menu zusMenu = new Menu(new String[]{"Americano", "Espresso", "Cappuccino"}, new double[]{16.99, 12.99, 10.99});
            Menu lemonTreeMenu = new Menu(new String[]{"Burger", "Pizza Primavera", "Pasta"}, new double[]{10.99, 10.99, 11.99});
            Menu ezzMenu = new Menu(new String[]{"Nasi Ayam Goreng", "Nasi Lemak Goreng", "Chicken Chop", "Chicken Wrap"}, new double[]{10.99, 10.99, 11.99, 10.50});

            //Array of vendor objects
            Vendor[] vendors = {
                new Vendor(1, "RICHIAMO COFFEE", "Floor 0 section C", CuisineType.BEVERAGE, richiMenu),
                new Vendor(2, "Tealive", "Floor 0 section A", CuisineType.BEVERAGE, tealiveMenu),
                new Vendor(3, "ZUS COFFEE", "Floor 0 section B", CuisineType.BEVERAGE, zusMenu),
                new Vendor(4, "LEMON TREE", "Floor 0 section A", CuisineType.INTERNATIONAL, lemonTreeMenu),
                new Vendor(5, "EZZ ONE", "Floor 0 section E", CuisineType.MALAYSIAN, ezzMenu)
            };

            //Display vendor list
            System.out.print("\nAvailable Vendors:");
            for (int i = 0; i < vendors.length; i++) {
                System.out.print((i + 1) + ". ");
                vendors[i].displayVendorDetails();
                System.out.println("");
            }

            //Allow user to select a vendor
            Vendor selectedVendor = null;
            try {
                System.out.print("Select a vendor by number: ");
                int vendorChoice = sc.nextInt();
                sc.nextLine();
                if (vendorChoice < 1 || vendorChoice > vendors.length) {
                    throw new IllegalArgumentException("Invalid vendor selection.");
                }
                selectedVendor = vendors[vendorChoice - 1];
            } catch (Exception e) {
                System.out.println("Invalid vendor input. Exiting.");
                return;
            }

            //Display vendor's menu
            selectedVendor.displayVendorMenu();

            //Create a new order
            Order order = new Order();
            order.setVendor(selectedVendor);
            order.setMenu(selectedVendor.getMenu());

            // Set up payment with polymorphism
            if (currentUser instanceof Student) {
                order.setPayment(new Payment(order.getTotalAmount(), (Student) currentUser, null));
            } else {
                order.setPayment(new Payment(order.getTotalAmount()));
            }

            // Add items to the order
            char addMore = 'y';
            while (addMore == 'y' || addMore == 'Y') {
                try {
                    System.out.print("Enter item number: ");
                    int itemNum = sc.nextInt();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    order.addItem(itemNum, qty);
                } catch (Exception e) {
                    System.out.println(" Invalid item input. Try again.");
                    sc.nextLine();
                }
                System.out.print("Add more items? (y/n): ");
                addMore = sc.next().charAt(0);
            }

            // Delivery option
            System.out.print("Do you want delivery? (y/n): ");
            char deliveryOpt = sc.next().charAt(0);
            Delivery delivery = null;
            if (deliveryOpt == 'y' || deliveryOpt == 'Y') {
                delivery = new Delivery();
                if (delivery.selectDeliveryAddress()) {
                    order.setDelivery(delivery);
                }
            }

            // Final payment handling
            Payment payment = order.getPayment();
            if (delivery != null) {
                payment.setDelivery(delivery);
            }

            double finalAmount = payment.calculateFinalAmount(order.getTotalAmount());
            payment.setTotalAmount(finalAmount);

            // Choose payment method (implements Payable interface)
            System.out.println("\n--- Payment Menu ---");
            System.out.println("Choose Payment Method:\n1. Cash\n2. Card");
            int methodChoice = sc.nextInt();

            Payable payable;
            if (methodChoice == 1) {
                payable = new CashPayment(payment);
            } else {
                payable = new CardPayment(payment, sc);
            }

            // Process the payment
            payable.processPayment();

            // Set and show order tracking info
            OrderTracking tracking = new OrderTracking(String.valueOf(order.getOrderID()), delivery);
            order.setOrderTracking(tracking);

            // Display order summary and save to file
            order.displayOrder();
            order.writeOrderToFile("order_" + order.getOrderID() + ".txt");

        } catch (Exception e) {
            System.out.println(" An unexpected error occurred: " + e.getMessage());
        }
    }
}
