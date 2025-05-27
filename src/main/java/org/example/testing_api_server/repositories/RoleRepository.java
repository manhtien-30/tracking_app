package org.example.testing_api_server.repositories;

import org.example.testing_api_server.enties.models.ERole;
import org.example.testing_api_server.enties.models.Roles;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {
    Roles findbyName(ERole name);
}
