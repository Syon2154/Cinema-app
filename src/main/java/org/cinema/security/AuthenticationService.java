package org.cinema.security;

import org.cinema.exception.AuthenticationException;
import org.cinema.exception.RegistrationException;
import org.cinema.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password) throws RegistrationException;
}
