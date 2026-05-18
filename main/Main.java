package main;
import model.*;
public class Main {

    public static void main(String[] args) {

        ShopSystem shop = new ShopSystem("Happy Plushie Shop");

        Plushie teddy = new Plushie("P001", "Teddy Bear", 25.0, 10);
        Plushie bunny = new Plushie("P002", "Bunny", 18.0, 8);
        Plushie unicorn = new Plushie("P003", "Unicorn", 30.0, 5);
        shop.addPlushie(teddy);
        shop.addPlushie(bunny);
        shop.addPlushie(unicorn);

        Customer customer1 = new Customer("C001", "Dara", "012345678");
        Customer customer2 = new Customer("C002", "Sokha", "098765432");
        shop.addCustomer(customer1);
        shop.addCustomer(customer2);

        Staff staff1 = new Staff("S001", "Vanna", "011111111", "Cashier");
        Staff staff2 = new Staff("S002", "Malis", "022222222", "Manager");
        shop.addStaff(staff1);
        shop.addStaff(staff2);

        System.out.println("Before order:");
        shop.displayAllPlushies();

        Order order1 = new Order("O001", customer1, staff1, "2026-05-18");
        order1.addItem(new OrderItem(teddy, 2));
        order1.addItem(new OrderItem(bunny, 1));
        shop.placeOrder(order1);

        System.out.println("\nAfter order:");
        order1.displayInfo();
        shop.displayAllPlushies();

        Order foundOrder = shop.findOrderById("O001");
        if (foundOrder != null) {
            System.out.println("\nSearch result for O001:");
            foundOrder.displayInfo();
        }

        customer1.displayOrderHistory();
        shop.displayInfo();

        System.out.println("\nStatic counters vs collection size:");
        System.out.println("Plushie.getPlushieCount(): " + Plushie.getPlushieCount());
        System.out.println("shop.getPlushieListSize(): " + shop.getPlushieListSize());
        System.out.println("Customer.getCustomerCount(): " + Customer.getCustomerCount());
        System.out.println("shop.getCustomerListSize(): " + shop.getCustomerListSize());
        System.out.println("Staff.getStaffCount(): " + Staff.getStaffCount());
        System.out.println("shop.getStaffListSize(): " + shop.getStaffListSize());
        System.out.println("Order.getOrderCount(): " + Order.getOrderCount());
        System.out.println("shop.getOrderHistorySize(): " + shop.getOrderHistorySize());
    }
}