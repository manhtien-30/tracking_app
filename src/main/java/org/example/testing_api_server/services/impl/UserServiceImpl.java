package org.example.testing_api_server.services.impl;

import org.example.testing_api_server.enties.models.User;

import java.util.Optional;

public interface UserServiceImpl {
    Optional<User> getUserById(Integer id);
}
