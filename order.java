// Order.java
import java.time.LocalDateTime;

public class Order {
    private static int counter = 1;
    private String orderId;
    private String itemName;
    private double cost;
    private LocalDateTime orderTime;

    public Order(String itemName, double cost) {
        this.orderId = "ORD" + (counter++);
        this.itemName = itemName;
        this.cost = cost;
        this.orderTime = LocalDateTime.now();
    }

    public String getOrderId() { return orderId; }
    public String getItemName() { return itemName; }
    public double getCost() { return cost; }
    public LocalDateTime getOrderTime() { return orderTime; }

    @Override
    public String toString() {
        return orderId + " | " + itemName + " | ₹" + cost + " | " + orderTime;
    }
}
