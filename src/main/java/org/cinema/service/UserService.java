package org.cinema.service;

import java.util.Optional;
import org.cinema.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
