package order;

//  import statements
import delivery.Delivery;

public class OrderTracking {
    //Attributes
    private String orderID;
    private Delivery delivery; //Association

    //Constructor
    public OrderTracking(String orderID, Delivery delivery) {
        this.orderID = orderID;
        this.delivery = delivery;
    }

    //Display full tracking info
    public void displayTrackingInfo() {
        System.out.println("\n--- Order Tracking ---");
        System.out.println("Order ID        : " + orderID);

        if (delivery != null) {
            System.out.println("User Location   : " + delivery.getDeliveryAddress());
            System.out.println("Prep Time       : 10 minutes");
            System.out.println("Delivery Time   : " + delivery.getEstimatedTime() + " minutes");
            System.out.println("Delivery Status : " + delivery.getDeliveryStatus());
        } else {
            System.out.println("Pickup Order");
            System.out.println("Prep Time       : 10 minutes");
            System.out.println("Pickup Ready in : 10 minutes");
        }
    }
}