package user;
public abstract class User {
    //Attributes
    private String userID;
    private String userName;
    private String userEmail;
    private String userNumber;
    private String password;

    //Constructor
    public User(String id, String name, String email, String phone, String pwd) {
        this.userID = id;
        this.userName = name;
        this.userEmail = email;
        this.userNumber = phone;
        this.password = pwd;
    }

    //Accessors (getter methods)
    public String getUserID() { return userID; }
    public String getUserName() { return userName; }
    public String getUserEmail() { return userEmail; }
    public String getUserNumber() { return userNumber; }
    public String getPassword() { return password; }

    //Mutators (setter methods)
    public void setPassword(String pwd) { this.password = pwd; }

    //Abstract method to display user details
    public abstract void displayUserDetails();
}
