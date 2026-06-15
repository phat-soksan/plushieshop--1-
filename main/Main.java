package main;
import model.*;

public class Main {

    public static void main(String[] args) {

        ShopSystem shop = new ShopSystem("Happy Plushie Shop");

        // -------------------------------------------------------
        // Products: Plushie extends abstract PlushieItem
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
        // Plushie and LimitedEditionPlushie implement it differently
        // -------------------------------------------------------
        System.out.println("========================================");
        System.out.println("TEST 1: ABSTRACT PlushieItem — showCategory()");
        System.out.println("========================================");

        // Use PlushieItem references (polymorphism through abstract class)
        PlushieItem[] products = { teddy, bunny, unicorn, dragon };
        for (PlushieItem item : products) {
            item.displayBasicInfo();   // shared normal method
            item.showCategory();       // abstract method — different per subclass
            System.out.println();
        }

        // -------------------------------------------------------
        // TEST 2: Abstract Staff — work()
        // Cashier and Manager implement it differently
        // -------------------------------------------------------
        System.out.println("========================================");
        System.out.println("TEST 2: ABSTRACT Staff — work()");
        System.out.println("========================================");

        Staff cashier1 = new Cashier("S001", "Vanna", "011111111");
        Staff manager1 = new Manager("S002", "Malis", "022222222");

        // Use Staff references — polymorphism through abstract class
        Staff[] allStaff = { cashier1, manager1 };
        for (Staff s : allStaff) {
            s.displayInfo();   // shared normal method
            s.work();          // abstract method — different per role
            System.out.println();
        }

        // -------------------------------------------------------
        // TEST 3: Cannot create Person or Staff directly (abstract)
        // Uncommenting the lines below will cause a compile error:
        //   Person p = new Person("X001", "Test", "000");
        //   Staff  s = new Staff("X002", "Test", "000");
        // -------------------------------------------------------
        System.out.println("========================================");
        System.out.println("TEST 3: Person and Staff are abstract — only subclass objects exist");
        System.out.println("========================================");
        System.out.println("customer1 is a Customer (extends abstract Person): OK");
        System.out.println("cashier1 is a Cashier  (extends abstract Staff):  OK");
        System.out.println("manager1 is a Manager  (extends abstract Staff):  OK");
        System.out.println();

        // -------------------------------------------------------
        // TEST 4: Placing an order using abstract Staff references
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
        order1.addItem(bunny);
        order1.addItem(unicorn, 1);
        shop.placeOrder(order1);
        order1.displayInfo();

        // -------------------------------------------------------
        // TEST 5: All previous Week 7 features still work
        // -------------------------------------------------------
        System.out.println("========================================");
        System.out.println("TEST 5: Week 7 overloading still works");
        System.out.println("========================================");
        teddy.displayInfo();
        teddy.displayInfo(true);
        teddy.displayInfo(false, true);

        cashier1.processOrder(order1);
        cashier1.processOrder(order1, "Birthday gift — please wrap.");
        manager1.processOrder("O001");

        // Final summary
        customer1.displayOrderHistory();
        shop.displayInfo();

        System.out.println("\nStatic counters:");
        System.out.println("Plushie.getPlushieCount():   " + Plushie.getPlushieCount());
        System.out.println("Customer.getCustomerCount(): " + Customer.getCustomerCount());
        System.out.println("Staff.getStaffCount():       " + Staff.getStaffCount());
        System.out.println("Order.getOrderCount():       " + Order.getOrderCount());
    }
}
