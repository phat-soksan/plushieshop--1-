package model;
import interfaces.Displayable;
import interfaces.StockManageable;

// Week 9: PlushieItem is now an abstract class.
// Every plushie product must declare its own category via showCategory().
// displayBasicInfo() is shared across all product types.
public abstract class PlushieItem implements Displayable, StockManageable {

    protected String plushieId;
    protected String name;
    protected double price;
    protected int stock;

    public PlushieItem(String plushieId, String name, double price, int stock) {
        this.plushieId = cleanText(plushieId, "UNKNOWN_PLUSHIE");
        this.name      = cleanText(name,      "Unknown Plushie");
        setPrice(price);
        setStock(stock);
    }

    private String cleanText(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) return defaultValue;
        return value.trim();
    }

    public String getPlushieId() { return plushieId; }
    public String getName()      { return name; }
    public double getPrice()     { return price; }
    public int    getStock()     { return stock; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) this.name = name.trim();
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative: " + price);
        this.price = price;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative: " + stock);
        this.stock = stock;
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

    // Normal shared method — basic product info is the same for all types
    public void displayBasicInfo() {
        System.out.println(plushieId + " - " + name + " - $" + price + " - Stock: " + stock);
    }

    // Abstract method — each product type must declare its own category
    public abstract void showCategory();

    @Override
    public void displayInfo() {
        displayBasicInfo();
        showCategory();
    }
}