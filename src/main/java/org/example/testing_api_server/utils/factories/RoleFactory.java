package org.example.testing_api_server.utils.factories;

import org.example.testing_api_server.enties.models.ERole;
import org.example.testing_api_server.enties.models.Roles;
import org.example.testing_api_server.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
    @Autowired
    private RoleRepository roleRepository;
    public Roles getInstance(String role){
        switch (role){
            case "user" -> {
                return roleRepository.findbyName(ERole.USER);
            }
            case "admin" -> {
                return roleRepository.findbyName(ERole.ADMIN);
            }
            default -> throw new IllegalArgumentException("Invalid role");
        }
    }
}
