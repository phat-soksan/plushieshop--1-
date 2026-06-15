package model;

// Week 9: Cashier extends abstract class Staff.
// It must implement both work() and getRole().
public class Cashier extends Staff {

    public Cashier(String staffId, String name, String phone) {
        super(staffId, name, phone);
    }

    @Override
    public String getRole() {
        return "Cashier";
    }

    @Override
    public void work() {
        System.out.println(name + " is processing customer orders and handling payments.");
    }
}
