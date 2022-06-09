package org.cinema.dao;

import java.util.Optional;
import org.cinema.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
