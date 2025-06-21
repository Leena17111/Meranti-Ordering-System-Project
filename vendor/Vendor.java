package vendor;
import menu.*;


public class Vendor {
    private int vendorID;
    private String vendorName;
    private String vendorLocation;
    private CuisineType cuisineType;
    private Menu menu;

    public Vendor(int vendorID, String vendorName, String vendorLocation,
                  CuisineType cuisineType, Menu menu) {
        this.vendorID = vendorID;
        this.vendorName = vendorName;
        this.vendorLocation = vendorLocation;
        this.cuisineType = cuisineType;
        this.menu = menu;
    }

    // ✅ Added getter for vendorID
    public int getVendorID() { return vendorID; }

    public String getVendorName() { return vendorName; }
    public String getVendorLocation() { return vendorLocation; }
    public CuisineType getCuisineType() { return cuisineType; }
    public Menu getMenu() { return menu; }

    public void displayVendorDetails() {
        System.out.println("\n--- Vendor Info ---");
        System.out.println("Vendor ID: " + vendorID);
        System.out.println("Name: " + vendorName);
        System.out.println("Location: " + vendorLocation);
        System.out.println("Cuisine Type: " + cuisineType + cuisineType.getDescription());
    }

    public void displayVendorMenu() {
        menu.displayMenu(cuisineType);
    }
}