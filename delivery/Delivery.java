package delivery;

//import statements
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Delivery {
    // Attributes
    private double deliveryFee;
    private String deliveryAddress;
    private String deliveryStatus;
    private int estimatedTime;
    private ArrayList<String> locations;
    private ArrayList<Integer> distances;

    //static method to get arraylist of locations
    public static ArrayList<String> getDefaultLocations() {
        return new ArrayList<>(List.of(
            "Faculty of Computing",
            "Faculty of Electrical",
            "KLG Residence",
            "Library",
            "UTMSPACE"
        ));
    }

    //static method to get arraylist of distances
    public static ArrayList<Integer> getDefaultDistances() {
        return new ArrayList<>(List.of(1, 2, 10, 2, 9));
    }

    //Constructor
    public Delivery() {
        this.deliveryStatus = "Pending";
        this.estimatedTime = 0;
        this.locations = new ArrayList<>();
        this.distances = new ArrayList<>();
    }

    //Accessors (getter methods)
    public double getDeliveryFee() { return deliveryFee; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public String getDeliveryStatus() { return deliveryStatus; }
    public int getEstimatedTime() { return estimatedTime; }

    //Mutators (setter methods)
    public void setDeliveryFee(double fee) { this.deliveryFee = fee; }
    public void setDeliveryAddress(String address) { this.deliveryAddress = address; }
    public void setDeliveryStatus(String status) { this.deliveryStatus = status; }
    public void setEstimatedTime(int time) { this.estimatedTime = time; }

    // Select delivery address from the menu
    public boolean selectDeliveryAddress(Scanner scanner) { // uses scanner passed from Main 
        if (locations.isEmpty() || distances.isEmpty()) {
            locations = getDefaultLocations();
            distances = getDefaultDistances();
        }

        System.out.println("\n--- Select Delivery Location ---");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i));
        }
        System.out.println((locations.size() + 1) + ". Enter a custom location");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice > 0 && choice <= locations.size()) {
            deliveryAddress = locations.get(choice - 1);
            deliveryFee = 1.0 + (distances.get(choice - 1) * 0.3); // base 1.0 rm delivery fee + 0.3 rm per 1 km distance
            estimatedTime = distances.get(choice - 1);
            return true;
        } else if (choice == locations.size() + 1) {
            System.out.print("Enter your custom delivery address: ");
            deliveryAddress = scanner.nextLine();
            deliveryFee = 5.0; // constant 5.0 delivery fee if custom address
            estimatedTime = 30;
            return true;
        } else {
            System.out.println("Invalid choice. Please try again.");
            return false;
        }
    }

    // set delivery status and estimated time
    public void setDeliveryDetails(String deliveryStatus, int time) {
        this.deliveryStatus = deliveryStatus;
        this.estimatedTime = time;
    }

    // display full delivery info
    public void displayDeliveryInfo() {
        System.out.println("\n--- Delivery Details ---");
        System.out.println("Address: " + deliveryAddress);
        System.out.println("Estimated Time: " + estimatedTime + " minutes");
        System.out.println("Delivery Fee: RM " + deliveryFee);
        System.out.println("Status: " + deliveryStatus);
    }
}
