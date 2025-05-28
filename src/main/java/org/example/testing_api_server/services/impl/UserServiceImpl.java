package org.example.testing_api_server.services.impl;

import org.example.testing_api_server.enties.models.User;
import org.example.testing_api_server.repositories.UserRepository;
import org.example.testing_api_server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.getUserById(id);
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}