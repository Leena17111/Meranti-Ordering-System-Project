public class Vendor {
    private int vendorID;
    private String vendorName;
    private String vendorLocation;
    private CuisineType cuisineType; // type Enum CuisineType
    private Menu menu; //Composition : Vendor has menu

    // Constructor
    public Vendor(int vendorID, String vendorName, String vendorLocation,
                  CuisineType cuisineType, Menu menu) {
        this.vendorName = vendorName;
        this.vendorLocation = vendorLocation;
        this.cuisineType = cuisineType;
        this.menu = menu;
    }

    // Accessors (Getter methods)
    public String getVendorName() { return vendorName; }
    public String getVendorLocation() { return vendorLocation; }
    public CuisineType getCuisineType() { return cuisineType; }
    public Menu getMenu() { return menu; }

    // Display full vendor info
    public void displayVendorDetails() {
        System.out.println("\n--- Vendor Info ---");
        System.out.println("Vendor ID: " + vendorID);
        System.out.println("Name: " + vendorName);
        System.out.println("Location: " + vendorLocation);
        System.out.println("Cuisine Type: " + cuisineType + cuisineType.getDescription());
    }

    //Display vendor's menu
    public void displayVendorMenu() {
        menu.displayMenu(cuisineType);
    }
}
