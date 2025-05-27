package org.example.testing_api_server.services;

import org.example.testing_api_server.enties.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserService {
    public Optional<User> getUserById(Integer id);
    void save(User user);

}
