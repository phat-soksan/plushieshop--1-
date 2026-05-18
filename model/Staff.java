package model;

public class Staff extends Person {

    private String role;
    private static int staffCount = 0;

    public Staff(String staffId, String name, String phone, String role) {
        super(staffId, name, phone);
        setRole(role);
        staffCount++;
    }

    public String getStaffId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            this.role = "General Staff";
        } else {
            this.role = role.trim();
        }
    }

    public void processOrder(Order order) {
        if (order == null) {
            System.out.println(name + " cannot process a null order.");
            return;
        }
        System.out.println(name + " is processing order " + order.getOrderId());
    }

    @Override
    public void displayInfo() {
        System.out.println("Staff ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Role: " + role);
    }

    public static int getStaffCount() {
        return staffCount;
    }
}