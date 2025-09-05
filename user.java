// User.java (base class)
public class User {
    private String id;
    private String name;
    private String password;
    private String role;

    public User(String id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }

    // simple password check
    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
}
