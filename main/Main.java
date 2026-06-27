package main;
import model.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            ShopSystem shop = new ShopSystem("Happy Plushie Shop");

            // -------------------------------------------------------
            // Products
            // -------------------------------------------------------
            Plushie teddy   = new Plushie("P001", "Teddy Bear", 25.0, 10);
            Plushie bunny   = new Plushie("P002", "Bunny",      18.0,  8);
            Plushie unicorn = new Plushie("P003", "Unicorn",    30.0,  5);
            LimitedEditionPlushie dragon = new LimitedEditionPlushie("P004", "Dragon", 55.0, 3, "2026 Lunar New Year");
            shop.addPlushie(teddy);
            shop.addPlushie(bunny);
            shop.addPlushie(unicorn);

            // -------------------------------------------------------
            // TEST 1: Abstract PlushieItem — showCategory()
            // -------------------------------------------------------
            System.out.println("========================================");
            System.out.println("TEST 1: ABSTRACT PlushieItem — showCategory()");
            System.out.println("========================================");
            PlushieItem[] products = { teddy, bunny, unicorn, dragon };
            for (PlushieItem item : products) {
                item.displayBasicInfo();
                item.showCategory();
                System.out.println();
            }

            // -------------------------------------------------------
            // TEST 2: Abstract Staff — work()
            // -------------------------------------------------------
            System.out.println("========================================");
            System.out.println("TEST 2: ABSTRACT Staff — work()");
            System.out.println("========================================");
            Staff cashier1 = new Cashier("S001", "Vanna", "011111111");
            Staff manager1 = new Manager("S002", "Malis", "022222222");
            Staff[] allStaff = { cashier1, manager1 };
            for (Staff s : allStaff) {
                s.displayInfo();
                s.work();
                System.out.println();
            }

            // -------------------------------------------------------
            // TEST 3: Abstract classes cannot be instantiated
            // -------------------------------------------------------
            System.out.println("========================================");
            System.out.println("TEST 3: Person and Staff are abstract — only subclass objects exist");
            System.out.println("========================================");
            System.out.println("cashier1 is a Cashier (extends abstract Staff):  OK");
            System.out.println("manager1 is a Manager (extends abstract Staff):  OK");
            System.out.println();

            // -------------------------------------------------------
            // TEST 4: Placing an order
            // -------------------------------------------------------
            System.out.println("========================================");
            System.out.println("TEST 4: Placing an order with Cashier staff");
            System.out.println("========================================");
            shop.addStaff(cashier1);
            shop.addStaff(manager1);

            Customer customer1 = new Customer("C001", "Dara",  "012345678");
            Customer customer2 = new Customer("C002", "Sokha", "098765432");
            shop.addCustomer(customer1);
            shop.addCustomer(customer2);

            Order order1 = new Order("O001", customer1, cashier1, "2026-05-18");
            order1.addItem(new OrderItem(teddy, 2));
            order1.addItem(bunny, 1);
            order1.addItem(unicorn, 1);
            shop.placeOrder(order1);
            order1.displayInfo();

            // -------------------------------------------------------
            // TEST 5: PlushieFilter functional interface
            // -------------------------------------------------------
            System.out.println("========================================");
            System.out.println("TEST 5: PlushieFilter — search by price <= 30");
            System.out.println("========================================");
            ArrayList<Plushie> affordable = shop.searchPlushie(p -> p.getPrice() <= 30.0);
            for (Plushie p : affordable) p.displayInfo();

            System.out.println("\nSearch by name containing 'Bear':");
            ArrayList<Plushie> bears = shop.searchPlushie(p -> p.getName().toLowerCase().contains("bear"));
            for (Plushie p : bears) p.displayInfo();

            // -------------------------------------------------------
            // TEST 6: StaffAction functional interface
            // -------------------------------------------------------
            System.out.println("========================================");
            System.out.println("TEST 6: StaffAction — call work() on all staff");
            System.out.println("========================================");
            shop.applyToAllStaff(s -> s.work());

            System.out.println("\nStaffAction — display all staff info:");
            shop.applyToAllStaff(s -> s.displayInfo());

            // -------------------------------------------------------
            // TEST 7: displayAllPlushies (sorted by price — lambda)
            // -------------------------------------------------------
            System.out.println("========================================");
            System.out.println("TEST 7: displayAllPlushies sorted by price");
            System.out.println("========================================");
            shop.displayAllPlushies();

            // -------------------------------------------------------
            // TEST 8: displayAllOrders (sorted by date — anonymous inner class)
            // -------------------------------------------------------
            System.out.println("========================================");
            System.out.println("TEST 8: displayAllOrders sorted by date");
            System.out.println("========================================");
            shop.displayAllOrders();

            // Final summary
            customer1.displayOrderHistory();
            shop.displayInfo();

            System.out.println("\nStatic counters:");
            System.out.println("Plushie.getPlushieCount():   " + Plushie.getPlushieCount());
            System.out.println("Customer.getCustomerCount(): " + Customer.getCustomerCount());
            System.out.println("Staff.getStaffCount():       " + Staff.getStaffCount());
            System.out.println("Order.getOrderCount():       " + Order.getOrderCount());

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}