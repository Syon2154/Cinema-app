package org.cinema.dao;

import java.util.List;
import org.cinema.model.Order;
import org.cinema.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getByUser(User user);
}
