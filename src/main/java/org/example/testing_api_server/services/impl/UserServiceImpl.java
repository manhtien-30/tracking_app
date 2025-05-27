package org.example.testing_api_server.services.impl;

import org.example.testing_api_server.enties.models.User;
import org.example.testing_api_server.services.AccountService;
import org.example.testing_api_server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserService userService;
    @Override
    public Optional<User> getUserById(Integer id) {
        return userService.getUserById(id);
    }

    @Override
    public void save(User user) {
        userService.save(user);
    }
}