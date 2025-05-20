package org.example.testing_api_server.services;

import org.example.testing_api_server.enties.models.User;
import org.example.testing_api_server.repositories.UserRepository;
import org.example.testing_api_server.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements UserServiceImpl {

    private final  UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
}
