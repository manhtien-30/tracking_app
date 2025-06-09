package org.example.testing_api_server.utils.factories;

import org.example.testing_api_server.enties.models.Enum.ERole;
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
                return roleRepository.findByName(ERole.USER);
            }
            case "admin" -> {
                return roleRepository.findByName(ERole.ADMIN);
            }
            case "moderator" -> {
                return roleRepository.findByName(ERole.MODERATOR);
            }
            default -> throw new IllegalArgumentException("Invalid role");
        }
    }
}
