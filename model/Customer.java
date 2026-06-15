package model;
import java.util.ArrayList;

public class Customer extends Person {

    private ArrayList<Order> orders;
    private static int customerCount = 0;

    public Customer(String customerId, String name, String phone) {
        super(customerId, name, phone);
        this.orders = new ArrayList<>();
        customerCount++;
    }

    public String getCustomerId() { return id; }

    public void addOrder(Order order) {
        if (order != null && !orders.contains(order)) orders.add(order);
    }

    public ArrayList<Order> getOrdersCopy() { return new ArrayList<>(orders); }
    public int getOrderHistorySize()         { return orders.size(); }

    public void displayOrderHistory() {
        System.out.println("\nOrder History for " + name + ":");
        if (orders.isEmpty()) { System.out.println("No orders yet."); return; }
        for (Order order : orders) order.displayInfo();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();                                   // prints ID, Name, Phone from Person
        System.out.println("Total Orders: " + orders.size()); // adds Customer-specific info
    }

    public static int getCustomerCount() { return customerCount; }
}
