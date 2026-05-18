package model;
import interfaces.Calculatable;
import interfaces.Displayable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Order implements Displayable, Calculatable {

    private final String orderId;
    private Customer customer;
    private Staff staff;
    private ArrayList<OrderItem> items;
    private String orderDate;
    private boolean confirmed;
    private static int orderCount = 0;

    public Order(String orderId, Customer customer, Staff staff, String orderDate) {
        this.orderId = cleanText(orderId, "UNKNOWN_ORDER");
        this.customer = customer;
        this.staff = staff;
        this.orderDate = cleanText(orderDate, "No Date");
        this.items = new ArrayList<>();
        this.confirmed = false;
        orderCount++;
    }

    private String cleanText(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value.trim();
    }

    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Staff getStaff() { return staff; }
    public String getOrderDate() { return orderDate; }
    public boolean isConfirmed() { return confirmed; }

    public ArrayList<OrderItem> getItemsCopy() {
        return new ArrayList<>(items);
    }

    public boolean addItem(OrderItem item) {
        if (confirmed) {
            System.out.println("Cannot add item. Order is already confirmed.");
            return false;
        }
        if (item == null || item.getPlushie() == null) {
            System.out.println("Cannot add invalid order item.");
            return false;
        }
        items.add(item);
        return true;
    }

    @Override
    public double calculate() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.calculate();
        }
        return total;
    }

    private boolean hasEnoughStockForAllItems() {
        Map<Plushie, Integer> requiredStock = new HashMap<>();
        for (OrderItem item : items) {
            Plushie plushie = item.getPlushie();
            int currentRequired = requiredStock.getOrDefault(plushie, 0);
            requiredStock.put(plushie, currentRequired + item.getQuantity());
        }
        for (Map.Entry<Plushie, Integer> entry : requiredStock.entrySet()) {
            Plushie plushie = entry.getKey();
            int totalRequired = entry.getValue();
            if (!plushie.hasEnoughStockForAllItems(totalRequired)) {
                System.out.println("Not enough stock for " + plushie.getName());
                return false;
            }
        }
        return true;
    }

    public boolean confirm() {
        if (confirmed) {
            System.out.println("Order " + orderId + " is already confirmed.");
            return false;
        }
        if (customer == null) {
            System.out.println("Order cannot be confirmed without a customer.");
            return false;
        }
        if (staff == null) {
            System.out.println("Order cannot be confirmed without staff.");
            return false;
        }
        if (items.isEmpty()) {
            System.out.println("Order cannot be confirmed without items.");
            return false;
        }
        if (!hasEnoughStockForAllItems()) {
            System.out.println("Order " + orderId + " failed because stock is not enough.");
            return false;
        }
        for (OrderItem item : items) {
            item.reduceStock();
        }
        confirmed = true;
        customer.addOrder(this);
        return true;
    }

    @Override
    public void displayInfo() {
        System.out.println("\n========== Order Detail ==========");
        System.out.println("Order ID: " + orderId);
        System.out.println("Date: " + orderDate);
        System.out.println("Status: " + (confirmed ? "CONFIRMED" : "PENDING"));
        if (customer != null) {
            System.out.println("Customer: " + customer.getName());
        }
        if (staff != null) {
            System.out.println("Processed by: " + staff.getName() + " (" + staff.getRole() + ")");
        }
        System.out.println("Items:");
        if (items.isEmpty()) {
            System.out.println("No items in this order.");
        } else {
            for (OrderItem item : items) {
                item.displayInfo();
            }
        }
        System.out.println("Total: $" + calculate());
        System.out.println("==================================");
    }

    public static int getOrderCount() {
        return orderCount;
    }
}