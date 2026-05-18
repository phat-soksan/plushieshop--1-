package model;
import interfaces.Displayable;

public class Person implements Displayable {

    protected String id;
    protected String name;
    protected String phone;

    public Person(String id, String name, String phone) {
        this.id = cleanText(id, "UNKNOWN_ID");
        this.name = cleanText(name, "Unknown Name");
        this.phone = cleanText(phone, "No Phone");
    }

    private String cleanText(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value.trim();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone.trim();
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
    }
}