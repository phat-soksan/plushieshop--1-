package model;
import interfaces.Calculatable;
import interfaces.Displayable;

public class OrderItem implements Displayable, Calculatable {

    private Plushie plushie;
    private int quantity;

    public OrderItem(Plushie plushie, int quantity) {
        this.plushie = plushie;
        setQuantity(quantity);
    }

    public Plushie getPlushie() { return plushie; }
    public int getQuantity()    { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = (quantity > 0) ? quantity : 1;
    }

    @Override
    public double calculate() {
        if (plushie == null) return 0;
        return plushie.getPrice() * quantity;
    }

    public boolean reduceStock() {
        return plushie != null && plushie.reduceStock(quantity);
    }

    @Override
    public void displayInfo() {
        if (plushie == null) {
            System.out.println("Invalid order item: no plushie selected.");
            return;
        }
        System.out.println(plushie.getName() + " x " + quantity + " = $" + calculate());
    }
}
