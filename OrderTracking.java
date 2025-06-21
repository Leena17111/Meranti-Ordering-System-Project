public class OrderTracking {
    //Attributes
    private String orderID;
    private Delivery delivery;

    // Constructor
    public OrderTracking(String orderID, Delivery delivery) {
        this.orderID = orderID;
        this.delivery=delivery;
    }

    // Show tracking info
   public void displayTrackingInfo() {
    System.out.println("\n--- Order Tracking ---");
    System.out.println("Order ID: " + orderID);

    if (delivery != null) {
        System.out.println("User Location: " + delivery.getDeliveryAddress());
        System.out.println("Time to Prepare Order: 10 minutes");
        int deliveryTime = delivery.getEstimatedTime();
        System.out.println("Estimated Delivery Time: " + deliveryTime + " minutes");
        System.out.println("Delivery Status: " + delivery.getDeliveryStatus());
    } else {
        System.out.println("Pickup Order");
        System.out.println("Time to Prepare Order: 10 minutes");
        System.out.println("Pickup: Ready after 10 minutes");
    }
}
}
