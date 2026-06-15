package model;

// Week 9: LimitedEditionPlushie is another subclass of abstract PlushieItem.
// It implements showCategory() differently from Plushie.
public class LimitedEditionPlushie extends PlushieItem {

    private String edition;

    public LimitedEditionPlushie(String plushieId, String name, double price, int stock, String edition) {
        super(plushieId, name, price, stock);
        this.edition = (edition != null && !edition.trim().isEmpty()) ? edition.trim() : "Special Edition";
    }

    public String getEdition() { return edition; }

    @Override
    public void showCategory() {
        System.out.println(name + " is a Limited Edition Plushie (" + edition + ").");
    }
}
