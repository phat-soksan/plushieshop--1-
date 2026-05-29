package main;
import java.util.ArrayList;
import model.*;

public class Main {

    public static void main(String[] args) {

        ShopSystem shop = new ShopSystem("Happy Plushie Shop");

        Plushie teddy   = new Plushie("P001", "Teddy Bear", 25.0, 10);
        Plushie bunny   = new Plushie("P002", "Bunny",      18.0,  8);
        Plushie unicorn = new Plushie("P003", "Unicorn",    30.0,  5);
        shop.addPlushie(teddy);
        shop.addPlushie(bunny);
        shop.addPlushie(unicorn);

        Customer customer1 = new Customer("C001", "Dara",  "012345678");
        Customer customer2 = new Customer("C002", "Sokha", "098765432");
        shop.addCustomer(customer1);
        shop.addCustomer(customer2);

        Staff staff1 = new Staff("S001", "Vanna", "011111111", "Cashier");
        Staff staff2 = new Staff("S002", "Malis", "022222222", "Manager");
        shop.addStaff(staff1);
        shop.addStaff(staff2);

        // TEST 1: OVERRIDING - displayInfo()
        System.out.println("TEST 1: OVERRIDING - displayInfo()");
        customer1.displayInfo();
        System.out.println();
        staff1.displayInfo();
        System.out.println();

        // TEST 2: OVERLOADING - Order.addItem()
        System.out.println("\nTEST 2: OVERLOADING - Order.addItem()");
        Order order1 = new Order("O001", customer1, staff1, "2026-05-18");
        order1.addItem(new OrderItem(teddy, 2));
        order1.addItem(bunny);
        order1.addItem(unicorn, 3);
        shop.placeOrder(order1);

        System.out.println("\nAfter order:");
        order1.displayInfo();
        shop.displayAllPlushies();

        // TEST 3: OVERLOADING - Staff.processOrder()
        System.out.println("\nTEST 3: OVERLOADING - Staff.processOrder()");
        Order order2 = new Order("O002", customer2, staff2, "2026-05-19");
        order2.addItem(bunny, 2);

        staff2.processOrder(order2);
        staff2.processOrder(order2, "Gift wrap requested");
        staff1.processOrder("O002");

        // TEST 4: OVERLOADING - Plushie.displayInfo()
        System.out.println("\nTEST 4: OVERLOADING - Plushie.displayInfo()");
        teddy.displayInfo();
        teddy.displayInfo(true);
        teddy.displayInfo(false);
        bunny.displayInfo(true, true);
        bunny.displayInfo(false, false);

        // TEST 5: OVERLOADING - ShopSystem.searchPlushie()
        System.out.println("\nTEST 5: OVERLOADING - ShopSystem.searchPlushie()");
        Plushie found = shop.searchPlushie("P002");
        if (found != null) found.displayInfo();

        ArrayList<Plushie> results1 = shop.searchPlushie("bear", 30.0);
        if (results1.isEmpty()) System.out.println("No results.");
        for (Plushie plushie : results1) plushie.displayInfo();

        ArrayList<Plushie> results2 = shop.searchPlushie("", 15.0, 25.0);
        if (results2.isEmpty()) System.out.println("No results.");
        for (Plushie plushie : results2) plushie.displayInfo();

        // Final summary
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