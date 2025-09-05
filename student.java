// Student.java
import java.util.ArrayList;

public class Student extends User {
    private double balance;
    private ArrayList<Order> orders = new ArrayList<>();

    public Student(String id, String name, String password, double balance) {
        super(id, name, password, "student");
        this.balance = balance;
    }

    public double getBalance() { return balance; }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public boolean placeOrder(String itemName, double cost) {
        if (balance >= cost) {
            balance -= cost;
            Order order = new Order(itemName, cost);
            orders.add(order);
            return true;
        }
        return false;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}