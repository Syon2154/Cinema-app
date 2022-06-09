package org.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.cinema.dao.OrderDao;
import org.cinema.lib.Inject;
import org.cinema.lib.Service;
import org.cinema.model.Order;
import org.cinema.model.ShoppingCart;
import org.cinema.model.User;
import org.cinema.service.OrderService;
import org.cinema.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setTickets(new ArrayList<>(shoppingCart.getTickets()));
        order.setOrderDate(LocalDateTime.now());
        order.setUser(shoppingCart.getUser());
        shoppingCartService.clearShoppingCart(shoppingCart);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getByUser(user);
    }
}
