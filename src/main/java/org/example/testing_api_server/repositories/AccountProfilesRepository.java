package org.example.testing_api_server.repositories;

import org.example.testing_api_server.enties.models.AccountProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProfilesRepository extends JpaRepository<AccountProfiles, Integer> {

}
