package org.example.testing_api_server.utils.factories;

import jakarta.transaction.Transactional;
import org.example.testing_api_server.enties.models.ERole;
import org.example.testing_api_server.enties.models.Roles;
import org.example.testing_api_server.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleDataSeeder {
    @Autowired
    private RoleRepository roleRepository;

    @EventListener
    @Transactional
    public void LoadRoles(ContextRefreshedEvent event) {

        List<ERole> roles = Arrays.stream(ERole.values()).toList();

        for(ERole erole: roles) {
            if (roleRepository.findByName(erole)==null) {
                roleRepository.save(new Roles(erole));
            }
        }

    }
}