package model;

// Week 9: Manager extends abstract class Staff.
// It must implement both work() and getRole().
public class Manager extends Staff {

    public Manager(String staffId, String name, String phone) {
        super(staffId, name, phone);
    }

    @Override
    public String getRole() {
        return "Manager";
    }

    @Override
    public void work() {
        System.out.println(name + " is overseeing shop operations and managing the team.");
    }
}
