package model;

// Week 9: Plushie now extends the abstract class PlushieItem.
// It implements showCategory() to identify itself as a Standard Plushie.
// All overloaded displayInfo() variants are kept for backward compatibility.
public class Plushie extends PlushieItem {

    private static int plushieCount = 0;

    public Plushie(String plushieId, String name, double price, int stock) {
        super(plushieId, name, price, stock);
        plushieCount++;
    }

    @Override
    public void showCategory() {
        System.out.println(name + " is a Standard Plushie.");
    }

    // Overloaded displayInfo variants kept from Week 7
    public void displayInfo(boolean showStock) {
        String info = plushieId + " - " + name + " - $" + price;
        if (showStock) info += " - Stock: " + stock;
        System.out.println(info);
    }

    public void displayInfo(boolean showStock, boolean showPrice) {
        String info = plushieId + " - " + name;
        if (showPrice) info += " - $" + price;
        if (showStock) info += " - Stock: " + stock;
        System.out.println(info);
    }

    public static int getPlushieCount() { return plushieCount; }
}
