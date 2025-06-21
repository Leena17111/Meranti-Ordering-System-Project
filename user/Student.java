package user;
public class Student extends User {
    //Attributes
    private int meritPoints;

    //Constructor
    public Student(String id, String name, String email, String phone, String pwd, int points) {
        super(id, name, email, phone, pwd);
        this.meritPoints = points;
    }

    //Accessors(getter methods)
    public int getMeritPoints() { return meritPoints; }
    public void setMeritPoints(int points) { this.meritPoints = points; }

    //Override method to display staff-specific user details
    @Override
    public void displayUserDetails() {
        System.out.println("User ID: " + getUserID());
        System.out.println("Name: " + getUserName());
        System.out.println("Email: " + getUserEmail());
        System.out.println("Phone: " + getUserNumber());
        System.out.println("Merit Points: " + meritPoints);
    }
}
