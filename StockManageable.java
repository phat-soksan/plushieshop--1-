package interfaces;

public interface StockManageable {
    boolean hasEnoughStockForAllItems(int quantity);
    boolean reduceStock(int quantity);
}
