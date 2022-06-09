package org.cinema.service.impl;

import java.util.Optional;
import org.cinema.dao.UserDao;
import org.cinema.lib.Inject;
import org.cinema.lib.Service;
import org.cinema.model.User;
import org.cinema.service.UserService;
import org.cinema.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
