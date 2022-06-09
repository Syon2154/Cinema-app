package org.cinema.service;

import java.util.List;
import org.cinema.model.Order;
import org.cinema.model.ShoppingCart;
import org.cinema.model.User;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
