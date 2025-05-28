package org.example.testing_api_server.services;

import org.example.testing_api_server.enties.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
public interface UserService {
    Optional<User> getUserById(Integer id);
    void save(User user);

}
