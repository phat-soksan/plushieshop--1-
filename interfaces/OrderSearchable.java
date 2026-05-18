package interfaces;

import model.Order;

public interface OrderSearchable {
    Order findOrderById(String orderId);
}