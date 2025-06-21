package delivery;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Delivery {
    private int deliveryID;
    private double deliveryFee;
    private String deliveryAddress;
    private String deliveryStatus;
    private int estimatedTime;
    private ArrayList<String> locations;
    private ArrayList<Integer> distances;

    public static ArrayList<String> getDefaultLocations() {
        return new ArrayList<>(List.of(
            "Faculty of Computing",
            "Faculty of Electrical",
            "KLG Residence",
            "Library",
            "UTMSPACE"
        ));
    }

    public static ArrayList<Integer> getDefaultDistances() {
        return new ArrayList<>(List.of(1, 2, 10, 2, 9));
    }

    public Delivery() {
        this.deliveryStatus = "Pending";
        this.estimatedTime = 0;
        this.locations = new ArrayList<>();
        this.distances = new ArrayList<>();
    }

    public int getDeliveryID() { return deliveryID; }
    public double getDeliveryFee() { return deliveryFee; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public String getDeliveryStatus() { return deliveryStatus; }
    public int getEstimatedTime() { return estimatedTime; }

    public void setDeliveryID(int id) { this.deliveryID = id; }
    public void setDeliveryFee(double fee) { this.deliveryFee = fee; }
    public void setDeliveryAddress(String address) { this.deliveryAddress = address; }

    // ✅ Fixed Typo: Method renamed from setDliveryStatus to setDeliveryStatus
    public void setDeliveryStatus(String status) { this.deliveryStatus = status; }

    public void setEstimatedTime(int time) { this.estimatedTime = time; }

    // ✅ FIXED: Now uses scanner passed from Main (no resource leak)
    public boolean selectDeliveryAddress(Scanner scanner) {
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
            deliveryFee = 1.0 + (distances.get(choice - 1) * 0.3);
            estimatedTime = distances.get(choice - 1);
            return true;
        } else if (choice == locations.size() + 1) {
            System.out.print("Enter your custom delivery address: ");
            deliveryAddress = scanner.nextLine();
            deliveryFee = 5.0;
            estimatedTime = 30;
            return true;
        } else {
            System.out.println("Invalid choice. Please try again.");
            return false;
        }
    }

    public void setDeliveryDetails(String deliveryStatus, int time) {
        this.deliveryStatus = deliveryStatus;
        this.estimatedTime = time;
    }

    public void displayDeliveryInfo() {
        System.out.println("\n--- Delivery Details ---");
        System.out.println("Address: " + deliveryAddress);
        System.out.println("Estimated Time: " + estimatedTime + " minutes");
        System.out.println("Delivery Fee: RM " + deliveryFee);
        System.out.println("Status: " + deliveryStatus);
    }
}
