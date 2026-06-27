package model;

import interfaces.Calculatable;
import interfaces.Displayable;
import java.util.ArrayList;

public class Order implements Displayable, Calculatable {

    private final String orderId;
    private Customer customer;
    private Staff staff;
    private ArrayList<OrderItem> items;
    private String orderDate;
    private boolean confirmed;
    private static int orderCount = 0;

    public Order(String orderId, Customer customer, Staff staff, String orderDate) {
        this.orderId   = cleanText(orderId,   "UNKNOWN_ORDER");
        this.customer  = customer;
        this.staff     = staff;
        this.orderDate = cleanText(orderDate, "No Date");
        this.items     = new ArrayList<>();
        this.confirmed = false;
        orderCount++;
    }

    private String cleanText(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) return defaultValue;
        return value.trim();
    }

    public String getOrderId()   { return orderId; }
    public Customer getCustomer(){ return customer; }
    public Staff getStaff()      { return staff; }
    public String getOrderDate() { return orderDate; }
    public boolean isConfirmed() { return confirmed; }

    // Core addItem — does the actual work
    public boolean addItem(OrderItem item) {
        if (confirmed) throw new IllegalStateException("Cannot add item — order " + orderId + " is already confirmed.");
        if (item == null || item.getPlushie() == null) throw new IllegalArgumentException("Cannot add invalid order item.");
        items.add(item);
        return true;
    }

    // Convenience overload — caller specifies plushie and quantity
    public boolean addItem(Plushie plushie, int quantity) {
        if (plushie == null) throw new IllegalArgumentException("Cannot add a null plushie.");
        if (quantity <= 0)   throw new IllegalArgumentException("Quantity must be greater than 0.");
        return addItem(new OrderItem(plushie, quantity));
    }

    // Lambda: stream replaces manual accumulator loop
    @Override
    public double calculate() {
        return items.stream()
                .mapToDouble(OrderItem::calculate)
                .sum();
    }

    // Lambda: stream replaces manual loop with allMatch check
    private boolean hasEnoughStockForAllItems() {
        return items.stream().allMatch(item ->
            item.getPlushie().hasEnoughStockForAllItems(item.getQuantity())
        );
    }

    public boolean confirm() {
        if (confirmed)        throw new IllegalStateException("Order " + orderId + " is already confirmed.");
        if (customer == null) throw new IllegalStateException("Order cannot be confirmed without a customer.");
        if (staff == null)    throw new IllegalStateException("Order cannot be confirmed without staff.");
        if (items.isEmpty())  throw new IllegalStateException("Order cannot be confirmed without items.");
        if (!hasEnoughStockForAllItems()) { System.out.println("Order " + orderId + " failed because stock is not enough."); return false; }
        for (OrderItem item : items) item.reduceStock();
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
        if (customer != null) System.out.println("Customer: " + customer.getName());
        if (staff != null)    System.out.println("Processed by: " + staff.getName() + " (" + staff.getRole() + ")");
        System.out.println("Items:");
        if (items.isEmpty()) System.out.println("No items in this order.");
        else for (OrderItem item : items) item.displayInfo();
        System.out.println("Total: $" + calculate());
        System.out.println("==================================");
    }

    public static int getOrderCount() { return orderCount; }
}