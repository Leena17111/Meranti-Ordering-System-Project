package menu;
import vendor.CuisineType;

public class Menu {
    //Attributes
    private String[] items;
    private double[] prices;

    // Constructor with fixed arrays
    public Menu(String[] itemArray, double[] priceArray) {
        this.items = itemArray;
        this.prices = priceArray;
    }

    // Displays full menu details with item names, prices and cuisine type
    public void displayMenu(CuisineType type) {
        System.out.println("\nMenu (" + type + "): " + type.getDescription());
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%d. %s - RM %.2f%n", i + 1, items[i], prices[i]);
        }
    }

    // Returns name of the menu item at the specified index
    public String getItemName(int index) {
        return items[index];
    }

    // Returns price of the menu item at the specified index
    public double getItemPrice(int index) {
        return prices[index];
    }

    // Returns the total number of menu items
    public int getSize() {
        return items.length;
    }
}
