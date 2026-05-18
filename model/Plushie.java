package model;
import interfaces.Displayable;
import interfaces.StockManageable;


public class Plushie implements Displayable, StockManageable {

    private String plushieId;
    private String name;
    private double price;
    private int stock;
    private static int plushieCount = 0;

    public Plushie(String plushieId, String name, double price, int stock) {
        this.plushieId = cleanText(plushieId, "UNKNOWN_PLUSHIE");
        this.name = cleanText(name, "Unknown Plushie");
        setPrice(price);
        setStock(stock);
        plushieCount++;
    }

    private String cleanText(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value.trim();
    }

    public String getPlushieId() { return plushieId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void setPrice(double price) {
        this.price = (price >= 0) ? price : 0;
    }

    public void setStock(int stock) {
        this.stock = (stock >= 0) ? stock : 0;
    }

    @Override
    public boolean hasEnoughStockForAllItems(int quantity) {
        return quantity > 0 && quantity <= stock;
    }

    @Override
    public boolean reduceStock(int quantity) {
        if (hasEnoughStockForAllItems(quantity)) {
            stock -= quantity;
            return true;
        }
        return false;
    }

    @Override
    public void displayInfo() {
        System.out.println(plushieId + " - " + name + " - $" + price + " - Stock: " + stock);
    }

    public static int getPlushieCount() {
        return plushieCount;
    }
}