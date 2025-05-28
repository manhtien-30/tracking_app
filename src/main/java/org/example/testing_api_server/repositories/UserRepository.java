package org.example.testing_api_server.repositories;

import org.example.testing_api_server.enties.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserById(Integer id);
}
