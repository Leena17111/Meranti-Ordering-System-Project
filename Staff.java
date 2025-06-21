public class Staff extends User {
    //Attributes
    private String staffPosition;

    //Constructor
    public Staff(String id, String name, String email, String phone, String pwd, String position) {
        super(id, name, email, phone, pwd);
        this.staffPosition = position;
    }

    //Accessors(getter methods)
    public String getStaffPosition() { return staffPosition; }

    //Override method to display staff-specific user details
    @Override
    public void displayUserDetails() {
        System.out.println("User ID: " + getUserID());
        System.out.println("Name: " + getUserName());
        System.out.println("Email: " + getUserEmail());
        System.out.println("Phone: " + getUserNumber());
        System.out.println("Staff Position: " + getStaffPosition());
    }
}
