package org.example.testing_api_server.repositories;

import org.example.testing_api_server.enties.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
