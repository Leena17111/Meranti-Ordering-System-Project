package vendor;

// constant vendor's cuisine types
public enum CuisineType {
    MALAYSIAN("Local Malaysian food"),
    BEVERAGE("Drinks and refreshments"),
    INTERNATIONAL("Foreign cuisine");

    //Attribute
    private final String description;

    // Constructor
    CuisineType(String description) {
        this.description = description;
    }

    // Accessor (Getter method)
    public String getDescription() {
        return description;
    }
}
