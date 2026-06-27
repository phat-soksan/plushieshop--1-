package model;

import interfaces.Displayable;
import interfaces.OrderSearchable;
import interfaces.PlushieFilter;
import interfaces.Staffaction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ShopSystem implements Displayable, OrderSearchable {

    private String shopName;
    private ArrayList<Plushie> plushies;
    private ArrayList<Customer> customers;
    private ArrayList<Staff> staffMembers;
    private ArrayList<Order> orderHistory;
    private Map<String, Order> orderMap;

    public ShopSystem(String shopName) {
        this.shopName     = (shopName == null || shopName.trim().isEmpty()) ? "Plushie Shop" : shopName.trim();
        this.plushies     = new ArrayList<>();
        this.customers    = new ArrayList<>();
        this.staffMembers = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        this.orderMap     = new HashMap<>();
    }

    public boolean addPlushie(Plushie plushie)   { if (plushie == null) return false; plushies.add(plushie); return true; }
    public boolean addCustomer(Customer customer) { if (customer == null) return false; customers.add(customer); return true; }
    public boolean addStaff(Staff staff)          { if (staff == null) return false; staffMembers.add(staff); return true; }

    public boolean placeOrder(Order order) {
        if (order == null) { System.out.println("Cannot place a null order."); return false; }
        if (orderMap.containsKey(order.getOrderId())) { System.out.println("Order ID already exists: " + order.getOrderId()); return false; }
        Staff staff = order.getStaff();
        if (staff != null) staff.processOrder(order);
        boolean confirmed = order.confirm();
        if (confirmed) {
            orderHistory.add(order);
            orderMap.put(order.getOrderId(), order);
            System.out.println("Order " + order.getOrderId() + " placed successfully.");
            return true;
        }
        System.out.println("Order " + order.getOrderId() + " was not placed.");
        return false;
    }

    @Override
    public Order findOrderById(String orderId) {
        if (orderId == null) return null;
        return orderMap.get(orderId);
    }

    public Plushie searchPlushieById(String plushieId) {
        for (Plushie p : plushies) if (p.getPlushieId().equals(plushieId)) return p;
        return null;
    }

    public Customer searchCustomerById(String customerId) {
        for (Customer c : customers) if (c.getCustomerId().equals(customerId)) return c;
        return null;
    }

    public Staff searchStaffById(String staffId) {
        for (Staff s : staffMembers) if (s.getStaffId().equals(staffId)) return s;
        return null;
    }

    // PlushieFilter functional interface — replaces the two overloaded searchPlushie() methods
    public ArrayList<Plushie> searchPlushie(PlushieFilter filter) {
        ArrayList<Plushie> results = new ArrayList<>(plushies);
        results.removeIf(p -> !filter.matches(p));
        return results;
    }

    public ArrayList<Plushie> getPlushiesCopy()   { return new ArrayList<>(plushies); }
    public ArrayList<Customer> getCustomersCopy() { return new ArrayList<>(customers); }
    public ArrayList<Staff> getStaffMembersCopy() { return new ArrayList<>(staffMembers); }
    public ArrayList<Order> getOrderHistoryCopy() { return new ArrayList<>(orderHistory); }

    // Lambda with parameters: sort by price before displaying
    public void displayAllPlushies() {
        plushies.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        System.out.println("\nPlushies in " + shopName + ":");
        for (Plushie p : plushies) p.displayInfo();
    }

    // Anonymous inner class: sort orders by date before displaying
    public void displayAllOrders() {
        orderHistory.sort(new Comparator<Order>() {
            @Override
            public int compare(Order a, Order b) {
                return a.getOrderDate().compareTo(b.getOrderDate());
            }
        });
        System.out.println("\nOrder history in " + shopName + ":");
        if (orderHistory.isEmpty()) { System.out.println("No confirmed orders yet."); return; }
        for (Order o : orderHistory) o.displayInfo();
    }

    // StaffAction functional interface — apply any action to all staff
    public void applyToAllStaff(Staffaction action) {
        for (Staff s : staffMembers) action.perform(s);
    }

    @Override
    public void displayInfo() {
        System.out.println("\n========== Shop Summary ==========");
        System.out.println("Shop Name: " + shopName);
        System.out.println("Plushies: " + plushies.size());
        System.out.println("Customers: " + customers.size());
        System.out.println("Staff: " + staffMembers.size());
        System.out.println("Confirmed Orders: " + orderHistory.size());
        System.out.println("==================================");
    }
}