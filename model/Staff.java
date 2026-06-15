package model;

// Week 9: Staff is now an abstract class.
// Every staff member must provide their own version of work().
// No one should create a plain "Staff" object — only Cashier or Manager.
public abstract class Staff extends Person {

    private static int staffCount = 0;

    public Staff(String staffId, String name, String phone) {
        super(staffId, name, phone);
        staffCount++;
    }

    public String getStaffId() { return id; }

    // Normal shared method — all staff process orders the same way
    public void processOrder(Order order) {
        if (order == null) {
            System.out.println(name + " cannot process a null order.");
            return;
        }
        System.out.println(name + " is processing order " + order.getOrderId());
    }

    public void processOrder(Order order, String note) {
        if (order == null) {
            System.out.println(name + " cannot process a null order.");
            return;
        }
        System.out.println(name + " is processing order " + order.getOrderId());
        System.out.println("Note: " + note);
    }

    public void processOrder(String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) {
            System.out.println(name + " cannot process: order ID is missing.");
            return;
        }
        System.out.println(name + " is processing order ID: " + orderId);
    }

    // Abstract method — each staff role has its own way of working
    public abstract void work();

    // Abstract method — each staff role has a different title/role label
    public abstract String getRole();

    @Override
    public void displayInfo() {
        System.out.println("Staff ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Role: " + getRole());
    }

    public static int getStaffCount() { return staffCount; }
}
