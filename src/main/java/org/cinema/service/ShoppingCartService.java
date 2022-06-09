package org.cinema.service;

import org.cinema.model.MovieSession;
import org.cinema.model.ShoppingCart;
import org.cinema.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clearShoppingCart(ShoppingCart cart);
}
