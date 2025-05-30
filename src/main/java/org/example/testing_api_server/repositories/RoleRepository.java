package org.example.testing_api_server.repositories;

import org.example.testing_api_server.enties.models.Enum.ERole;
import org.example.testing_api_server.enties.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Roles findByName(ERole name);
}
