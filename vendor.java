// Vendor.java
public class Vendor extends User {

    public Vendor(String id, String name, String password) {
        super(id, name, password, "vendor");
    }

    // mark an order as served
    public void serveOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + " served.");
    }
}
